var planThisYearDate = new Date();
function showLastBlogs() {
    $.ajax({
        url:"/admin/showLastBlogs",
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
                            + "<td style='width: 500px;'><div class='show-blog-left'>文章</div><div class='blogInroduceLimit'>" + po.title + "<br>" + po.introduce + "</div></td>"
                            +"<td class='show-blog-right'>" + po.views + "<br>阅读数</td>"
                            +"<td class='show-blog-right'>" + po.commentCount + "<br>评论数</td>"
                            +"<td class='show-blog-right'>" + po.likes + "<br>点赞数</td>"
                            +"</tr>";
                }
                $("#lastBlogList").html(content);

                $("#planThisYearDate").html(planThisYearDate);
            }
        }
    });
}
showLastBlogs();


function showLastComments() {
    $.ajax({
        url:"/admin/showLastComments",
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
                        + po.observer
                        + "&nbsp;&nbsp;&nbsp;"
                        + po.commentdate
                        + "&nbsp;&nbsp;&nbsp;评论了&nbsp;&nbsp;&nbsp;"
                        + po.commenter+" ：</div><div style='padding-right: 800px'>"
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
showLastComments();