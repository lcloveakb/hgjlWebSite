package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.senseId.social.dao.DeveloperItemCategoryDao;
import com.senseId.social.dao.DeveloperItemDao;
import com.senseId.social.entity.DeveloperItemCategory;
import com.senseId.social.service.DeveloperItemCategoryService;

@Service("developerItemCategoryService")
public class DeveloperItemCategoryServiceImpl implements DeveloperItemCategoryService {

	@Resource(name = "developerItemCategoryDao")
	private DeveloperItemCategoryDao devCatDao;
	
	@Resource(name = "developerItemDao")
	private DeveloperItemDao devDao;
	
	@Override
	public DeveloperItemCategory get(Integer id) throws Exception {
		return devCatDao.findById(id);
	}
	@Override
	public boolean delete(Integer id) {
		try {
			devDao.deleteByProperty("categoryId", id);
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		DeveloperItemCategory findById = null;
		try {
			findById = devCatDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(findById==null)
			return false;
		try {
			devCatDao.delete(findById);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean update(DeveloperItemCategory item) {
		try {
			devCatDao.update(item);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		};
		return true;
	}
	@Override
	public Integer add(DeveloperItemCategory item) throws Exception {
		return (Integer) devCatDao.save(item);
	}
	@Override
	public List<DeveloperItemCategory> getAll() throws Exception {
		return devCatDao.findAll();
	}


}
