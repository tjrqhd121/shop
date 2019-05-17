<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 <head>
  <title>회원정보수정</title>
  <link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
 </head>
 <body>
<%@ include file="./header/head.jsp" %> 
<div class="info">
    <form class="login" method="post" id="frm">
        <h1 class="login-title">회원정보수정</h1>
            <input type="text" id="id" class="login-input" placeholder="아이디" value="${list.id}" readonly>
            <input type="password" name="pw" id="pw" class="login-input" placeholder="비밀번호" value="${list.pw}">
            <input type="password" name="checkpw" id="checkpw" class="login-input" placeholder="비밀번호확인" value="${list.pw}">
            <input type="text" name="name" id="name" class="login-input" placeholder="이름" value="${list.name}">
            <input type="text" name="email" id="email" class="login-input" placeholder="이메일" value="${list.email}">
            <input type="text" name="phone" id="phone" class="login-input" placeholder="전화번호" value="${list.phone}">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="login-button"><br>
			<input type="text" name="postadd" id="postadd" class="login-input" placeholder="우편번호" value="${list.postadd}" readonly>
			<input type="text" name="roadadd" id="roadadd" class="login-input" placeholder="도로명주소" value="${list.roadadd}">
			<input type="text" name="jibadd" id="jibadd" class="login-input" placeholder="지번주소" value="${list.jibadd}">
			<span id="guide" style="color:#999"></span>
			<button type="button" onclick="DosignUp()" id="signupbtn" class="login-button">회원정보수정</button>
    </form>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postadd').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('roadadd').value = fullRoadAddr;
                document.getElementById('jibadd').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
    function DosignUp() {
	    var pw = $("#pw").val();
	    var checkpw = $("#checkpw").val();
	    var name = $("#name").val();
	    var email = $("#email").val();
		var phone = $("#phone").val();
		var postadd = $("#postadd").val();
		var roadadd = $("#roadadd").val();
		var jibadd = $("#jibadd").val();
	    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
	    
    if(pw.length == 0){
        alert("비밀번호를 입력해 주세요"); 
        $("#pw").focus();
        return false;
    }
    
    if((pw.length < 4) || (pw.length > 12)){
        alert("비밀번호는 4~12자리까지 입력해주세요"); 
        $("#pw").focus();
        return false;
    }	
    
    if(pw != checkpw){
        alert("비밀번호가 서로 다릅니다. 비밀번호를 확인해 주세요."); 
        $("#checkpw").focus();
        return false; 
    }
 
    if(name.length == 0){
        alert("이름을 입력해주세요");
        $("#name").focus();
        return false;
    }
    
    if(email.length == 0){
        alert("이메일을 입력해주세요");
        $("#email").focus();
        return false;
    }
    
    if(re2.test(email) == false){
        alert("잘못된 이메일 형식입니다");
        $("#email").focus();
        return false;
    }
	if(phone.length == 0){
        alert("폰번호를 입력해주세요");
        $("#phone").focus();
        return false;
    }
    if(regPhone.test(phone) == false){
        alert("휴대폰 번호를 올바르게 입력해 주세요");
        $("#phone").focus();
        return false;
    }	
	if(postadd.length == 0){
        alert("주소를 적어주세요");
        $("#postadd").focus();
        return false;
    }		
    else{
    	alert("회원정보가 수정되었습니다.");
    }
    $("#frm").submit();
}	
</script> 	
 </body>
</html>