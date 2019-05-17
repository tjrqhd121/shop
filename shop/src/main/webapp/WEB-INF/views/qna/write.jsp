<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<html>
	<head>
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
		<title>글 쓰기 페이지</title>
		<style>		
			form > .in_block {text-align:left;}
			span {position:relative;top:-270px;}
			input[type="button"] {width:70px;height:35px;}
			input[type="submit"] {width:70px;height:35px;}
		</style>
	</head>
	<body>
<%@ include file="../header/head.jsp" %>
			<div style="margin:30 auto; width:70%;">
		<p style="text-align:center;font-size:20px;">글쓰는 페이지</p>
		<form method="post" id="checking_form">
					<div class="in_block">
						제목 &nbsp;&nbsp;&nbsp;<input type="text" style="width:90%;" name="title" id="title">
					</div>
				<br />
 					<div class="in_block">
						글쓴이 <input style="width:90%;" value="<c:out value="${id}"></c:out>" name="id" readonly>
					</div>
				<br />
					<div>
						<span>내용</span> &nbsp;&nbsp;&nbsp;
						<textarea rows="20" style="width:90%;" name="body"></textarea>
					</div>
					<div style="float:right; position:relative; left:-6%;">
						<input type="submit" value="완료" class="login-button">
						&nbsp;&nbsp;&nbsp;			
					<input type="button" value="취소" onclick='back()'  class="login-button">
					</div>
		</form>
		<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
		<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>		
  		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script> 		    				
		<script>
		
		function back() {
				href = "/qna/qna";
				href += "?currentPageNo=" + ${cri.currentPageNo} + "&maxPost=" + ${cri.maxPost};
				href += "&search=" + "${search.search}" + "&searchType=" + "${search.searchType}";				 
				location.href = href; 
			}		
		function check(){
			var form = document.forms[0];
			if(form.title.value == "")
			{
				alert('제목을 적어주세요');
				return false;
			}
			if(form.body.value == "")
			{
				alert('내용을 채워주세요');
				return false;
			}
			return true;
		}
		
		$("#checking_form").submit(function(){
			if(check() == false){
				return false;
			}else{
				return true;
			}
		});
		</script>
		</div>
	</body>
</html>