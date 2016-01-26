;/*!/lib/js/app.js*/

$(function() {
    // 滚动条监听
    $(".spy a").each(function(){
        var e=$(this);
        var t=e.closest(".spy");
        var target=t.attr("data-target");
        var top=t.attr("data-offset-spy");
        var thistarget="";
        var thistop="";
        if(top==null){top=0;};
        if(target==null){thistarget=$(window);}else{thistarget=$(target);};
        
        thistarget.bind("scroll",function(){
            if(target==null){
                thistop=$(e.attr("href")).offset().top - $(window).scrollTop() - parseInt(top); 
            }else{
                thistop=$(e.attr("href")).offset().top - thistarget.offset().top - parseInt(top);
            };
            if(thistop<0){
                t.find('li').removeClass("active");
                e.parents('li').addClass("active");
            };

        });
    });
    // home键回顶部
    $(document).keydown(function(event){ 
        var e = event || window.event; 
        var k = e.keyCode || e.which; 
        if (k === 36) {
            $('body,html').animate({
                scrollTop: 0
            }, 300);
        };
    });
    //固定导航
    $(window).on('scroll', function(e) {
        var scrollTop = $(window).scrollTop();

        if (scrollTop > 150) {
            $('.fixed').addClass('fixed-top');
            $(".nav").addClass("fixed-nav");
            $("#navBanber").show();
        } else {
            $('.fixed').removeClass('fixed-top');
            $(".nav").removeClass("fixed-nav");
            $("#navBanber").hide();
        }

    });
    
    //返回顶部
    $(window).scroll(function() {
        if ($(window).scrollTop() > 300) {
            $(".go-top").fadeIn('200');
        } else {
            $(".go-top").fadeOut('100');
        }
    });
    $(".go-top").click(function() {
        $('body,html').animate({
            scrollTop: 0
        }, 300);
        return false;
    });

    //关闭ie提示
    $(".close-tip").click(function(){
        $(this).parent().remove();
        return false;
    });
    /**********
    // 表单验证
    */
    //文本框失去焦点后
    $(".login .form .ipt").blur(function(){
        var $parent = $(this).parent();
        $parent.find(".tip").remove();

        //验证用户名
        if( $(this).is('#username') ){
            if( $(this).value=="" || this.value.length < 6 ){
                var errorMsg = '请输入至少6位的用户名';
                $parent.append('<span class="tip inError">'+errorMsg+'</span>');
                $(this).addClass('errorColor');
            }else{
                $(this).removeClass('errorColor');
            }
        }
        
        //验证密码
        if( $(this).is('#password') ){
            if( $(this).value=="" || this.value.length < 8){
                var errorMsg = '密码不能低于8位数字哦';
                $parent.append('<span class="tip inError">'+errorMsg+'</span>');
                $(this).addClass('errorColor');
            }else{
                $(this).removeClass('errorColor');
            }
        }
        
        //确认密码
        if( $(this).is('#passwords') ){
            if( $(this).value=="" || this.value.length < 8 ){
                var errorMsg = '请保持密码一致';
                $parent.append('<span class="tip inError">'+errorMsg+'</span>');
                $(this).addClass('errorColor');
            }else{
                $(this).removeClass('errorColor');
            }
        }
        
        //验证邮件
        if( $(this).is('#mail') ){
            if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value) ) ){
              var errorMsg = '请输入正确的E-Mail地址.';
              $parent.append('<span class="tip inError">'+errorMsg+'</span>');
              $(this).addClass('errorColor');
        }else{
              $(this).removeClass('errorColor');
            }
        }
    }).keyup(function(){
       $(this).triggerHandler("blur");
    }).focus(function(){
       $(this).triggerHandler("blur");
    });//end blur

    //提交，最终验证。
    $('.login-mod :submit').click(function(){
        $(".login .form .ipt").trigger('blur');
        var numError = $('form .inError').length;
        if(numError){
            return false;
        } 
    });

    //重置
    $('#res').click(function(){
        $(".tip").remove(); 
    });
});

// 老版浏览器提示
var once_per_session = 1;

function get_cookie(Name) {
    var search = Name + "=";
    var returnvalue = "";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // if cookie exists
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1) end = document.cookie.length;
            returnvalue = unescape(document.cookie.substring(offset, end));
        }
    }
    return returnvalue;
}

function alertornot() {
    if (get_cookie('alerted') == '') {
        loadalert();
        document.cookie = "alerted=yes";
    }
}

function loadalert() {
    isIE();
}

if (once_per_session == 0) {
    loadalert();
} else {
    alertornot();
};

//判断 ie 6 7
function isIE(){
    var browser=navigator.appName;
    var b_version=navigator.appVersion;
    var version=b_version.split(";"); 
    var trim_Version=version[1].replace(/[ ]/g,""); 
    if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE6.0" || browser=="Microsoft Internet Explorer" && trim_Version=="MSIE7.0") { 
        $('#tips').show();
    } 
}