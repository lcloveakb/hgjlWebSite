package com.senseId.social.entity;

import java.io.Serializable;
import java.util.Date;

public class CommentItem implements Serializable {

	private String id;	//	主键ID
	private Integer userId;	//	用户ID
	private Long articleId;		//	文章ID
	private String content;		//	内容
	private Date time;		//		评论时间
	
	private User user;		//	用户
	private Article article;		//	文章
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", userId:" + userId + ", articleId:"
				+ articleId + ", content:" + content + ", time:" + time
				+ ", user:" + user + ", article:" + article + "}";
	}
}
