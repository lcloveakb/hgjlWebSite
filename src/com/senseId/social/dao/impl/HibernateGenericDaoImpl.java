package com.senseId.social.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.senseId.social.dao.GenericDAO;

public abstract class HibernateGenericDaoImpl<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	private Class<T> persistentClass;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public HibernateGenericDaoImpl() {
		this.setPersistentClass((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
//		System.out.println(getPersistentClass());
	}

	@Override
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getSession().createQuery("from "+getPersistentClass().getName()).setCacheable(true).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T findById(ID id) throws Exception {
		return (T) getSession().load(getPersistentClass(), id);
	}

	@Override
	public Serializable save(T entity) throws Exception{
		return getSession().save(entity);
	}

	@Override
	public void delete(T entity) throws Exception {
		this.getSession().delete(entity);
	}

	@Override
	public boolean deleteByProperty(String propertyName, Object value) throws Exception {
		String queryString = "delete from " + getPersistentClass().getName()
				+ " as model where model." + propertyName + "= :"+propertyName;
		Query query = this.getSession().createQuery(queryString);
		query.setParameter(propertyName, value);
		int affect = query.executeUpdate();
		return affect>0;
	}
	
	@Override
	public boolean delete(String[] pNameLikes, Object[] pValueLikes,
			String[] pNameEquals, Object[] pValueEquals,
			String rangedPropertyName, Object[] ranges) {
		// 标识是否含有where关键
		boolean flag = false;
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("delete from " + getPersistentClass().getName());
		strBuffer.append(" as model");
		int pl = pNameLikes == null ? 0 : pNameLikes.length;
		int vl = pValueLikes == null ? 0 : pValueLikes.length;
		if (pl > 0 && pl == vl) {
			strBuffer.append(" where ");
			flag = true;
			for (int i = 0; i < pl; i++) {
				if (i != 0)
					strBuffer.append(" and");
				strBuffer.append(" model.");
				strBuffer.append(pNameLikes[i]);
				strBuffer.append(" like :"+pNameLikes[i]);
			}
		}

		int pl2 = pNameEquals == null ? 0 : pNameEquals.length;
		int vl2 = pValueEquals == null ? 0 : pValueEquals.length;

		if (pl2 > 0 && pl2 == vl2) {
			if (!flag) {
				strBuffer.append(" where ");
				flag = true;
			} else {
				strBuffer.append(" and ");
			}
			for (int i = 0; i < pl2; i++) {
				if (i != 0)
					strBuffer.append(" and");
				strBuffer.append(" model.");
				strBuffer.append(pNameEquals[i]);
				strBuffer.append("= :"+pNameEquals[i]);
			}
		}
		if (rangedPropertyName != null) {
			if (!flag) {
				strBuffer.append(" where ");
				flag = true;
			} else {
				strBuffer.append(" and ");
			}
			strBuffer.append(" model."+rangedPropertyName+" between :lower and :upper");
		}

		String queryString = strBuffer.toString();
		Query query = this.getSession().createQuery(queryString);

		if (pl > 0 && pl == vl) {
			for (int i = 0; i < pl; i++) {
				query.setParameter(pNameLikes[i], "%" + pValueLikes[i] + "%");
			}
		}
		if (pl2 > 0 && pl2 == vl2) {
			for (int i = 0; i < pl2; i++) {
				query.setParameter(pNameEquals[i], pValueEquals[i]);
			}
		}
		if (ranges != null && ranges.length == 2) {
			query.setParameter("lower", ranges[0]);
			query.setParameter("upper", ranges[1]);
		}
		int affect = query.executeUpdate();
		return affect>0;
	}

	@Override
	public void saveOrUpdate(T entity) throws Exception {
		this.getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) throws Exception {
		this.getSession().update(entity);
	}

	@Override
	public Long countAll() throws Exception {
		String queryString = "select count(*) from "
				+ getPersistentClass().getName();
		Query query = this.getSession().createQuery(queryString);
		query.setCacheable(true);
		return (Long) query.uniqueResult();
	}
	
	@Override
	public Long count(T entity) throws Exception {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.setProjection(Projections.rowCount());
		criteria.add(Example.create(entity).excludeZeroes());
		criteria.setCacheable(true);
		return (Long)criteria.uniqueResult();
	};

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T entity, int page, int size, String orderBy, boolean sequence) throws Exception {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Example.create(entity).excludeZeroes());
		if(orderBy!=null) {
			if(sequence){
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}		
		if (page > 0) {	//如果是负数，则查询所有
			criteria.setFirstResult((page - 1) * size);
			criteria.setMaxResults(size);
		}
		criteria.setCacheable(true);
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String propertyName, Object[] valueSet) throws Exception {
		if (propertyName == null || valueSet == null || valueSet.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer("from "
				+ getPersistentClass().getName() + " as model where model."
				+ propertyName + " in (");
		int i = 1;
		int size = valueSet.length;
		for (Object v : valueSet) {
			if (i == size) {
				sb.append("'" + v + "')");
			} else {
				sb.append("'" + v + "',");
			}
			i++;
		}
		return this.getSession().createQuery(sb.toString()).setCacheable(true)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(int pageNumber, int size, String[] pNameLikes,
			Object[] pValueLikes, String[] pNameEquals, Object[] pValueEquals,
			String rangedPropertyName, Object[] ranges,
			String orderedPropertyName, boolean sequence) throws Exception {
		
		// 标识是否含有where关键
		boolean flag = false;
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from " + getPersistentClass().getName());
		strBuffer.append(" as model");
		int pl = pNameLikes == null ? 0 : pNameLikes.length;
		int vl = pValueLikes == null ? 0 : pValueLikes.length;
		if (pl > 0 && pl == vl) {
			strBuffer.append(" where ");
			flag = true;
			for (int i = 0; i < pl; i++) {
				if (i != 0)
					strBuffer.append(" and");
				strBuffer.append(" model.");
				strBuffer.append(pNameLikes[i]);
				strBuffer.append(" like :"+pNameLikes[i]);
			}
		}

		int pl2 = pNameEquals == null ? 0 : pNameEquals.length;
		int vl2 = pValueEquals == null ? 0 : pValueEquals.length;

		if (pl2 > 0 && pl2 == vl2) {
			if (!flag) {
				strBuffer.append(" where ");
				flag = true;
			} else {
				strBuffer.append(" and ");
			}
			for (int i = 0; i < pl2; i++) {
				if (i != 0)
					strBuffer.append(" and");
				strBuffer.append(" model.");
				strBuffer.append(pNameEquals[i]);
				strBuffer.append("= :"+pNameEquals[i]);
			}
		}
		if (rangedPropertyName != null) {
			if (!flag) {
				strBuffer.append(" where ");
				flag = true;
			} else {
				strBuffer.append(" and ");
			}
			strBuffer.append(" model."+rangedPropertyName+" between :lower and :upper");
		}

		if (orderedPropertyName != null && orderedPropertyName.trim().length()>0) {
			strBuffer.append(" order by model." + orderedPropertyName);
			if (!sequence) {
				strBuffer.append(" DESC");
			}
		}
		String queryString = strBuffer.toString();
		Query query = this.getSession().createQuery(queryString);

		if (pl > 0 && pl == vl) {
			for (int i = 0; i < pl; i++) {
				query.setParameter(pNameLikes[i], "%" + pValueLikes[i] + "%");
			}
		}
		if (pl2 > 0 && pl2 == vl2) {
			for (int i = 0; i < pl2; i++) {
				query.setParameter(pNameEquals[i], pValueEquals[i]);
			}
		}
		if (ranges != null && ranges.length == 2) {
			query.setParameter("lower", ranges[0]);
			query.setParameter("upper", ranges[1]);
		}
		if (pageNumber > 0) {
			query.setFirstResult((pageNumber - 1) * size);
			query.setMaxResults(size);
		}
		query.setCacheable(true);
		return query.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Long count(String[] pLikes, Object[] vLikes, String[] pEquals,
			Object[] vEquals, String rangedPropertyName, Object[] ranges) throws Exception {
		// 标识是否含有where关键字
		boolean flag = false;
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select count(*) from " + getPersistentClass().getName());
		strBuffer.append(" as model");
		int pl = pLikes == null ? 0 : pLikes.length;
		int vl = vLikes == null ? 0 : vLikes.length;
		if (pl > 0 && pl == vl) {
			strBuffer.append(" where");
			flag = true;
			for (int i = 0; i < pl; i++) {
				if (i != 0)
					strBuffer.append(" and");
				strBuffer.append(" model.");
				strBuffer.append(pLikes[i]);
				strBuffer.append(" like :"+pLikes[i]);
			}
		}

		int pl2 = pEquals == null ? 0 : pEquals.length;
		int vl2 = vEquals == null ? 0 : vEquals.length;

		if (pl2 > 0 && pl2 == vl2) {
			if (!flag) {
				strBuffer.append(" where ");
				flag = true;
			} else {
				strBuffer.append(" and ");
			}
			for (int i = 0; i < pl2; i++) {
				if (i != 0)
					strBuffer.append(" and");
				strBuffer.append(" model.");
				strBuffer.append(pEquals[i]);
				strBuffer.append("= :"+pEquals[i]);
			}
		}
		if (rangedPropertyName != null) {
			if (!flag) {
				strBuffer.append(" where ");
			} else {
				strBuffer.append(" and ");
			}
			strBuffer.append(" model."+rangedPropertyName+" between :lower and :upper");
		}
		String queryString = strBuffer.toString();
		Query query = this.getSession().createQuery(queryString);

		if (pl > 0 && pl == vl) {
			for (int i = 0; i < pl; i++) {
				query.setParameter(pLikes[i], "%" + vLikes[i] + "%");
			}
		}
		if (pl2 > 0 && pl2 == vl2) {
			for (int i = 0; i < pl2; i++) {
				query.setParameter(pEquals[i], vEquals[i]);
			}
		}
		if (ranges != null && ranges.length == 2) {
			query.setParameter("lower", ranges[0]);
			query.setParameter("upper", ranges[1]);
		}
		query.setCacheable(true);
		List list = query.list();
		return (Long) list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ID> insertBatch(List<T> entities) throws Exception {
		Session session = getSession();
		List<ID> pks = new ArrayList<ID>();
		for (int i = 0, size = entities.size(); i < size; i++) {
			pks.add((ID)save(entities.get(i)));
			if (i % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
		return pks.size()==0?null:pks;
	}
	
	@Override
	public void updateBatch(List<T> entities) throws Exception {
		Session session = getSession();
		for (int i = 0, size = entities.size(); i < size; i++) {
			update(entities.get(i));
			if (i % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
	}
	
	@Override
	public boolean update(String[] properties, Object[] values, String idName, Object id) throws Exception {
		boolean flag = false;
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("update " + getPersistentClass().getName());
		strBuffer.append(" as model");

		int pCount = properties == null ? 0 : properties.length;
		int vCount = values == null ? 0 : values.length;
		
		if (pCount > 0 && pCount == vCount) {
			if (!flag) {
				strBuffer.append(" set ");
				flag = true;
			}
			for (int i = 0; i < pCount; i++) {
				if (i != 0)
					strBuffer.append(" ,");
				strBuffer.append(" model.");
				strBuffer.append(properties[i]);
				strBuffer.append("= :"+properties[i]);
			}
		}
		strBuffer.append(" where model."+idName+" = :"+idName);
		String queryString = strBuffer.toString();
		Query query = this.getSession().createQuery(queryString);

		if (pCount > 0 && pCount == vCount) {
			for (int i = 0; i < pCount; i++) {
				query.setParameter(properties[i], values[i]);
			}
		}
		query.setParameter(idName, id);
//		query.setCacheable(true);
		int affect = query.executeUpdate();
		return affect>0;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Override
	public Object executeHql(String hql) {
		return getSession().createQuery(hql).uniqueResult();
	}

}
