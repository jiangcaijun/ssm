# ssm
Maven搭建SpringMVC+Spring+MyBatis框架，将陆续集成新功能

---
## 2017-01-14
- **Maven工程创建**
    * Eclipse (版本：4.6.2)，JDK 1.7
    * 创建过程,可参考：Maven3路程（三）用Maven创建第一个web项目(1) - leiOOlei - 博客园（http://www.cnblogs.com/leiOOlei/p/3361633.html）
- **将该项目提交至gitbub**
    * 提交过程,可参考Eclipse提交项目到github ksfzhaohui的个人页面（https://my.oschina.net/OutOfMemory/blog/294133）
- **此时，项目结构如下图**
![](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-16_104235.png)
---

## 2017-01-17

- **搭建基础工程目录**
    * 在src/main/java文件夹中，新建包（如下）
      * com.ssm.model(存放javabean类),
      * com.ssm.dao（存放spring与mybatis连接接口）,
      * com.ssm.service（存放service接口类）,
      * com.ssm.service.impl（存放service接口的实现类），
      * com.ssm.controller（存放控制层controller）
    * 在src/main/resource文件夹中，新建包conf（存放配置文件），
    * 在src/test/java文件夹中，新建包com.ssm.test(存放测试文件)

- **MAVEN引入jar包**
	* 当前为最基础的jar包，将其引入pom.xml文件中，附上代码，如下：
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
	<build>
		<finalName>ssm_20170114</finalName>
	</build>
</project>
```
	* 此时，project中原先报错的index.jsp页面，错误消失。这是因为添加了servlet-api，为project添加了Servlet依赖。

- **配置conf下的配置文件**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:p="http://www.springframework.org/schema/p" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="
</beans>
```
- **修改web.xml**

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
</web-app>
```

- **新建jsp文件**
	* 在WEB-INF文件夹下新增 view/hello.jsp,如下：
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

- **新建controller文件**
	* com.ssm.controller包下新增 IndexController.java,如下：

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

- **运行项目**
  * 当前结构如下图所示，基本骨架已经出来了。如下图：
  
![](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-18_004214.png)
 * 可用tomcat或jetty运行该项目，输入url：http://127.0.0.1:8080/ssm_20170114/hello，可见下图，证明spring框架搭建成功。
 
![](https://raw.githubusercontent.com/jiangcaijun/pictureAsset/HEAD/src/ssm_20170114/2017-01-18_004028.png)
