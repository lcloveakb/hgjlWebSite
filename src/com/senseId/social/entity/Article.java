package com.senseId.social.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有可展示的对象的父类
 * @author Xu Chunyang, 2015-5-28
 * 
 */
public class Article implements Serializable {

	/*
	 * Article子类型
	 */
	public static final int TYPE_NEWS = 0;
	public static final int TYPE_CASE = 1;
	public static final int TYPE_PRODUCT = 2;

	// ID
	private Long articleId;
	// 类型
	private Integer type;
	// 点击次数
	private Integer clickCount;
	// 分享次数
	private Integer shareCount;
	// 创建时间
	private Date createTime;
	// 创建人
	private String creator;
	// 标题
	private String title;
	// 是否显示在首页
	private boolean shown;
	// 首页图片路径
	private String indexImg;
	// 简介
	private String brief;
	//置顶
	private Long top;

	public Article() {
		super();
	}

	public Article(Long articleId, Integer type, Integer clickCount,
			Integer shareCount, Date createTime, String creator, String title) {
		super();
		this.articleId = articleId;
		this.type = type;
		this.clickCount = clickCount;
		this.shareCount = shareCount;
		this.createTime = createTime;
		this.creator = creator;
		this.title = title;
	}

	public Article(Integer type, Integer clickCount, Integer shareCount,
			Date createTime, String creator, String title) {
		super();
		this.type = type;
		this.clickCount = clickCount;
		this.shareCount = shareCount;
		this.createTime = createTime;
		this.creator = creator;
		this.title = title;
	}

	public Article(Integer type, Integer clickCount, Integer shareCount,
			Date createTime, String creator, String title, boolean shown,
			String brief) {
		super();
		this.type = type;
		this.clickCount = clickCount;
		this.shareCount = shareCount;
		this.createTime = createTime;
		this.creator = creator;
		this.title = title;
		this.shown = shown;
		this.brief = brief;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

	
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getIndexImg() {
		return indexImg;
	}

	public void setIndexImg(String indexImg) {
		this.indexImg = indexImg;
	}

	public Long getTop() {
		return top;
	}

	public void setTop(Long top) {
		this.top = top;
	}

	@Override
	public String toString() {
		return "{articleId:" + articleId + ", type:" + type
				+ ", clickCount:" + clickCount + ", shareCount:" + shareCount
				+ ", createTime:" + createTime + ", creator:" + creator
				+ ", title:" + title + ", shown:" + shown + ", indexImg:"
				+ indexImg + ", brief:" + brief + ", top:" + top + "}";
	}

	
	
}
