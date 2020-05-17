function showorder(){
	// alert("你好")
	// window.location.href = "/order";
	$.ajax({
		url:"showOrder",
		type:"post",
		dataType:"json",  //期待的响应数据类型
		success:function(data){
			// alert("你好")
			var content="";
			for(var i=0; i< data.length; i++){
				var po=data[i];
				content+="<tr><th>订单号："+po.oname+"，用户："+po.luser+",商品数量："+po.ocount+",商品id："+po.bid+"，借书时间："+po.bdate+"</th></tr>";
			}	
			$("#order").html(content);
	}
});
}
showorder();



function showorderre(){
//	alert("你好")
	$.ajax({
		url:"showOrderre",
		type:"post",
		dataType:'json',  //期待的响应数据类型
		success:function(data){
//			alert(data);
			var content="";
			for(var i=0; i< data.length; i++){
				var po=data[i];
				content+="<tr><th>订单号："+po.oname+"，用户："+po.luser+",商品数量："+po.ocount+",商品id："+po.bid+"，还书时间："+po.rdate+"</th></tr>";
			}	
			$("#orderre").html(content);
	}
});
}
showorderre();
