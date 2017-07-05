package cn.test.serviceTest;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by jiangcaijun on 2017/6/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:conf/spring.xml" })
public class userServiceTest {

    @Resource
    private UserService userService;

    public userServiceTest()
    {
        super();
        sop("<<Person Constructor>>");
    }

    @BeforeClass
    public static void beforeClassM()
    {
        sop("<<Before Class>>");
    }

    @Before
    public void beforeM()
    {
        sop("<<Before>>");
    }


    @AfterClass
    public static void afterClassM()
    {
        sop("<<After Class>>");
    }


    @After
    public void after()
    {
        sop("<<After>>");
    }

    @Test
    public void testMethod1()
    {
        sop("Test Method 1.");
        User user = userService.getUser("1");
        sop(user.getUserName());
    }

    @Test
    public void testMethod2()
    {
        sop("Test Method 2.");
    }

    private static void sop(String msg){
        System.out.println(msg);
    }
}
