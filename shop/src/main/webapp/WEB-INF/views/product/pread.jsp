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
<body onload="init();">
<%@ include file="../header/head.jsp" %>
<form role="form" name="form" method="post">
	<input type="hidden" name="pno" value="${read.pno}">
	<div style="width: 350px;margin: 0 auto;padding-top: 50px;padding-left: 150px;">
		<div>
			<input type="text" value="${read.pname}" style="outline: none;border: none;font-size: 50;" readonly>
		</div>
		<div style="margin-left: -270;">
			<img src="/resources/file${read.fullname}" style="width: 250px;height: 250px;">
		</div>
		<div style="margin-top: -159px;">가격
			<fmt:formatNumber value="${read.price}" pattern="###,###,###"/>
		</div>
		<div>
			수량 : <input type="hidden" name="sell_price" id="sell_price" value="${read.price}">
				<input type="text" name="p_count" id="p_count" value="1" size="3" onchange="change();" style="width: 24px;border: none;outline: none;margin-left: 10;" readonly>
				<input type="button" value=" + " onclick="add();"><input type="button" value=" - " onclick="del();"><br>
		</div>
		<div>
			총 금액 : <input type="text" name="sum" id="sum" size="11" style="border: none;outline: none;margin-left: 10;width: 60px;" readonly>원
		</div>
		
		<c:if test="${id != null}"><button type="button" onclick="new_cart(${read.pno})" class="login-button"style="width: 100px;margin-bottom: 10px;margin-top: 10px;">구매</button></c:if>
		<c:if test="${id == 'root'}"><button type="button" onclick="modify()" class="login-button" style="width: 100px;">수정</button></c:if>
		<c:if test="${id == 'root'}"><button type="button" onclick="delete_button(${read.pno})" class="login-button" style="width: 100px;">삭제</button></c:if>

		<div class="text1" style="margin-top: 95px;margin-left: -234px;"><p>상세내용</p>
			<input class="text2" type="hidden" value="<c:out value="${read.text}"></c:out>" readonly>
		</div>
	</div>	
</form>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	/* $(".text2").val() class text값 가져오기 */
	$(".text1").append($(".text2").val());
	function new_cart(){
		href = "/product/cart";
		location.href = href;
	}
	function modify(){
		href = "pmodify?pno=" + ${read.pno};
		location.href = href;
	}
	
	function new_cart(pno){
		var p_count = $('#p_count').val();
		 $.ajax({
			 type : "post",
			 data : "pno="+pno+"&p_count="+p_count,
			 url : "/product/newcartlist",
			 success : function(data){
				 if(data == 1){
					 alert("상품이 장바구니에 추가되었습니다.");
				 }else{
					 alert("이미 존재하는 상품입니다.");
				 }
			 }
		 });
	}
	
	function delete_button(pno){
		var form = document.createElement("form");
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "Post");
		form.setAttribute("action", "delete");

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "pno");
		hiddenField.setAttribute("value", pno);
		form.appendChild(hiddenField);
		
		document.body.appendChild(form);
		
		form.submit();
	}
	
	var sell_price;
	var p_count;
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
	
	function init() {
		sell_price = document.form.sell_price.value;
		p_count = document.form.p_count.value;
		document.form.sum.value = sell_price.toString().replace(regexp,',');
		change();
	}
	function add () {
		hm = document.form.p_count;
		sum = document.form.sum;
		hm.value ++ ;
		var sumint = parseInt(hm.value) * sell_price;
		sum.value = sumint.toString().replace(regexp,',');
	}

	function del () {
		hm = document.form.p_count;
		sum = document.form.sum;
			if (hm.value > 1) {
				hm.value -- ;
				var sumint = parseInt(hm.value) * sell_price;
				sum.value = sumint.toString().replace(regexp,',');
			}
	}

	function change () {
		hm = document.form.p_count;
		sum = document.form.sum;

			if (hm.value < 0) {
				hm.value = 0;
			}
		var sumint = parseInt(hm.value) * sell_price;
		sum.value = sumint.toString().replace(regexp,',');
	} 
</script>
</body>
</html>