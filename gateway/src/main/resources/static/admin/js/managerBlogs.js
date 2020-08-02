function showAdminAllBlogs(page) {
    $.ajax({
        url : "/admin/showAdminAllBlogs",
        type : "GET",
        data : {
            page : page
        },
        dataType : 'json', // 期待的响应数据类型
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                window.location.href = "/loginPage";
            } else {
                var arr = data.blogShowList;
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += "<tr>"
                        + "<td class='text-center' id='blog-title'>" + po.title + "</td>"
                        + "<td class='infor'>" + po.pubdate + "</td>"
                        + "<td class='infor'>" + po.views + "</td>"
                        + "<td class='infor'>" + po.categoryname + "</td>"
                        + "<td class='infor'>"
                        + "<img class='deleteBlog' src='../../img/删除.png' width='25px' onclick='deleteBlog("
                        + po.blogid
                        + ")'/>&nbsp;&nbsp;删除&nbsp;&nbsp;"
                        + "<a href='/queryBlog?blogId=" + po.blogid + "'><img class='queryBlog' src='../../img/修改.png' width='20px' /></a>&nbsp;&nbsp;修改"
                        + "</td>"
                        + "</tr>";
                }
                $("#adminShowBlogs").html(content);
                $("#currentPage").html(page);
                $("#totalPage").html(data.totalPage);
            }
        }
    });
}

showAdminAllBlogs(1);
function prePage() {
    // 获取当前页码
    var currentPage = parseInt($("#currentPage").html());
    if (currentPage > 1) {
        showAdminAllBlogs(currentPage - 1);
    }
}
function nextPage() {
    // 获取当前页码
    var currentPage = parseInt($("#currentPage").html());
    // 获取总页码
    var totalPage = parseInt($("#totalPage").html());
    if (currentPage < totalPage) {
        showAdminAllBlogs(currentPage + 1);
    }
}
function endPage() {
    // 获取总页码
    var totalPage = parseInt($("#totalPage").html());
    showAdminAllBlogs(totalPage);
}



function deleteBlog(blogId) {
    var blogTitle = $("#blog-title").val();
    var status = confirm("是否删除 " + blogTitle + " ?");
    if (!status) {
        return false;
    }
    $.ajax({
        url: "/admin/deleteBlog",
        type: "delete",
        data: {
            "blogId": blogId,
            "blogCount": 1,
        },
        dataType: "text",  //期待的响应数据类型
        success: function (data) {
            if (data == "删除成功") {
                alert(data);
                window.location.reload();
            } else if (data == "删除失败，该文章已被评论，请先删除评论"){
                alert(data);
                window.location.reload();
            }  else if (data == "删除失败"){
                alert(data);
                window.location.reload();
            } else {
                alert(data);
                window.location.reload();
            }
        },
        error:function () {
            alert("系统出错，请联系帅气的管理员");
        }
    });
}


// 修改图书内容
function updatebook() {
    // alert("你好");
    $.ajax({
        url : "updateBooks",
        type : "post",
        data : {
            // 将input中内容赋值给bname
            "bid" : $("#ubid").val(),
            // 将修改后的赋值到input中
            "bname" : $("#ubname").val(),
            "author" : $("#uauthor").val(),
            "price" : $("#uprice").val(),
            "bcount" : $("#ubcount").val(),
            "pubdate" : $("#upubdate").val(),
            "press" : $("#upress").val(),
            "img" : $("#uimg").val(),
            "intro" : $("#uintro").val()
        },
        dataType : 'json',
        success : function(data) {
            // alert("修改");
            if (data == "更新图书失败，请重新输入字段") {
                $("#updatebookinfo").html(data);
            } else {
                alert(data);
                window.location.reload();
            }
        }
    })
}