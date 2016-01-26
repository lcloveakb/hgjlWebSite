package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.senseId.social.dao.ArticleDao;
import com.senseId.social.dao.NewDao;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.New;
import com.senseId.social.service.NewService;

@Service("newService")
public class NewServiceImpl implements NewService {

	@Resource(name="newDao")
	private NewDao newDao;

	@Resource(name="articleDao")
	private ArticleDao articleDao;
	
	@Override
	public New get(String id, boolean cascade) throws Exception {
		New news = newDao.findById(id);
		if(cascade) {
			Article article = articleDao.findById(news.getParentId());
			BeanUtils.copyProperties(news, article);
		}
		return news;
	}

	@Override
	public String add(New news) throws Exception {

		Article article = new Article(Article.TYPE_NEWS, 0, 0,
				news.getCreateTime(), news.getCreator(), news.getTitle(),
				news.getShown(), news.getBrief());
		article.setIndexImg(news.getIndexImg());
		article.setTop(new Long(0));
		Long articleId = (Long) articleDao.save(article);
		news.setParentId(articleId);
		return (String) newDao.save(news);
	}

	@Override
	public boolean delete(String id) {
		New findById = null;
		try {
			findById = newDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findById==null)
			return false;
		try {
			newDao.delete(findById);
			Article article = articleDao.findById(findById.getParentId());
			articleDao.delete(article);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(New news) {
		Article article = new Article(news.getParentId(),
				news.getType(), news.getClickCount(),
				news.getShareCount(), news.getCreateTime(),
				news.getCreator(), news.getTitle());
		article.setShown(news.getShown());
		article.setIndexImg(news.getIndexImg());
		article.setBrief(news.getBrief());
		article.setTop(news.getTop());
		try {
			articleDao.update(article);
			newDao.update(news);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		};
		return true;
	}

	@Override
	public List<New> searchByCategory(Integer category, int pageNumber,
			int pageSize, boolean cascade) throws Exception {
		List<New> find = null;
		if(category==null) {
			find = newDao.find(pageNumber, pageSize, null, null, null, null, null, null, null, false);
		} else {
			find = newDao.find(pageNumber, pageSize, null, null, new String[]{"category"}, new Object[]{category}, null, null, null, false);
		}
		if(cascade && find!=null) {
			for(New news: find) {
				Article article = articleDao.findById(news.getParentId());
				BeanUtils.copyProperties(news, article);
			}
		}
		return find;
	}

	@Override
	public Long getTotalCount() throws Exception {
		return newDao.countAll();
	}

	@Override
	public List<New> getAll(int pageNumber, int pageSize, boolean cascade) throws Exception {
		List<New> find = newDao.findAll();
		if(cascade && find!=null) {
			for(New news: find) {
				Article article = articleDao.findById(news.getParentId());
				BeanUtils.copyProperties(news, article);
			}
		}
		return find;
	}
	
	@Override
	public New searchByProperties(String[] keys, Object[] values)
			throws Exception {
		List<New> news = newDao.find(1, 1, null, null, keys, values, null, null, null, false);
		if(news!=null)
			return news.get(0);
		return null;
	}

	@Override
	public Long countByCategory(Integer category) throws Exception {
		if(category!=null) {
			return newDao.count(null, null, new String[]{"category"}, new Object[]{category}, null, null);
		}
		return newDao.countAll();
	}
}
