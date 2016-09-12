<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登月舱</title>
</head>
<body>
</body>

<script type="text/javascript">
    window.open("http://app.dengyuecang.com/h5.html?articleId=<%=request.getParameter("articleId")%>","_self");
</script>

</html>