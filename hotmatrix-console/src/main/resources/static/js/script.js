(function($) {
  
  "use strict";

    //  ==================== SCROLLING FUNCTION ====================

    $(window).on("scroll", function() {
        var scroll = $(window).scrollTop();
        if (scroll > 30) {
            $(".top_bar").addClass("scroll animated slideInDown");
        } else if (scroll < 30) {
            $(".top_bar").removeClass("scroll animated slideInDown")
        }
    });


    var header_height = $(".top_bar").innerHeight();

    $(".side_menu").css({
        "top": header_height
    });

    $(".menu").on("click", function(){
      $(".side_menu").toggleClass("active");
      return false;
    });

    $("html").on("click", function() {
        $(".side_menu").removeClass("active");
    });
    $(".menu, .side_menu").on("click", function(e){
        e.stopPropagation();
    });

    $(".user-log").on("click", function() {
        $(".account-menu").slideToggle();
    });
    $("html").on("click", function() {
        $(".account-menu").slideUp();
    });
    $(".user-log, .account-menu").on("click", function(e) {
        e.stopPropagation();
    });


    //  ==================== SCROLLING FUNCTION ====================
    
    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })

    //  =========================系统菜单方法============================

    $(".top_bar .mm_menu .ruser").on("click", function(e) {
        layer.open({
            type: 2,
            title: '用户中心',
            shadeClose: true,
            shade: false,
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '/ruserview/profile'
        });
    });

    $(".top_bar .mm_menu .roles").on("click", function(e) {
        layer.open({
            type: 2,
            title: '角色切换',
            shadeClose: true,
            shade: false,
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '/ruserview/swtrole'
        });
    });

    $(".top_bar .mm_menu .setting").on("click", function(e) {
        layer.open({
            type: 2,
            title: '设置',
            shadeClose: true,
            shade: false,
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '/ruserview/setting'
        });
    });

    $(".top_bar .mm_menu .logout").on("click", function(e) {
        layer.confirm('您确定要退出当前登录？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            location.href = '/logout';
        }, function(){
        });
    });

    $(".sd_menu .mm_menu .about").on("click", function(e) {
        layer.open({
            type: 2,
            title: '关于',
            shadeClose: true,
            shade: false,
            maxmin: false, //开启最大化最小化按钮
            area: ['600px', '370px'],
            content: '/mainview/about'
        });
    });

})(window.jQuery);
