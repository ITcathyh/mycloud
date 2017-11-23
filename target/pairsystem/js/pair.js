var type = 1;

function showsuccess() {
    $("#msgerror").hide();
    $("#msgsuccess").show();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function showsuccess1(msgtext) {
    $("#ss-msg-success-p").text(msgtext);
    $("#msgerror").hide();
    $("#msgsuccess").show();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function showerror(msgtext) {
    $("#msg-error-p").text(msgtext);
    $("#msgerror").show();
    $("#msgsuccess").hide();
    $('html,body').animate({scrollTop: 0}, 'slow');
}

function check(name, studentid, qq, email, phone) {
    if (!new RegExp("^[\u4e00-\u9fa5]{2,10}$", "").test(name)) {
        showerror("请输入正确的中文名(如名字内带符号请去掉符号)");
        return false;
    } else if (!new RegExp("^201[0-9]{5,5}$", "").test(studentid)) {
        showerror("请输入正确的学号");
        return false;
    } else if (!new RegExp("[1-9][0-9]{4,}", "").test(qq)) {
        showerror("请输入正确的QQ号码");
        return false;
    } else if (!new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$", "").test(email)) {
        showerror("请输入正确的邮箱");
        return false;
    } else if (!(/^1(3|4|5|7|8)\d{9}$/.test(phone))) {
        showerror("请输入正确的手机号");
        return false;
    }

    return true;
}

function addselfpair(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone +
        "&anothername=" + anothername + "&selfsex=" + $("#selfsex").val() +
        "&anotherstudentid=" + anotherstudentid + "&anotherqq=" + anotherqq +
        "&anotheremail=" + anotheremail + "&anotherphone=" + anotherphone + "&type=2&id=" + breurl,
        type: "post",
        headers: headers,
        url: "/checkaddbreuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "notfound") {
                showerror("提交失败，请确认需要匹配的人的个人信息");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "full") {
                showerror("提交失败，本次活动人数已满，请明天再报名吧~");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

function addselfpair1(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&anothername=" + anothername +
        "&selfsex=" + $("#selfsex").val() + "&anotherstudentid=" + anotherstudentid +
        "&anotherqq=" + anotherqq + "&anotheremail=" + anotheremail + "&anotherphone=" + anotherphone +
        "&college=" + $("#college").val() + "&time=" + $("#time").val() +
        "&selfcollege=" + $("#selfcollege").val() + "&sex=" + $("#sex").val() + "&type=3&id=" + breurl,
        type: "post",
        headers: headers,
        url: "/checkaddbreuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "notfound") {
                showerror("提交失败，请确认需要匹配的人的个人信息");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "full") {
                showerror("提交失败，本次活动人数已满，请明天再报名吧~");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

function addsystempair(name, studentid, qq, email, phone) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&college=" + $("#college").val() +
        "&selfsex=" + $("#selfsex").val() + "&time=" + $("#time").val() +
        "&selfcollege=" + $("#selfcollege").val() + "&sex=" + $("#sex").val() + "&type=1&id=" + breurl,
        type: "post",
        headers: headers,
        url: "/checkaddbreuser",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "full") {
                showerror("提交失败，本次活动人数已满，请明天再报名吧~");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitpair", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (type == 1) {
            la.start();
            addsystempair(name, studentid, qq, email, phone);
        } else {
            var anothername = $("#anothername").val();
            var anotherstudentid = $("#anotherstudentid").val();
            var anotherqq = $("#anotherqq").val();
            var anotheremail = $("#anotheremail").val();
            var anotherphone = $("#anotherphone").val();

            if (check(name, anotherstudentid, anotherqq, email, anotherphone)) {
                if (anotherstudentid == studentid || anotherqq == qq || anotheremail == email || anotherphone == phone) {
                    showerror("请输入匹配人正确的信息");
                } else {
                    la.start();
                    if (type == 2) {
                        addselfpair(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone);
                    } else {
                        addselfpair1(name, studentid, qq, email, phone, anothername, anotherstudentid, anotherqq, anotheremail, anotherphone);
                    }
                }
            }
        }
    }
});

