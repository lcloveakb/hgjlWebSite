package com.senseId.social.dao;

import com.senseId.social.entity.Article;


public interface ArticleDao extends GenericDAO<Article, Long> {
	
	/**
	 *  for Automatic completion method
	 * @param keyword 
	 * @return 
	 * @throws SQLException 
	 * @throws ParseException 
	 * @throws IOException 
	 */
//	public List<Article> searchForAuto(String keyword) throws SQLException, IOException, ParseException;
	
	/**
	 *  for when user click the search button searching news or product
	 * @param keyword 
	 * @return List<Obejct> maybe news or maybe product
	 */
//	public List<Article> searchByLucence(String keyword);
	
	

}
