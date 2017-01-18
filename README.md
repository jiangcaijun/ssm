# ssm
Maven搭建SpringMVC+Spring+MyBatis框架，将陆续集成新功能
---
## 2017-01-14
1. **Maven工程创建**
    * Eclipse (版本：4.6.2)，JDK 1.7
    * 创建过程,可参考：Maven3路程（三）用Maven创建第一个web项目(1) - leiOOlei - 博客园（http://www.cnblogs.com/leiOOlei/p/3361633.html）
2. **将该项目提交至gitbub**
    * 提交过程,可参考Eclipse提交项目到github ksfzhaohui的个人页面（https://my.oschina.net/OutOfMemory/blog/294133）
3. **此时，项目结构如下图**
![](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-16_104235.png)
---
## 2017-01-17
1. **搭建基础工程目录**
    * 在src/main/java文件夹中，新建包（如下）
      * com.ssm.model(存放javabean类),
        com.ssm.dao（存放spring与mybatis连接接口）,
        com.ssm.service（存放service接口类）,
        com.ssm.service.impl（存放service接口的实现类），
        com.ssm.controller（存放控制层controller）
    * 在src/main/resource文件夹中，新建包conf（存放配置文件），
    * 在src/test/java文件夹中，新建包com.ssm.test(存放测试文件)
2. **MAVEN引入jar包**
    当前为最基础的jar包，将其引入pom.xml文件中，附上代码，如下：
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
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${slf4j.version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${slf4j.version}</version>
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
此时，project中原先报错的index.jsp页面，错误消失。这是因为添加了servlet-api，为project添加了Servlet依赖。
3. **配置conf下的配置文件**

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
4.   **修改web.xml**

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

5. 新建jsp文件
  *在WEB-INF文件夹下新增 view/hello.jsp,如下：
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

6. 新建controller文件
  *com.ssm.controller包下新增 IndexController.java,如下：

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

7. 运行项目
  * 当前结构如下图所示，基本骨架已经出来了。如下图：
![](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-18_004214.png)
 * 可用tomcat或jetty运行该项目，输入url：http://127.0.0.1:8080/ssm_20170114/hello，可见下图，证明spring框架搭建成功。
![](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-18_004028.png)
