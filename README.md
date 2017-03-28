## 2017-01-14(maven项目初始化)

### 1、Maven工程创建

Eclipse (版本：4.6.2)，JDK 1.7；
创建过程,可参考：Maven3路程（三）用Maven创建第一个web项目(1) - leiOOlei - 博客园（http://www.cnblogs.com/leiOOlei/p/3361633.html）

### 2、 将该项目提交至gitbub

提交过程,可参考Eclipse提交项目到github ksfzhaohui的个人页面（https://my.oschina.net/OutOfMemory/blog/294133）

### 3、 此时，项目结构如下图

 ![第1阶段： maven项目初始化结构图](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-16_104235.png)


---
## 2017-01-17(maven + spring项目)

### 1、 搭建基础工程目录

#### 1.1、 在src/main/java文件夹中，新建包（如下）

com.ssm.model(存放javabean类)；  
com.ssm.dao（存放spring与mybatis连接接口）；  
com.ssm.service（存放service接口类）；  
com.ssm.service.impl（存放service接口的实现类）；  
com.ssm.controller（存放控制层controller）

#### 1.2、在src/main/resource文件夹中，新建包conf（存放配置文件），

#### 1.3、 在src/test/java文件夹中，新建包com.ssm.test(存放测试文件)

### 2、 MAVEN引入jar包
当前为最基础的jar包，将其引入pom.xml文件中。此时，project中原先报错的index.jsp页面，错误消失。这是因为添加了servlet-api，为project添加了Servlet依赖。附上代码，如下：
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>cn.springmvc</groupId>
	<artifactId>ssm_20170114</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>ssm_20170114 Maven Webapp</name>
	<url>http://maven.apache.org</url>

	<properties>
		<!-- spring版本号 -->
		<spring.version>3.2.4.RELEASE</spring.version>
		<!-- mybatis版本号 -->
		<mybatis.version>3.2.4</mybatis.version>
		<!-- log4j日志文件管理包版本 -->
		<slf4j.version>1.6.6</slf4j.version>
		<log4j.version>1.2.9</log4j.version>
	</properties>

	<!-- 解决One or more constraints have not been satisfied问题，如无该问题，可删除 -->
	<profiles>
		<profile>
			<id>jdk-1.7</id>
			<!-- 另外一种激活方式 -->
			<activation>
				<activeByDefault>true</activeByDefault>
				<jdk>1.7</jdk>
			</activation>
			<properties>
				<maven.compiler.source>1.7</maven.compiler.source>
				<maven.compiler.target>1.7</maven.compiler.target>
				<maven.compiler.compilerVersion>1.7</maven.compiler.compilerVersion>
			</properties>
		</profile>
	</profiles>

	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
		<!-- 与junit配套使用 -->
		<dependency>
			<groupId>org.hamcrest</groupId>
			<artifactId>hamcrest-core</artifactId>
			<version>1.1</version>
		</dependency>

		<!-- spring核心包 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-oxm</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<!-- spring核心包 end -->
		
		<!-- mybatis核心包 -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>${mybatis.version}</version>
		</dependency>
		
		<!-- mybatis/spring包 -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.2</version>
		</dependency>
		
		<!-- mysql驱动包 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.29</version>
		</dependency>
		
		<!-- 添加Servlet依赖的jar -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		
		<!-- 阿里巴巴数据源包 -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.2</version>
		</dependency>

		<!-- json数据 -->
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.4</version>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.20</version>
		</dependency>

		<!-- jstl依赖 -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		
		<!-- 日志log start -->
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j.version}</version>
		</dependency>
		<!-- log end -->
		
		<!-- 支持 ${pageContext.request.contextPath} -->
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.2.1-b03</version>
		</dependency>
	</dependencies>
	<build>
		<finalName>ssm_20170114</finalName>
	</build>
</project>
```

### 3、 配置conf下的配置文件
/conf/spring-mvc.xml如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:p="http://www.springframework.org/schema/p" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.2.xsd
    http://www.springframework.org/schema/mvc
    http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">

	<!-- 扫描controller（controller层注入） -->
	<context:component-scan base-package="com.ssm.controller" />

	<!-- 避免IE在ajax请求时，返回json出现下载 -->
	<bean id="jacksonMessageConverter"
		class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
		<property name="supportedMediaTypes">
			<list>
				<value>text/html;charset=UTF-8</value>
			</list>
		</property>
	</bean>

	<!-- 对模型视图添加前后缀 -->
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
	</bean>
</beans>
```

