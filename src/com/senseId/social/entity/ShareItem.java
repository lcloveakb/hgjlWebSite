package com.senseId.social.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享类
 * @author Xu Chunyang
 *
 */
public class ShareItem implements Serializable {

	private Long id;	// 主键ID
	private Long userId;	//分享者ID
	private Long articleId;	//分享的Article的ID
	private Date time;		//分享时间
	
	private Article article;
	private User user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
