<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>로그인</title>
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
</head>
<style>

</style>
<body onload="init();">
<%@ include file="../header/head.jsp" %> 
<div class="info">
    <form class="login" action="/login/login" method="post">
        <h1 class="login-title">로그인</h1>
            <input type="text" name="id" class="login-input" placeholder="아이디" autofocus>
            <input type="password" name="pw" class="login-input" placeholder="비밀번호">
            <input type="submit" value="로그인" class="login-button">
        <a class="login-lost" href="/login/signup">회원가입</a>
        <a class="login-lost" href="/login/checkid">ID찾기</a>
        <a class="login-lost" href="/login/checkpw">PW찾기</a>
    </form>
</div>  
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script>
	function check() {
		var result = '<c:out value="${check}"></c:out>';
		if(result == 'no')
			alert("아이디나 패스워드가 틀립니다.");			
	}
	
	function init() {
		check();
	}
	</script>         
</body>
</html>