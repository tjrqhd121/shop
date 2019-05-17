<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<html>
<head>

<title>market</title>
	
	<link rel="stylesheet" href="/resources/css/table.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>

<style>
			a {text-decoration:none;}
</style>

</head>
<body>
<%@ include file="../header/head.jsp" %>
 
 <div style="width:100px; margin:0 auto; margin-left:19%;" >
	
	<table id="hor-minimalist-c">
		
		<thead>
			<tr>
				<td>상품명</td>
				<td>이미지</td>
				<td>수량</td>
				<td>가격</td>
				<td>버튼</td>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
				
				<c:when test="${!empty cartlist}">
					<c:forEach items="${cartlist}" var="cartlist">
						<tr>
							<td>${cartlist.pname}</td>
							<td><img src="/resources/file${cartlist.fullname}"></td>
							<td>${cartlist.p_count}</td>
							<td><fmt:formatNumber value="${cartlist.price * cartlist.p_count}" pattern="###,###,###" /></td>
							<td><button type="button" onclick="delete_list(${cartlist.cno})" class="login-button">삭제</button></td>
						</tr>
					</c:forEach>
				</c:when>
				
				<c:otherwise>
					<tr>
						<td>상품이 없습니다.</td>
					</tr>
				</c:otherwise>
			
			</c:choose>
		</tbody>
	
	</table>
	
	<div><button type="button" onclick="purchase()" class="login-button">구매</button></div>
</div>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
function delete_list(cno){
	 $.ajax({
		 type : "post",
		 data : "cno="+cno,
		 url : "/product/deletelist",
		 success : function(data){
			 if(data == 1){
				 alert("상품이 제거되었습니다.");
				 window.location.reload();
			 }else{
				 alert("존재하지 않는 상품입니다.");
				 window.location.reload();
			 }
		 }
	 });
}
function purchase(){
	href = "/purchase/purchase";
	location.href = href;
}
</script>

</body>
</html>
