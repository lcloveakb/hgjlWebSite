package com.senseId.social.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 下载资源类别
 * @author Xu Chunyang
 *
 */
public class DownloadItemCategory implements Serializable {
	
	private Integer id;	// 主键ID
	private String name;	// 类别名称
	
	private List<DownloadItem> items = null;
	
	public DownloadItemCategory() {
		super();
	}
	public DownloadItemCategory(String name) {
		super();
		this.name = name;
	}
	public DownloadItemCategory(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DownloadItem> getItems() {
		return items;
	}
	public void setItems(List<DownloadItem> items) {
		this.items = items;
	}
}
