package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.New;

public interface NewService {

	public New get(String id, boolean cascade) throws Exception;   //是否级联
	
	public String add(New news) throws Exception;
	
	public boolean delete(String id);
	
	public boolean update(New news);
	
	public List<New> searchByCategory(Integer category, int pageNumber, int pageSize, boolean cascade) throws Exception;
	
	public List<New> getAll(int pageNumber, int pageSize, boolean cascade) throws Exception;
	
	public Long getTotalCount() throws Exception;

	public New searchByProperties(String[] keys, Object[] values) throws Exception;
	
	public Long countByCategory(Integer category) throws Exception;
}
