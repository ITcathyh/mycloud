<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/11/24
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <title>My cloud</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/mycloud.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark" type="img/x-icon"/>
    <link href="img/logo.ico" rel="shortcut icon">
    <style>
        .alert-warning {
            background-color: #f39c12;
        }

        .alert-success {
            background-color: #00a65a;
        }
    </style>
</head>
<body id="homebody">
<div id="wapper">
    <div id="main-content">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">ITcat</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/">Homepage</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">Classification
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Books</a></li>
                                <li><a href="#">Courseware</a></li>
                                <li><a href="#">Pictures</a></li>
                                <li><a href="#">Other</a></li>
                            </ul>
                        </li>
                    </ul>
                    <div class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="" id="novsearchtext">
                        </div>
                        <button class="btn btn-primary" id="novsearch">Search</button>
                        <button id="uploadbutton" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                            Upload
                        </button>
                    </div>

                    <ul class="nav navbar-nav navbar-right" id="logindiv">
                        <li><a href="/login">Log In</a></li>
                        <li><a href="/signup">Sign Up</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right" id="userdiv">
                        <li><a href="/user"><strong>${username}</strong></a></li>
                        <li><a href="/logout">Log out</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-full">
            <div class="row">

                <div class="col-lg-12 text-center v-center">
                    <h1>My Cloud</h1>
                    <p class="lead">Share with the world</p>
                    <br/>
                    <p id="yan">
                        <a href="#" class="pushcontent" id="pushcontent">Here is the push content</a>
                    </p>
                    <div class="col-lg-12" style="margin-top:10px">
                        <div class="input-group input-group-lg col-sm-offset-4 col-sm-4">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-default
                        dropdown-toggle" data-toggle="dropdown" id="showselect">All<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a id="books" href="/search?type=Books">Books</a>
                                    </li>
                                    <li>
                                        <a id="courseware" href="/search?type=Courseware">Courseware</a>
                                    </li>
                                    <li>
                                        <a id="pictures" href="/search?type=Pictures">Pictures</a>
                                    </li>
                                    <li>
                                        <a id="other" href="/search?type=Other">Other</a>
                                    </li>
                                </ul>
                            </div>

                            <input type="text" class="form-control" id="searchtext" placeholder="search something"/>
                            <span class="input-group-btn">
						                <span class="input-group-btn"><button class="btn btn-lg btn-primary"
                                                                              id="mainsearch">search</button></span>
                                </span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row" style="font-size:36px">
                    <div id="hotresource" style="text-align:center;margin-top:25px;">
                        <p>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Thinkint in Java
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Effective Java
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                C Primer Plus
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                C++ Primer Plus
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Assembly Language
                            </a>
                        </p>
                        <p>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Data Structures
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Alogirthm
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Computer Networking
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Digital Design
                            </a>
                            <a class="btn btn-primary btn-sm" role="button" href="#">
                                Refactoring To Patterns
                            </a>
                        </p>
                    </div>


                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" style="display:none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Upload new resource
                    </h4>
                </div>
                <div id="msgerror" class="alert alert-warning alert-dismissable" style="display:none">
                    <h4><i class="icon fa fa-warning" id="errormsg"></i>Wrong</h4>

                    <p id="msg-error-p"></p>
                </div>
                <div id="msgsuccess" class="alert alert-success alert-dismissable" style="display:none">
                    <h4><i class="icon fa fa-info"></i>Success</h4>
                    <p id="ss-msg-success-p"></p>
                </div>
                <form id="myform" enctype="multipart/form-data">
                    <input type="hidden" name="token" id="token" value="${sessionScope.csrftoken}"/>

                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Name</label>
                            <input type="text" class="form-control" id="filename" name="name">
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Summary</label>
                            <textarea type="text" class="form-control" id="filesummary" name="summary"></textarea>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Type</label>
                            <select class="form-control" id="filetype" name="type">
                                <option>Books</option>
                                <option>Courseware</option>
                                <option>Pictures</option>
                                <option>Other</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Tag</label>
                            <input type="text" class="form-control" id="filetag" name="tag">
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">File</label>
                            <input type="file" class="file" name="file" id="file">
                        </div>

                    </div>
                </form>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary  ladda-button" id="submitupload" data-style="zoom-in">
                        <span class="ladda-label">Submit</span>
                    </button>
                </div>
            </div>
        </div>
    </div>


    <div id="footer" class="container navbar navbar-fixed-bottom">
        <div class="container">
            <p class="text-muted credit">Pwoered by <a
                    href="https://www.github.com/itcathyh">Itcathyh</a></p>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<script type="text/javascript" src="js/fileinput.min.js"></script>
<script type="text/javascript" src="js/mycloud.js"></script>
<script>
    var username = "${username}";
    var la = Ladda.create(document.querySelector("#submitupload"));

    $(document).ready(function () {
        if (username == "0") {
            $("#uploadbutton").hide();
            $("#userdiv").hide();
        } else {
            $("#logindiv").hide();
        }

        getRecommend();
    });

    $("#all").click(function () {
        $("#showselect").text("All");
    });

    $("#books").click(function () {
        $("#showselect").text("Books");
    });

    $("#courseware").click(function () {
        $("#showselect").text("Courseware");
    });

    $("#pictures").click(function () {
        $("#showselect").text("Pictures");
    });

    $("#other").click(function () {
        $("#showselect").text("Other");
    });

    $("#mainsearch").click(function () {
        location.href = "/search?text=" + $("#searchtext").val() + "&type=" + $("#showselect").text();
    });

    $("#novsearch").click(function () {
        location.href = "/search?text=" + $("#novsearchtext").val() + "&type=All";
    });
</script>
</body>
</html>