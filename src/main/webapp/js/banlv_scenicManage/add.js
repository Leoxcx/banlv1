var tableName = ""
var zhujian = ""

function init(){
    this.setTableName("scenicManage")
    this.initTableTitle('')
    this.initzhujian('')
    this.initButton("button_reset","重置","button_reset()")
    this.initButton("button_submit","提交","button_submit()")
    this.changeDispalyById('turningnav')
    this.initAttributeColumn()
}
// 设置映射表
function setTableName(name){
    tableName = name;
}
// 获取映射表
function getTableName(){
    return tableName;
}
// 初始化页面标题
function initTitle(titleName) {
    $('title').html(titleName)
}
// 初始化主键信息
function initzhujian(){
    var search = window.location.search;
    // 获取主键与id
    var index = search.indexOf("=");
    zhujian = search.substring(index + 1);
}
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
// 初始化表格标题
function initTableTitle(tablename) {
    var $mainContainer = $("#mainContainer");
    // 初始化标题
    var tableTitle = $("<h3 style=\"text-align: center\">"+tablename+"</h3>");
    // 标题加入到主容器中
    $mainContainer.prepend(tableTitle);
}
// 初始化按钮
function initButton(buttonId, buttonText, buttonFuction) {
    var $buttondiv = $("#buttondiv");
    // 初始化按钮
    var button = $("<button id=\""+buttonId+"\" class=\"btn btn-primary\" onClick=\""+buttonFuction+"\">"+buttonText+"</button>");
    // 按钮加入到按钮div中
    $buttondiv.prepend(button);
}
// 获取表格表头信息
function getTableTr(tableName){
    var trList;
    $.ajax({
        type : "post",
        url : "../../tabletrservlet",
        data : {
            "tableName":tableName
        },
        async : false,
        success : function(data){
            if (data.exist){
                trList = data.attributeList;
            }
        }
    });
    return trList;
}
// 初始化输入表单
function initAttributeColumn() {
    var trList = getTableTr(getTableName());
    var $table = $("#table");
    for (var i = 0; i < trList.length; i++) {
        // 初始化行
        var tr = $("<tr id='tableTr" + i + "'></tr>");
        // 添加行至表格末尾
        $("#tableTr").after(tr);
        // 初始化td
        var td1 = $("<td>" + trList[i].attrName + "</td>");
        if (trList[i].attrShow) {
            var td2 = $("<td></td>");
        if (trList[i].attrName == zhujian) {
                var input = $("<input id=" + trList[i].attrValue +" name = '"+ trList[i].attrValue +"' value = \"主键 自动生成\" class = 'myinput' type=\"text\" disabled=\"disabled\">");;
            }else {
                var input = $("<input id=" + trList[i].attrValue +" name = '"+ trList[i].attrValue +"' class = 'myinput' type=\"text\">");
            }
            td2.append(input)
        }else{
            var td2 = $("<td></td>");
        }
        // td加入tr中
        tr.append(td1);
        tr.append(td2);
        // div加入到表格中
        $table.append(tr);
    }
}
// 提交方法
function button_submit(){
    var formData = {};
    $('.myinput').each(function () {
        var key = $(this).attr("name")
        var value = $(this).val()
        formData[key] = value
    })
    $.post(
        "../../"+getTableName()+"submitservlet",
        {
            "formData" : formData
        },
        // 返回信息处理
        function (data) {
            if (data.message == true){
                alert("添加成功！");
            } else alert("添加失败！");
        },
        "json"
    );
}

// 重置表单
function button_reset(){
    $('input').each(function () {
        $(this).val("")
    });
}
// 返回列表
function returnList(){
    var url = window.location.origin;
    location.href = url + "/page/banlv_scenicManage/scenicManagelist.html";
}