### 4、 修改web.xml

  ```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	id="WebApp_ID" version="2.5">
	<display-name>Archetype Created Web Application</display-name>
	<!-- 设计路径变量值 -->
	<context-param>
		<param-name>webAppRootKey</param-name>
		<param-value>springmvc.root</param-value>
	</context-param>
	<!-- Spring字符集过滤器 -->
	<filter>
		<filter-name>SpringEncodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>SpringEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<!-- 多个项目在同一个tomcat下时，要考虑webAppRootKey -->
	<context-param>
		<param-name>webAppRootKey</param-name>
		<param-value>web1.root</param-value>
	</context-param>

	<!-- springMVC核心配置 -->
	<servlet>
		<servlet-name>springMVC</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:conf/spring-mvc.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>springMVC</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

	<!-- druid监控 -->
	<!-- druid监控页面方位地址：http://ip:8080/项目名称/druid/ -->
	<servlet>
		<servlet-name>DruidStatView</servlet-name>
		<servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
		<init-param>
			<!-- 用户名 -->
			<param-name>loginUsername</param-name>
			<param-value>druid</param-value>
		</init-param>
		<init-param>
			<!-- 密码 -->
			<param-name>loginPassword</param-name>
			<param-value>dream</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>DruidStatView</servlet-name>
		<url-pattern>/druid/*</url-pattern>
	</servlet-mapping>
</web-app>
```

### 5、  新建jsp文件
在WEB-INF文件夹下新增 view/hello.jsp,如下：

  ```
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>这是WEB-INF/view/目录下的hello.jsp</h1>
</body>
</html>
```

### 6、 新建controller文件
com.ssm.controller包下新增 IndexController.java,如下：
  ```
  package com.ssm.controller;
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/")
    public class IndexController {

        @RequestMapping("hello")
        public String index(){
	  	  return "hello";
        }	
  }
```

### 7、 运行项目
当前结构如下图所示，基本骨架已经出来了。如下图：

![第2阶段：maven + spring项目结构图](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-18_004214.png)

可用tomcat或jetty运行该项目，输入url：http://127.0.0.1:8080/ssm_20170114/hello 
可见下图，证明maven + spring框架搭建成功。
 
![第2阶段：maven + spring项目结构图](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-18_004028.png)

---

Maven搭建SpringMVC+Spring+MyBatis框架，将陆续集成新功能（github地址：https://github.com/jiangcaijun/ssm）

##2017-01-22(maven + spring + spring MVC + mybatis项目)

---
### 1、配置conf下数据库连接

