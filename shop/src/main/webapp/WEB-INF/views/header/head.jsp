<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>market</title>
<link rel="stylesheet" href="/resources/css/main.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
	<form id="form" name="form" action="" method="post">
        <div class="text">
           <c:choose>
                <c:when test='${session == "yes"}'>
					<a href="/logout" class="header">로그아웃</a>
					<c:if test="${id != 'root'}"><a href="/profile?id=${id}" class="header">회원정보</a></c:if>
					<c:if test="${id == 'root'}"><a href="/membermanage" class="header">회원관리</a></c:if>
				</c:when>
                <c:otherwise>
                    <a href="/login/login" class="header">로그인</a>
                    <a href="/login/signup" class="header">회원가입</a>
                </c:otherwise>
            </c:choose>
        </div>
        
        <div style="margin-top: 45px;">
            <a href="/" class="title"><img src="/resources/images/logo1.PNG" class="title"/></a>
        </div>
        
    <div>    
        <div class="catagory" style="margin: 26px;">
            <ul>
                <li><a class="list" href="/healthy">건강 보조제</a></li>
                <li><a class="list" href="/object">스포츠 용품</a></li>
                <li><a class="list" href="/mass">보충제</a></li>
                <c:if test="${session == 'no'}"><li><a class="list" href="/login/login">장바구니</a></li></c:if>
                <c:if test="${session == 'yes'}"><li><a class="list" href="/product/cartlist">장바구니</a></li></c:if>
                <li><a class="list" href="/qna/qna">QnA</a></li>
            </ul>
            
             <div class="search">
            	<input type="text" name="search" class="search" placeholder="음식 이름 입력" />
            	<i class="fas fa-search" aria-hidden="true"></i>
            	<button onclick="search_category()">검색</button>
        	 </div>
        	
        </div>
	</div>	
	
	</form>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script>
		function search_category(){
			document.form.action = "/search.do";
			document.form.submit();
		}
		function id_check(){
			var id = ${session};
			if(id == "no"){
				href ="/product/cartlist";
				return href;
			}else{
				alert("로그인을 하십시오");
				href ="/login/login";
				return href;
			}
		}
	</script> 
</body>
</html>