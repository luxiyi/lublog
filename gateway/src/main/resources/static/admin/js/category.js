function categoryList() {
    $.ajax({
        url: "/admin/listBlogCategory",
        type: "get",
        dataType: "json",
        success: function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<div class='categoryDiv'>"
                    + po.categoryname
                    + "<img id='deleteCategory' src='/admin/img/deleteIcon.png' "
                    + "onclick=\"deleteCategory('" + po.categoryname + "')\" "
                    + "style='width: 20px;padding-left: 5px;cursor: pointer' >"
                    + "</div>";
            }
            $("#categoryBox").html(content);
        }
    });
}

categoryList();

function addCategory() {
    var categoryName = $("#categoryName").val();
    if (categoryName.match("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？《》]")) {
        alert("请不要输入特殊字符!");
        window.location.reload();
        return false;
    }
    $.ajax({
        url: "/admin/addCategory",
        type: "post",
        data: {
            "categoryName": $("#categoryName").val(),
        },
        dataType: "text",
        success: function (msg) {
            if (msg == "添加 " + categoryName + " 分类成功") {
                alert(msg);
                window.location.reload();
            } else if (msg == "添加的分类为空，请输入需要添加的分类") {
                alert(msg);
            } else if (msg == categoryName + " 分类已存在，请重新输入需要添加的分类") {
                alert(msg);
            } else if (msg == "添加的分类名超过限制长度，请重新输入需要添加的分类"){
                alert(msg);
            } else if (msg == "添加 " + categoryName + " 分类失败") {
                alert(msg);
            } else {
                alert("未知错误");
            }


        }
    });
}

function deleteCategory(categoryName) {
    var status = confirm("是否删除 " + categoryName + " 分类?");
    if (!status) {
        return false;
    }
    $.ajax({
        url: "/admin/deleteCategory",
        type: "post",
        data: {
            "categoryName": categoryName
        },
        dataType: "json",
        success: function (data) {
            var msg = data.msg;
            if (msg == "删除 " + categoryName + " 分类成功") {
                alert(msg);
                window.location.reload();
            } else if(msg == categoryName + " 分类有博客使用，无法删除"){
                alert(msg);
            } else if(msg == "删除 " + categoryName + " 分类失败"){
                alert(msg);
            } else {
                alert("未知错误");
            }
        }
    });
}


function tagList() {
    $.ajax({
        url: "/admin/listBlogTag",
        type: "get",
        dataType: "json",
        success: function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<div class='tagDiv'>"
                    + po.tagname
                    + "<img src='/admin/img/deleteIcon.png'"
                    + "onclick=\"deleteTag('" + po.tagname + "')\" "
                    + "style='width: 20px;padding-left: 5px;cursor: pointer'>"
                    + "</div>";
            }
            $("#tagBox").html(content);
        }
    });
}

tagList();


function addTag() {
    var tagName = $("#tagName").val();
    if (tagName.match("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？《》]")) {
        alert("请不要输入特殊字符!");
        window.location.reload();
        return false;
    }
    $.ajax({
        url: "/admin/addTag",
        type: "post",
        data: {
            "tagName": $("#tagName").val(),
        },
        dataType: "text",
        success: function (msg) {
            if (msg == "添加 " + tagName + " 标签成功") {
                alert(msg);
                window.location.reload();
            } else if (msg == "添加的标签为空，请输入需要添加的标签") {
                alert(msg);
            } else if (msg == tagName + " 标签已存在，请重新输入需要添加的标签") {
                alert(msg);
            } else if (msg == "添加的标签名超过限制长度，请重新输入需要添加的标签"){
                alert(msg);
            } else if (msg == "添加 " + tagName + " 标签失败") {
                alert(msg);
            } else {
                alert("未知错误");
            }
        }
    });
}

function deleteTag(tagName) {
    var status = confirm("是否删除 " + tagName + " 标签?");
    if (!status) {
        return false;
    }
    $.ajax({
        url: "/admin/deleteTag",
        type: "post",
        data: {
            "tagName": tagName
        },
        dataType: "json",
        success: function (data) {
            var msg = data.msg;
            // alert(msg)
            if (msg == "删除 " + tagName + " 标签成功") {
                alert(msg);
                window.location.reload();
            } else if(msg == "删除 " + tagName + " 标签失败"){
                alert(msg);
            } else if(msg == tagName + " 标签有博客使用，无法删除"){
                alert(msg);
            } else {
                alert("未知错误");
            }
        }

    });
}