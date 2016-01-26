package com.senseId.social.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 开发者子项
 * 
 * @author Xu Chunyang
 * 
 */
public class DeveloperItem extends Article implements Serializable {

	private Integer id; // 主键ID
	private Integer categoryId; // 类别ID
	private String content; // 内容
	// 父类的ID
	private Long parentId;

	private String name, path;

	private DeveloperItemCategory category; // 开发者子项的类别

	public DeveloperItem() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DeveloperItemCategory getCategory() {
		return category;
	}

	public void setCategory(DeveloperItemCategory category) {
		this.category = category;
	}

	public DeveloperItem(Integer id, Integer categoryId, String content,
			Long parentId, String name, String path,
			DeveloperItemCategory category) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.content = content;
		this.parentId = parentId;
		this.name = name;
		this.path = path;
		this.category = category;
	}

	public DeveloperItem(Integer categoryId, String content, Long parentId,
			String name, String path, DeveloperItemCategory category) {
		super();
		this.categoryId = categoryId;
		this.content = content;
		this.parentId = parentId;
		this.name = name;
		this.path = path;
		this.category = category;
	}

	public DeveloperItem(Integer type, Integer clickCount, Integer shareCount,
			Date createTime, String creator, String title, boolean shown,
			String brief) {
		super(type, clickCount, shareCount, createTime, creator, title, shown,
				brief);
	}

	public DeveloperItem(Integer type, Integer clickCount, Integer shareCount,
			Date createTime, String creator, String title) {
		super(type, clickCount, shareCount, createTime, creator, title);
	}

	public DeveloperItem(Long articleId, Integer type, Integer clickCount,
			Integer shareCount, Date createTime, String creator, String title) {
		super(articleId, type, clickCount, shareCount, createTime, creator,
				title);
	}

	@Override
	public String toString() {
		return "DeveloperItem [id=" + id + ", categoryId=" + categoryId
				+ ", content=" + content + ", parentId=" + parentId + ", name="
				+ name + ", path=" + path + ", category=" + category + "]";
	}
	
	

}
