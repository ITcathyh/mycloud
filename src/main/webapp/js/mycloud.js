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

function prepage(url) {
    var nurl = url + "?key=" + $("#searchtext").val() + "&spage=" + (page - 1);

    if (page > 0) {
        location.href = nurl;
    }
}

function nextpage(url) {
    var nurl = url + "?key=" + $("#searchtext").val() + "&spage=" + (page + 1);

    if (nextdisabled != 1) {
        location.href = nurl;
    }
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

function login(email, password, la) {
    $.ajax({
        data: {
            "email": email,
            "password": password
        },
        type: "post",
        url: loginurl,
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
                $("#login").addClass("disabled");
            } else {
                showLoginSuccess("Success");
                window.setTimeout("location.href='" + successurl + "'", 1000);
                return true;
            }
        }
    });
}

function checkLogin() {
    var email = $("#email").val();
    var password = $("#password").val();

    /*if (!new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$", "").test(email)
        || !new RegExp("^[a-zA-z][a-zA-Z0-9.-]{5,16}$", "").test(password)) {
        $("#msg-success").hide(10);
        $("#msg-error").show(100);
        $("#msg-error-p").html("email or password is wrong");
    } else {
        var la = Ladda.create(document.querySelector("#login"));

        la.start();
        login(email, password, la);
    }*/

    var la = Ladda.create(document.querySelector("#login"));

    la.start();
    login(email, password, la);
}

/* login end */

/* signup begin */
function checkSignup() {
    var email = $("#email").val();
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    var name = $("#username").val();
    var qq = $("#qq").val();
    var invitationcode = $("#invitationcode").val();

    if (!new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$", "").test(email)) {
        showLoginError("Please input the right email");
    } else if (!new RegExp("^[a-zA-z][a-zA-Z0-9.-]{5,16}$", "").test(password)) {
        showLoginError("The password must be less than 16 bits and not be less than 5 bits");
    } else if (!new RegExp("^[1-9][0-9]{4,}", "").test(qq)) {
        showLoginError("Please input the right qq number");
    } else if (!new RegExp("^[a-zA-Z0-9.-]{20,20}$", "").test(invitationcode)) {
        showLoginError("Please input the right invitationcode");
    } else if (!new RegExp("^[\u4e00-\u9fa5a-zA-Z0-9.-]{4,12}$", "").test(name)) {
        showLoginError("Please input the right name");
    } else if (password != repassword) {
        showLoginError("Entered passwords differ");
    } else {
        var la = Ladda.create(document.querySelector("#signup"));

        la.start();
        signup(email, password, name, qq, invitationcode, la);
    }
}


function signup(email, password, name, qq, invitationcode, la) {
    $.ajax({
        data: {
            "email": email,
            "password": password,
            "name": name,
            "qq": qq,
            "invitationcode": invitationcode
        },
        type: "post",
        url: "/checksignup",
        dataType: "json",
        error: function (data) {
            la.stop();
            showLoginError("Unknown error");
        },
        success: function (response) {
            la.stop();

            if (response == "done") {
                showLoginSuccess("Success");
                window.setTimeout("location.href='/login'", 1000);
                return true;
            } else if (response == "invitcodeerror") {
                showLoginError("Please input the right invitationcode");
            } else if (response == "isexist") {
                showLoginError("The email or username is isexist");
            } else if (response == "error") {
                showLoginError("Unknown error");
            }
        }
    });
}

/* signup end */

/* homepage begin */
function checkLegnth(obj, low, high) {
    return obj.length > low && obj.length < high;
}

function checkUploadFile() {
    var filename = $("#filename").val();
    var filesummary = $("#filesummary").val();
    var originname = $("#file").val();

    if (originname == "") {
        showerror("The file must not be empty");
    } else if (originname.length > 50) {
        showerror("The origin filename must be less than 50 bits");
    } else if (!checkLegnth(filename, 0, 31)) {
        showerror("The filename must be less than 30 bits")
    } else if (!checkLegnth(filesummary, 0, 151)) {
        showerror("The summary must be less than 150 bits")
    } else {
        la.start();
        $("#myform").ajaxSubmit(options);
    }
}

var options = {
    type: 'POST',
    url: "/file/uploadfile",
    dataType: 'json',
    success: function (response) {
        la.stop();

        if (response == "error") {
            showerror("Unknown error");
        } else if (response == "full") {
            showerror("Balance is not enough");
        } else if (response == "lockupload") {
            showerror("Lock the function for a while");
            $("#submitauthentication").addClass("disabled");
        } else if (response == "done") {
            $("#filename").val("");
            $("#filetag").val("");
            $("#filesummary").val("");
            showsuccess("Upload successfully");
        }
    }, error: function (data) {
        la.stop();
        showerror("Unkown error");
    }
};

