package com.senseId.social.entity;

import java.io.Serializable;

/**
 * 产品
 * 
 * @author Liu Chang,2015-6-12
 * 
 */

@SuppressWarnings("serial")
public class Product extends Article implements Serializable {
	
	public static final int READER = 0;
	public static final int TAG = 1;
	public static final int OTHER = 2;

	private String id; // 主键ID

	private Long parentId; // 父类ID，即Article的主键ID

	private String params; // 参数

	private Integer category; // 类别

	private String imgs; // 图片路径

	public Product() {
		super();
	}

	public Product(String id, Long parentId, String params, Integer category,
			String imgs) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.params = params;
		this.category = category;
		this.imgs = imgs;
	}

	public Product(Long parentId, String params, Integer category, String imgs) {
		super();
		this.parentId = parentId;
		this.params = params;
		this.category = category;
		this.imgs = imgs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", parentId=" + parentId + ", params="
				+ params + ", category=" + category + ", imgs=" + imgs + "]";
	}

}
