package com.senseId.social.entity;

import java.io.Serializable;

/**
 * 开发者栏目类别
 * @author Xu Chunyang
 *
 */
public class DeveloperItemCategory implements Serializable {
	
	private Integer id;	// 主键ID
	private String name;	// 名称
	
	
	
	public DeveloperItemCategory() {
		super();
	}
	public DeveloperItemCategory(String name) {
		super();
		this.name = name;
	}
	
	public DeveloperItemCategory(Integer id, String name) {
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
	@Override
	public String toString() {
		return "DeveloperItemCategory [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
