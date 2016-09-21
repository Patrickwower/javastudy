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

    <style>

        *{ margin: 0px; padding: 0px; }
        div,ul,li,h1,h2,h3,h4,h5,h6,p,table,tr,td,header,footer,article,section,aside { margin: 0px; padding: 0px; list-style: none; }
        #mainframe { max-width: 768px; }
        p { padding: 0px 15px; margin: 0 auto; color: #3c3c3c; font-size: 17px; line-height: 24px; word-wrap: break-word;}
        img { height: auto; width: 100%; }

    </style>
</head>
<body>
<div id="wholeframe">
${article.content}
</div>
</body>
</html>