/* apply teacher begin */
function addteacher(name, studentid, qq, email, skill, phone, la) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&selfsex=" + $("#selfsex").val() +
        "&skill=" + skill + "&college=" + $("#selfcollege").val(),
        type: "post",
        headers: headers,
        url: "/checkaddteacher",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitaddteacher", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var skill = $("#skill").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (skill.length <= 1 || skill.length >= 11) {
            showerror("请输入擅长领域(10字符内)");
            $('html,body').animate({scrollTop: 0}, 'slow');
        } else {
            var la = Ladda.create(document.querySelector("#submitaddteacher"));
            la.start();

            addteacher(name, studentid, qq, email, skill, phone, la);
        }
    }
});
/* apply teacher end */

/*self-studying begin */
function addstudying(name, studentid, qq, email, phone, la) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&college=" + $("#college").val() +
        "&selfsex=" + $("#selfsex").val() + "&time=" + $("#time").val() +
        "&selfcollege=" + $("#selfcollege").val() + "&sex=" + $("#sex").val(),
        type: "post",
        headers: headers,
        url: "/checkaddstudying",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}


$(document).on("click", "#submitaddstudying", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        var la = Ladda.create(document.querySelector("#submitaddstudying"));
        la.start();

        addstudying(name, studentid, qq, email, phone, la);
    }
});
/*self-studying end */

/*addstudent begin */
function addstudent(name, studentid, qq, email, college, phone, la) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&college=" + college +
        "&pairid=" + $("#teacherstudentid").val(),
        type: "post",
        headers: headers,
        url: "/checkaddstudent",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("提交失败，请稍后重试");
            } else if (response == "exist") {
                showerror("提交失败，个人信息已存在。若非本人报名，请联系活动负责人");
            } else if (response == "booked") {
                showerror("别人已经抢先一步啦，换个辅学人试试吧");
            } else if (response == "emailerror") {
                showerror("匹配邮件发送失败，请稍后重试或联系负责人");
            } else if (response == "ipfull") {
                showerror("提交失败，您的IP申请本次活动已超过次数，若非本人操作，请联系活动负责人");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitaddstudent", function (e) {
    var name = $("#name").val();
    var studentid = $("#studentid").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var college = $("#selfcollege").val();

    if (check(name, studentid, qq, email, phone)) {
        if (college != $("#teachercollege").val()) {
            showerror("目前只支持在同学院间进行辅学");
        } else if (studentid == $("#teacherstudentid")) {
            showerror("请输入正确的学号");
        } else {
            var la = Ladda.create(document.querySelector("#submitaddstudent"));

            la.start();
            addstudent(name, studentid, qq, email, college, phone, la);
        }
    }
});
/*addstudent end */

/* admin beigin */
$(document).on("click", "#changebreurl", function (e) {
    $.ajax({
        type: "post",
        url: "/adminchangeurl",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("出现异常，请稍后重试");
            } else {
                $("#urlvalue").text(response);
            }
        }
    });
})

function beginpair(la) {
    $.ajax({
        type: "post",
        url: "/admincheckpairbre",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("配对失败，请查看错误日志");
        },
        success: function (response) {
            la.stop();

            if (response == -1 || response == 0) {
                showerror("配对失败，请查看错误日志");
            } else {
                showsuccess1("配对完成,共配对" + response + "对");
                brenotpairednum  -= response << 1;
                $("#brenotpairednum").text(brenotpairednum );
            }
        }
    });
}


$(document).on("click", "#beginpair", function (e) {
    if (window.confirm('确定开始匹配早餐叫醒用户吗？')) {
        var la = Ladda.create(document.querySelector("#beginpair"));

        la.start();
        beginpair(la);
    }
})


function sendpairinfo(la) {
    $.ajax({
        type: "post",
        url: "/adminsendbrepairinfo",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("暂无需要发送邮件的用户");
            } else {
                showsuccess1("发送邮件成功,已发送" + response + "%");
            }
        }
    });
}

