package com.senseId.social.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.senseId.social.entity.User;
import com.senseId.social.service.UserService;
import com.senseId.social.util.EmailSender;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("userAction")
public class UserAction extends ActionSupport {

	@Autowired
	UserService userService;

	/* params list */
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String telephone;
	private String createTime;
	private String weiboId;
	private String weixinId;
	private String qqId;
	private String qzoneId;
	private String loginTime;
	private Integer loginCount;
	private Integer credits;
	private String level;
	private Integer ajaxStatus;
	private Date date1;
	private Integer remember;
	private User user = new User();
	private String msg;
	private boolean activated = false;
	private String passwordCode;
	private boolean modified = false;	// 密码是否修改成功
	private String sign;
	private String description;
	private String imageUrl;
	private String weiboUrl;
	private String location;
	private String errorInput;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
	HttpServletResponse response = (HttpServletResponse) ActionContext
			.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpServletRequest reuqest = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);

	public String toLogin() {
		return "login";
	}

	public String login() {
		
		if(username==null || !username.matches("[A-Za-z0-9_]{6,19}")) {
			ajaxStatus = -1;
			msg = "用户名只能包含数字、字母下划线，长度6-19个字符";
			errorInput = "username";
			return "ajax";
		}
		
		if(password==null || !password.matches("[a-zA-Z]\\w{7,19}")) {
			ajaxStatus = -1;
			msg = "密码必须以字母开头，8-20个数字、字母和下划线组合";
			errorInput = "password";
			return "ajax";
		}
		
		if(!userService.exists("username", username)) {
			ajaxStatus = -1;
			errorInput = "username";
			msg = "账号不存在";
			return "ajax";
		}
		user = userService.login(username, password);
		if (user != null) {
			if(!user.isValidate()) {
				ajaxStatus = -1;
				errorInput = "username";
				msg = "账户未激活";
				return "ajax";
			}
			ActionContext.getContext().getSession()
			.put("date1", user.getLoginTime());
			ajaxStatus = 1;
			if (user.getCredits() <= 100) {
				user.setLevel("初涉江湖");
			} else if (user.getCredits() > 100 && user.getCredits() <= 200) {
				user.setLevel("无名之辈");
			} else if (user.getCredits() > 200 && user.getCredits() <= 300) {
				user.setLevel("仗剑天涯");
			} else if (user.getCredits() > 300 && user.getCredits() <= 400) {
				user.setLevel("江湖少侠");
			} else {
				user.setLevel("武林高手");
			}
			user.setLoginCount(user.getLoginCount() + 1);
			user.setCredits(user.getCredits() + 10);
			ServletActionContext.getRequest().getSession().setAttribute("user", user);

			if (remember == null) {
				cookieRemove();
			} else if (remember == 1) {
				Cookie cookie = new Cookie("num", user.getPassword());
				Cookie cookie1 = new Cookie("num1", user.getUsername());
				cookie.setMaxAge(365 * 24 * 60 * 60);
				cookie1.setMaxAge(365 * 24 * 60 * 60);
				response.addCookie(cookie);
				response.addCookie(cookie1);
			} else {
				cookieRemove();
			}
			user.setLoginTime(new Date());
			userService.update(user);
		} else {
			errorInput = "password";
			msg = "账号或密码错误";
			ajaxStatus = 0;
		}
		return "ajax";
	}

	public String logout() {
		ActionContext.getContext().getSession().remove("user");
		cookieRemove();
		return "show";
	}

	public String info() {
		return "info";
	}

	public String update() {

		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setWeiboId(weiboId);
		user.setWeixinId(weixinId);
		user.setQqId(qqId);
		user.setQzoneId(qzoneId);
		try {
			user.setCreateTime(sdf.parse(createTime));
			user.setLoginTime(sdf.parse(loginTime));
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		}
		user.setLoginCount(loginCount);
		user.setCredits(credits);
		user.setLevel(level);
		if (userService.update(user)) {
			ServletActionContext.getRequest().getSession().removeAttribute("user");
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			ajaxStatus = 1;
		} else {
			ajaxStatus = 0;
		}
		return "ajax";
	}

	public String toRegister() {
		return "register";
	}

	public String register() {
		
		if(username==null || !username.matches("[A-Za-z0-9_]{6,19}")) {
			ajaxStatus = -1;
			msg = "用户名只能包含数字、字母下划线，长度6-19个字符";
			errorInput = "username";
			return "ajax";
		}
		
		if(password==null || !password.matches("[a-zA-Z]\\w{7,19}")) {
			ajaxStatus = -1;
			msg = "密码必须以字母开头，8-20个数字、字母和下划线组合";
			errorInput = "password";
			return "ajax";
		}
		
		if(email==null || !email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
			ajaxStatus = -1;
			msg = "邮箱格式不正确";
			errorInput = "email";
			return "ajax";
		}
		
		if(userService.exists("username", username)) {
			ajaxStatus = -1;
			msg = "用户名已被占用";
			errorInput = "username";
			return "ajax";
		}
		
		if(userService.exists("email", email)) {
			ajaxStatus = -1;
			msg = "邮箱已被占用";
			errorInput = "email";
			return "ajax";
		}
		
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String timemilis = System.currentTimeMillis()+"";
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		String url = basePath+"user/userAction_activate.action?sign="+timemilis;
		String mailText = "<span>恭喜你完成注册，请点击以下链接进行账户激活</span><br><br>"
				+ "<a href='"+url+"'>"+url+"</a>";
		if (EmailSender.send(email, "请激活您的账户", mailText) > 0) {
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setTelephone("");
			user.setCreateTime(new Date());
			user.setWeiboId(weiboId);
			user.setWeixinId(null);
			user.setQqId(null);
			user.setQzoneId(null);
			user.setLoginTime(null);
			user.setLoginCount(0);
			user.setCredits(0);
			user.setLevel("初涉江湖");
			user.setSign(timemilis);
			user.setValidate(false);
			Integer id = userService.save(user);
			if (id != null) {
				ajaxStatus = 1;	// 注册成功
			} else {
				ajaxStatus = 0;	// 注册失败
			}
		} else {
			ajaxStatus = -1;	// 邮件发送失败
			errorInput = "email";
			msg = "邮件发送失败";
		}
		return "ajax";
	}

	public String activate() {
		if(userService.activate(sign)){
			setActivated(true);
			msg = "激活成功，3秒后跳转到登录界面";
		} else {
			setActivated(false);
			msg = "激活失败，请返回重新激活";
		}
		return "activateResult";
	}
	
	/**
	 * 去身份认证页
	 * @return
	 */
	public String toConfirmStatus() {
		return "confirmStatus";
	}
	
	/**
	 * 修改密码前的身份验证
	 * @return
	 */
	public String confirmStatus() {
		
		if(email==null || !email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
			ajaxStatus = -1;
			msg = "邮箱格式不正确";
			errorInput = "email";
			return "ajax";
		}
		
		user = null;
		try {	//检查邮箱
			user = userService.findPwd(email);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxStatus = -1;
			msg = "邮箱验证失败，请稍后重试";
			return "ajax";
		}
		/*if(user.getPasswordCode()!=null) {
			ajaxStatus = -1;
			msg = "您已经执行过该操作，请登录邮箱继续进行密码修改";
			return "ajax";
		}*/
		HttpServletRequest request = ServletActionContext.getRequest();
		if(user!=null){
			long timemillis = System.currentTimeMillis();
			user.setPasswordCode(timemillis+"");
			//将链接标志更新到数据库
			boolean update = userService.update(user);
			if (update) {
				String url = request.getScheme() + "://" + request.getServerName() + request.getContextPath() 
						+ "/user/userAction_toModifyPassword.action?passwordCode=" + timemillis;
				String send = "<span style='color:blue;font-style:italic;font-size:13px'>请点击以下链接修改密码，如果点击无效，请将其复制后手动添加到浏览器地址栏中</span>"
						+ "<br><br><br>"
						+ "<a style='color:blue;text-decoration:none' href='"+url+"'>"+url+"</a>";
				if(EmailSender.send(user.getEmail(), "修改密码", send)>0) {
					ajaxStatus = 1;
					msg = "请登录邮箱后点击链接修改密码";
				} else {
					ajaxStatus = -1;
					msg = "邮件发送失败，请稍后重试";
				}
			} else {
				ajaxStatus = -1;
				msg =  "信息更新失败，请重试";
			}
		} else {
			msg =  "该邮箱尚未注册";
			ajaxStatus = -1;
		}
		return "ajax";
	}

	public String toModifyPassword() throws Exception {
		//TODO 错误页面
		//根据passwordCode检查链接是否一致
		if(passwordCode!=null && passwordCode.length()>0) {
			user = userService.checkPasswordCode(passwordCode);
			if(user==null){
				return "urlInvalidate";
			}
		} else {
			return "urlInvalidate";
		}
		return "toModifyPassword";
	}

	public String modifyPassword() {
		modified = false;
		if(password	!=null && password.matches("[a-zA-Z]\\w{7,19}")) {
			try {
				user = userService.findByUsername(username);
				if(user!=null) {
					user.setPassword(password);
					user.setPasswordCode(null);
					modified = userService.update(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "modifyPasswordResult";
	}
	
	public void cookieRemove() {
		Cookie[] cookies = reuqest.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("num")) {
					Cookie cookie = new Cookie("num", null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				if (cookies[i].getName().equals("num1")) {
					Cookie cookie = new Cookie("num1", null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}

			}
		}
	}

	public String checkWeiBo() {

		if (weiboId != null) { // 用第三方账号登陆 用到
			if (userService.exists("weiboId", weiboId)) {
				ajaxStatus = 0;
				msg = "微博已有记录";
			} else {
				ajaxStatus = 1;
			}
		}
		return "ajax";
	}

	/**
	 * 用第三方账号直接登陆
	 * 
	 * @return
	 */
	public String loginByAccount() {

		try {
			user = userService.findByWeiboId(weiboId);
			if (user != null) {
				ajaxStatus = 1;
			} else {
				ajaxStatus = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession()
				.setAttribute("user", user);
		return "ajax";
	}

	public String toWeiboInfo() {

		try {
			username = URLDecoder.decode(username, "utf-8");
			description = URLDecoder.decode(description, "utf-8");
			location = URLDecoder.decode(location, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "toWeiboInfo";
	}

	// 第三方账号绑定
	public String accountBind() {
		if (weiboId != null && weiboId.length() >= 0) { // 绑定微博账号
			User user = userService.login(username, password);
			user.setWeiboId(weiboId);
			userService.save(user);
			ServletActionContext.getRequest().getSession()
					.setAttribute("user", user);
			ajaxStatus = 1;
		}
		return "ajax";
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

	public Integer getAjaxStatus() {
		return ajaxStatus;
	}

	public void setAjaxStatus(Integer ajaxStatus) {
		this.ajaxStatus = ajaxStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
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

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Integer getRemember() {
		return remember;
	}

	public void setRemember(Integer remember) {
		this.remember = remember;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean getActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getPasswordCode() {
		return passwordCode;
	}

	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getErrorInput() {
		return errorInput;
	}

	public void setErrorInput(String errorInput) {
		this.errorInput = errorInput;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getWeiboUrl() {
		return weiboUrl;
	}

	public void setWeiboUrl(String weiboUrl) {
		this.weiboUrl = weiboUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
