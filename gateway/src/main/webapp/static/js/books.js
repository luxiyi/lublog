function showBooks(ppage) {
		$.ajax({
				url : "allBooks",
				type : "post",
				data : {
					page : ppage
				},
				dataType : 'json', // 期待的响应数据类型
				success : function(data) {
					var arr = data.books;
					var content = "";
					for (var i = 0; i < arr.length; i++) {
						var po = arr[i];
						content += "<div class='panel panel-default'>"
								+ "<div class='panel-body'>"
								+ "<div id=image>"
								+ "<img src='"
								+ po.img
								+ "' class='img-responsive'>"
								+ "</div>"
								+ "<div id='tother'><a href='sendDatil?bid="
								+ po.bid
								+ "' style='text-decoration:none;'><font color='#cd5c5c'>"
								+ "<h2>"
								+ po.bname
								+ " &nbsp;</h2></font></a>"
								+ "<h5><img src='../../static/img/人物.png' width='20px'/>&nbsp;"
								+ po.author
								+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../static/img/短信.png' width='20px'/>&nbsp;"
								+ po.comcount
								+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../static/img/原创.png' width='20px'/>&nbsp;"
							    + "原创"
								+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../static/img/时间.png' width='20px'/>&nbsp;"
								+ po.pubdate
								+ "</h5>"
								+ "<h5>"
								+ po.intro
								+ "</h5>"
								+ "</div>"
								+ "<div id='operate'>"
								+ "<div id='addimg'><img class='addimg' src='../../static/img/喜欢.png' width='25px' onclick='addcart("
								+ po.bid
								+ ",\""
								+ po.bname
								+ "\",\""
								+ po.img
								+ "\","
								+ po.price
								+ ")'/>&nbsp;&nbsp;推荐</div>"
								+ "<div id='deletebook'>"
								+ "<img class='deletebook' src='../../static/img/删除.png' width='25px' onclick='removebook("
								+ po.bid
								+ ")'/>&nbsp;&nbsp;删除</div>"
								+ "<div id='alterbook'>"
								+ "&nbsp;<img class='alterbook' src='../../static/img/修改.png' width='20px' onclick='querybook("
								+ po.bid
								+ ")'/>&nbsp;&nbsp;修改</div>"
								+ "</div>" + "</div>" + "</div>";
					}
					$("#books").html(content);
					$("#currentpage").html(ppage);
					$("#totalpage").html(data.totalPage);
					
				}
			});
}

showBooks(1);
function prepage() {
	// 获取当前页码
	var current = parseInt($("#currentpage").html());
	if (current > 1) {
		showBooks(current - 1);
	}
}
function nextpage() {
	// 获取当前页码
	var current = parseInt($("#currentpage").html());
	// 获取总页码
	var totalpage = parseInt($("#totalpage").html());
	if (current < totalpage) {
		showBooks(current + 1);
	}
}
function endpage() {
	// 获取总页码
//	alert("已经是最后一页");
	var totalpage = parseInt($("#totalpage").html());
	showBooks(totalpage);
}

// 添加新书
function addbook() {
	$.ajax({
		url : "addBooks",
		type : "post",
		data : {
			"bname" : $("#bname").val(),
			"author" : $("#author").val(),
			"price" : $("#price").val(),
			"bcount" : $("#bcount").val(),
			"pubdate" : $("#pubdate").val(),
			"press" : $("#press").val(),
			"img" : $("#img").val(),
			"intro" : $("#intro").val()
		},
		dataType : "text",
		success : function(data) {
			 alert(data);
			if(data=="你不是管理员，无法操作"){
				alert(data);
			}else{
				 if (data == "添加图书失败，字段不能为空") {
					 $("#addbookinfo").html(data);
					}else if(data=="添加图书失败，请输入正确字段"){
						$("#addbookinfo").html(data);
					}else {
						alert(data);
						window.location.reload();
					}
			}
		},
		error:function () {
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

// 添加商品到购物车
// 注意\" 转义符
function addcart(pgid, pgname, pimg, pprice) {
	// alert("你好");
	$.ajax({
		url : "addCart",
		type : "post",
		data : {
			"bid" : pgid,
			"bname" : pgname,
			"img" : pimg,
			"ccount" : 1,
			"price" : pprice

		},
		dataType : 'json', // 期待的响应数据类型
		success : function(data) {
			if (data.msg == "请先登录"){
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
		error : function () {
			alert("系统错误，请联系帅气的管理员");
		}
	});
}

function findbook() {
		$.ajax({
				url : "likeBook",
				type : "post",
				data : {
					lbname : $("#find").val(),
				},
				dataType : 'json',
				success : function(data) {
					// alert(data);
					var arr = data;
					var content = "";
					for (var i = 0; i < arr.length; i++) {
						var po = arr[i];
						content += "<div class='panel panel-default'>"
								+ "<div class='panel-body'>"
								+ "<div id=image><a href='/detail?gid="
								+ po.bid
								+ "'>"
								+ "<img src='"
								+ po.img
								+ "' class='img-responsive'></a>"
								+ "</div>"
								+ "<div id='tother'>"
								+ "<h4>书名："
								+ po.bname
								+ " &nbsp;</h4>"
								+ "<h4>作者："
								+ po.author
								+ "&nbsp;&nbsp;&nbsp;出版社："
								+ po.press
								+ "&nbsp;&nbsp;&nbsp;评论数量："
								+ po.comcount
								+ "</h4>"
								+ "<h4>出版日期："
								+ po.pubdate
								+ "&nbsp;&nbsp;&nbsp;&nbsp;当前库存："
								+ po.bcount
								+ "本&nbsp;&nbsp;&nbsp;&nbsp;订阅价："
								+ po.price
								+ "元</h4>"
								+ "<h5>"
								+ po.intro
								+ "</h5>"
								+ "</div>"
								+ "<div id='operate'>"
								+ "<div id='addimg'><img class='addimg' src='../../static/img/jiahao.png' width='25px' onclick='addcart("
								+ po.bid
								+ ",\""
								+ po.bname
								+ "\",\""
								+ po.img
								+ "\","
								+ po.price
								+ ")'/>&nbsp;&nbsp;加入书架</div>"
								+ "<div id='deletebook'><img class='deletebook' src='../../static/img/delete.png' width='25px' onclick='removebook("
								+ po.bid
								+ ")'/>&nbsp;&nbsp;删除书籍</div>"
								+ "<div id='alterbook'>"
								+ "<img class='alterbook' src='../../static/img/xiugai.png' width='23px' onclick='querybook("
								+ po.bid
								+ ")'/>&nbsp;&nbsp;修改书籍</div>"
								+ "</div>" + "</div>" + "</div>";
					}
					$("#books").html(content);

				}
			});
}


function cart() {
	$.ajax({
		url : "cart",
		type : "post",
		dataType : "text",
		success:function (data) {
			if (data == "请先登录") {
				alert(data);
			}
		}
	})
}