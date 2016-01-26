package com.senseId.social.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.senseId.social.dao.CommentDao;
import com.senseId.social.dao.UserDao;
import com.senseId.social.entity.CommentItem;
import com.senseId.social.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource(name="commentDao")
	private CommentDao dao;
	
	@Resource(name="userDao")
	private UserDao userDao;

	@Override
	public CommentItem add(Integer userId, Long articleId, String content) throws Exception{
		CommentItem comment = new CommentItem();
		comment.setArticleId(articleId);
		comment.setContent(content);
		comment.setUserId(userId);
		comment.setTime(new Date());
		comment.setId(UUID.randomUUID().toString());
		dao.save(comment);
		comment.setUser(userDao.findById(userId));
		return comment;
	}

	@Override
	public boolean delete(Long commentId) {
		try {
			dao.deleteByProperty("id", commentId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CommentItem> get(Long articleId, int pageNumber, Integer pageSize) throws Exception {
		List<CommentItem> items = dao.find(pageNumber, pageSize, null, null, new String[]{"articleId"}, new Object[]{articleId}, null, null, "time", false);
		for(CommentItem comment : items) {
			comment.setUser(userDao.findById(comment.getUserId()));
		}
		return items;
	}

	@Override
	public Long getTotalCount(Long articleId) {
		try {
			return dao.count(null, null, new String[]{"articleId"}, new Object[]{articleId}, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return 0l;
		}
	}

}
