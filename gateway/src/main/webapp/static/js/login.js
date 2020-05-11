function login(){	
	// alert("你好");
	$.ajax({
		url:"login",
		type:"post",
		data:{
			"luser":$("#luser").val(),
			"pass":$("#pass").val()
		},
		dataType:"text",
		success:function(data){
		 	if(data =="提示：输入的字段有误，请输入正确字段"){
				$("#info").html(data);
			}else{
				window.location.reload();
			}
		},
		error:function() {
			alert("请联系帅气的管理员");
		}
	});
}




function queryuser(){
	$.ajax({
		url:"queryUser",
		type:"post",
		dataType:'json',
		success:function(data){
			$("#userinfo").val(data.luser);
			$("#uname").val(data.uname);
			$("#phone").val(data.phone);
			$("#age").val(data.age);
			$("#sex").val(data.sex);
			$("#addr").val(data.addr);
		}	
	});
}

//修改个人信息

function updateuser(){
		var formData = new FormData();
		formData.append('userhead', $('#userhead')[0].files[0]);  
		formData.append("luser",$("#userinfo").val());
		formData.append("uname",$("#uname").val());
		formData.append("phone",$("#phone").val());
		formData.append("age",$("#age").val());
		formData.append("sex",$("#sex").val());
		formData.append("addr",$("#addr").val());
		alert($('#userhead')[0].files[0]);
		$.ajax({
			url:"updateUser",
			type:"post",
			data: formData,
			/**
			*必须false才会自动加上正确的Content-Type
			*/
			contentType: false,
			/**
			* 必须false才会避开jQuery对 formdata 的默认处理
			* XMLHttpRequest会对 formdata 进行正确的处理
			*/
			processData: false,
			success: function (data) {
				/*if(data=="文件格式不符，请重新上传"){
					alert(data);
					$("#userinfo").html(data);
				}
				else*/ 
				if(data=="修改失败,请重新输入"){
					$("#userinfo").html(data);
				}else{	
					alert(data);
					window.location.href="index";
				}
			}
		});	
	}		
 