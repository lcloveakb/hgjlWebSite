package com.senseId.social.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author Xu Chunyang, 2015年9月6日
 *
 */
public class User implements Serializable {
	
	// ID
	private Integer id;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 邮箱
	private String email;
	// 电话
	private String telephone;
	// 创建时间
	private Date createTime;
	// 微博ID
	private String weiboId;
	// 微信ID
	private String weixinId;
	// QQ ID
	private String qqId;
	// Qzone ID
	private String qzoneId;
	// 上次登录时间
	private Date loginTime;
	// 登录次数
	private Integer loginCount;
	// 积分
	private Integer credits;
	// 等级
	private String level;
	// 是否是激活用户
	private boolean validate;
	// 修改密码的验证码
	private String passwordCode;
	// 注册链接的签名
	private String sign;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getWeiboId() {
		return weiboId;
	}
	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}
	public String getWeixinId() {
		return weixinId;
	}
	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
	public String getQqId() {
		return qqId;
	}
	public void setQqId(String qqId) {
		this.qqId = qqId;
	}
	public String getQzoneId() {
		return qzoneId;
	}
	public void setQzoneId(String qzoneId) {
		this.qzoneId = qzoneId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getPasswordCode() {
		return passwordCode;
	}
	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", username:" + username + ", password:"
				+ password + ", email:" + email + ", telephone:" + telephone
				+ ", createTime:" + createTime + ", weiboId:" + weiboId
				+ ", weixinId:" + weixinId + ", qqId:" + qqId + ", qzoneId:"
				+ qzoneId + ", loginTime:" + loginTime + ", loginCount:"
				+ loginCount + ", credits:" + credits + ", level:" + level
				+ "}";
	}
}
