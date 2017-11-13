$(function() {
    initLoad();
    console.log($basePath);
});

function initLoad() {
    ajaxRequest("POST", "/manage/user/testPOJO", function(ajaxRet) {
        console.log(ajaxRet);
        if(ajaxRet.success){
            $('.ajaxPOJO4userName').html(ajaxRet.obj.userName);
            $('.ajaxPOJO4passWord').html(ajaxRet.obj.password);
        }
    }, undefined,undefined);
}
$('.logout').click(function() {
    ajaxRequest("POST", "/logout", function(ajaxRet) {
        console.log(ajaxRet);
        if(ajaxRet.success){
            window.location.href = $basePath;
        }
    }, undefined,undefined);
});