var url =window.location.href;
var id=url.split("?blogid=")[1];

function findonebook() {
	// var url =window.location.href;
	// var id=url.split("?bid=")[1];
		$.ajax({
				url : "findOneBook",
				type : "post",	
				data : {
					"blogid" : id
				},
				dataType : 'json',
				success : function(data) {
					// alert(data);
					var content = "<div class='panel panel-default'>"
								+ "<div class='panel-body'>"
								+ "<div id=image>"
								+ "<img src='"
								+ data.blogcover
								+ "' class='img-responsive'>"
								+ "</div>"
								+ "<div id='tother'>"
								+ "<h3>&nbsp;&nbsp;&nbsp;&nbsp;标题："
								+ data.title
								+ " &nbsp;</h3>"
								+ "<h3>&nbsp;&nbsp;&nbsp;&nbsp;评论数量："
								+ data.commentcount
								+ "&nbsp;&nbsp;&nbsp;&nbsp;<img class='addimg' src='../../static/img/喜欢.png' width='40px' onclick='addcart("
								+ data.blogid
								+ ",\""
								+ data.title
								+ "\",\""
								+ data.blogcover
								+ "\","
								+ data.price
								+ ")'/>&nbsp;&nbsp;点个赞吧"
								+ "</h3>"
								+ "</div>"
								+ "</div>" + "</div>";
					
					$("#detailbook").html(content);
					
				},
			error:function () {
				alert("error");
			}
			});
}
findonebook();


function findcommets(){
	$.ajax({
		url:"findComments",
		type:"post",
		data : {
			"blogid" : id
		},
		dataType:'json',
		success:function(data){
			var content="";
			for(var i=0; i< data.length; i++){
				var po=data[i];
				content+="<tr>"				
					+"<td class='infor1' style='padding-left: 100px'>"+po.user+"</td>"
					+"<td class='infor2' style='padding-left: 120px'>"+po.commentdate+"</td>"
					+"<td class='infor3' style='padding-left: 100px'>"+po.commentcontent+"</td>"
										
					+"</tr>";
			}
				$("#comment").html(content);
			
		}
	});
}
findcommets();

function insertcomment(){
	$.ajax({
		url:"insertComment",
		type:"post",
		data:{
			"ccont":$("#ccont").val(),
			"bid" : id
		},
		dataType:"text",
		success:function(data){
		 	if(data=="评论失败，请先登录"){
		 		alert(data);
			}else if(data=="请输入评价"){
				alert(data);
			}else{
				alert(data);
				window.location.reload();
			}
		}	
	});
}

function addcart(pgid, pgname, pimg, pprice) {
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
		error : function () {
			alert("系统错误，请联系帅气的管理员");
		}
	});
}