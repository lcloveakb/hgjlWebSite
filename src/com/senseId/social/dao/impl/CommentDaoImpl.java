package com.senseId.social.dao.impl;

import org.springframework.stereotype.Repository;

import com.senseId.social.dao.CommentDao;
import com.senseId.social.entity.CommentItem;

@Repository("commentDao")
public class CommentDaoImpl extends HibernateGenericDaoImpl<CommentItem, String> implements CommentDao{
	
}
