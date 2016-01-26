package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.senseId.social.dao.DownloadItemCategoryDao;
import com.senseId.social.dao.DownloadItemDao;
import com.senseId.social.entity.DownloadItem;
import com.senseId.social.entity.DownloadItemCategory;
import com.senseId.social.service.DownloadItemCategoryService;

@Service("downloadItemCategoryService")
public class DownloadItemCategoryServiceImpl implements DownloadItemCategoryService{

	@Resource(name="downloadItemCategoryDao")
	private DownloadItemCategoryDao dao;
	
	@Resource(name="downloadItemDao")
	private DownloadItemDao itemDao;
	
	@Override
	public void add(String name) throws Exception {
		dao.save(new DownloadItemCategory(name));
	}

	@Override
	public void delete(Integer categoryId) {
		try {
			itemDao.deleteByProperty("categoryId", categoryId);
			DownloadItemCategory entity=dao.findById(categoryId);
			dao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DownloadItemCategory category) throws Exception {
		dao.update(category);
	}

	@Override
	public List<DownloadItemCategory> getAll() {
		return dao.findAll();
	}

	@Override
	public DownloadItemCategory get(Integer id) throws Exception {
		return dao.findById(id);
	}
	
	@Override
	public List<DownloadItemCategory> getAllCascade() {
		List<DownloadItemCategory> cats = dao.findAll();
		if(cats!=null && cats.size()>0) {
			for(DownloadItemCategory cat : cats) {
				List<DownloadItem> items = null;
				try {
					items = itemDao.find("categoryId", new Object[]{cat.getId()});
				} catch (Exception e) {
					e.printStackTrace();
				}
				cat.setItems(items);
			}
		}
		return cats;
	}

}