新建/conf/spring-mybatis.xml


  ```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.2.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx-3.2.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
    http://www.springframework.org/schema/util 
    http://www.springframework.org/schema/util/spring-util-3.2.xsd">

	<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
		init-method="init" destroy-method="close">
		<property name="driverClassName">
			<value>${jdbc_driverClassName}</value>
		</property>
		<property name="url">
			<value>${jdbc_url}</value>
		</property>
		<property name="username">
			<value>${jdbc_username}</value>
		</property>
		<property name="password">
			<value>${jdbc_password}</value>
		</property>
		<!-- 连接池最大使用连接数 -->
		<property name="maxActive">
			<value>20</value>
		</property>
		<!-- 初始化连接大小 -->
		<property name="initialSize">
			<value>1</value>
		</property>
		<!-- 获取连接最大等待时间 -->
		<property name="maxWait">
			<value>60000</value>
		</property>
		<!-- 连接池最大空闲 -->
		<property name="maxIdle">
			<value>20</value>
		</property>
		<!-- 连接池最小空闲 -->
		<property name="minIdle">
			<value>3</value>
		</property>
		<!-- 自动清除无用连接 -->
		<property name="removeAbandoned">
			<value>true</value>
		</property>
		<!-- 清除无用连接的等待时间 -->
		<property name="removeAbandonedTimeout">
			<value>180</value>
		</property>
		<!-- 连接属性 -->
		<property name="filters" value="config,wall,stat" />
		<property name="connectionProperties">
			<value>config.decrypt=true</value>
		</property>
	</bean>

	<!-- mybatis文件配置，扫描所有mapper文件 -->
	<!-- p:configLocation加载mybatis的配置文件 -->
	<!-- p:mapperLocations自动扫描model目录中的映射xml文件，省去了在config中手工配置 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="dataSource" 
		p:configLocation="classpath:conf/mybatis-config.xml"
		p:mapperLocations="classpath:cn/springmvc/mapper/*.xml" /><!-- configLocation为mybatis属性mapperLocations为所有mapper -->

	<!-- spring与mybatis整合配置，扫描所有dao -->
	<!-- 对Dao 接口动态实现，需要知道接口在哪  -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer"
		p:basePackage="cn.springmvc.dao" p:sqlSessionFactoryBeanName="sqlSessionFactory" />

	<!-- 对数据源进行事务管理 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">  
		<property name="dataSource" ref="dataSource" />
	</bean> 

	<tx:advice id="transactionAdvice"  transaction-manager="transactionManager"> 
	<tx:attributes>  
		<tx:method name="save*" propagation="REQUIRED" /> 
		<tx:method name="update*" propagation="REQUIRED" /> 
		<tx:method name="delete*" propagation="REQUIRED" /> 
		<tx:method name="insert*" propagation="REQUIRED" /> 
		<tx:method name="load*" propagation="SUPPORTS" /> 
		<tx:method name="find*" propagation="SUPPORTS" /> 
		<tx:method name="list*" propagation="SUPPORTS" /> 
		<tx:method name="check*" propagation="SUPPORTS" />   
		<tx:method name="*" propagation="SUPPORTS" /> 
	</tx:attributes>
	</tx:advice> 

	<aop:config>  
		<aop:pointcut id="transactionPointcut" expression="execution(* com.ssm.service.*.*(..))" />
		<aop:advisor pointcut-ref="transactionPointcut" advice-ref="transactionAdvice" /> 
	</aop:config>
</beans>
```
### 2、 配置spring相关配置文件

/conf/spring.xml (实现spring配置文件的扫描)


  ```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
      http://www.springframework.org/schema/context
      http://www.springframework.org/schema/context/spring-context.xsd">

	<!-- 引入jdbc配置文件 -->
	<context:property-placeholder location="classpath:jdbc.properties" />

	<!-- 扫描文件（自动将servicec层注入） -->
	<context:component-scan base-package="com.ssm.service" />
</beans>
```
### 3、 配置jdbc属性文件

jdbc.properties(配置与数据库的连接属性，此处数据库的密码为明文存在，后续将对其进行加密处理)

  ```
jdbc_driverClassName=com.mysql.jdbc.Driver
jdbc_url=jdbc:mysql://localhost:3306/ssm_20170114?useUnicode=true&characterEncoding=utf-8
jdbc_username=root
jdbc_password=admin
```
### 4、 src/main/java下java代码

#### 4.1、Mybatis-Generator

注意可以利用Mybatis-Generator来生成Dao、Model、Mapping相关文件，以此减少工作量。
可参考链接：使用Mybatis-Generator自动生成Dao、Model、Mapping相关文件 - 李晨玮 - 博客园（http://www.cnblogs.com/lichenwei/p/4145696.html）

#### 4.2、新建user表(对应user_t)

```
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

```

#### 4.3、model层新建User.java ( 用户表，对应用户的基本信息)

  ```
package com.ssm.model;
/**
 * @Description: 用户表
 * @author jiangcaijun
 * @date 2017年01月20日 下午6:37:51
 */
public class User {
    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
```

#### 4.4、dao层新建UserMapper.java 

  ```
  package com.ssm.dao;

  import com.ssm.model.User;

  public interface UserMapper {
      int deleteByPrimaryKey(Integer id);
      int insert(User record);
      int insertSelective(User record);
      User selectByPrimaryKey(Integer id);
      int updateByPrimaryKeySelective(User record);
      int updateByPrimaryKey(User record);
  }
  ```
  
