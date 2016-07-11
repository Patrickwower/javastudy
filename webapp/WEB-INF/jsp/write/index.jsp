<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登月舱</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link href="<%=path%>/write/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/write/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/write/css/templatemo-style.css" rel="stylesheet">

    <!--引入wangEditor.css-->
    <link rel="stylesheet" type="text/css" href="<%=path%>/write/css/wangEditor.min.css">
</head>
<body>
<!-- Left column -->
<div class="templatemo-flex-row">
    <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
            <div class="square"></div>
            <h1>登月舱</h1>
        </header>
        <div class="profile-photo-container">
            <img src="<%=path%>/write/images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">
            <div class="profile-photo-overlay"></div>
        </div>
        <!-- Search box -->
        <form class="templatemo-search-form" role="search">
            <div class="input-group">

            </div>
        </form>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">
            <ul>
                <li><a href="<%=path%>/community/write/index" class="active"><i class="fa fa-home fa-fw"></i>首页</a></li>
                <li><a href="<%=path%>/community/write/toWrite"><i class="fa fa-bar-chart fa-fw"></i>写文章</a></li>
                <li><a href="login.html"><i class="fa fa-eject fa-fw"></i>退出</a></li>
            </ul>
        </nav>
    </div>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <nav class="templatemo-top-nav col-lg-12 col-md-12">
                    <ul class="text-uppercase">
                        <li><a href="<%=path%>/community/write/index" class="active">首页</a></li>
                        <li><a href="<%=path%>/community/write/toWrite">写文章</a></li>
                        <li><a href="login.html">退出</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
                <table class="table table-striped table-bordered templatemo-user-table">
                    <thead>
                    <tr>
                        <td><a href="" class="white-text templatemo-sort-by"># <span class="caret"></span></a></td>
                        <td><a href="" class="white-text templatemo-sort-by">First Name <span class="caret"></span></a></td>
                        <td><a href="" class="white-text templatemo-sort-by">Last Name <span class="caret"></span></a></td>
                        <td><a href="" class="white-text templatemo-sort-by">User Name <span class="caret"></span></a></td>
                        <td><a href="" class="white-text templatemo-sort-by">Topic <span class="caret"></span></a></td>
                        <td>Edit</td>
                        <td>Action</td>
                        <td>Delete</td>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${articles}" var="article" varStatus="index">

                        <tr>
                            <td>${index.index+1}.</td>
                            <td>${article.title}</td>
                            <td>${article.ctime}</td>
                            <td>${article.member.memberInfo.nickname}</td>
                            <td>
                            <c:forEach items="${article.tags}" var="tag">
                                #${tag.name}#
                            </c:forEach>
                            </td>
                            <td><a href="" class="templatemo-edit-btn">Edit</a></td>
                            <td><a href="<%=path%>/community/article/detail?articleId=${article.id}" target="_blank" class="templatemo-link">查看</a></td>
                            <td><a href="" class="templatemo-link">Delete</a></td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>

            <div class="pagination-wrap">
                <ul class="pagination">
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li class="active"><a href="#">3 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true"><i class="fa fa-play"></i></span>
                        </a>
                    </li>
                </ul>
            </div>

        </div>
        <footer class="text-right">
            <p>Copyright &copy; 2084 Company Name
                | Designed by <a href="http://www.templatemo.com" target="_parent">templatemo</a></p>
        </footer>
    </div>
</div>
</div>

<!-- JS -->
<script src="<%=path%>/write/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
<script src="<%=path%>/write/js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->

<script type="text/javascript" src="<%=path%>/write/js/templatemo-script.js"></script>      <!-- Templatemo Script -->


</body>
</html>
