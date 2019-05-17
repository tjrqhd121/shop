<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<html>
<head>
<title>market</title>
<link rel="stylesheet" href="/resources/css/table.css" type="text/css" media="screen" />
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
</head>
<body>
<%@ include file="../header/head.jsp" %>
<div style="width:100px; margin:0 auto; margin-left:19%;" >
	<table id="hor-minimalist-c">
		<thead>
			<tr>
				<td>상품명</td>
				<td>이미지</td>
				<td>가격</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty list}">
					<c:forEach items="${list}" var="product">
						<tr>
							<td><a href="/product/pread?pno=${product.pno}">${product.pname}</a></td>
							<td><img src="/resources/file${product.fullname}"></td>
							<td><fmt:formatNumber value="${product.price}" pattern="###,###,###" /></td>
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
	<div><c:if test="${id == 'root'}"><button type="button" onclick="new_write()" class="login-button">상품등록</button></c:if></div>
</div>	
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	function new_write(){
		href = "/product/pwrite";
		location.href = href;
	}
</script>
</body>
</html>
