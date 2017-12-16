<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/12/14
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>My Files</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css/ionicons.min.css" rel="stylesheet">
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
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
                    <!-- User Account: style can be found in dropdown.less -->
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
                Query Files
            </h1>
        </section>
        <section class="content">
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
                            <i class="fa fa-edit"></i>

                            <h3 class="box-title">Base Info</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">filename</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="filename" autocomplete="off"
                                               value="${file.filename}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">type</label>

                                    <div class="col-sm-9">
                                        <select class="form-control" id="filetype">
                                            <option>Books</option>
                                            <option>Courseware</option>
                                            <option>Pictures</option>
                                            <option>Other</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">tag</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="filetag" autocomplete="off"
                                               value="${file.tag}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">size</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="filesize" autocomplete="off"
                                               value="${file.size}kb">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-edit"></i>

                            <h3 class="box-title">Other Info</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">downloads</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="downloads" autocomplete="off"
                                               value="${file.downloads}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">summary</label>

                                    <div class="col-sm-9">
                                                            <textarea type="text" class="form-control" id="filesummary"
                                                                      autocomplete="off"
                                                                      style="height:76px;">${file.summary}</textarea>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="box-footer">
                            <button id="edit" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">Edit</span>
                            </button>
                            <button id="delete" class="btn btn-primary ladda-button" data-style="zoom-in">
                                <span class="ladda-label">Delete</span>
                            </button>
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
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript" src="js/mycloud.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    var id = ${file.id}
    var downloads = ${file.downloads};
    var size = "${file.size}kb";

    $(document).ready(function () {
        $("#filetype").val("${filetype}");
    });

    $(document).on("click", "#edit", function (e) {
        var la = Ladda.create(document.querySelector("#edit"));

        la.start();
        $("#delete").attr("disabled", "disabled");
        editFile(la);
    });

    $(document).on("click", "#delete", function (e) {
        if (window.confirm('Confirm deletion？')) {
            var la = Ladda.create(document.querySelector("#delete"));

            la.start();
            $("#edit").attr("disabled", "disabled");
            deleteFile(la);
        }
    });

    $(document).on("blur", "#downloads", function (e) {
        $("#downloads").val(downloads);
    });

    $(document).on("blur", "#filesize", function (e) {
        $("#filesize").val(size);
    });
</script>
</body>
</html>
