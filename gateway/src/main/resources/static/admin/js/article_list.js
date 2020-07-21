function artilcleList(ppage) {
    $.ajax({
        url : "/admin/showAllArticles",
        type : "GET",
        data : {
            page : ppage
        },
        dataType : 'json', // 期待的响应数据类型
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
                        + "<td class='text-center'>" + po.title + "</td>"
                        + "<td class='infor'>" + po.pubdate + "</td>"
                        + "<td class='infor'>" + po.views + "</td>"
                        + "<td class='infor'>" + po.categoryname + "</td>"
                        + "<td class='infor'>"
                        + "<img class='deletebook' src='../../img/删除.png' width='25px' onclick='removebook("
                        + po.bid
                        + ")'/>&nbsp;&nbsp;删除&nbsp;&nbsp;"
                        + "<img class='alterbook' src='../../img/修改.png' width='20px' onclick='querybook("
                        + po.bid
                        + ")'/>&nbsp;&nbsp;修改"
                        + "</td>"
                        + "</tr>";
                }
                $("#articleList").html(content);
                $("#currentpage").html(ppage);
                $("#totalpage").html(data.totalPage);
            }
        }
    });
}

artilcleList(1);
function prepage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    if (current > 1) {
        artilcleList(current - 1);
    }
}
function nextpage() {
    // 获取当前页码
    var current = parseInt($("#currentpage").html());
    // 获取总页码
    var totalpage = parseInt($("#totalpage").html());
    if (current < totalpage) {
        artilcleList(current + 1);
    }
}
function endpage() {
    // 获取总页码
//	alert("已经是最后一页");
    var totalpage = parseInt($("#totalpage").html());
    artilcleList(totalpage);
}



function removecart(pgid) {
	// alert("删除");
    $.ajax({
        url: "reCartBook",
        type: "post",
        data: {
            "bid": pgid,
            "ccount": 1,
        },
        dataType: "text",  //期待的响应数据类型
        success: function (data) {
            if (data == "已成功还书1本") {
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


function showorder() {
    alert("你好")
    $.ajax({
        url: "showOrder",
        type: "post",
        dataType: 'json',  //期待的响应数据类型
        success: function (data) {
            alert(data);
            var content = "";
            for (var i = 0; i < data.length; i++) {
                var po = data[i];
                content += "<tr><th>订单号：" + po.oname + "，用户：" + po.luser + ",商品数量：" + po.ocount + ",商品id：" + po.bid + "，借书时间：" + po.bdate + "还书时间" + po.gdate + "</th></tr>";
            }
            $("#order").html(content);
        }
    });
}

// 删除书籍
function removebook(pgid) {
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