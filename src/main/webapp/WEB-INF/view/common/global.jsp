<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctx = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ctx; %>
<html>
<head>
    <title>我是title(全局性的)</title>
</head>
<body>
<input type=hidden class="ctx4Global" value=<%=ctx%> />

<input type=hidden class="basePath4Global" value=<%= basePath %> />

</body>
<script type="text/javascript">
    var $ctx='<%=request.getContextPath()%>';
</script>

</html>
