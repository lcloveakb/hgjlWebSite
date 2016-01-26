package com.senseId.social.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHibernateDaoImpl {
	
	
	static ApplicationContext c;
	static {
		c = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}
	
//	@Test
//	public void testParameterizedQuery() {
//		UserService us =  (UserService) c.getBean("userService");
//		try {
//			List<User> search = us.search(1, 1, new String[]{"username"}, new Object[]{"123"}, new String[]{"password"}, new Object[]{"sdfsdfsdfsdf"}, "id", new Object[]{1,100}, "id", false);
//			System.out.println(search);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testParameterizedCount() {
//		UserService us =  (UserService) c.getBean("userService");
//		try {
//			Long count = us.count(new String[]{"username"}, new Object[]{"123"}, new String[]{"validate"}, new Object[]{true}, "id", new Object[]{1,100});
//			System.out.println(count);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testParameterizedDelete() {
//		UserService us =  (UserService) c.getBean("userService");
//		try {
//			boolean delete = us.delete(new String[]{"username"}, new Object[]{"123"}, new String[]{"validate"}, new Object[]{false}, "id", new Object[]{1,100});
//			System.out.println(delete);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testParameterizedUpdate() {
//		UserService us =  (UserService) c.getBean("userService");
//		try {
//			boolean update = us.update(new String[]{"email"}, new Object[]{"960021173@qq.com"}, "id", 4);
//			System.out.println(update);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
