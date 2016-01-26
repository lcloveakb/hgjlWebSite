package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.DeveloperItem;

public interface DeveloperItemService {

	public DeveloperItem get(Integer id) throws Exception;

	public boolean delete(Integer id);

	public List<DeveloperItem> getAll() throws Exception;

	public boolean update(DeveloperItem item);

	public Integer add(DeveloperItem item) throws Exception;

	public List<DeveloperItem> search(int pageNumber, Integer pageSize,
			Integer categoryId) throws Exception;

	public List<DeveloperItem> search_(int pageNumber, Integer pageSize)
			throws Exception;

	public List<DeveloperItem> about(int pageNumber, int size,
			String[] pNameLikes, Object[] pValueLikes, String[] pNameEquals,
			Object[] pValueEquals, String rangedPropertyName, Object[] ranges,
			String orderedPropertyName, boolean sequence) throws Exception;

	public Integer getTotalCount() throws Exception;

}
