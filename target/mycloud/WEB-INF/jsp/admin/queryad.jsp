<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/12/14
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="hyh.entity.Advertisement" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>Query Ad</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/ionicons.min.css" rel="stylesheet">
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark" type="img/x-icon"/>
    <link href="img/logo.ico" rel="shortcut icon">

</head>
<body class="skin-blue">
<div class="wrapper aside-bg">
    <header class="main-header">
        <a class="logo text_big">ITcat</a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle text_small" data-toggle="offcanvas" role="button" id="menu">
                <span class="sr-only">Toggle navigation</span>
                Menu
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="/logout">
                            <img src="../img/logo.jpg"
                                 class="user-image" alt="User Image"/>
                            <span class="hidden-xs text_small">Log out</span>
                        </a>

                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar aside-bg aside-text">
        <section class="sidebar text_small">
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="../img/logo.jpg" class="img-circle"
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
                    <a href="/admin" class="sidebar-hover">
                        <i class="fa fa-desktop"></i> <span>Website Info</span>
                    </a>
                </li>

                <li>
                    <a href="/admin/setad" class="sidebar-hover">
                        <i class="fa fa-money"></i> <span>Advertisements</span>
                    </a>
                </li>

                <li>
                    <a href="/admin/queryusers" class="sidebar-hover">
                        <i class="fa fa-user"></i> <span>Queryusers</span>
                    </a>
                </li>

                <li>
                    <a href="/admin/queryfiles" class="sidebar-hover">
                        <i class="fa fa-folder"></i> <span>QueryFiles</span>
                    </a>
                </li>

                <li>
                    <a href="/" class="sidebar-hover">
                        <i class="fa fa-home"></i> <span>Back To Homepage</span>
                    </a>
                </li>
            </ul>
        </section>
    </aside>


    <div class="content-wrapper content-bg">
        <section class="content-header">
            <h1 class="text_bigger">
                Query Ad
            </h1>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <button id="addteacher" class="btn btn_1 text_small"
                            onclick="javascrtpt:window.location.href='/admin/setad'">Add ad
                    </button>
                    <br><br>
                    <div class="box non-padding text_small">
                        <div class="box-body table-responsive no-padding">
                            <table class="table non-padding">
                                <tr>
                                    <th class="border_1 text_small">href</th>
                                    <th class="border_1 text_small">deadline</th>
                                </tr>
                                <%
                                    List<Advertisement> ads = (List<Advertisement>) request.getAttribute("ads");
                                    if (ads == null || ads.size() == 0) {
                                        out.println("<tr>");
                                        out.println("<th class=\"text_small border_1\">No ad now, just add some</th>");
                                        out.println("</tr>");
                                    } else {
                                        Advertisement ad;
                                        for (int i = 0, len = ads.size(); i < len; i++) {
                                            ad = ads.get(i);
                                            out.println("<tr class=\"text_small border_1\" onclick=\"javascrtpt:window.location.href='/admin/setad?id=" + ad.getId() + "'\">");
                                            out.println(ad);
                                            out.println("</tr>");
                                        }
                                    }
                                %>
                            </table>
                            <!--  <ul class="pagination">
                                <li id="pre"><a id="preurl">&laquo;</a>
                                </li>
                                <li class="active"><span>${page + 1}</span></li>
                                <li id="next"><a id="nexturl" >&raquo;</a></li>
                            </ul> -->
                        </div>
                    </div>
                </div>
            </div>

        </section>
    </div>
    <footer class="main-footer footer">
        <div align="center">

        </div>
        <div class="pull-right hidden-xs">
            Code is beautiful
        </div>
        <strong>Powered by <a href="javascript:void(0)">ITcathyh</a> </strong>
    </footer>
</div>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/mycloud.js"></script>
<script type="text/javascript">
</script>
</body>
</html>
