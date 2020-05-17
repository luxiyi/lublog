function register(){
	$.ajax({
		url:"register",
		type:"post",
		data:{
			"luser":$("#registerphone").val(),
			"pass":$("#registerpass").val(),
			"confirm":$("#scomfirm").val(),
		},
		dataType:"text",
		success:function(data){
			if(data=="注册失败,请重新输入"){
				$("#reginfo").html(data);
			}else{
				alert(data);
				window.location.reload();
			}
				
		}	
	});
}