#### 4.4、mapper类新建UserMapper.xml ( mybatis方法)

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ssm.dao.UserMapper">
<!-- 这里用User而不是com.ssm.model.User(用它也是可以的)，是spring-mybatis别名 -->
  <resultMap id="BaseResultMap" type="User">
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="user_name" jdbcType="VARCHAR" property="userName" />
    <result column="password" jdbcType="VARCHAR" property="password" />
    <result column="age" jdbcType="INTEGER" property="age" />
  </resultMap>
  <sql id="Base_Column_List">
    id, user_name, password, age
  </sql>
  <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from user_t
    where id = #{id,jdbcType=INTEGER}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
    delete from user_t
    where id = #{id,jdbcType=INTEGER}
  </delete>
  <insert id="insert" parameterType="com.ssm.model.User">
    insert into user_t (id, user_name, password, 
      age)
    values (#{id,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, 
      #{age,jdbcType=INTEGER})
  </insert>
  <insert id="insertSelective" parameterType="com.ssm.model.User">
    insert into user_t
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <if test="id != null">
        id,
      </if>
      <if test="userName != null">
        user_name,
      </if>
      <if test="password != null">
        password,
      </if>
      <if test="age != null">
        age,
      </if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
      <if test="id != null">
        #{id,jdbcType=INTEGER},
      </if>
      <if test="userName != null">
        #{userName,jdbcType=VARCHAR},
      </if>
      <if test="password != null">
        #{password,jdbcType=VARCHAR},
      </if>
      <if test="age != null">
        #{age,jdbcType=INTEGER},
      </if>
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="com.ssm.model.User">
    update user_t
    <set>
      <if test="userName != null">
        user_name = #{userName,jdbcType=VARCHAR},
      </if>
      <if test="password != null">
        password = #{password,jdbcType=VARCHAR},
      </if>
      <if test="age != null">
        age = #{age,jdbcType=INTEGER},
      </if>
    </set>
    where id = #{id,jdbcType=INTEGER}
  </update>
  <update id="updateByPrimaryKey" parameterType="com.ssm.model.User">
    update user_t
    set user_name = #{userName,jdbcType=VARCHAR},
      password = #{password,jdbcType=VARCHAR},
      age = #{age,jdbcType=INTEGER}
    where id = #{id,jdbcType=INTEGER}
  </update>
</mapper>

```

### 5、src/main/java下java代码
#### 5.1 service层新建UserService.java (接口)
```
  
package com.ssm.service;

import com.ssm.model.User;

public interface UserService {
	public int insert(User user);

	/**
	* @Description: 根据id获取user
	* @param @param id
	* @param @return    参数
	* @return User    返回类型
	*/
	public User getUser(int id);
}

```
#### 5.2 service.impl层新建UserServiceImpl.java (UserService.java接口的实现)
```
package com.ssm.service.impl;

import com.ssm.dao.UserMapper;
import com.ssm.model.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public int insert(User user) {
		return usermapper.insert(user);
	}

	@Override
	public User getUser(int id) {
		return usermapper.selectByPrimaryKey(id);
	}
}

```
#### 5.3 Controller层

##### 新建UserController.java

```
package com.ssm.controller;

import javax.annotation.Resource;

import com.ssm.model.User;
import com.ssm.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 用户页
     *
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userManager(Model model) {
        int id = 1;
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        LOG.info(user.toString());
        return "user/showUser";
    }
}
```
此处，也可以采用 @Resource ,用@Resource后就不必使用@Autowired。如下：

```
@Resource(name="userService")
private UserService userService;
```

最好是将@Resource放在setter方法上，因为这样更符合面向对象的思想，通过set、get去操作属性，而不是直接去操作属性。如下：

```
@Resource(name="userService")
public void UserService(UserService userService) {
	this.userService = userService;
}
```
在userController中添加如下两个方法：

testAjax是模拟ajax请求，testPOJO是直接将POJO类转为json
```
/**
* @Title: testAjax
* @Description: post请求（测试用）
* @param @param model
* @param @return    参数
* @return String    返回类型
* @throws
*/
@RequestMapping(value = "/testAjax", method = RequestMethod.POST)
@ResponseBody
public String testAjax(Model model) {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("id","数据拿到了");
	
	return jsonObject.toString();
}
/**
* @Title: testPOJO
* @Description: 
* @param @param model
* @param @return    参数
* @return String    返回类型
* @throws
*/
@RequestMapping(value = "/testPOJO", method = RequestMethod.POST)
@ResponseBody
public User testPOJO(Model model) {
    int id = 1;
    User user = userService.getUser(id);  
    return user;
}
```

此时，访问 http://localhost:8080/ssm_20170114/user/user 即可正常访问了。框架结构如下（IDE由Eclipse转为idea）：

![maven + spring + spring MVC + mybatis项目项目结构图](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-03-28.png)