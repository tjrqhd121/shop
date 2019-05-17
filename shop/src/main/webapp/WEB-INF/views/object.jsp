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
<%@ include file="./header/head.jsp" %>
<div style="width:1200px; margin:0 auto;">
    <div style="width:100%;" >
        
        <c:choose>
            <c:when test="${!empty object_list}">
                <c:forEach items="${object_list}" varStatus="status" var="product">
                    <c:choose>
                        <c:when test="${status.index % 5 eq 0}">
                            <ul style="border-bottom: 1px solid;" class="catagory mainlist">
									<li style="margin: 20px;">
										<a class="header" href="/product/pread?pno=${product.pno}"><img src="/resources/file${product.fullname}"></a>
										<a style="font-size:15px;" class="header" href="/product/pread?pno=${product.pno}">${product.pname}</a>
										<strong><fmt:formatNumber value="${product.price}" pattern="###,###,###" /></strong>
									</li>
                        </c:when>
                        <c:when test="${status.index % 5 eq 4}">
									<li style="margin: 20px;">
										<a class="header" href="/product/pread?pno=${product.pno}"><img src="/resources/file${product.fullname}"></a>
										<a style="font-size:15px;" class="header" href="/product/pread?pno=${product.pno}">${product.pname}</a>
										<strong><fmt:formatNumber value="${product.price}" pattern="###,###,###" /></strong>
									</li>        
                            </ul>
                        </c:when>
                        <c:otherwise>
								<li style="margin: 20px;">
									<a class="header" href="/product/pread?pno=${product.pno}"><img src="/resources/file${product.fullname}"></a>
									<a style="font-size:15px;" class="header" href="/product/pread?pno=${product.pno}">${product.pname}</a>
									<strong><fmt:formatNumber value="${product.price}" pattern="###,###,###" /></strong>
								</li> 
                        </c:otherwise>    
                    </c:choose>
                </c:forEach>    
            </c:when>
            <c:otherwise>
                <p>상품이 없습니다.</p>
            </c:otherwise>
        </c:choose>

        <div><c:if test="${id == 'root'}"><button type="button" onclick="new_write()" class="login-button">상품등록</button></c:if></div>
    </div>
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
