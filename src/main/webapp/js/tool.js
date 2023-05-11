// 隐藏或者显示某模块
function changeDispalyById(id) {
    var $id = $("#"+id+"");
    var css = $id.css('display');
    if (css !== 'none') {
        $id.css('display','none');
    } else if (css === 'none') {
        $id.css('display','inline');
    } else {
        $id.css('display','inline');
    }
}

// 隐藏某模块
function dispalyById(id) {
    var $id = $("#"+id+"");
    var css = $id.css('display');
    if (css !== 'none') {
        $id.css('display','none');
    }
}

// 显示某模块
function showById(id) {
    var $id = $("#"+id+"");
    var css = $id.css('display');
    if (css === 'none') {
        $id.css('display','inline');
    }
}

// 获取URL
function getURL(){
    var url = window.location.href;
    var index1 = url.indexOf("?");
    if (index1 != -1 ){
        var url = url.substring(0,index1-9);
        return url;
    } else {
        var index2 = url.indexOf(".");
        var url = url.substring(0,index2-4);
        return url;
    }
}

// 获取表格行的id
function getId(id){
    var id = id.substring(7);
    return id
}