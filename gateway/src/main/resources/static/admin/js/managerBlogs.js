function showAdminAllBlogs(page) {
    $.ajax({
        url : "/admin/showAdminAllBlogs",
        type : "GET",
        data : {
            page : page
        },
        dataType : 'json', // 期待的响应数据类型
        success: function (data) {
                var arr = data.blogShowList;
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += "<tr>"
                        + "<td class='text-center' id='blog-title'>" + po.title + "</td>"
                        + "<td class='infor'>" + po.publishTime + "</td>"
                        + "<td class='infor'>" + po.views + "</td>"
                        + "<td class='infor'>" + po.categoryName + "</td>"
                        + "<td class='infor'>"
                        + "<img class='deleteBlog' src='../../img/删除.png' width='25px' onclick='deleteBlog("
                        + po.blogId
                        + ")'/>&nbsp;&nbsp;删除&nbsp;&nbsp;"
                        + "<a href='/queryBlog?blogId=" + po.blogId + "'><img class='queryBlog' src='../../img/修改.png' width='20px' /></a>&nbsp;&nbsp;修改"
                        + "</td>"
                        + "</tr>";
                }
                $("#adminShowBlogs").html(content);
                $("#currentPage").html(page);
                $("#totalPage").html(data.totalPage);

        },
        error:function () {
            alert("系统出错，请联系帅气的管理员");
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