$(document).on("click", "#sendpairinfo", function (e) {
    if (window.confirm('确定开始发送邮件吗？')) {
        var la = Ladda.create(document.querySelector("#sendpairinfo"));

        la.start();
        sendpairinfo(la);
    }
})

$(document).on("click", "#donwloadexcel", function (e) {
    var la = Ladda.create(document.querySelector("#donwloadexcel"));

    la.start();
    $.ajax({
        type: "post",
        url: "/adminmakeexcel",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("生成excel失败");
            } else {
                window.location.href = "/admindownload?type=userinfoexcel&filename=userinfo.zip";
            }
        }
    });
})

$(document).on("click", "#donwloadauthdata", function (e) {
    var la = Ladda.create(document.querySelector("#donwloadauthdata"));

    la.start();
    $.ajax({
        type: "post",
        url: "/adminmakeauthdatazip",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("生成压缩包失败,可能尚未有用户上传资料");
            } else {
                window.location.href = "/admindownload?type=authdatazip&filename=authdata.zip";
            }
        }
    });
})

function submitinfo(bremaxnum, la) {
    $.ajax({
        data: "bremaxnum=" + bremaxnum + "&title=" + $("#title").val() + "&logo=" + $("#logo").val(),
        type: "post",
        url: "/adminalterinfo",
        dataType: "json",
        error: function (data) {
            la.stop();
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                showerror("修改失败");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#submitinfo", function (e) {
    var bremaxnum = $("#bremaxnum").val();

    if (/^[1-9]\d*$/.test(bremaxnum)) {
        var la = Ladda.create(document.querySelector("#submitinfo"));
        la.start();

        submitinfo(bremaxnum, la);
    } else {
        showerror("请输入正确的数量");
    }
})

$(document).on("click", "#downloaderrormsag", function (e) {
    window.location.href = "/admindownload?type=errormsg&filename=my.log";
})
/* admin end */

/* queryuser begin */

function updateuser(name, studentid, qq, email, phone, time) {
    $.ajax({
        data: "name=" + name + "&studentid=" + studentid +
        "&qq=" + qq + "&email=" + email + "&phone=" + phone + "&time=" + time +
        "&selfcollege=" + $("#selfcollege").val() + "&college=" + $("#college").val() +
        "&sex=" + $("#sex").val() + "&type=" + type,
        type: "post",
        url: "/admincheckupdateuser",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("修改失败，请稍后重试");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#updateuser", function (e) {
    var name = $("#name").val();
    var qq = $("#qq").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (type == 1) {
            updateuser(name, studentid, qq, email, phone, $("#time1").val());
        } else {
            updateuser(name, studentid, qq, email, phone, $("#time2").val());
        }
    }
})


function updateteacher(name, studentid, qq, email, skill, phone) {
    $.ajax({
        data: "teachername=" + name + "&teacherstudentid=" + studentid +
        "&teacherqq=" + qq + "&teacheremail=" + email + "&teacherphone=" + phone +
        "&teacherskill=" + skill + "&teachercollege=" + $("#teachercollege").val(),
        type: "post",
        url: "/admincheckupdateassist",
        dataType: "json",
        error: function (data) {

            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("修改失败，请稍后重试");
            } else {
                showsuccess();
            }
        }
    });
}

$(document).on("click", "#updateassist", function (e) {
    var name = $("#teachername").val();
    var qq = $("#teacherqq").val();
    var email = $("#teacheremail").val();
    var skill = $("#teacherskill").val();
    var phone = $("#teacherphone").val();

    if (check(name, studentid, qq, email, phone)) {
        if (skill.length <= 1 || skill.length >= 11) {
            showerror("请输入擅长领域(2-10字符)");
        } else {
            updateteacher(name, studentid, qq, email, skill, phone);
        }
    }
});

function checkdelete(genre, studentid) {
    $.ajax({
        data: "genre=" + genre + "&studentid=" + studentid,
        type: "post",
        url: "/admincheckdelete",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("删除失败，请稍后重试");
            } else {
                showsuccess();
                setTimeout(function () {
                    window.location.href = "/adminqueryuser";
                }, 1000);
            }
        }
    });
}

