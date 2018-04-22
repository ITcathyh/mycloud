<%@ page import="hyh.entity.UserFile" %>
<%@ page import="java.util.List" %>
<%@ page import="hyh.entity.Advertisement" %>
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
    <link href="image/logo.ico" rel="bookmark" type="img/x-icon"/>
    <link href="img/logo.ico" rel="shortcut icon">
</head>
<body>
<div id="wapper">
    <div id="main-content">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container containerNew">
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
                            <a class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-search"></i>Classification
                                <span class="caret"></span></a>
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
                        </li>
                    </ul>
                    <div class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" id="searchtext">
                        </div>
                        <button class="btn btn-primary" id="novsearch">Search</button>
                        <button id="uploadbutton" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                            Upload
                        </button>
                    </div>

                    <ul class="nav navbar-nav navbar-right" id="logindiv">
                        <li><a href="/login">Log In</a></li>
                        <li><a href="/signup">Sgin Up</a></li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right" id="userdiv">
                        <li><a href="/user"><strong>${username}</strong></a></li>
                        <li><a href="/logout">Log out</a></li>
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
                                <p>Search - <span id="aw-search-type">${text}</span></p>
                            </h2>
                        </ul>
                    </div>
                </div>
                <div class="mod-body">

                    <div class="tab-content">
                        <div class="tab-pane active" style="word-break:break-all;">
                            <div style="display: flex;">
                                <p id="pageinfo" class="pxc">Search result : ${cot} item </p>
                            </div>

                            <%
                                List<Advertisement> ads = (List<Advertisement>) request.getAttribute("ads");

                                if (ads != null && ads.size() != 0) {
                                    Advertisement ad = ads.get(0);

                                    out.println("<div id=\"myCarousel\" class=\"carousel slide\" style=\"height:150px;width:300px\">");
                                    out.println("<div class=\"carousel-inner\">");
                                    out.println("<div class=\"active item\"><a href=\"" + ad.getHref() + "\">");
                                    out.println("<img class=\"adimg\" src=\"" + ad.getImgpath() + "\" height=\"150\" width=\"300\"/></a></div>");

                                    for (int i = 1, len = ads.size(); i < len; i++) {
                                        ad = ads.get(i);

                                        out.println("<div class=\"item\"><a href=\"" + ad.getHref() + "\">");
                                        out.println("<img class=\"adimg\" src=\"" + ad.getImgpath() + "\" height=\"150\" width=\"300\"/></a></div>");
                                    }

                                    out.println("</div></div>");
                                }

                                ads = null;
                                List<UserFile> userfiles = (List<UserFile>) request.getAttribute("files");

                                if (userfiles == null || userfiles.size() == 0) {
                                    out.println("<h1 class=\"searchh1\">\n" +
                                            "<a class=\"b_title\">No such resource</a>\n" +
                                            "</h1>");
                                    out.println("<p class=\"content\">\n" +
                                            "Please search something new</p>");
                                } else {
                                    UserFile file;

                                    for (int i = 0, len = userfiles.size(); i < len; i++) {
                                        file = userfiles.get(i);

                                        out.println("<h1 class=\"searchh1\">\n" +
                                                "<a href='/file/download/" + file.getId() + "' target=\"_blank\">" + file.getFilename() + "</a>\n" +
                                                "<a rel=\"noreferrer\" href='/file/download/" + file.getId() + "' \n" +
                                                "target=\"_blank\"> <i class=\"glyphicon glyphicon-share-alt\"></i>\n" +
                                                "</a>\n" +
                                                "</h1>");
                                        out.println("<p class=\"content\">\n" + file.getSummary() +
                                                "</p>");
                                        out.println("<p class=\"aw-text-color-green\">\n" + file + "</p>");
                                    }
                                }

                                userfiles = null;
                            %>
                            <div class="page-control" id="pageto" data-page="1">
                                <ul class="pagination pull-right">
                                    <li id="pre"><a id="preurl">&laquo;</a>
                                    </li>
                                    <li class="active"><span>${page + 1}</span></li>
                                    <li id="next"><a id="nexturl">&raquo;</a></li>
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
                </button>
                <button type="button" class="btn btn-primary  ladda-button" id="submitupload" data-style="zoom-in">
                    <span class="ladda-label">Submit</span>
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
    var username = "${username}";
    var page = ${page};
    var type = "${type}";
    var nextdisabled = ${nextdisabled};
    var la = Ladda.create(document.querySelector("#submitupload"));

    $(document).ready(function () {
        if (username == "0") {
            $("#uploadbutton").hide();
            $("#userdiv").hide();
        } else {
            $("#logindiv").hide();
        }

        if (nextdisabled == 1) {
            $("#next").addClass("disabled");
            $("#nexturl").attr("href", "javascript:void(0)");
        }

        if (page == 0) {
            $("#pre").addClass("disabled");
            $("#preurl").attr("href", "javascript:void(0)");
        }
    });

    $('#myCarousel').carousel({
        interval: 4000,
    });

    $("#novsearch").click(function () {
        location.href = "/search?text=" + $("#searchtext").val() + "&type=All";
    });

    $(document).on("click", "#next", function (e) {
        if (nextdisabled != 1) {
            location.href = "/search?key=" + $("#searchtext").val() + "&spage=" + (page + 1) + "&type=" + type;
        }
    });

    $(document).on("click", "#pre", function (e) {
        if (page > 0) {
            location.href = "/search?key=" + $("#searchtext").val() + "&spage=" + (page - 1) + "&type=" + type;
        }
    });

    $(document).on("click", "#submitupload", function (e) {
        checkUploadFile();
    });
</script>
</body>
</html>