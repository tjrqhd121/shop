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
<form id="form" name="form" action="/purchase/buy" method="post">
 <div style="width:1200px; margin:0 auto;" >
	<div style="width:100%;">
        <c:choose>
            <c:when test="${!empty cartlist}">
                <c:forEach items="${cartlist}" varStatus="status" var="product">
                    <c:choose>
                        <c:when test="${status.index % 5 eq 0}">
                            <ul style="border-bottom: 1px solid; padding-left:100px;"class="catagory mainlist">
									<li style="margin: 20px;">
										<img src="/resources/file${product.fullname}">
										<p style="margin:0px;">${product.pname}</p>
										<p style="margin:0px;">개수 : ${product.p_count}</p>
										<strong><fmt:formatNumber value="${product.price * product.p_count}" pattern="###,###,###" /></strong>
									</li>
                        </c:when>
                        <c:when test="${status.index % 5 eq 4}">
									<li style="margin: 20px;">
										<img src="/resources/file${product.fullname}">
										<p style="margin:0px;">${product.pname}</p>
										<p style="margin:0px;">개수 : ${product.p_count}</p>
										<strong><fmt:formatNumber value="${product.price * product.p_count}" pattern="###,###,###" /></strong>
									</li>        
                            </ul>
                        </c:when>
                        <c:otherwise>
								<li style="margin: 20px;">
									<img src="/resources/file${product.fullname}">
									<p style="margin:0px;">${product.pname}</p>
									<p style="margin:0px;">개수 : ${product.p_count}</p>
									<strong><fmt:formatNumber value="${product.price * product.p_count}" pattern="###,###,###" /></strong>
								</li> 
                        </c:otherwise>    
                    </c:choose>
                </c:forEach>    
            </c:when>
            <c:otherwise>
                <p>상품이 없습니다.</p>
            </c:otherwise>
        </c:choose>
    </div>    
	<div style="margin-left:280px;">
			구매자 정보 :
			<input type="text" name="del_name" id="del_name" class="login-input" placeholder="이름" value="" style="display:block; margin-left:100px; margin-top:-30px">
            <input type="text" name="del_phone" id="del_phone" class="login-input" placeholder="전화번호" value="" style="display:block; margin-left:100px;">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="login-button" style="margin-bottom: -100px; display: inline; margin-left: 410px; width: 15%;"><br>
			<input type="text" name="postadd" id="postadd" class="login-input" placeholder="우편번호" value=""readonly style="display: block; margin-left: 100px;">
			<input type="text" name="roadadd" id="roadadd" class="login-input" placeholder="도로명주소" value="" style="display: block; margin-left: 100px;">
			<input type="text" name="jibadd" id="jibadd" class="login-input" placeholder="지번주소" value="" style="display: block; margin-left: 100px;">
			<input type="radio" id="label1" name="radiob" onclick="sameId()" value="주문자정보 동일" style="margin-left:100px"><label for="label1">주문자정보 동일</label>
			<input type="radio" id="label2" name="radiob" onclick="clear()" value="직접 입력"><label for="label2">직접 입력</label>
			
	</div>
	<div style="margin-left:280px"><button type="button" onclick="purchase()" class="login-button" style="margin-left: 100px; width: 15%;">결제하기</button></div>
 </div>
</form>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="/resources/js/postcode.js"></script>
<script>
function sameId(){
    document.getElementById('del_name').value = "${list.name}";
    document.getElementById('del_phone').value = "${list.phone}";
    document.getElementById('postadd').value = "${list.postadd}";
    document.getElementById('roadadd').value = "${list.roadadd}";
    document.getElementById('jibadd').value = "${list.jibadd}";
}

$("#label2").click(function(){
	$("#del_name").val("");
	$("#del_phone").val("");
	$("#postadd").val("");
	$("#roadadd").val("");
	$("#jibadd").val("");
})
function purchase(){
	var name = $("#del_name").val();
	var phone = $("#del_phone").val();
	var del_add = $("#postadd").val()+$("#roadadd").val()+$("#jibadd").val();
    var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;

	if(name.length == 0)
	{
		alert("이름을 입력하세요");
		$("#del_name").focus();
		return false;
	}
	if(phone.length == 0)
	{
		alert("번호를 입력하세요");
		$("#del_phone").focus();
		return false;
	}
	if(del_add.length == 0)
	{
		alert("주소를 입력하세요");
		$("roadadd").focus();
		return false;
	}
	if(regPhone.test(phone) == false){
	    alert("휴대폰 번호를 올바르게 입력해 주세요");
	    $("#del_phone").focus();
	    return false;
	}
	else
	{
		alert("구매가 완료되었습니다.");
		document.form.submit();
	}	
}
</script>
</body>
</html>
