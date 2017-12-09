<%--
  Created by IntelliJ IDEA.
  User: 黄宇航
  Date: 2017/12/5
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>My Cloud - Sign Up</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/AdminLTE.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/all-skins.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/ladda-themeless.min.css" rel="stylesheet" type="text/css"/>
    <link href="image/logo.ico" rel="bookmark"  type="img/x-icon"  />
    <link href="img/logo.ico" rel="shortcut icon" >
</head>
<body class="login-page">
<div class="login-box">
    <div class="login-logo">
        <a><b>My Cloud</b></a>
    </div>
    <div class="login-box-body">
        <p class="login-box-msg">Sign Up</p>

        <form>
            <div class="form-group has-feedback">
                <input id="email" name="email" type="text" class="form-control" placeholder="Email"
                       autocomplete="off"/>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="password" name="password" type="password" class="form-control" placeholder="Password"/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="repassword" name="repassword" type="password" class="form-control" placeholder="Repassword"/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="username" name="username" type="text" class="form-control" placeholder="Username"
                       autocomplete="off"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="qq" name="qq" type="text" class="form-control" placeholder="QQ"
                       autocomplete="off"/>
                <span class="glyphicon glyphicon-info-sign form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input id="invitationcode" name="invitationcode" type="text" class="form-control" placeholder="Invitationcode"
                       autocomplete="off"/>
                <span class="glyphicon glyphicon-info-sign form-control-feedback"></span>
            </div>
        </form>
        <div class="row">
            <div class="col-xs-4">
                <button id="signup" type="submit" class="btn btn-primary btn-block btn-flat ladda-button" data-style="zoom-in">
                    <span class="ladda-label">Sign Up</span>
                </button>
            </div>
        </div>
        <div id="msg-success" class="alert alert-info alert-dismissable" style="display: none;">
            <button type="button" class="close" id="ok-close" aria-hidden="true">&times;</button>
            <h4><i class="icon fa fa-info"></i> Success!</h4>
            <p id="msg-success-p"></p>
        </div>
        <div id="msg-error" class="alert alert-warning alert-dismissable" style="display: none;">
            <button type="button" class="close" id="error-close" aria-hidden="true">&times;</button>
            <h4><i class="icon fa fa-warning"></i> Wrong!</h4>
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
    var lock = 0;

    $(document).ready(function () {
        if (lock == 1) {
            $("#login").addClass("disabled");
        }
    });

    $("#signup").click(function () {
        checkSignup();
    })

    $("#invitationcode").keydown(function (event) {
        if (event.keyCode == 13) {
            checkSignup();
        }
    });
</script>
</body>
</html>