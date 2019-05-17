<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>로그인</title>
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
</head>
<body>
<%@ include file="../header/head.jsp" %> 	
<div class="info">
    <form class="login" action="/login/checkpw" method="post">
        <h1 class="login-title">비밀번호 찾기</h1>
            <input type="text" name="id" id="id" class="login-input" placeholder="아이디" autofocus>
            <input type="text" name="name" id="name" class="login-input" placeholder="이름">
            <input type="text" name="email" id="email" class="login-input" placeholder="이메일">
        <button type="button" onclick="Docheck()" class="login-button">비밀번호찾기</button>
    </form>
</div>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
function Docheck() {
	    var id = $("#id").val();
	    var name = $("#name").val();
    var email = $("#email").val();
    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
 if(id.length == 0 ){
  	alert("아이디를 입력해 주세요"); 
      $("#id").focus();
      return false;
  }	    
 if(name.length == 0){
     alert("이름을 입력해주세요");
     $("#name").focus();
     return false;
 }     
 if(email.length == 0){
     alert("이메일을 입력해주세요");
     $("#email").focus();
     return false;
 }
if(re2.test(email) == false){
    alert("잘못된 이메일 형식입니다");
    $("#email").focus();
    return false;
}     
 $.ajax({
	 type : "post",
	 cache : false,
	 data : "id="+$("#id").val()+"&email="+$('#email').val()+"&name="+$('#name').val(),
	 url : "/login/checkpw",
	 success : function(data){
		 if(data.pw==null || data.pw==""){
			 alert("가입정보를 다시 입력하시요.");
			 location.reload();
		 }else{
			 alert("회원님의 비밀번호는"+data.pw+"입니다.");
			 location.reload();
		 }
	 }
 });
 }
</script>         
</body>
</html>