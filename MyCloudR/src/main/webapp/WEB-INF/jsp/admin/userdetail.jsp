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
    <title>User Detail</title>
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
                        <a href="/logout" class="text_small">
                            <img src="../img/logo.jpg"
                                 class="user-image" alt="User Image"/>
                            <span class="hidden-xs">Log out</span>
                        </a>

                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <aside class="main-sidebar aside-bg aside-text">
        <section class="sidebar">
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
                    <a href="/admin/queryad" class="sidebar-hover">
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
                View User
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
                    <div class="box box-primary non-padding">
                        <div class="box-header text_big border_1">
                            <i class="fa fa-edit"></i>

                            <h3 class="box-title">Base Info</h3>
                        </div>

                        <div class="box-body text_small border_2">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">ID</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control non-padding border_3 text_small" id="id" autocomplete="off"
                                               value="${suser.id}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">email</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control non-padding text_small border_3" id="email" autocomplete="off"
                                               value="${suser.email}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">name</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control non-padding text_small border_3" id="name" autocomplete="off"
                                               value="${suser.name}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">QQ</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control non-padding border_3 text_small" id="qq" autocomplete="off"
                                               value="${suser.qq}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">invitationid</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control text_small non-padding border_3" id="invitationid" autocomplete="off"
                                               value="${suser.invitationid}">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="box box-primary non-padding"
                    >
                        <div class="box-header text_big border_1">
                            <i class="fa fa-edit"></i>

                            <h3 class="box-title">Other Info</h3>
                        </div>

                        <div class="box-body border_2 text_small">
                            <div class="form-horizontal">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">surplus</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control text_small non-padding border_3" id="surplus" autocomplete="off"
                                               value="${suser.surplus}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">points</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control text_small non-padding border_3" id="points" autocomplete="off"
                                               value="${suser.points}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label">filecount</label>

                                    <div class="col-sm-9">
                                        <input type="text" class="form-control non-padding text_small border_3" id="filecount" autocomplete="off"
                                               value="${suser.filecount}">
                                    </div>
                                    <br/><br/><br/>
                                    <div class="box-footer non-padding btn_align">
                                        <button id="edit" class="btn ladda-button btn_1" data-style="zoom-in">
                                            <span class="ladda-label text_small">&nbsp;&nbsp;&nbsp;Edit&nbsp;&nbsp;&nbsp;</span>
                                        </button>
                                        &nbsp;&nbsp; &nbsp;&nbsp;
                                        <button id="delete" class="btn ladda-button btn_1" data-style="zoom-in">
                                            <span class="ladda-label text_small">Delete</span>
                                        </button>
                                    </div>
                                </div>


                            </div>
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
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript" src="js/mycloud.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    var id = ${suser.id};
    var surplus = ${suser.surplus};
    var points = ${suser.points};
    var filecount = ${suser.filecount};
    var invitationid = ${suser.invitationid};
    var email = ${suser.email};


    $(document).on("click", "#edit", function (e) {
        var la = Ladda.create(document.querySelector("#edit"));

        la.start();
        $("#delete").attr("disabled", "disabled");
        editUser(la);
    });

    $(document).on("click", "#delete", function (e) {
        if (window.confirm('Confirm deletion？')) {
            var la = Ladda.create(document.querySelector("#delete"));

            la.start();
            $("#edit").attr("disabled", "disabled");
            deleteUser(la);
        }
    });

    $(document).on("blur", "#surplus", function (e) {
        $("#surplus").val(surplus);
    });

    $(document).on("blur", "#points", function (e) {
        $("#points").val(points);
    });

    $(document).on("blur", "#filecount", function (e) {
        $("#filecount").val(filecount);
    });

    $(document).on("blur", "#invitationid", function (e) {
        $("#invitationid").val(invitationid);
    });

    $(document).on("blur", "#id", function (e) {
        $("#id").val(id);
    });

    $(document).on("blur", "#email", function (e) {
        $("#email").val(email);
    });
</script>
</body>
</html>
