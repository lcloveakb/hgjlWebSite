package com.senseId.social.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.senseId.social.dao.UserDao;
import com.senseId.social.entity.User;
import com.senseId.social.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public Integer save(User user) {
		try {
			return (Integer) userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(User user) {
		try {
			userDao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean exists(String property, String value) {
		try {
			List<User> find = userDao.find(1, 1, null, null,
					new String[] { property }, new Object[] { value }, null,
					null, null, false);
			if (find != null && find.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User login(String userName, String userPwd) {

		User user = null;
		try {
			List<User> find = userDao
					.find(1, 1, null, null, new String[] { "username",
							"password" }, new Object[] { userName, userPwd },
							null, null, null, false);
			if (find != null && find.size() > 0)
				user = find.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findPwd(String email) {
		User user = null;
		try {
			List<User> find = userDao.find(1, 1, null, null,
					new String[] { "email" }, new Object[] { email }, null,
					null, null, false);
			if (find != null && find.size() > 0)
				user = find.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public boolean activate(String sign) {
		if(sign==null||"".equals(sign.trim())){
			return false;
		}
		try {
			List<User> list = userDao.find(-1, 0, null, null, new String[]{"sign"}, new Object[]{sign}, null, null, null, false);
			if(list==null||list.size()==0){
				return false;
			} else {
				User user = list.get(0);
				user.setValidate(true);
				user.setSign(null);
				userDao.update(user);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User checkEmail(String username, String email) throws Exception {
		User user = null;
		List<User> find = userDao.find(1, 1, null, null, new String[]{"username", "email"}, new Object[]{username, email}, null, null, null, false);
		if(find!=null && find.size()>0) {
			user = find.get(0);
		}
		return user;
	}

	@Override
	public User checkPasswordCode(String passwordCode) throws Exception {
		User user = null;
		List<User> find = userDao.find(1, 1, null, null, new String[]{"passwordCode"}, new Object[]{passwordCode}, null, null, null, false);
		if(find!=null && find.size()>0) {
			user = find.get(0);
		}
		return user;
	}
	
	@Override
	public User findByUsername(String username) throws Exception {
		User user = null;
		List<User> find = userDao.find(1, 1, null, null, new String[]{"username"}, new Object[]{username}, null, null, null, false);
		if(find!=null && find.size()>0) {
			user = find.get(0);
		}
		return user;
	}

	@Override
	public User findByWeiboId(String weiboid) throws Exception {
		User user = null;
		List<User> find = userDao.find(1, 1, null, null, new String[]{"weiboId"}, new Object[]{weiboid}, null, null, null, false);
		if(find!=null && find.size()>0) {
			user = find.get(0);
		}
		return user;
	}

}
