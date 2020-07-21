function showLastArticle() {
    $.ajax({
        url:"/admin/showLastArticle",
        type:"get",
        dataType:"json",
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                window.location.href = "/loginPage";
            } else {
                var arr = data.blogContents;
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += "<tr>"
                            + "<td style='width: 500px;'><div style='float: left;'>文章</div><div  class='blogInroduceLimit'>" + po.title + "<br>" + po.introduce + "</div></td>"
                            +"<td>" + po.views + "<br>阅读数</td>"
                            +"<td>" + po.commentcount + "<br>评论数</td>"
                            +"<td>" + po.likes + "<br>点赞数</td>"
                            +"</tr>";
                }
                $("#lastArticleList").html(content);
            }
        }
    });
}
showLastArticle();


function showLastComment() {
    $.ajax({
        url:"/admin/showLastComment",
        type:"get",
        dataType:"json",
        success: function (data) {
            if (data.msg == "请先登录") {
                alert(data.msg);
                window.location.href = "/loginPage";
            } else {
                var arr = data.commentShows;
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += " <tr>"
                        + "<td><div  style='text-align: left'>"
                        + po.user
                        + "&nbsp;&nbsp;&nbsp;"
                        + po.commentdate
                        + "&nbsp;&nbsp;&nbsp;评论了你的文章&nbsp;&nbsp;&nbsp;"
                        + po.title+" ：</div><div style='padding-right: 800px'>"
                        + "“"
                        + po.commentcontent
                        + "”</div></td>"
                        + "</tr>";
                }
                $("#lastCommentList").html(content);
            }
        }
    });
}
showLastComment();