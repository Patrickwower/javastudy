<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
    <meta name="description" content="">¬
    <meta name="author" content="templatemo">
    <link href="<%=path%>/write/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/write/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/write/css/templatemo-style.css" rel="stylesheet">

    <!--引入wangEditor.css-->
    <link rel="stylesheet" type="text/css" href="<%=path%>/write/css/wangEditor.min.css">
</head>
<body>

<div id="savetip" class="alert alert-success" style="display:none;">已保存</div>

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
                <li><a href="<%=path%>/community/write/index"><i class="fa fa-home fa-fw"></i>首页</a></li>
                <li><a href="<%=path%>/community/write/toWrite" class="active"><i class="fa fa-bar-chart fa-fw"></i>写文章</a></li>
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
                        <li><a href="<%=path%>/community/write/index">首页</a></li>
                        <li><a href="<%=path%>/community/write/toWrite" class="active">写文章</a></li>
                        <li><a href="login.html">退出</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-flex-row flex-content-row templatemo-overflow-hidden"> <!-- overflow hidden for iPad mini landscape view-->
                <div class="col-1 templatemo-overflow-hidden">
                    <div class="templatemo-content-widget white-bg templatemo-overflow-hidden">
                        <i class="fa fa-times"></i>
                        <input type="button" value="baocun" onclick="saveArticle()"/>
                        <div class="templatemo-flex-row flex-content-row">
                            <div class="col-1 col-lg-6 col-md-12">
                                <h2 class="text-center">编辑器</h2>
                                <div id="div1" style="height:400px;max-height:500px;"><p>请输入内容...</p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
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



<!--引入jquery和wangEditor.js-->   <!--注意：javascript必须放在body最后，否则可能会出现问题-->
<script type="text/javascript" src="<%=path%>/write/js/wangEditor.min.js"></script>

<!--这里引用jquery和wangEditor.js-->
<script type="text/javascript">
    var editor = new wangEditor('div1');

    // 自定义菜单
    editor.config.menus = [

        // '|' 是菜单组的分割线
        'bold',
        'underline',
        'italic',
        'strikethrough',
        'eraser',
        '|',
        'quote',
        'head',
        'unorderlist',
        'orderlist',
        'alignleft',
        'aligncenter',
        'alignright',
        '|',
        'link',
        'unlink',
        'img',
        'video',
        '|',
        'undo',
        'redo',
        'fullscreen',
        '|',
        'source',
    ];

    if (window.console) {
        console.log(editor);
    }

    editor.config.uploadImgUrl = '<%=path%>/img/uploadForArticle';
    editor.config.uploadImgFileName = 'file'


        // 配置自定义参数（举例）
    editor.config.uploadParams = {
        type: 'image',
        usefor: 'article'
    };

    // 设置 headers（举例）
    editor.config.uploadHeaders = {
        'memberId' : '8a2a67a25505963401550a65958a000f',
        'type': 'image',
        'usefor': 'article'
    };


    editor.create();
</script>


<script type="text/javascript">

    function saveArticle(){

        var longText = editor.$txt.html();

        var aj = $.ajax( {
            url:'<%=path %>/community/write/write',
            data:{content:longText},
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                $("#savetip").fadeIn("slow");
                $("#savetip").fadeOut("slow");
            },
            error : function() {
            }
        });


    }


</script>


</body>
</html>