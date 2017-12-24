<%@ page import="hyh.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/12/19
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>Query Users</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/ionicons.min.css" rel="stylesheet">
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark" type="img/x-icon"/>
    <link href="img/logo.ico" rel="shortcut icon">
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
                    <a href="/admin">
                        <i class="fa fa-desktop"></i> <span>Website Info</span>
                    </a>
                </li>

                <li>
                    <a href="/admin/queryad">
                        <i class="fa fa-money"></i> <span>Advertisements</span>
                    </a>
                </li>

                <li>
                    <a href="/admin/queryusers">
                        <i class="fa fa-user"></i> <span>Queryusers</span>
                    </a>
                </li>

                <li>
                    <a href="/admin/queryfiles">
                        <i class="fa fa-folder"></i> <span>QueryFiles</span>
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
                Query Users
            </h1>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="input-group" style="width: 350px;">
                        <input type="text" class="form-control" id="searchtext" placeholder="search something"
                               name="stuid"/>
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="search">Search</button>
                    </span>
                    </div>
                    <div class="box ">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tr>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>qq</th>
                                </tr>
                                <%
                                    List<User> users = (List<User>) request.getAttribute("users");

                                    if (users == null || users.size() == 0) {
                                        out.println("<tr>");
                                        out.println("<th>Sorry, no such user</th>");
                                        out.println("</tr>");
                                    } else {
                                        User user;

                                        for (int i = 0, len = users.size(); i < len; i++) {
                                            user = users.get(i);

                                            out.println("<tr onclick=\"javascrtpt:window.location.href='/admin/getuserdetail?userid=" + user.getId() + "'\">");
                                            out.println(user);
                                            out.println("</tr>");
                                        }
                                    }
                                %>
                            </table>
                            <ul class="pagination">
                                <li id="pre"><a id="preurl">&laquo;</a>
                                </li>
                                <li class="active"><span>${page + 1}</span></li>
                                <li id="next"><a id="nexturl">&raquo;</a></li>
                            </ul>
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
<script type="text/javascript" src="js/mycloud.js"></script>
<script type="text/javascript">
    var page = ${page};
    var nextdisabled = ${nextdisabled};

    $(document).ready(function () {
        if (nextdisabled == 1) {
            $("#next").addClass("disabled");
            $("#nexturl").attr("href", "javascript:void(0)");
        }

        if (page == 0) {
            $("#pre").addClass("disabled");
            $("#preurl").attr("href", "javascript:void(0)");
        }

        $("#searchtext").val("${key}");
    });

    $(document).on("click", "#search", function (e) {
        location.href = "/admin/queryusers?key=" + $("#searchtext").val();
    });

    $("#searchtext").keydown(function (event) {
        if (event.keyCode == 13) {
            location.href = "/admin/queryusers?key=" + $("#searchtext").val();
        }
    });

    $(document).on("click", "#next", function (e) {
        if (nextdisabled != 1){
            location.href = "/admin/queryusers?key=" + $("#searchtext").val() + "&page=${page + 1}";
        }
    });

    $(document).on("click", "#pre", function (e) {
        if (page > 0) {
            location.href = "/admin/queryusers?key=" + $("#searchtext").val() + "&page=${page - 1}";
        }
    });
</script>
</body>
</html>

