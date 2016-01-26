package com.senseId.social.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.senseId.social.entity.Article;

public interface ArticleService {
	
	public List<Article> getList();

	public Long add(Article article) throws Exception;

	public boolean delete(Long articleId);

	public boolean update(Article article);

	public Article get(Long articleId) throws Exception;
	
	/**
	 * 根据关键字获取搜索提示
	 * @param keyword
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Article> searchForAuto(String keyword) throws Exception;
	
	
	/**
	 * 根据关键字搜索结果
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Article> searchByLucence(String keyword) throws Exception;
	
	public Long getTotalCount() throws Exception;
	
	public List<Article> search(int pageNumber, int size, String[] pNameLikes,
			Object[] pValueLikes, String[] pNameEquals, Object[] pValueEquals,
			String rangedPropertyName, Object[] ranges,
			String orderedPropertyName, boolean sequence) throws Exception;
	
	public Long max();

}
