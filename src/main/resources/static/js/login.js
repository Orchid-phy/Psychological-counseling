$(function () {

    // 对密码进行MD5加密
    $(".login_pwd").on("input",function(e){
        //获取input输入的值
        var input_pwd = $(".login_pwd").val();

        $("#password").val($.md5((input_pwd + $(".orchid"))));

    });

})