/* queryuser end */

/* login begin */

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
            $("#msg-success").hide(10);
            $("#msg-error").show(100);
            $("#msg-error-p").html("出现异常");
        },
        success: function (response) {
            la.stop();

            if (response == "error") {
                $("#msg-success").hide(10);
                $("#msg-error").show(100);
                $("#msg-error-p").html("用户名或密码错误");
                return false;
            } else if (response == "loginlock") {
                $("#msg-success").hide(10);
                $("#msg-error").show(100);
                $("#msg-error-p").html("输入错误次数过多，禁止登录");
                $("#login").addClass("disabled");
            } else {
                $("#msg-error").hide(10);
                $("#msg-success").show(100);
                $("#msg-success-p").html("登录成功");
                window.setTimeout("location.href='/admin'", 1000);
                return true;
            }
        }
    });
}

function checkInfo() {
    var username = $("#username").val();
    var password = $("#password").val();

    if (username.length < 1 || password.length < 1) {
        $("#msg-success").hide(10);
        $("#msg-error").show(100);
        $("#msg-error-p").html("用户名或密码错误");
    } else {
        var la = Ladda.create(document.querySelector("#login"));

        la.start();
        login(username, password, la);
    }
}

$(document).on("click", "#login", function (e) {
    checkInfo();
});

$("#password").keydown(function(event){
    if(event.keyCode == 13){
        checkInfo();
    }
});
/* login end */

/* authenticate begin */

$("#submitauthentication").click(function () {
    var content = $("#content").val();

    if (!new RegExp("^201[0-9]{5,5}$", "").test($("#studentid").val())) {
        showerror("请填写正确的学号");
        return false;
    } else if (content.length < 1 || content.length > 150) {
        showerror("请填写不多于150字的辅学内容");
        return false;
    }

    la.start();
    $("#myform").ajaxSubmit(options);
    return false;
});

var options = {
    type: 'POST',
    url: "/uploadfile",
    dataType: 'json',
    success: function (response) {
        la.stop();

        if (response == "error") {
            showerror("出现异常，请稍候重试");
        } else if (response == "notenough") {
            showerror("请上传至少三张图片");
        } else if (response == "notfound") {
            showerror("未找到匹配学号，请确认后再提交");
        } else if (response == "lockupload") {
            showerror("请勿频繁上传，上传功能将锁定一段时间");
            $("#submitauthentication").addClass("disabled");
        } else {
            showsuccess1("提交成功，请注意提醒对方也要来认证喔");
        }
    }, error: function (data) {
        la.stop();
        showerror("出现异常，请稍候重试。（注意需上传至少三张照片）");
    }
};

$(".file").fileinput({
    showPreview: false,
    showUpload: false,
    showRemove: false,
    allowedFileTypes: ['image'],
    allowedPreviewMimeTypes: ['jpg', 'png', 'gif'],
    language: 'zh',
    maxFileSize: 13312
})
/* authenticate end */

/* editor begin */
function updateemail(add1, add2, add3, pwd1, pwd2, pwd3, host1, host2, host3) {
    $.ajax({
        data: {
            "add1": add1,
            "add2": add2,
            "add3": add3,
            "pwd1": pwd1,
            "pwd2": pwd2,
            "pwd3": pwd3,
            "host1": host1,
            "host2": host2,
            "host3": host3
        },
        type: "post",
        url: "/admincheckupdateemail",
        dataType: "json",
        error: function (data) {
            showerror("出现异常，请稍后重试");
        },
        success: function (response) {
            if (response == "error") {
                showerror("修改失败，请稍后重试");
            } else {
                showsuccess1("修改成功");
            }
        }
    });
}

/* editor end */