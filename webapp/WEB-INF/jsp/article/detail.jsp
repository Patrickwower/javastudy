<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <title>登月舱</title>
    <link rel="stylesheet" media="all" href="<%=path%>/write/css/article_base_detail.css">
    <link rel="stylesheet" media="all" href="<%=path%>/write/css/article_detail.css">

</head>
<body class="post output zh cn mac reader-day-mode reader-font2 " data-locale="zh-CN">
<div id="show-note-container">

    <div class="post-bg" id="flag">

        <div class="container">
            <div class="related-avatar-group activities"></div>
            <div class="article" style="padding:10px 40px 10px">
                <div class="show-content">
${article.content}
                </div>
            </div>
        </div>
    </div>
</body>

</html>