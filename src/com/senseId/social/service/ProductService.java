package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.Product;

public interface ProductService {

	/**
	 * 根据Product的ID获取Product，并指定是否获取其Article
	 * @param id Product的ID，UUID表示
	 * @param cascade	是否获取其Article
	 * @return
	 * @throws Exception
	 */
	public Product get(String id, boolean cascade) throws Exception;
	
	public String add(Product product) throws Exception;
	
	public boolean update(Product product);
	
	public boolean delete(String id);
	
	public List<Product> searchByCategory(Integer category, int pageNumber, int pageSize, boolean cascade) throws Exception;
	
	public Long getTotalCount() throws Exception;
	
	public Product searchByProperties(String[] keys, Object[] values) throws Exception;

	Long countByCategory(Integer category) throws Exception;
	
	public boolean update(String[] properties, Object[] values, Object value);
}
