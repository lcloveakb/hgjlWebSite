package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.senseId.social.dao.DownloadItemDao;
import com.senseId.social.entity.DownloadItem;
import com.senseId.social.service.DownloadItemService;

@Service("downloadItemService")
public class DownloadItemServiceImpl implements DownloadItemService {

	@Resource(name = "downloadItemDao")
	private DownloadItemDao downloadItemDao;
	
	@Override
	public DownloadItem get(Integer id) throws Exception {
		return downloadItemDao.findById(id);
	}

	@Override
	public Integer add(String title, String url, Integer category) {
		Integer op=null;
		DownloadItem entity = new DownloadItem();
		entity.setCategoryId(category);
		entity.setTitle(title);
		entity.setUrl(url);
		try {
			op=(Integer) downloadItemDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return op;
	}

	@Override
	public boolean delete(Integer downloadItemId) {

		DownloadItem entity;
		try {
			entity = downloadItemDao.findById(downloadItemId);
			if(entity==null){
				return false;
			}
			downloadItemDao.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<DownloadItem> getByCategory(Integer categoryId) throws Exception {
		return downloadItemDao.find("categoryId", new Object[]{categoryId});
	}

	@Override
	public boolean update(DownloadItem item) {
		try {
			downloadItemDao.update(item);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public Integer getTotalCount() throws Exception {
		return downloadItemDao.countAll().intValue();
	}

	@Override
	public List<DownloadItem> getAll() throws Exception {
		return downloadItemDao.findAll();
	}

}
