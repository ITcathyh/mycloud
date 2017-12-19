<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/12/5
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>User Centry</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/ionicons.min.css" rel="stylesheet">
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark"  type="img/x-icon"  />
    <link href="img/logo.ico" rel="shortcut icon" >
</head>
<body class="skin-blue">
<div class="wrapper">

    <header class="main-header">
        <a class="logo">ITcat</a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id="menu">
                <span class="sr-only">Toggle navigation</span>
                Menu
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="/logout">
                            <img src="img/logo.jpg"
                                 class="user-image" alt="User Image"/>
                            <span class="hidden-xs">Log out</span>
                        </a>

                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar">
        <section class="sidebar">
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="img/logo.jpg" class="img-circle"
                         alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <p>Mycloud
                    </p>
                    <a href="#">Online</a>
                </div>
            </div>
            <ul class="sidebar-menu">
                <li>
                    <a href="/user">
                        <i class="fa fa-user"></i> <span>My Info</span>
                    </a>
                </li>

                <li>
                    <a href="/user/files">
                        <i class="fa fa-folder"></i> <span>My Files</span>
                    </a>
                </li>

                <li>
                    <a href="/">
                        <i class="fa fa-home"></i> <span>Back To Homepage</span>
                    </a>
                </li>
            </ul>
        </section>
    </aside>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                System management daemon
                <small>Hello ${user.name}</small>
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-sm-12">
                    <div id="msg-info" class="alert alert-info alert-dismissable">
                        <h4>
                            <i class="icon fa fa-info"></i>
                            Push content
                        </h4>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div id="msgerror" class="alert alert-warning alert-dismissable" style="display:none">
                        <h4><i class="icon fa fa-warning" id="errormsg"></i>Wrong</h4>

                        <p id="msg-error-p"></p>
                    </div>
                    <div id="msgsuccess" class="alert alert-success alert-dismissable" style="display:none">
                        <h4><i class="icon fa fa-info"></i>Success</h4>

                        <p id="ss-msg-success-p"></p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-bullhorn"></i>

                            <h3 class="box-title">Personal Info</h3>
                        </div>
                        <div class="box-body">
                            <dl class="dl-horizontal">
                                <dt>surplus：</dt>
                                <dd>
                                    ${user.surplus}kb
                                </dd>
                                <dt>points：</dt>
                                <dd>
                                    ${user.points}
                                </dd>
                                <dt>file count：</dt>
                                <dd>
                                    ${user.filecount}
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">

                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-inbox"></i>

                            <h3 class="box-title">Login Info</h3>
                        </div>
                        <div class="box-body">
                            <dl class="dl-horizontal">
                                <dt>last IP:</dt>
                                <dd>
                                    ${user.ip}
                                </dd>
                                <dt>last time</dt>
                                <dd>
                                    ${user.logintime}
                                </dd>
                            </dl>


                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
<footer class="main-footer">
    <div align="center">

    </div>
    <div class="pull-right hidden-xs">
        Code is beautiful
    </div>
    <strong>Powered by <a href="javascript:void(0)">ITcathyh</a> </strong>
</footer>
</div>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>
