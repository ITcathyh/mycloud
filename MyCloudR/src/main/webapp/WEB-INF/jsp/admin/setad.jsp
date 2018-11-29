<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2018/4/6
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>Set Ad</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../css/ionicons.min.css" rel="stylesheet">
    <link href="../css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
    <link href="../img/logo.ico" rel="bookmark" type="img/x-icon"/>
    <link href="../img/logo.ico" rel="shortcut icon">
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
                Set ad
            </h1>
        </section>
        <hr class="line">
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

            <form id="adform" enctype="multipart/form-data"  action="javascript:void(0)">
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
                                        <label class="col-sm-3 control-label">href</label>

                                        <div class="col-sm-9">
                                            <input type="text" class="form-control non-padding border_3 text_small" name="href" id="adhref"
                                                   autocomplete="off">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">deadline</label>

                                        <div class="col-sm-9">
                                            <div class="input-group date form_datetime"
                                                 data-date-format="yyyy-mm-dd HH:ii:mm"
                                                 data-link-field="dtp_input1">
                                                <input class="form-control non-padding border_3 text_small" size="16" type="text" value="" readonly
                                                       id="addeadline" name="deadline">
                                                <span class="input-group-addon non-padding text_small"><span
                                                        class="glyphicon glyphicon-remove"></span></span>
                                                <span class="input-group-addon non-padding text_small"><span
                                                        class="glyphicon glyphicon-th"></span></span>
                                            </div>
                                            <input type="hidden" value=""/><br/>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="box box-primary non-padding">
                            <div class="box-header text_big border_1">
                                <i class="fa fa-edit"></i>

                                <h3 class="box-title">Image Info</h3>
                            </div>

                            <div class="box-body border_2 text_small">
                                <div class="form-horizontal">

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Image</label>

                                        <div class="col-sm-9">
                                            <input type="file" name="file" id="adfile"> </span>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="box-footer non-padding btn_align">
                                <button id="setad" class="btn ladda-button btn_1"
                                        data-style="zoom-in">
                                    <span class="ladda-label text_small">Set ad</span>
                                </button>
                                &nbsp;&nbsp; &nbsp;&nbsp;
                                <button id="delete" class="btn ladda-button btn_1"
                                        data-style="zoom-in">
                                    <span class="ladda-label text_small">Delete</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
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

<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/spin.min.js"></script>
<script type="text/javascript" src="../js/ladda.min.js"></script>
<script type="text/javascript" src="../js/app.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.min.js"></script>
<script type="text/javascript" src="../js/fileinput.min.js"></script>
<script type="text/javascript" src="../js/mycloud.js"></script>
<script type="text/javascript">
    var id = "${id}";
    var href = "${href}";
    var deadline = "${deadline}";
    var la = Ladda.create(document.querySelector("#setad"));

    $(document).ready(function () {
        if (href.length == 0) {
            $("#delete").hide();
        } else {
            $("#adhref").val(href);
            $("#addeadline").val(deadline);
        }
    });

    $("#setad").click(function () {
        checkSetAd();
    });

    $("#delete").click(function () {
        if (window.confirm('Confirm deletion？')) {
            var la = Ladda.create(document.querySelector("#delete"));

            la.start();
            $("#setad").attr("disabled", "disabled");
            deleteAd(la);
        }
    });
</script>
</body>
</html>

