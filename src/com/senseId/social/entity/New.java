package com.senseId.social.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻
 * 
 * @author Xu Chunyang, 2015-5-19
 * 
 */
public class New extends Article implements Serializable{

	public static final int US = 0;
	public static final int INDUSTRY = 1;

	// ID
	private String id;
	// 父类的ID
	private Long parentId;
	// 新闻内容
	private String content;
	// 新闻类别，参见常量
	private Integer category;
	// 新闻来源
	private String source;
	
	private String imgs;
	
	public New() {
		super();
	}
	
	

	public New(Integer type, Integer clickCount, Integer shareCount,
			Date createTime, String creator, String title) {
		super(type, clickCount, shareCount, createTime, creator, title);
	}



	public New(Long articleId, Integer type, Integer clickCount,
			Integer shareCount, Date createTime, String creator, String title) {
		super(articleId, type, clickCount, shareCount, createTime, creator, title);
	}



	public New(String id, Long parentId, String content, Integer category,
			String source) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.content = content;
		this.category = category;
		this.source = source;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}



	@Override
	public String toString() {
		return "New [id=" + id + ", parentId=" + parentId + ", content="
				+ content + ", category=" + category + ", source=" + source+ "]";
	}



	public String getImgs() {
		return imgs;
	}



	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	

}
