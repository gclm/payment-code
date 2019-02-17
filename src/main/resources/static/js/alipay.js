$(".code_pick").on('click',function(){
	$(".popup_window").animate({width:"240px"});
});
$(".popup_bottom").on('click',function(){
	$(".popup_window").animate({width:"0"});
	$(".alipay_popup").fadeOut();
	$(".alipay_code").animate({top:"-500px"});
});

//主体颜色
$(".bgColor").on('click',function(){
    $(".item_1").slideToggle("slow");
});

var list = $('.item_1>ul>li');
for(var i=0; i<list.length; i++){
	$(list[i]).on('click',function(){
		$(".alipay_code").css("background-color", $(this).css("background-color"));
        $.cookie("background", $(this).css("background-color"));
        if ($.cookie("background")) {
        $(".alipay_code").css("background-color", $.cookie("background"));
    }
	})
}

//底部颜色
$(".bottomColor").on('click',function(){
    $(".item_2").slideToggle("slow");
});
var list = $('.item_2>ul>li');
for(var i=0; i<list.length; i++){
	$(list[i]).on('click',function(){
		$(".code_bottom").css("background-color", $(this).css("background-color"));
        $.cookie("background", $(this).css("background-color"));
        if ($.cookie("background")) {
        $(".code_bottom").css("background-color", $.cookie("background"));
    }
	})
}

//底部文字颜色
$(".wordColor").on('click',function(){
    $(".item_3").slideToggle("slow");
});
var list = $('.item_3>ul>li');
for(var i=0; i<list.length; i++){
	$(list[i]).on('click',function(){
		$(".code_bottom").css("color", $(this).css("background-color"));
        $.cookie("color", $(this).css("background-color"));
	})
}

//一键合成
// $(".key_btn").on('click',function(){
// 	$(".alipay_popup").show();
// 	$(".alipay_code").animate({top:"32px"});
// });