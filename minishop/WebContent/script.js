var isCheck=false;
var use; 
var isChange = false;

$(function(){
	
		
	$("input[name=id]").keydown(function() {
		isChange = true;
		use="";
		$('#idmessage').css("display","none");
		var isCheck=false;

	});
});


function writeSave2(){
	
	alert("writeSave2");
	
	if($('input[name="pname"]').val()==""){
		alert("상품명를 입력하세요.");
		$('input[name="pname"]').focus();
		return false;
	}
		
	
}

function writeSave(){
	
	if($('input[name="id"]').val()==""){
		alert("id를 입력하세요.");
		$('input[name="id"]').focus();
		isChange = false;
		return false;
	}
	
	if($('input[name="password"]').val()==""){
		alert("패스워드를 입력하세요.");
		$('input[name="password"]').focus();
		isChange = false;
		return false;
	}
	
	if($('input[name="repassword"]').val()==""){
		alert("패스워드 확인을 입력하세요.");
		$('input[name="repassword"]').focus();
		isChange = false;
		return false;
	}
	
	if($('input[name="name")]').val()==""){
		alert("이름을 입력하세요.");
		$('input[name="name")]').focus();
		isChange = false;
		return false;
		
	}	
	
	if(isCheck==false || isChange == true){
		alert("중복체크 하세요(submit)");
		return false;
	}
}


function duplicate(){

	//alert("id_check");
	isCheck = true;
	
	$.ajax({
		url: "id_check_proc.jsp",
		data : ({
			userid: $("input[name=id]").val() // userid=kim
		}), 

		success: function (data){

			if ($('input[name="id"]').val()==""){
				$('#idmessage').html("<font color=red>id를 입력하세요</font>");
				$('#idmessage').show();
				return;
			}
			else if (jQuery.trim(data)=='YES'){ 
				$('#idmessage').html("<font color=red>사용 가능합니다.</font>");
				$('#idmessage').show();  
				use = "possible";
				isChange = false;

			}else{
				$('#idmessage').html("<font color=red>이미 사용중인 아이디입니다.</font>");
				$('#idmessage').show();                
				use = "impossible";
			}
		} // success의 끝
	});//ajax()의 끝


	$("#sub").click(function(){
		if(use=="impossible"){
			alert("이미 사용중인 아이디입니다.");
			return false;
		}

		else if(isCheck==false || isChange == true){
			alert("중복체크를 하세요(중복체크)");
			return false;
		}

		var isCheck=false;
		var isChange = false;
	})
} // duplicate의 끝


function passwd_keyup(){ 
	if($("input[name=password]").val() == $("input[name=repassword]").val()){
		$('#pwmessage').html("<font color=red>비번 일치</font>");
		$('#pwmessage').show();
	}
	else{
		$('#pwmessage').html("<font color=red>비번 불일치</font>");
		$('#pwmessage').show();
	
	}
} // passwd_keyup의 끝

function pwcheck(){	
		
	var pw = documetn.forms[0].password.value;
	
	var regexp = /^[A-Z0-9]{3,6}$/i;	//A-Z, 0-9까지의 3-6자 //대소문자구분없이
	
	//i는 대소문자를 구분하지 않는다.
	//var regexp = /^[A-Za-Z0-9]{3,6}$/;
	
	if(!regexp.test(pw)){
		alert("비밀번호 형식이 틀렸습니다");
		return false;
	}
	
	var chk_num = pw.search(/[0-9]/g);
	var chk_eng = pw.search(/[a-z]/ig);
	
	if(chk_num <0 || chk_eng<0){
		alert('비밀번호는 영문자와 숫자를 조합해 주세요');
		return false;
	}
}




