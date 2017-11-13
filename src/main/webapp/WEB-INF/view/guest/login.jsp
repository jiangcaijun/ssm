<jsp:include page="../common/global.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div>
    <p>
        <span>正确的用户名和密码</span>
    </p>
    <p>
        <span>用户名</span><input type="text" name="username" value="admin" class="correctUsername"/>
    </p>
    <p>
        <span>密码</span><input type="text" name="password" value="admin" class="correctPassword"/>
    <p>
    <p>
        <input type="button" name="loginpost" value="登录" class="correctLoginpost"/>
    <p>
</div>
<div>
    <p>
        <span>错误的用户名和密码</span>
    </p>
    <p>
        <span>用户名</span><input type="text" name="username" value="admin" class="errorUsername"/>
    </p>
    <p>
        <span>密码</span><input type="text" name="password" value="admin2" class="errorPassword"/>
    <p>
    <p>
        <input type="button" name="password" value="登录" class="errorLoginpost"/>
    <p>
</div>
<div>
    <span> 登录请求返回信息：</span>
    <span class="returnMsg" style="color: red"></span>
</div>
<div>
    <span> 登录后才可进入的个人信息页面：</span>
    <a href="<%=request.getContextPath() %>/manage/user/user">个人信息</a>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/common/jquery-1.12.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        //按钮单击时执行
        $(".correctLoginpost").click(function () {
            $.ajax({
                type: "POST",
                url: $ctx + "/guest/loginPost",
                data: {
                    username: $(".correctUsername").val(),
                    password: $(".correctPassword").val()
                },
                success: function (data) {
                    $(".returnMsg").html(data.msg);
                }
            });
        });
        //按钮单击时执行
        $(".errorLoginpost").click(function () {
            $.ajax({
                type: "POST",
                url: $ctx + "/guest/loginPost",
                data: {
                    username: $(".errorUsername").val(),
                    password: $(".errorPassword").val()
                },
                success: function (data) {
                    $(".returnMsg").html(data.msg);
                }
            });
        });
    });
</script>
</html>
