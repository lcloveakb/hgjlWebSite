package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.senseId.social.dao.ArticleDao;
import com.senseId.social.dao.NewDao;
import com.senseId.social.dao.ProductDao;
import com.senseId.social.entity.Article;
import com.senseId.social.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Resource(name="articleDao")
	private ArticleDao dao;
	@Resource(name="productDao")
	private ProductDao pDao;
	@Resource(name="newDao")
	private NewDao nDao;
	
	
	@Override
	public Long add(Article article) throws Exception {
		return (Long) dao.save(article);
	}

	@Override
	public boolean delete(Long articleId) {
		Article findById = null;
		try {
			findById = dao.findById(articleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findById==null)
			return false;
		try {
			dao.delete(findById);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Article article) {
		try {
			dao.update(article);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Article get(Long articleId) throws Exception {
		return dao.findById(articleId);
	}
	
	@Override
	public List<Article> searchForAuto(String keyword) throws Exception {
		return dao.find(1, 5, new String[]{"title"}, new String[]{keyword}, null, null, null, null, "clickCount", false);
	}

	@Override
	public List<Article> searchByLucence(String keyword) throws Exception {
		return dao.find(1, 10, new String[]{"title"}, new String[]{keyword}, null, null, null, null, "clickCount", false);
	}

	@Override
	public Long getTotalCount() throws Exception {
		return dao.countAll();
	}
	
	@Override
	public List<Article> search(int pageNumber, int size, String[] pNameLikes,
			Object[] pValueLikes, String[] pNameEquals, Object[] pValueEquals,
			String rangedPropertyName, Object[] ranges,
			String orderedPropertyName, boolean sequence) throws Exception {
		return dao.find(pageNumber, size, pNameLikes, pValueLikes, pNameEquals, pValueEquals, rangedPropertyName, ranges, orderedPropertyName, sequence);
	}

	@Override
	public List<Article> getList() {
		return dao.findAll();
	}

	@Override
	public Long max() {
		return (Long) dao.executeHql("select MAX(a.top) from Article a");
	}

	
}
