package com.senseId.social.service;

import com.senseId.social.entity.User;

public interface UserService {
	
	public Integer save(User user);
	
	public boolean update(User user);
	
	public boolean exists(String property, String value);
	
	public User login(String userName,String userPwd);
	
	public User findPwd(String email);

	public boolean activate(String sign);
	
	public User checkEmail(String username, String email) throws Exception;
	
	public User checkPasswordCode(String passwordCode) throws Exception;
	
	public User findByUsername(String username) throws Exception;

	public User findByWeiboId(String weiboid) throws Exception;
	
	
}
