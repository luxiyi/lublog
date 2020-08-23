var url = window.location.href;
var planId = url.split("?planId=")[1];
var tagName;
var categoryName;
// 查询文章的内容
function showCreatDoPlanDetail() {
    $.ajax({
        url : "/admin/showCreatDoPlanDetail",
        type : "GET",
        data : {
            "planId" : planId
        },
        dataType : 'json',
        success : function(data) {
            var a =data.doPlanDetail;
            // 将内容放进div中
            // window.location.reload();
            $("#doPlanDetail").val(a);
        },
        error:function () {
            alert("系统出错，请联系帅气的管理员");
        }
    });
}
showCreatDoPlanDetail();


/* 文章标签下拉框数据绑定 */
function tagSelect() {//初始化数据
    $.ajax({
        type: "GET",
        url: '/admin/listBlogTag',
        dataType: 'json',
        contentType: "application/json",
        cache: false,
        success: function (data) {
            data = eval(data);
            buildTagOption(data);
        }
    });
}
function buildTagOption(data) {//构建下拉框数据
    var optionStr = "";
    for (var i = 0; i < data.length; i++) {
        if (data[i].tagName == tagName) {
            optionStr += "<option value='" + data[i].tagId + "' selected='selected'>";
        } else {
            optionStr += "<option value='" + data[i].tagId + "'>";
        }
        optionStr += data[i].tagName;
        optionStr += "</option>";
    }
    $("#blogTag").append(optionStr);
}


/* 文章类别下拉框数据绑定 */

function categorySelect() {//初始化数据
    $.ajax({
        type: "GET",
        url: '/admin/listBlogCategory',
        dataType: 'json',
        contentType: "application/json",
        cache: false,
        success: function (data) {
            data = eval(data);
            buildCategoryOption(data);
        }
    });
}
function buildCategoryOption(data) {//构建下拉框数据
    var optionStr = "";
    for (var i = 0; i < data.length; i++) {
        if (data[i].categoryName == categoryName) {
            optionStr += "<option value='" + data[i].categoryId + "' selected='selected'>";
        } else {
            optionStr += "<option value='" + data[i].categoryId + "'>";
        }

        optionStr += data[i].categoryName;
        optionStr += "</option>";
    }
    $("#blogCategory").append(optionStr);
}