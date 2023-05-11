var tableName = ""
var zhujian = ""
var id = ""

function init(){
    this.setTableName()
    //this.initTableTitle('更新XX表')
    this.initButton("button_reset","重置","button_reset()")
    this.initButton("button_submit","提交","button_submit()")
    this.changeDispalyById('turningnav')
    this.initAttributeColumn()
}
// 获取表名和主键
function setTableName(){
    var search = window.location.search;
    //获取当前表名
    var index = search.indexOf("=");
    var search = search.substring(index+1);
    index = search.indexOf("&");
    tableName = search.substring(0,index);
    // 获取主键与id
    var index1 = search.indexOf("&");
    var index2 = search.indexOf("=");
    zhujian = search.substring(index1 + 1,index2);
    id = search.substring(index2 + 1);
}
// 获取映射表
function getTableName(){
    return tableName;
}
// 初始化页面标题
function initTitle(titleName) {
    $('title').html(titleName)
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
// 通过主键获取实体类
function getBeanById(tableName,id){
    var formData = {}
    formData[zhujian] = id
    var bean = [];
    $.ajax({
        type : "post",
        url : "../../" + getTableName() +"searchservlet",
        data : {
            "currentPage":1,
            "rows":5,
            "formData" : formData
        },
        async : false,
        success : function(data){
            if (data.page.list.length > 0){
                bean = data.page.list[0];
            }
        }
    });
    return bean;
}
// 初始化输入表单
function initAttributeColumn() {
    var trList = getTableTr(tableName);
    var bean = getBeanById(tableName,id)
    var $table = $("#table");
    for (var i = 0; i < trList.length; i++) {
        // 初始化行
        var tr = $("<tr id='tableTr" + i + "'></tr>");
        // 添加行至表格末尾
        $("#tableTr").after(tr);
        // 初始化td
        var td1 = $("<td>" + trList[i].attrName + "</td>");
        var td2 = $("<td></td>");
        if (trList[i].attrName == zhujian) {
            var input = $("<input id=" + trList[i].attrValue +" name = '"+ trList[i].attrValue +"' class = 'myinput' type=\"text\" disabled=\"disabled\">" );
            var text = $("<text\">主键 不可修改</text> <br></br>");
            td2.append(text);
        }else {
            var input = $("<input id=" + trList[i].attrValue +" name = '"+ trList[i].attrValue +"' class = 'myinput' type=\"text\">");
        }
        input.val($(bean).attr(trList[i].attrValue))
        if (!trList[i].attrShow) {
            input.css('display','none');
            var text = $("<text>" + $(bean).attr(trList[i].attrValue) + "</text>");
            td2.append(text)
        }
        td2.append(input)
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
        "../../"+getTableName()+"updateservlet",
        {
            "formData" : formData
        },
        // 返回信息处理
        function (data) {
            if (data.message == true){
                alert("更新成功！");
            } else alert("更新失败！");
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
    location.href = url + "/page/banlv_scenicZone_scenicSpot/scenicZone_scenicSpotlist.html";
}