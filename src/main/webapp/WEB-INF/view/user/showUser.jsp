<jsp:include page="../common/global.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div>
    <p>我是登录后的个人信息页面</p><a class="logout" href="#" style="color: red">退出登录</a>
    <p>我是user请求过来的ajax:</p>
    <p>用户名：<span class="ajaxPOJO4userName"></span></p>
    <p>密码：<span class="ajaxPOJO4passWord"></span></p>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/common/jquery-1.12.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/common/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/user/user.js"></script>

</html>
