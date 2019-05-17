<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <head>
  <title>회원가입</title>
  <link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
 </head>
 <body>
<%@ include file="../header/head.jsp" %> 
<div class="info">
    <form class="login" action="/login/signup" method="post" id="frm">
        <h1 class="login-title">회원가입</h1>
            <input type="text" name="id" id="id" class="login-input" oninput="checkId()" placeholder="아이디" autofocus>
            <input type="password" name="pw" id="pw" class="login-input" placeholder="비밀번호">
            <input type="password" name="checkpw" id="checkpw" class="login-input" placeholder="비밀번호확인">
            <input type="text" name="name" id="name" class="login-input" placeholder="이름">
            <input type="text" name="email" id="email" class="login-input" placeholder="이메일">
            <input type="text" name="phone" id="phone" class="login-input" placeholder="전화번호">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="login-button"><br>
			<input type="text" name="postadd" id="postadd" class="login-input" placeholder="우편번호" readonly>
			<input type="text" name="roadadd" id="roadadd" class="login-input" placeholder="도로명주소">
			<input type="text" name="jibadd" id="jibadd" class="login-input" placeholder="지번주소">
			<span id="guide" style="color:#999"></span>
			<button type="button" onclick="DosignUp()" id="signupbtn" class="login-button">회원가입</button>
    </form>
</div>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="/resources/js/postcode.js"></script>
	<script src="/resource/js/checkinfo.js"></script>
 </body>
</html>