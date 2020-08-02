var url = window.location.href;
var blogId = url.split("?blogId=")[1];
var tagName;
var categoryName;
// 查询文章的内容
function showUpdateBlog() {
    $.ajax({
        url : "/admin/showUpdateBlog",
        type : "GET",
        data : {
            "blogId" : blogId,
        },
        dataType : 'json',
        success : function(data) {
            // 将内容放进div中
            if(data.msg=="你不是管理员，无法操作"){
                alert(data.msg);
            } else if (data.msg == "请先登录") {
                alert(data.msg);
            } else if (data.msg == "查询失败，请联系帅气的管理员") {
                alert(data.msg);
            } else {
                $("#blogTitle").val(data.title);
                $("#blogIntroduce").val(data.introduce);
                $("#blogAuthor").val(data.author);
                $("#blogCover").val(data.blogcover);
                $("#blogTag").val(data.tagname);
                $("#blogCategory").val(data.categoryname);
                $("#blogContent").val(data.content);
                tagName = data.tagname;
                categoryName = data.categoryname;
            }
        },
        error:function () {
            alert("系统出错，请联系帅气的管理员");
        }
    });
}

showUpdateBlog();


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
        if (data[i].tagname == tagName) {
            optionStr += "<option value='" + data[i].tagid + "' selected='selected'>";
        } else {
            optionStr += "<option value='" + data[i].tagid + "'>";
        }
        optionStr += data[i].tagname;
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
        if (data[i].categoryname == categoryName) {
            optionStr += "<option value='" + data[i].categoryid + "' selected='selected'>";
        } else {
            optionStr += "<option value='" + data[i].categoryid + "'>";
        }

        optionStr += data[i].categoryname;
        optionStr += "</option>";
    }
    $("#blogCategory").append(optionStr);
}