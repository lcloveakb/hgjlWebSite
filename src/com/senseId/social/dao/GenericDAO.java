package com.senseId.social.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 徐春阳
 *
 * @param <T>	Javabean类型
 * @param <ID>	ID类型
 */
public interface GenericDAO<T,ID extends Serializable>
{
	
	/**
	 * 获得持久化对象的类型
	 * @return
	 */
	public Class<T> getPersistentClass();
	
	/**
	 * 更新一个对象，该对象的Id属性必须存在数据库中
	 * @param entity	被更新的对象
	 * @return 
	 */
	public void update(T entity) throws Exception;

	/**
	 * 持久化一个对象
	 * @param entity	被保存的对象
	 * @return
	 * @throws Exception 
	 */
	public Serializable save(T entity) throws Exception;
	
	/**
	 * 删除一个对象
	 * @param entity	被删除的对象
	 */
	public void delete(T entity) throws Exception;

	public boolean delete(String[] pNameLikes,Object[] pValueLikes,String [] pNameEquals,Object[] pValueEquals,String rangedPropertyName,Object[] ranges);
	
	/**
	 * 与findByProperty相似，当properyName == value 时把相应的记录删除
	 */
	public boolean deleteByProperty(String propertyName, Object value) throws Exception;
	
	public List<T> findAll();
	
	/**
	 * 根据Id查找一个对象
	 * @param id	该对象在数据库中对应记录的Id
	 * @return	返回该对象的实例
	 */
	public T findById(ID id) throws Exception;
	
	/**
	 * 根据示例分页查询
	 * @param example	作为示例的对象
	 * @param page		
	 * @param size
	 * @return
	 */
	public List<T> findByExample(T example,int page,int size, String orderBy, boolean sequence) throws Exception;
	
	/**
	 * 依据某个字段的属性值属于某个集合查询
	 * @param propertyName	被限定的字段名称
	 * @param valueSet	属性值的集合
	 * @return
	 */
	public List<T> find(String propertyName, Object[] values) throws Exception;
	
	/**
	 * 根据属性值，属性范围查找，并根据指定属性名称排序
	 * @param pageNumber	分页码，如果是一个负值则表示查询所有
	 * @param size			分页大小
	 * @param pNameLikes	模糊查询属性名称数组，必须和模糊查询属性值数组按照索引一一对应
	 * @param pValueLikes	模糊查询属性值数组，必须和模糊查询属性名称数组按照索引一一对应
	 * @param pNameEquals	准确查询属性名称数组，必须和准确查询属性值数组按照索引一一对应
	 * @param pValueEquals	准确查询属性值数组，必须和准确查询属性名称数组按照索引一一对应
	 * @param rangedPropertyName	被限定范围的属性名称
	 * @param ranges				被限定的属性值范围
	 * @param orderedPropertyName	按照此参数对应的属性名排序，多属性之间用“,”隔开
	 * @param sequence				是否升序排列，true升序，false降序
	 * @return		返回指定类型的对象集合
	 */
	public List<T> find(int pageNumber,int size,String[] pNameLikes,Object[] pValueLikes,String [] pNameEquals,Object[] pValueEquals,String rangedPropertyName,Object[] ranges,String orderedPropertyName,boolean sequence) throws Exception;
	
	/**
	 * 统计所有记录的总数
	 * @return 总记录数
	 */
	public Long countAll() throws Exception;
	
	public Long count(T entity) throws Exception;
	
	/**
	 * 根据属性统计
	 * @param pNameLikes	模糊查询属性名称集合
	 * @param pValueLikes	模糊查询属性值集合
	 * @param pNameEquals   准确查询属性名称集合
	 * @param pValueEquals   准确查询属性值集合
	 * @param rangedPropertyName	被限定范围的属性名称
	 * @param ranges	被限定范围的属性值
	 * @return	返回查询的记录数量
	 */
	public Long count(String[] pNameLikes,Object[] pValueLikes, String[] pNameEquals, Object[] pValueEquals, String rangedPropertyName,Object[] ranges) throws Exception;
	
	/**
	 * 保存或更新一个对象
	 * @param entity	被保存或更新的对象
	 */
	public void saveOrUpdate(T entity) throws Exception;
	
	/**
	 * 批量插入
	 * @param entities 插入的实体集合
	 */
	public List<ID> insertBatch(List<T> entities) throws Exception;
	
	/**
	 * 批量修改
	 * @param entities 修改的实体集合
	 * @throws Exception
	 */
	public void updateBatch(List<T> entities) throws Exception;
	
	/**
	 * 修改指定属性值
	 * @param properties	属性名称集合
	 * @param values		属性值集合，必须与名称集合一一对应
	 * @param idName		主键名称
	 * @param id			主键值
	 * @throws Exception
	 */
	public boolean update(String[] properties, Object[] values, String idName, Object id) throws Exception;
	
	/**
	 * 执行一条sql語句
	 * 
	 * @return 受影响行数
	 */
	public Object executeHql(String hql);
	
}
