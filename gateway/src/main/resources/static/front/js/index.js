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
                var font= "华文彩云";
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
                        + po.blogcover
                        + "' class='img-responsive'>"
                        + "</div>"
                        + "<div id='tother'><a href='blog?blogId="
                        + po.blogid
                        + "' style='text-decoration:none;'><font color='#cd5c5c'>"
                        + "<h2>"
                        + po.title
                        + " &nbsp;</h2></font></a>"
                        + "<h5><img src='../../img/人物.png' width='20px'/>&nbsp;"
                        + po.author
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/短信.png' width='20px'/>&nbsp;"
                        + po.commentcount
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/原创.png' width='20px'/>&nbsp;"
                        + "原创"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/时间.png' width='20px'/>&nbsp;"
                        + po.pubdate
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/喜欢.png' width='26px'/>&nbsp;"
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


// 添加新书
function addbook() {
    $.ajax({
        url: "addBooks",
        type: "post",
        data: {
            "title": $("#bname").val(),
            "author": $("#author").val(),
            "price": $("#price").val(),
            "bcount": $("#bcount").val(),
            "pubdate": $("#pubdate").val(),
            "press": $("#press").val(),
            "blogcover": $("#img").val(),
            "introduce": $("#intro").val()
        },
        dataType: "text",
        success: function (data) {
            alert(data);
            if (data == "你不是管理员，无法操作") {
                alert(data);
            } else {
                if (data == "添加图书失败，字段不能为空") {
                    $("#addbookinfo").html(data);
                } else if (data == "添加图书失败，请输入正确字段") {
                    $("#addbookinfo").html(data);
                } else {
                    alert(data);
                    window.location.reload();
                }
            }
        },
        error: function () {
            alert("error")
        }
    });
}

// 删除书籍
// function removebook(pgid) {
// 	$.ajax({
// 		url : "deleteBooks",
// 		type : "post",
// 		data : {
// 			"bid" : pgid,
// 			"bcount" : 1
// 		},
// 		dataType : "text",
// 		success : function(data) {
// 			if(data=="请先登录"){
// 				alert(data);
// 			}else if (data == "你不是管理员，无法操作") {
// 				alert(data);
// 			} else {
// 				alert(data);
// 				window.location.reload();
// 			}
// 		}
// 	});
// }

// 找到一本书的内容
function querybook(bid) {
    $.ajax({
        url: "queryBooks",
        type: "post",
        data: {
            "bid": bid,
        },
        dataType: 'json',
        success: function (data) {
            // 将内容放进div中
            if (data.info == "你不是管理员，无法操作") {
                alert(data.info);
            } else if (data.info == "请先登录") {
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
        url: "updateBooks",
        type: "post",
        data: {
            // 将input中内容赋值给bname
            "bid": $("#ubid").val(),
            // 将修改后的赋值到input中
            "bname": $("#ubname").val(),
            "author": $("#uauthor").val(),
            "price": $("#uprice").val(),
            "bcount": $("#ubcount").val(),
            "pubdate": $("#upubdate").val(),
            "press": $("#upress").val(),
            "img": $("#uimg").val(),
            "intro": $("#uintro").val()
        },
        dataType: 'json',
        success: function (data) {
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

// 添加商品到购物车
// 注意\" 转义符
function addcart(pgid, pgname, pimg, pprice) {
    // alert("你好");
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
            } else if (data.msg == "已成功借书1本") {
                alert(data.msg);
                window.location.reload();
            } else if (data.msg == "库存不足，借书失败") {
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
                    + po.blogcover
                    + "' class='img-responsive'>"
                    + "</div>"
                    + "<div id='tother'><a href='sendDatil?bid="
                    + po.blogid
                    + "' style='text-decoration:none;'><font color='#cd5c5c'>"
                    + "<h2>"
                    + po.title
                    + " &nbsp;</h2></font></a>"
                    + "<h5><img src='../../img/人物.png' width='20px'/>&nbsp;"
                    + po.author
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/短信.png' width='20px'/>&nbsp;"
                    + po.commentcount
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/原创.png' width='20px'/>&nbsp;"
                    + "原创"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/时间.png' width='20px'/>&nbsp;"
                    + po.pubdate
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/喜欢.png' width='26px'/>&nbsp;"
                    + po.likes
                    + "</h5>"
                    + "<h5>"
                    + po.introduce
                    + "</h5>"
                    + "</div>"
                    + "<div id='operate'>"
                    + "<div id='addimg'><img class='addimg' src='../../img/爱你.png' width='25px' onclick='addcart("
                    + po.blogid
                    + ",\""
                    + po.title
                    + "\",\""
                    + po.blogcover
                    + "\","
                    + po.price
                    + ")'/>&nbsp;&nbsp;推荐</div>"
                    + "<div id='deletebook'>"
                    + "<img class='deletebook' src='../../img/删除.png' width='25px' onclick='removebook("
                    + po.blogid
                    + ")'/>&nbsp;&nbsp;删除</div>"
                    + "<div id='alterbook'>"
                    + "&nbsp;<img class='alterbook' src='../../img/修改.png' width='20px' onclick='querybook("
                    + po.blogid
                    + ")'/>&nbsp;&nbsp;修改</div>"
                    + "</div>" + "</div>" + "</div>";
            }
            $("#blogContents").html(content);

        }
    });
}


function cart() {
    $.ajax({
        url: "cart",
        type: "post",
        dataType: "text",
        success: function (data) {
            if (data == "请先登录") {
                alert(data);
            }
        }
    })
}