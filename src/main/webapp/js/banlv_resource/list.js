var totalCount= 0
var totalPage = 0
var currentPage = 0
var rows = 0
var tableName = ""
var zhujian = ""
// 页面初始化
function init(){
    this.setTableName("resource")
    this.initTableTitle('')
    //this.initSearch('attribute_name','attribute_name','XX')
    this.initButton("button_add","添加","button_add()")
    //this.initTitle("XX列表")
    this,initTableText()
    this.initTableTr(true)
    this.getTableData()
    this.search_check()
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
// 初始化表格标题
function initTableTitle(tablename) {
    var $mainContainer = $("#mainContainer");
    // 初始化标题
    var tableTitle = $("<h3 style=\"text-align: center\">"+tablename+"</h3>");
    // 标题加入到主容器中
    $mainContainer.prepend(tableTitle);
}
// 初始化搜索框
function initSearch(searchId, searchName, searchText) {
    var $searchform = $("#searchform");
    // 初始化div
    var searchdiv = $("<div class=\"form-group\" id=\"searchdiv\"> </div>");
    // 初始化label
    var searchlabel = $("<label for=\"exampleInputName\">"+searchText+"</label>");
    // 初始化input
    var searchinput = $("<input type=\"text\" class=\"searchinput\" id=\""+searchId+"\"name=\""+searchName+"\" value=\"\">")
    // input和label加入到div中
    searchdiv.prepend(searchlabel,searchinput);
    // div加入到高级搜索框中
    $searchform.prepend(searchdiv);
}
// 初始化按钮
function initButton(buttonId, buttonText, buttonFuction) {
    var $buttondiv = $("#buttondiv");
    // 初始化按钮
    var button = $("<button id=\""+buttonId+"\" class=\"btn btn-primary\" onClick=\""+buttonFuction+"\">"+buttonText+"</button>");
    // 按钮加入到按钮div中
    $buttondiv.prepend(button);
}
// 初始化表头
function initTableTr(ifoperation) {
    var trList = getTableTr(getTableName());
    var $table = $("#table");
    // 获取tr
    var tr = $("#table>tr");
    // 初始化tr
    tr = $("<tr class=\"success\" id=\"tableTr\"> </tr>");
    for (var i = 0; i < trList.length; i++) {
        if (trList[i].attrShow){
            // 初始化th
            var th = $("<th>"+trList[i].attrName+"</th>");
            // th加入tr中
            tr.append(th);
        }
    }
    if (ifoperation) {
        // 初始化操作th
        var th = $("<th>操作</th>");
        // th加入tr中
        tr.append(th);
    }
    // div加入到表格中
    $table.append(tr);
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
// 初始化表单page信息
function initTableText(){
    //获取当前页与每页记录数
    var search = window.location.search;
    //获取当前页
    var index = search.indexOf("=");
    var search = search.substring(index+1);
    index = search.indexOf("&");
    currentPage = search.substring(0,index);
    //获取当前页行数
    var index = search.indexOf("=");
    rows = search.substring(index+1);
    if (currentPage == "") currentPage = 0;
    if (rows == "") rows = 5;
}
// 更新表单page信息
function updateTableText(page){
    // 获取总数据量
    totalCount =page.totalCount;
    // 获取总页数
    totalPage = page.totalPage;
    // 设置当前页数
    currentPage = page.currentPage;
    // 设置当前每页显示个数
    rows = page.rows;

    //更新页数
    $("#totalCount").html(totalCount);
    $("#totalPage").html(totalPage);
    $("#currentPage").html(currentPage);
    $("#rows").html(rows);

    updateTurningnav(page)
}
// 更新表单翻页工具
function updateTurningnav(page){
    // 获取总页数
    totalPage = page.totalPage;
    // 设置当前页数
    currentPage = page.currentPage;
    // 获取网址
    var index = window.location.href.indexOf("?");
    // 判断是否显示
    if(currentPage == 1) {
        dispalyById("previous_button");
    } else {
        showById("previous_button");
        var previousURL = window.location.href.substring(0,index) + "?currentPage=" + (currentPage - 1) + "&rows=" + rows;
        $("#previous_button > a").attr("href",previousURL);
    }
    if (currentPage == totalPage) {
        dispalyById("next_button");
    } else {
        showById("next_button");
        var nextURL = window.location.href.substring(0,index) + "?currentPage=" + (currentPage + 1) + "&rows=" + rows;
        $("#next_button > a").attr("href",nextURL);
    }
}
// 获取表单数据
function getTableData(){
    $.post(
        "../../"+getTableName()+"listservlet",
        {
            "currentPage":currentPage,
            "rows":rows
        },
        // 返回信息处理
        function (data) {
            // 重置表单
            resetTable()
            // 更新表单page信息
            updateTableText(data.page);
            // 更新表单信息
            initTableData(data,tableName);
        },
        "json"
    );
}
// 初始化表单数据
function initTableData(tableData,tableName){
    // 获取表头信息
    var trList = getTableTr(tableName);
    // 更新表格
    var $table = $("#table");
    // 当前行数
    var currentrows = window.totalCount - (window.rows * (window.currentPage - 1))
    // 索引
    var index = currentrows < window.rows ? currentrows : window.rows;
    for (var i = 0; i < index; i++) {
        // 获取行数据
        var trData = tableData.page.list[i];
        for (var k = 0; k < trList.length; k++) {
            if (trList[k].attrValue == tableName + "_id") {
                zhujian = trList[k].attrValue;
            }
        }
        // 初始化行
        var tr = $("<tr id='tableTr" + $(trData).attr(zhujian) + "'></tr>");
        // 添加行至表格末尾
        $("#tableTr").after(tr);
        // 获取表格头
        for (var j = 0; j < trList.length; j++) {
            if (trList[j].attrShow) {
                // 获取标签
                var tdColumn = trList[j].attrValue;
                // 初始化td
                var td = $("<td>" + $(trData).attr(tdColumn) + "</td>");
                // td加入tr中
                tr.append(td);
            }
        }
        // 操作按钮
        var operation = $("<td></td>");
        var updateButton = $("<a class=\"btn btn-default btn-sm\" onclick='button_update(this,event)'>修改</a>");
        var deleteButton = $("<a class=\"btn btn-default btn-sm\" onclick='button_delete(this,event)'>删除</a>");

        operation.append(updateButton, deleteButton);

        tr.append(operation)

        $table.append(tr);
    }
}
// 新增页面跳转
function button_add() {
    location.href = getURL()+"add.html?zhujian=" + zhujian;
}
// 更新方法
function button_update(a,b) {
    if (confirm("确定修改？")) {
        var id = $(a).parent().parent().attr('id');
        location.href = getURL() + "update.html?tableName=" +getTableName()+"&"+ zhujian +"=" + getId(id);
    }
}
// 删除方法
function button_delete(a,b) {
    if (confirm("确定删除？")) {
        var id = $(a).parent().parent().attr('id');
        $.post(
            "../../"+getTableName()+"deleteservlet",
            {
                "id" : getId(id)
            },
            // 返回信息处理
            function (data) {
                if (data.message){
                    alert("删除成功！");
                    // 重置表单
                    resetTable()

                    getTableData();
                } else alert("删除失败！");
            },
            "json"
        );
    }
}
// 查询方法
function button_search(){
    var formData = {}
    $('.searchinput').each(function () {
        var key = $(this).attr("id")
        var value = $(this).val()
        formData[key] = value
    });
    $.post(
        "../../"+getTableName()+"searchservlet",
        {
            "currentPage":currentPage,
            "rows":rows,
            "formData" : formData
        },
        // 返回信息处理
        function (data) {
            // 重置表单
            resetTable()
            // 更新表单page信息
            updateTableText(data.page);
            // 更新表单信息
            initTableData(data,tableName);
        },
        "json"
    );
}

// 重置表单
function resetTable(){
    $('tr').each(function () {
        var id = $(this).attr("id")
        if (id == null) return;
        if (id.substring(0, 7) == 'tableTr' && id.length > 7) {
            $(this).remove();
        }
    });
}


// 重置查询表单
function search_reset(){
    $('#searchdiv > input').each(function () {
        $(this).val("")
    });
    // 空查询
    button_search()
}

// 查询表单检测是否为空
function search_check(){
    var num = 0;
    $('#searchform > #searchdiv > input').each(function () {
        num ++;
    });
    if (num == 0){
        changeDispalyById("searchbutton");
    }
}