package com.senseId.social.service;

import java.util.List;

import com.senseId.social.entity.CommentItem;

public interface CommentService {

	/**
	 * 用户对指定Article进行评论
	 * @param userId
	 * @param articleId
	 * @param content
	 * @return
	 */
	public CommentItem add(Integer userId, Long articleId, String content) throws Exception;
	
	/**
	 * 删除指定评论
	 * @param id		评论id
	 */
	public boolean delete(Long commentId);
	
	/**
	 * 分页获取某一个Article的评论，按照时间逆序排列
	 * @param articleId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public List<CommentItem> get(Long articleId, int pageNumber, Integer pageSize) throws Exception;
	
	/**
	 * 获取某一个article的评论总数
	 * @param articleId
	 * @return
	 * @throws Exception
	 */
	public Long getTotalCount(Long articleId) throws Exception;
	
}
