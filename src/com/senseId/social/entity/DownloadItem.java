package com.senseId.social.entity;

import java.io.Serializable;


/**
 * 下载
 * @author Xu Chunyang
 *
 */
public class DownloadItem implements Serializable{
	
	private Integer id;	// 主键ID
	private Integer categoryId;	// 下载资源的类别ID
	private String title;	// 下载资源的标题
	private String url;	// 下载资源的url
	
	private DownloadItemCategory category;
	
	
	
	public DownloadItem(Integer id, Integer categoryId, String title,
			String url) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.title = title;
		this.url = url;
	}
	public DownloadItem() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public DownloadItemCategory getCategory() {
		return category;
	}
	public void setCategory(DownloadItemCategory category) {
		this.category = category;
	}
	
}
