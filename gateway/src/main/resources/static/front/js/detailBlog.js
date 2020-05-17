var url = window.location.href;
var id = url.split("?blogid=")[1];

function findonebook() {
    // var url =window.location.href;
    // var id=url.split("?bid=")[1];
    $.ajax({
        url: "findOneBook",
        type: "post",
        data: {
            "blogid": id
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
                + data.categoryname
                + "' target='_blank' rel='tag'>"
                + data.categoryname
                + "</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='fa fa-heart'>&nbsp;"
                + data.likes
                + "</i></div>";

            $("#blogIntroduce").html(content);

        },
        error: function () {
            alert("error");
        }
    });
}

findonebook();


function findcommets() {
    $.ajax({
        url: "findComments",
        type: "post",
        data: {
            "blogid": id
        },
        dataType: 'json',
        success: function (data) {
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<tr>"
                    + "<td class='infor1' style='padding-left: 100px'>" + po.user + "</td>"
                    + "<td class='infor2' style='padding-left: 120px'>" + po.commentdate + "</td>"
                    + "<td class='infor3' style='padding-left: 100px'>" + po.commentcontent + "</td>"

                    + "</tr>";
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
            "ccont": $("#ccont").val(),
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