$("#file").fileinput({
    showPreview: false,
    showUpload: false,
    showRemove: false,
    language: 'zh',
    maxFileSize: 30720
});

$('.form_datetime').datetimepicker({
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});

$("#adfile").fileinput({
    showUpload: false,
    showRemove: false,
    language: 'zh',
    maxFileSize: 30720,
    allowedPreviewTypes: ['image'],
    allowedFileExtensions: ['jpg', 'png', 'gif', 'bmp']
});

$(document).on("click", "#submitupload", function (e) {
    checkUploadFile();
});

function getRecommend() {
    var precontent = $("#pushcontent").text();
    var prehref = $("#pushcontent").attr("href");
    $("#pushcontent").text("loading...");

    $.ajax({
        data: {},
        type: "post",
        url: "/push/getnewRecommend",
        dataType: "json",
        error: function (data) {
            $("#pushcontent").text(precontent);
            $("#pushcontent").attr("href", prehref);
        },
        success: function (response) {
            $("#pushcontent").text(response.content);
            $("#pushcontent").attr("href", response.href);
        }
    });

    setTimeout(function () {
        getRecommend();
    }, 10000);
}

/* homepage end */

/* file begin */
function deleteFile(la) {
    $.ajax({
        data: {
            "id": id
        },
        type: "post",
        url: "/file/deletefile",
        dataType: "json",
        error: function (data) {
            la.stop();
            $("#edit").removeAttr("disabled");
            showerror("Unknown error");
        },
        success: function (response) {
            la.stop();
            $("#edit").removeAttr("disabled");

            if (response == "done") {
                showsuccess("Delete successfully");
            } else if (response == "error") {
                showerror("Delete unsuccessfully");
            } else {
                showerror("File is non-existent");
            }
        }
    });
}

function editFile(la) {
    $.ajax({
        data: {
            "id": id,
            "filename": $("#filename").val(),
            "tag": $("#filetag").val(),
            "type": $("#filetype").val(),
            "summary": $("#filesummary").val()
        },
        type: "post",
        url: "/file/editfile",
        dataType: "json",
        error: function (data) {
            la.stop();
            $("#delete").removeAttr("disabled");
            showerror("Unknown error");
        },
        success: function (response) {
            la.stop();
            $("#delete").removeAttr("disabled");

            if (response == "error") {
                showerror("Unknown error");
            } else if (response == "done") {
                showsuccess("Edit successfully")
            } else if (response == "notfound") {
                showerror("File is non-existent")
            }
        }
    });
}

/* file end */

/* admin begin */
function setNotice(content, href) {
    var la = Ladda.create(document.querySelector("#setnotice"));

    la.start();

    $.ajax({
        data: {
            "content": content,
            "href": href
        },
        type: "post",
        url: "/admin/setnotice",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("Unknown error");
        },
        success: function (response) {
            la.stop();

            if (response == false) {
                showerror("Unknown error");
            } else {
                showsuccess("Edit successfully")
            }
        }
    });
}

/* admin end */

/* userdetail begin */
function deleteUser(la) {
    $.ajax({
        data: {
            "id": id
        },
        type: "post",
        url: "/admin/deleteuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            $("#edit").removeAttr("disabled");
            showerror("Unknown error");
        },
        success: function (response) {
            la.stop();
            $("#edit").removeAttr("disabled");

            if (response == "done") {
                showsuccess("Delete successfully");
            } else if (response == "error") {
                showerror("Delete unsuccessfully");
            }
        }
    });
}

function editUser(la) {
    $.ajax({
        data: {
            "id": id,
            "qq": $("#qq").val()
        },
        type: "post",
        url: "/admin/edituser",
        dataType: "json",
        error: function (data) {
            la.stop();
            $("#delete").removeAttr("disabled");
            showerror("Unknown error");
        },
        success: function (response) {
            la.stop();
            $("#delete").removeAttr("disabled");

            if (response == "done") {
                showsuccess("Edit successfully")
            } else if (response == "error") {
                showerror("Unknown error");
            }
        }
    });
}

/* userdetail end */

/* setad begin */
var adoptions = {
    type: 'POST',
    url: "/admin/setad",
    dataType: 'json',
    success: function (response) {
        la.stop();

        if (response == "done") {
            showsuccess("Set ad successfully");
        } else if (response == "error") {
            showerror("Unkown error");
        }
    }, error: function (data) {
        la.stop();
        showerror("Unkown error");
    }
};
/* setad end */