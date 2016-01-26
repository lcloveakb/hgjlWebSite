package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.DownloadItem;

public interface DownloadItemService {

	public DownloadItem get(Integer id) throws Exception;
	
	public Integer add(String title, String url, Integer category);

	public boolean delete(Integer downloadItemId);

	public List<DownloadItem> getByCategory(Integer categoryId) throws Exception;
	
	public List<DownloadItem> getAll() throws Exception;

	public boolean update(DownloadItem item);

	public Integer getTotalCount() throws Exception;
	

}
