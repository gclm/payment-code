$(document).ready(function () {

    function judgePayCode(code){
        if(code !=null && code != undefined && code !=''){
            return true;
        }
        return false;
    }

    $(".key_btn").click(function(){

        var aliPay = $("#alipay").val();
        var qqPay = $("#qq").val();
        var weixinPay = $("#weixin").val();
        var username = $("#username").val();

        console.log("alipay="+aliPay+"\nqqPay="+qqPay+"\nweixinPay="+weixinPay+"\nusername"+username)

        if (judgePayCode(aliPay)  && judgePayCode(qqPay) && judgePayCode(weixinPay) && judgePayCode(username)){
            // console.log("zxzxc")

            $.ajax({
                type: "POST",
                url: "/qr",
                contentType: "application/json;charset=UTF-8", //必须有
                dataType: "json", //表示返回值类型，不必须
                data: JSON.stringify({"aliPay":aliPay,"weixinPay":weixinPay, "qqPay":qqPay,"username":username}),
                cache: false,
                timeout: 600000,
                success: function (data) {
                    console.log("SUCCESS : ",data);
                    var newData = JSON.stringify(data);
                    newData = eval("("+newData+")");
                    var code = newData.data.code;
                    var username = newData.data.username;

                    console.log(username+" "+code)

                    // 执行赋值操作
                    $("#name").text(username);
                    $("#code").attr("src",code);
                    // $("#code").setAttribute("src",code);

                    //打开模态框
                    $(".alipay_popup").show();
                    $(".alipay_code").animate({top:"32px"});
                },
                error: function (error) {
                    // $("#result").text(e.responseText);
                    console.log("ERROR : ", error);
                    // $("#btnSubmit").prop("disabled", false);
                }
            });
        }else {
            alert("请检查二维码\n看三种收款码是否都上传了或者收款人名是否填写！！！！！");
        }
    });
});