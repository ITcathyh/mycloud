
$(".file").fileinput({
    showPreview: false,
    showUpload: false,
    showRemove: false,
    language: 'zh',
    maxFileSize: 30720
})

function showerror(msg) {
    $("#msg-error-p").text(msg);
    $("#msg-error-p").text(msg);
    $("#msgerror").show();
    $("#msgsuccess").hide();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function showsuccess(msg) {
    $("#ss-msg-success-p").text(msg);
    $("#msgerror").hide();
    $("#msgsuccess").show();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

$("#submitupload").click(function () {
    checkUpload();
})

function checkUpload() {
    var name = $("#name").val();
    var summary = $("#summary").val();
    var type = $("#type").val();

    if ($("#file").val() == "") {
        showerror("The file must not be empty");
        return false;
    } else if (name.length == 0 || name.length > 30) {
        showerror("The name must be less than 30 bits");
        return false;
    } else if (summary.length == 0 || summary.length > 150) {
        showerror("The summary must be less than 150 bits");
        return false;
    }

    addRecoure(name, summary, type);
}

function addRecoure(name, summary, type) {
    /* 传递给后台 */
}


/* login begin */
function showLoginError(msg) {
    $("#msg-success").hide(10);
    $("#msg-error").show(100);
    $("#msg-error-p").html(msg);
}

function showLoginSuccess(msg) {
    $("#msg-error").hide(10);
    $("#msg-success").show(100);
    $("#msg-success-p").html(msg);
}

function login(username, password, la) {
    $.ajax({
        data: {
            "username": username,
            "password": password
        },
        type: "post",
        url: "/checklogin",
        dataType: "json",
        error: function (data) {
            la.stop();
            showLoginError("Unknown error");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showLoginError("Email or password is wrong");
            } else if (response == "loginlock") {
                showLoginError("Prohibit landing");
            } else {
                showLoginSuccess("Success");
                window.setTimeout("location.href='/admin'", 1000);
                return true;
            }
        }
    });
}

function checkLogin() {
    var email = $("#email").val();
    var password = $("#password").val();

    if (!new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$", "").test(email)
        || !new RegExp("^[a-zA-z][a-zA-Z0-9.-]{5,16}$", "").test(password)) {
        $("#msg-success").hide(10);
        $("#msg-error").show(100);
        $("#msg-error-p").html("email or password is wrong");
    }  else {
        var la = Ladda.create(document.querySelector("#login"));

        la.start();
        login(email, password, la);
    }
}


$("#password").keydown(function (event) {
    if (event.keyCode == 13) {
        checkInfo();
    }
});
/* login end */

function checkSignup(){
    var email = $("#email").val();
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    var username = $("#username").val();
    var qq = $("#qq").val();
    var invitationcode = $("#invitationcode").val();

    if (!new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$", "").test(email)){
        showLoginError("Please input the right email");
    } else if (!new RegExp("^[a-zA-z][a-zA-Z0-9.-]{5,16}$", "").test(password)){
        showLoginError("The password must be less than 16 bits and not be less than 5 bits");
    }  else if (!new RegExp("^[1-9][0-9]{4,}", "").test(qq)){
        showLoginError("Please input the right qq number");
    } else if (!new RegExp("^[a-zA-Z0-9.-]{20,20}$", "").test(invitationcode)){
        showLoginError("Please input the right invitationcode");
    }else if (username.length == 0 || username.length > 12){
        showLoginError("Please input the right username");
    } else {
        var la = Ladda.create(document.querySelector("#signup"));

        la.start();
        login(email, password, la);
    }
}