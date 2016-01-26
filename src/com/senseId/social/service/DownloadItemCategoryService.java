package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.DownloadItemCategory;

public interface DownloadItemCategoryService {
	
	public void add(String name) throws Exception;
	
	public void delete(Integer categoryId);
	
	public void update(DownloadItemCategory category) throws Exception;
	
	public List<DownloadItemCategory> getAll();
	
	public DownloadItemCategory get(Integer id) throws Exception;
	
	public List<DownloadItemCategory> getAllCascade();

}
