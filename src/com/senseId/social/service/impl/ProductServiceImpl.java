package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.senseId.social.dao.ArticleDao;
import com.senseId.social.dao.ProductDao;
import com.senseId.social.entity.Article;
import com.senseId.social.entity.Product;
import com.senseId.social.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource(name="productDao")
	private ProductDao productDao;
	
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	
	
	
	@Override
	public Product get(String id, boolean cascade) throws Exception {
		Product product = productDao.findById(id);
		if(cascade) {
			Article article = articleDao.findById(product.getParentId());
			BeanUtils.copyProperties(product, article);
		}
		return product;
	}



	@Override
	public String add(Product product) throws Exception{
		
		Article article = new Article(Article.TYPE_PRODUCT, 0, 0, product.getCreateTime(), product.getCreator(), product.getTitle(),product.getShown(),product.getBrief());
		article.setIndexImg(product.getIndexImg());
		article.setTop(new Long(0));
		Long articleId = (Long) articleDao.save(article);
		product.setParentId(articleId);
		return (String) productDao.save(product);
	}



	@Override
	public boolean update(Product product) {
		Article article = new Article(product.getParentId(),
				product.getType(), product.getClickCount(),
				product.getShareCount(), product.getCreateTime(),
				product.getCreator(), product.getTitle());
		article.setBrief(product.getBrief());
		article.setShown(product.getShown());
		article.setIndexImg(product.getIndexImg());
		article.setBrief(product.getBrief());
		article.setTop(product.getTop());
		try {
			articleDao.update(article);
			productDao.update(product);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}



	@Override
	public boolean delete(String id) {
		Product findById = null;
		try {
			findById = productDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findById==null)
			return false;
		try {
			productDao.delete(findById);
			Article article = articleDao.findById(findById.getParentId());
			articleDao.delete(article);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}



	@Override
	public List<Product> searchByCategory(Integer category, int pageNumber,
			int pageSize, boolean cascade) throws Exception {
		List<Product> find = null;
		if(category==null) {
			find = productDao.find(pageNumber, pageSize, null, null, null, null, null, null, null, false);
		} else {
			find = productDao.find(pageNumber, pageSize, null, null, new String[]{"category"}, new Object[]{category}, null, null, null, false);
		}
		if(cascade && find!=null) {
			for(Product product: find) {
				Article article = articleDao.findById(product.getParentId());
				BeanUtils.copyProperties(product, article);
			}
		}
		return find;
	}


	@Override
	public Long getTotalCount() throws Exception {
		return productDao.countAll();
	}
	
	@Override
	public Product searchByProperties(String[] keys, Object[] values)
			throws Exception {
		List<Product> products = productDao.find(1, 1, null, null, keys, values, null, null, null, false);
		if(products!=null)
			return products.get(0);
		return null;
	}
	
	@Override
	public Long countByCategory(Integer category) throws Exception {
		if(category!=null) {
			return productDao.count(null, null, new String[]{"category"}, new Object[]{category}, null, null);
		}
		return productDao.countAll();
	}



	@Override
	public boolean update(String[] properties, Object[] values, Object id) {
		try {
			productDao.update(properties, values, "id", id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
