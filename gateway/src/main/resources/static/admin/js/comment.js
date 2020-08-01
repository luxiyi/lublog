var id;
function commentList(ppage) {
    $.ajax({
        url : "/admin/showAllComments",
        type : "get",
        data : {
            page : ppage
        },
        dataType : 'json', // 期待的响应数据类型
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                window.location.href = "/loginPage";
            } else {
                var arr = data.commentShows;
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += "<tr>"
                        + "<td class='infor'>"
                        + "<img id='remove-comment' src='../../img/删除.png' width='25px' onclick='deleteComment("
                        + po.commentid
                        + ")'/>&nbsp;&nbsp;删除&nbsp;&nbsp;<a href='#comment-content' title='回复" + po.observer + "的这条评论' "
                        + "onclick=\"quote_comment('comment-quote-"+po.commentid+"','"+po.observer+"','"+po.blogid+"')\">"
                        + "<img id='reply-comment' src='../../img/修改.png' width='20px' /></a>&nbsp;&nbsp;回复"
                        + "</td>"
                        + "<td class='infor'>" + po.observer + "</td>"
                        + "<td class='infor'>" + po.commenter + "</td>"
                        + "<td class='infor'>" + po.commentdate + "</td>"
                        + "<td class='infor' style='text-align: left;width: 400px' id='comment-quote-"+po.commentid+"'><div>" + po.commentcontent + "</div></td>"
                        + "</tr>";
                }
                $("#commentList").html(content);
                $("#currentpage").html(ppage);
                $("#totalpage").html(data.totalPage);
            }
        }
    });
}





commentList(1);
function prepage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    if (current > 1) {
        commentList(current - 1);
    }
}
function nextpage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    if (current < totalpage) {
        commentList(current + 1);
    }
}
function endpage() {
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    commentList(totalpage);
}



function deleteComment(commentId) {
    var status = confirm("是否删除该评论?");
    if (!status) {
        return false;
    }
    $.ajax({
        url: "/admin/deleteComment",
        type: "delete",
        data: {
            "commentId": commentId,
            "commentCount": 1,
        },
        dataType: "text",  //期待的响应数据类型
        success: function (data) {
            if (data == "删除评论成功") {
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


//引用js功能
function quote_comment(contentDiv, observer, blogId) {
    id = blogId;
    var content = $("#"+contentDiv+"").text();
    var quote_content = '<blockquote><pre>引用' + observer + '的评论：' + content.trim() + '</pre></blockquote>';
    document.getElementById("comment-content").value = quote_content;
    document.getElementsByTagName('body')[0].scrollTop=document.getElementsByTagName('body')[0].scrollHeight;
}

function insertComment() {
    var commentContent = $("#comment-content").val();
    if (typeof (commentContent) == undefined || commentContent == null || commentContent == "") {
        alert("请输入评论内容");
        return;
    }

    $.ajax({
        url: "/admin/addBlogComment",
        type: "post",
        data: {
            "blogId": id,
            "observer": adminUser,
            "contact" : contact,
            "commentContent": commentContent
        },
        dataType: "text",
        success: function (data) {
            if (data == "评论成功") {
                alert(data);
                window.location.reload();
            } else {
                alert(data);
            }
        },
        error: function () {
            alert("系统问题，请联系帅气的管理员");
        }
    });

}


// 删除书籍
function removeComment(pgid) {
    $.ajax({
        url : "deleteBooks",
        type : "post",
        data : {
            "bid" : pgid,
            "bcount" : 1
        },
        dataType : "text",
        success : function(data) {
            if(data=="请先登录"){
                alert(data);
            }else if (data == "你不是管理员，无法操作") {
                alert(data);
            } else if (data == "该博客已被点赞,不能被删除") {
                alert(data);
            } else {
                alert(data);
                window.location.reload();
            }
        }
    });
}


// 找到一本书的内容
function querybook(bid) {
    $.ajax({
        url : "queryBooks",
        type : "post",
        data : {
            "bid" : bid,
        },
        dataType : 'json',
        success : function(data) {
            // 将内容放进div中
            if(data.info=="你不是管理员，无法操作"){
                alert(data.info);
            }else if (data.info == "请先登录") {
                alert(data.info);
            } else {
                $("#ubid").val(bid);
                $("#ubname").val(data.book.bname);
                $("#uauthor").val(data.book.author);
                $("#uprice").val(data.book.price);
                $("#ubcount").val(data.book.bcount);
                $("#upubdate").val(data.book.pubdate);
                $("#upress").val(data.book.press);
                $("#uimg").val(data.book.img);
                $("#uintro").val(data.book.intro);
                $("#updatebookform").modal('show');
            }
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