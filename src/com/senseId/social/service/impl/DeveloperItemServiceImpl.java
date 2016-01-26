package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.senseId.social.dao.ArticleDao;
import com.senseId.social.dao.DeveloperItemCategoryDao;
import com.senseId.social.dao.DeveloperItemDao;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.DeveloperItem;
import com.senseId.social.entity.DeveloperItemCategory;
import com.senseId.social.service.DeveloperItemService;

@Service("developerItemService")
public class DeveloperItemServiceImpl implements DeveloperItemService {
	
	@Resource(name="developerItemDao")
	private DeveloperItemDao devDao;
	@Resource(name="developerItemCategoryDao")
	private DeveloperItemCategoryDao devCatDao;
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	
	@Override
	public DeveloperItem get(Integer id) throws Exception {
		DeveloperItem item = devDao.findById(id);
		Article article = articleDao.findById(item.getParentId());
		BeanUtils.copyProperties(item, article);
		return item;
	}
	
	@Override
	public List<DeveloperItem> about(int pageNumber, int size, String[] pNameLikes,
			Object[] pValueLikes, String[] pNameEquals, Object[] pValueEquals,
			String rangedPropertyName, Object[] ranges,
			String orderedPropertyName, boolean sequence) throws Exception {
				return devDao.find(pageNumber, size, pNameLikes, pValueLikes, pNameEquals, pValueEquals, rangedPropertyName, ranges, orderedPropertyName, sequence);
	}

	@Override
	public boolean delete(Integer id){
		DeveloperItem findById = null;
		try {
			findById = devDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findById==null)
			return false;
		try {
			devDao.delete(findById);
			Article article=articleDao.findById(findById.getParentId());
			articleDao.delete(article);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(DeveloperItem item){
		Article article =new Article(item.getParentId(),
				item.getType(), item.getClickCount(),
				item.getShareCount(), item.getCreateTime(),
				item.getCreator(), item.getTitle());
		article.setShown(item.getShown());
		article.setIndexImg(item.getIndexImg());
		article.setBrief(item.getBrief());
		article.setTop(item.getTop());
		try {
			articleDao.update(article);
			devDao.update(item);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		};
		return true;
	}

	@Override
	public Integer add(DeveloperItem item) throws Exception {
		Integer categoryId = item.getCategoryId();
		if (categoryId == null)
			return null;
		DeveloperItemCategory category = devCatDao.findById(categoryId);
		if (category == null)
			throw new Exception("不存在的开发者类别");
		item.setCategory(category);
		Article article =new Article(Article.TYPE_CASE, 0, 0,
				item.getCreateTime(), item.getCreator(), item.getTitle(),
				item.getShown(), item.getBrief());
		article.setIndexImg(item.getIndexImg());
		article.setTop(new Long(0));
		Long articleId = (Long) articleDao.save(article);
		item.setParentId(articleId);
		return (Integer) devDao.save(item);
	}

	@Override
	public List<DeveloperItem> search(int pageNumber, Integer pageSize,
			Integer categoryId) throws Exception {
		if(categoryId!=null && categoryId>0)
			return devDao.find(pageNumber, pageSize, null, null, new String[]{"categoryId"}, new Object[]{categoryId},
					null, null, null, false);
		else
			return devDao.find(pageNumber, pageSize, null, null, null, null, null, null, null, false);
	}

	@Override
	public Integer getTotalCount() throws Exception {
		return devDao.countAll().intValue();
	}
	
	@Override
	public List<DeveloperItem> search_(int pageNumber, Integer pageSize) throws Exception {
		List<DeveloperItem> find=devDao.find(pageNumber, pageSize, null, null,
				null,null,
				null, null, null, false);
		
		if(find!=null){
			for(DeveloperItem item:find){
				Article article =articleDao.findById(item.getParentId());
				BeanUtils.copyProperties(item, article);
			}
		}
		return find;
	}

	@Override
	public List<DeveloperItem> getAll() throws Exception{
		List<DeveloperItem> find = devDao.findAll();
		for(DeveloperItem item:find){
			Article article =articleDao.findById(item.getParentId());
			BeanUtils.copyProperties(item, article);
		}
		return find;
	}
	
}
