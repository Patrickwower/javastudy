<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登月舱</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0 maximum-scale=1.0,user-scalable=no"/>
    <meta charset="utf-8"/>
    <link href="<%=path %>/css/article_h5.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wholeframe">
${article.content}
</div>
</body>
</html>