package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.DeveloperItemCategory;

public interface DeveloperItemCategoryService {
	
	public DeveloperItemCategory get(Integer id) throws Exception;
	
	public boolean delete(Integer id);
	
	public boolean update(DeveloperItemCategory cat);
	
	public Integer add(DeveloperItemCategory cat) throws Exception;

	public List<DeveloperItemCategory> getAll() throws Exception;
	
}
