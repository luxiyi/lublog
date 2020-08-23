function findAllBlog(ppage) {
    $.ajax({
        url: "findAllBlog",
        type: "get",
        data: {
            page: ppage
        },
        dataType: 'json', // 期待的响应数据类型
        success: function (data) {
            var arr = data.blogContents;
            if (arr == "") {
                $("#blogContents").html("<div class='background-intro'>" +
                    "<h1>暂时还没有内容哦！敬请期待！</h1>" +
                    "<h1>不要着急走，先看看<a href='/aboutMe' style='text-decoration: none;color: #d43f3a'>博主</a>是谁呀！</h1>" +
                    "</div>");
            } else {
                var content = "";
                for (var i = 0; i < arr.length; i++) {
                    var po = arr[i];
                    content += "<div class='panel'style='background-color:transparent'>"
                        + "<div class='panel-body'>"
                        + "<div id=image>"
                        + "<img src='"
                        + po.blogCover
                        + "' class='img-responsive'>"
                        + "</div>"
                        + "<div id='tother'><a href='blog?blogId="
                        + po.blogId
                        + "' style='text-decoration:none;'><font color='#cd5c5c'>"
                        + "<h2>"
                        + po.title
                        + " &nbsp;</h2></font></a>"
                        + "<h5><img src='../../img/人物.png' width='20px'/>&nbsp;原创&nbsp;"
                        + po.author
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/时间.png' width='20px'/>&nbsp;"
                        + po.publishTime
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/标签.png' width='20px'/>&nbsp;"
                        + po.tagName
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/短信.png' width='20px'/>&nbsp;评论&nbsp;"
                        + po.commentCount
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/浏览.png' width='20px'/>&nbsp;浏览&nbsp;"
                        + po.views
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/喜欢.png' width='26px'/>&nbsp;点赞&nbsp;"
                        + po.likes
                        + "</h5>"
                        + "<h5 class='blogContentLimit'>"
                        + po.introduce
                        + "</h5>"
                        + "</div>"
                        + "</div>" + "</div>";
                }
            var pageContent ="<li id='first' onclick='findAllBlog(" + 1 +")' ><span style=' background-color:transparent'>首页</span></li>"
                + "<li id='top' onclick='prepage()'><span style=' background-color:transparent'>上一页</span></li>"
                + "<li class='xifenye' id='xifenye'><span style=' background-color:transparent'><span id ='currentpage'>1</span>/ <span id='totalpage'>1</span></span></li>"
                + "<li id='down' onclick='nextpage()'><span style=' background-color:transparent'>下一页</span></li>"
                + "<li id='last' onclick='endpage()'><span style=' background-color:transparent'>末页</span></li>";
                $("#blogContents").html(content);
                $("#pagebox").html(pageContent);

                $("#currentpage").html(ppage);
                $("#totalpage").html(data.totalPage);
            }
        }
    });
}

findAllBlog(1);

function prepage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    if (current > 1) {
        findAllBlog(current - 1);
    }
}

function nextpage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    if (current < totalpage) {
        findAllBlog(current + 1);
    }
}

function endpage() {
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    findAllBlog(totalpage);
}


function findBlog() {
    $.ajax({
        url: "findBlog",
        type: "post",
        data: {
            findTitle: $("#find").val(),
        },
        dataType: 'json',
        success: function (data) {
            // alert(data);
            var arr = data;
            var content = "";
            for (var i = 0; i < arr.length; i++) {
                var po = arr[i];
                content += "<div class='panel panel-default'>"
                    + "<div class='panel-body'>"
                    + "<div id=image>"
                    + "<img src='"
                    + po.blogCover
                    + "' class='img-responsive'>"
                    + "</div>"
                    + "<div id='tother'><a href='sendDatil?bid="
                    + po.blogId
                    + "' style='text-decoration:none;'><font color='#cd5c5c'>"
                    + "<h2>"
                    + po.title
                    + " &nbsp;</h2></font></a>"
                    + "<h5><img src='../../img/人物.png' width='20px'/>&nbsp;"
                    + po.author
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/短信.png' width='20px'/>&nbsp;"
                    + po.commentCount
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/原创.png' width='20px'/>&nbsp;"
                    + "原创"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/时间.png' width='20px'/>&nbsp;"
                    + po.publishTime
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/喜欢.png' width='26px'/>&nbsp;"
                    + po.likes
                    + "</h5>"
                    + "<h5>"
                    + po.introduce
                    + "</h5>"
                    + "</div>"
                    + "<div id='operate'>"
                    + "<div id='addimg'><img class='addimg' src='../../img/爱你.png' width='25px' onclick='addcart("
                    + po.blogId
                    + ",\""
                    + po.title
                    + "\",\""
                    + po.blogCover
                    + "\","
                    + po.price
                    + ")'/>&nbsp;&nbsp;推荐</div>"
                    + "<div id='deletebook'>"
                    + "<img class='deletebook' src='../../img/删除.png' width='25px' onclick='removebook("
                    + po.blogId
                    + ")'/>&nbsp;&nbsp;删除</div>"
                    + "<div id='alterbook'>"
                    + "&nbsp;<img class='alterbook' src='../../img/修改.png' width='20px' onclick='querybook("
                    + po.blogId
                    + ")'/>&nbsp;&nbsp;修改</div>"
                    + "</div>" + "</div>" + "</div>";
            }
            $("#blogContents").html(content);

        }
    });
}


