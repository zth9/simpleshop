$(function(){
    $("#login").click(function () {
        if ($("#login_name").val()=="" || $("#login_password").val()==""){
            alert("请输入完整的用户名密码!")
            return false;
        }
    })
    $("#register_btn").click(function () {
        var registerName = $("#register_name");
        var pwd1 = $("#register_pwd1");
        var pwd2 = $("#register_pwd2");
        if (registerName.val()=="" || pwd1.val()=="" || pwd2.val()==""){
            alert("请输入完整的注册信息!")
            return false;
        }
        var formRule = new RegExp(/^[a-zA-Z0-9]{6,16}$/);
        if (!registerName.val().match(formRule) || !pwd1.val().match(formRule) || !pwd2.val().match(formRule)){
            alert("用户名和密码为6~16位,只能包含字母,数字和'_'");
            return false;
        }
    })
})