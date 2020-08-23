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
                // $("#blogIntroduce").val(data.introduce);
                $("#blogAuthor").val(data.author);
                $("#blogCover").val(data.blogCover);
                $("#blogTag").val(data.tagName);
                $("#blogCategory").val(data.categoryName);
                // $("#blogContent").val(data.content);
                tagName = data.tagName;
                categoryName = data.categoryName;
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
        url: '/listBlogTag',
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
        url: '/listBlogCategory',
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


/* 修改发送文章*/
function writeBlog() {//提交
    if (doCheck()) {
        //debugger;
        var title = $("#blogTitle").val();
        var content = $("#blogContent").val();
        var tagId = $("#blogTag").val();
        var categoryId = $("#blogCategory").val();
        var author = $("#blogAuthor").val();
        var introduce = $("#blogIntroduce").val();
        var blogCover = $("#blogCover").val();
        $.ajax({
            type: "PUT",
            url: '/admin/updateBlog',
            data: {
                'blogId': blogId,
                'title': title,
                'content': content,
                'tagId': tagId,
                'categoryId': categoryId,
                'author': author,
                'introduce': introduce,
                'blogCover': blogCover
            },
            dataType: 'text',
            cache: false,
            success: function (data) {
                if (data == "更新成功!") {
                    alert(data);
                    window.location.href = "/admin/index";
                } else if (data == "标题已存在，请重新命名标题") {
                    alert(data);
                } else {
                    alert(data);
                }
            },
            error: function () {
                alert("系统问题，请联系帅系的管理员");
            }
        });
    }
}


function doCheck() {//校验
    var title = $("#blogTitle").val();
    var content = $("#blogContent").val();
    var author = $("#blogAuthor").val();
    var introduce = $("#blogIntroduce").val();
    var blogCover = $("#blogCover").val();
    if (typeof (title) == undefined || title == null || title == "") {
        alert("请填写文章标题!");
        return false;
    }

    if (typeof (content) == undefined || content == null || content == "") {
        alert("请填写文章内容!");
        return false;
    }

    if (typeof (author) == undefined || author == null || author == "") {
        alert("请填写文章作者署名!");
        return false;
    }

    if (typeof (author) == undefined || author == null || author == "") {
        alert("请填写文章作者署名!");
        return false;
    }

    if (typeof (introduce) == undefined || introduce == null || introduce == "") {
        alert("请填写文章简介!");
        return false;
    }

    if (typeof (blogCover) == undefined || blogCover == null || blogCover == "") {
        alert("请上传封面图片!");
        return false;
    }

    return true;
}