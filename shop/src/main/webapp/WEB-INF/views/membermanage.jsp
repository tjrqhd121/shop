<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<html>
<head>
<title>pumping muscle</title>
<link rel="stylesheet" href="/resources/css/table.css" type="text/css" media="screen" />
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
</head>
<body>
<%@ include file="./header/head.jsp" %>
 <div style="width:100px; margin:0 auto; margin-left:19%;" >
	<table id="hor-minimalist-c">
		<thead>
			<tr>
				<td>아이디</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>이메일</td>
				<td>전화번호</td>
				<td>주소</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty memberlist}">
					<c:forEach items="${memberlist}" var="memberlist">
						<tr>
							<td><a href="/membermodify?id=${memberlist.id}">${memberlist.id}</a></td>
							<td>${memberlist.pw}</td>
							<td>${memberlist.name}</td>
							<td>${memberlist.email}</td>
							<td>${memberlist.phone}</td>
							<td>${memberlist.postadd} ${memberlist.roadadd} ${memberlist.jibadd}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td>회원이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
</script>
</body>
</html>
