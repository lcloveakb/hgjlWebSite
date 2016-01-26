package com.senseId.social.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.CommentItem;
import com.senseId.social.service.CommentService;
@Scope("prototype")
@Controller("commentAction")
public class CommentAction extends ActionSupport {

	private static final long serialVersionUID = 3911387870089192545L;
	
	@Resource(name="commentService")
	private CommentService commentService;
	
	private Integer userId;
	private Long articleId;
	private String content;
	
	private String addResult;
	private CommentItem item;
	
	private Integer pageno;
	private Integer pagesize;
	private Long pagecount;
	private Long commentcount;
	
	private List<CommentItem> items;
	
	/**
	 * 添加一条评论，userId须从客户端传递
	 * @return
	 */
	public String addItem() {
		//TODO 是否需要验证userId的合法性，并添加新的addResult
		try {
			item = commentService.add(userId, articleId, content);
			setAddResult("1");
		} catch (Exception e) {
			e.printStackTrace();
			setAddResult("0");
		}
		return "add";
	}
	
	public String pageItem() {
		if(pageno==null)
			pageno = 1;
		pagesize = 10;
		try {
			commentcount = commentService.getTotalCount(articleId);
			pagecount = (commentcount+pagesize-1)/pagesize;
			items = commentService.get(articleId, pageno, pagesize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "page";
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
	public String getAddResult() {
		return addResult;
	}
	public void setAddResult(String addResult) {
		this.addResult = addResult;
	}
	public CommentItem getItem() {
		return item;
	}

	public void setItem(CommentItem item) {
		this.item = item;
	}

	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Long getPagecount() {
		return pagecount;
	}
	public void setPagecount(Long pagecount) {
		this.pagecount = pagecount;
	}
	public Long getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(Long commentcount) {
		this.commentcount = commentcount;
	}
	public List<CommentItem> getItems() {
		return items;
	}
	public void setItems(List<CommentItem> items) {
		this.items = items;
	}
}
