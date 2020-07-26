var url = window.location.href;
var id = url.split("?blogId=")[1];

function showBlog() {
    $.ajax({
        url: "showBlog",
        type: "post",
        data: {
            "blogId": id
        },
        dataType: 'json',
        success: function (data) {
            var content = "<h1 class='title'>"
                + data.title
                + "</h1><div class='meta'><i class='fa fa-calendar-o'>&nbsp;"
                + data.pubdate
                + "</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-user'>&nbsp;"
                + data.author + "</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "<i class=\"fa fa-tag\"></i>&nbsp;<span class='tags'><a class='tag' href='/tag/"
                + data.tagname
                + "' target='_blank' rel='tag'>"
                + data.tagname
                + "</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class=\"fa fa-list\"></i>&nbsp;<span class='categorys'><a class='category' href='/category/"
                + data.categoryname
                + "' target='_blank' rel='category'>"
                + data.categoryname
                + "</a></span>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-heart'>&nbsp;"
                + data.likes
                + "</i><blockquote class='introduce-style' style='text-align: left'><p>"
                + data.introduce
                + "</p></blockquote><img src='"
                + data.blogcover
                + "' class='img-responsive' alt='' data-action='zoom' style='height: 400px;margin-left: 200px'></div>";

            $("#blogIntroduce").html(content);

        },
        error: function () {
            alert("error");
        }
    });
}

showBlog();


function findcomments(page) {
    $.ajax({
        url: "findComments",
        type: "get",
        data: {
            "blogId": id,
             page : page
        },
        dataType: 'json',
        success: function (data) {
            var arr = data.comments;
            var content = "";
            for (var i = 0; i < arr.length; i++) {
                var po = arr[i];
                content += "<div class='comment-inner' ><div class='comment-content' id='comment-quote-" + po.commentid + "'><div class='asset-meta'>"
                         + po.observer + "&nbsp;：" + po.commentcontent + "</div></div>"
                    + "<div class='comment-footer'><div class='comment-footer-inner'><p><abbr class='published' title='"
                    + po.commentdate + "'>" + po.commentdate + "</abbr>| <a href='#'>#</a>| <a href='#comment-content' title='回复" + po.observer + "的这条评论' "
                    + "onclick=\"quote_comment('comment-quote-"+po.commentid+"','"+po.observer+"')\">回复</a></p></div></div></div>";
            }
            $("#comment").html(content);
            $("#currentpage").html(page);
            $("#totalpage").html(data.totalPage);

        }
    });
}

findcomments(1);
function prepage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    if (current > 1) {
        findcomments(current - 1);
    }
}
function nextpage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    if (current < totalpage) {
        findcomments(current + 1);
    }
}
function endpage() {
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    findcomments(totalpage);
}

//引用js功能
function quote_comment(contentId, observer) {
    // var q = document.getElementById("#"+contentId+"");
    // var w =document.getElementsByTagName("#comment-content-p");
    // alert(q);
    var content = $("#"+contentId+"").text();
    // $(document).ready(function()
    // {
    //     $("button").click(function()
    //     {
    //         $(".o div p:eq(0)").css("background","red");
    //     });
    // });
    var quote_content = '<blockquote><pre>引用' + observer + '的评论：' + content.trim() + '</pre></blockquote>';
    document.getElementById("comment-content").value = quote_content;
    document.getElementsByTagName('body')[0].scrollTop=document.getElementsByTagName('body')[0].scrollHeight;
}


function insertComment() {
    var observer = $("#comment-observer").val();
    var contact = $("#comment-contact").val();
    var commentContent = $("#comment-content").val();
    if (typeof (observer) == undefined || observer == null || observer == "") {
        alert("请输入您的昵称");
        return;
    }

    if (typeof (contact) == undefined || contact == null || contact == "") {
        alert("请输入您的QQ或微信");
        return;
    }

    if (typeof (commentContent) == undefined || commentContent == null || commentContent == "") {
        alert("请输入评论内容");
        return;
    }

    $.ajax({
        url: "addBlogComment",
        type: "post",
        data: {
            "blogId": id,
            "observer": observer,
            "contact": contact,
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






function addReplyComment() {

    var div = "<div class='comment-inner' id='comment-quote-'><div class='comment-header'><div class='asset-meta'><p><span class='byline'><span class='vcard author'>评论者 &nbsp;&nbsp;</span>回复： 被评论者</span></p></div></div>"
        + "<div class='comment-content' ><p>内容</p></div>"
        + "<div class='comment-footer'><div class='comment-footer-inner'><p><abbr class='published' title='时间'>时间</abbr>| <a href='#'>#</a>| <a href='#comment-text' title='回复' onclick='return CommentQuote('comment-quote-420375','Jack');'>回复</a></p></div></div></div>";
    $('#asdasdasdasds').after(div)
}

function addcart(pgid, pgname, pimg, pprice) {
    $.ajax({
        url: "addCart",
        type: "post",
        data: {
            "bid": pgid,
            "bname": pgname,
            "img": pimg,
            "ccount": 1,
            "price": pprice

        },
        dataType: 'json', // 期待的响应数据类型
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                // window.location.reload();
            } else if (data.msg == "谢谢点赞") {
                alert(data.msg);
                window.location.reload();
            } else if (data.msg == "已经点过赞了哦") {
                alert(data.msg);
                window.location.reload();
            } else {
                alert(data.msg);
                window.location.reload();
            }
        },
        error: function () {
            alert("系统错误，请联系帅气的管理员");
        }
    });
}