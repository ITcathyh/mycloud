
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%
        String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    %>
    <base href=" <%=basepath%>">
    <meta charset="UTF-8">
    <title>My Cloud - Log In</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/mycloud.css" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark" type="img/x-icon"/>
    <link href="img/logo.ico" rel="shortcut icon">
</head>
<body class="login-page">
<div class="login-box">
    <div class="login-logo">
       <h1>
           <a><b>My Cloud</b></a>
       </h1>
    </div>
    <div class="login-box-body">
        <p class="login-box-msg">Log In</p>

        <form>
            <div class="form-group has-feedback">
                <input id="email" name="email" type="text" class="form-control" placeholder="email"
                       autocomplete="off"/>
                <span class="glyphicon glyphicon-envelope  form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="password" name="password" type="password" class="form-control" placeholder="password"/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-4">
                <button id="login" type="submit" class="btn btn-primary btn-block btn-flat ladda-button buttonNew"
                        data-style="zoom-in">
                    <span class="ladda-label">Log In</span>
                </button>
            </div>
            <div class="col-xs-4">
                <a href="/signup">
                    <button type="submit" class="btn btn-primary btn-block btn-flat ladda-button buttonNew"
                            data-style="zoom-in">
                        <span class="ladda-label">Sign Up</span>
                    </button>
                </a>
            </div>
        </div>
        <div id="msg-success" class="alert alert-info alert-dismissable" style="display: none;">
            <button type="button" class="close" id="ok-close" aria-hidden="true">&times;</button>
            <h4><i class="icon fa fa-info"></i>Success!</h4>
            <p id="msg-success-p">123</p>
        </div>
        <div id="msg-error" class="alert alert-warning alert-dismissable" style="display: none;">
            <button type="button" class="close" id="error-close" aria-hidden="true">&times;</button>
            <h4><i class="icon fa fa-warning"></i>Wrong!</h4>
            <p id="msg-error-p"></p>
        </div>

    </div>
</div>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/spin.min.js"></script>
<script type="text/javascript" src="js/ladda.min.js"></script>
<script type="text/javascript" src="js/mycloud.js"></script>
<script type="text/javascript">
    var lock = "${loginlock}";
    var loginurl = "${loginurl}";
    var successurl = "${successurl}";

    $(document).ready(function () {
        if (lock == "1") {
            $("#login").addClass("disabled");
        }
    });

    $("#login").click(function () {
        checkLogin();
    });

    $("#password").keydown(function (event) {
        if (event.keyCode == 13) {
            checkLogin();
        }
    });

</script>
</body>
</html>