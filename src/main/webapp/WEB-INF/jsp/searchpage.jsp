<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/11/24
  Time: 10:19
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
    <title>My cloud - Search Result</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/mycloud.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark"  type="img/x-icon"  />
    <link href="img/logo.ico" rel="shortcut icon" >
</head>
<body>
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
                        <li><a href="./homepage.html">Homepage</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-search"></i>Classification
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
                            <input type="text" class="form-control" placeholder="">
                        </div>
                        <button class="btn btn-primary">Search</button>
                        <button id="uploadbutton" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                            Upload
                        </button>
                    </div>

                    <ul class="nav navbar-nav navbar-right" id="logindiv">
                        <li><a href="#">Log In</a></li>
                        <li><a href="#">Sgin Up</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right" id="userdiv">
                        <li><a><strong>ITcathyh</strong></a></li>
                        <li><a href="#">Log out</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="col-sm-12 aw-main-content" style="background-color:rgba(248,248,248,0.7);">
                <div class="mod-head">
                    <div class="tabbable">
                        <ul class="nav nav-tabs aw-nav-tabs active" id="list_nav">
                            <h2 class="hidden-xs">
                                <p>Search - <span id="aw-search-type">Effective Java</span></p>
                            </h2>
                        </ul>
                    </div>
                </div>
                <div class="mod-body">

                    <div class="tab-content">
                        <div class="tab-pane active" style="word-break:break-all;">
                            <div style="display: flex;">
                                <p id="pageinfo" class="pxc">Search result : 1 item </p>
                            </div>

                            <div id="myCarousel" class="carousel slide" style="height:150px;width:300px">
                                <div class="carousel-inner">
                                    <div class="active item"><a href="https://www.baidu.com"><img src="img/ad.bmp"
                                                                                                  height="150"
                                                                                                  width="300"/></a>
                                    </div>
                                    <div class="item"><a href="https://www.baidu.com"><img src="img/bg1.jpg"
                                                                                           height="150"
                                                                                           width="300"/></a></div>
                                </div>
                            </div>

                            <div id="searchresult">

                                <p class="aw-title">
                                </p>
                                <h1 style="font-size:20px;">
                                    <a href="#" class="b_title" target="_blank">Effective Java</a>
                                    <a rel="noreferrer" href="#" class="a_url" data-surl="1bpAmKCB"
                                       target="_blank"> <i class="glyphicon glyphicon-share-alt"></i>
                                    </a>
                                </h1>
                                <p></p>
                                <p class="content">
                                    A book about Java </p>
                                <p class="aw-text-color-green">
                                    Time：2017-11-23 | User：PigTom | Size：10M | Type：Book | Downloads：10 </p>
                            </div>

                            <div class="page-control" id="pageto" data-page="1">
                                <ul id="pagecreater_1511487547117_8191428779905634"
                                    class="pagination pull-right" onselectstart="return false;">
                                    <li class="pagecreater_prev pagecreater_disable" data-click="page"
                                        data-pg="0"><a>Pre</a></li>
                                    <li class="active" data-click="page" data-pg="0"><a>1</a></li>
                                    <li class="pagecreater_next pagecreater_disable" data-click="page"
                                        data-pg="0"><a>Next</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
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
            <form>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Name</label>
                        <input type="text" class="form-control" id="name">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Summary</label>
                        <textarea type="text" class="form-control" id="summary"></textarea>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">Type</label>
                        <select class="form-control" id="type">
                            <option>Books</option>
                            <option>Courseware</option>
                            <option>Pictures</option>
                            <option>Other</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">File</label>
                        <input type="file" class="file" name="file">
                    </div>

                </div>
            </form>

            <div class="modal-footer">
                </button>
                <button type="button" class="btn btn-primary" id="submitupload">
                    Submit
                </button>
            </div>
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
    var islogin = 1;

    $(document).ready(function () {
        if (islogin == 0) {
            $("#uploadbutton").hide();
            $("#userdiv").hide();
        } else {
            $("#logindiv").hide();
        }
    });

    $('#myCarousel').carousel({
        interval: 4000,
    });

</script>
</body>
</html>