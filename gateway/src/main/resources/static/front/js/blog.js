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
                + "</i><blockquote class='introduce-style' style=''><p>"
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


function findcommets() {
    $.ajax({
        url: "findComments",
        type: "get",
        data: {
            "blogId": id
        },
        dataType: 'json',
        success: function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<div class='comment-inner'><div class='comment-header'><div class='asset-meta'><p><span class='byline'><span class='vcard author'>"
                        + po.user
                        + "</span>评论：</span></p></div></div>"
                        + "<div class='comment-content' id='comment-quote-420375'><p>"
                        + po.commentcontent
                        + "</p></div>"
                        + "<div class='comment-footer'><div class='comment-footer-inner'><p><abbr class='published' title='"
                        + po.commentdate + "'>" + po.commentdate + "</abbr>| <a href='#'>#</a>| <a href='#comment-text' title='回复' onclick='return CommentQuote('comment-quote-420375','Jack');'>回复</a></p></div></div></div>";
            }
            $("#comment").html(content);

        }
    });
}

findcommets();

function insertcomment() {
    $.ajax({
        url: "insertComment",
        type: "post",
        data: {
            "ccont": $("#comment-author").val(),
            "bid": id
        },
        dataType: "text",
        success: function (data) {
            if (data == "评论失败，请先登录") {
                alert(data);
            } else if (data == "请输入评价") {
                alert(data);
            } else {
                alert(data);
                window.location.reload();
            }
        }
    });
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