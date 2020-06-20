$(function () {

    // 对密码进行MD5加密
    $("#password").on("input",function(e){
        //获取input输入的值
        var input_password = $("#password").val();
        var input_confirm = $("#confirmPwd").val();

        $("#pwd").val($.md5((input_password + $(".orchid"))));

    });

})