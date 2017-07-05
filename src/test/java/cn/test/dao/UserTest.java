package cn.test.dao;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.model.User;
import com.ssm.service.UserService;

public class UserTest {
	private static final Logger LOG = Logger.getLogger(UserTest.class);  

	private UserService userService;

	@Before
	public void before() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:conf/spring.xml"});
		userService = (UserService) context.getBean("userService");
		//不用@Resource，而用@Autowired时,也可采用下面这种getBean方式
//		userService = (UserService) context.getBean("userServiceImpl");
	}

	
	/**
	* @Title: addUser
	* @Description: 事物的回滚功能测试
	* @param
	* @return void    返回类型
	* @throws
	*/
	@Test
	public void aasdfdUser() {
		User user = new User();
		user.setUserName("事物1");
		user.setPassword("adminadmin");
		user.setAge(22);
		LOG.info(userService.insert(user));
		User user2 = new User();
		user2.setId("1");
		user2.setUserName("事物2");
		user2.setPassword("adminadmin");
		user2.setAge(22);
//		LOG.info(userService.insert(user2));
	}
	@Test
	public void testMethod1()
	{
		System.out.println("Test Method 1.");
		User user = userService.getUser("1");
		System.out.println(user.getUserName());
	}
}
