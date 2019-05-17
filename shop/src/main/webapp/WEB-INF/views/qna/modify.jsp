<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
	<head>
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
		<title>글 수정 페이지</title>
		<style>		
			form > .in_block {text-align:left;}
			span {position:relative;top:-270px;}
			/* input[type="button"] {width:70px;height:35px;}
			input[type="submit"] {width:70px;height:35px;} */
		</style>
	</head>
	<body>
<%@ include file="../header/head.jsp" %>
<div style="width: 70%; margin:30 auto;">
		<p style="text-align:center;font-size:20px;">글 수정 페이지</p>
		<form method="post"  id="registerForm">
			<div style="margin:0 auto; width:90%;">
				<input type="hidden" value="${post.bno}" name="bno">
						<div class="in_block">
							<table>
								<tr>
									<td style="height:40px;">날짜:<fmt:formatDate value="${post.regdate}" pattern="yyyy-MM-dd"/></td>
								</tr>
							</table>
						</div>					
					<div class="in_block" style="margin-left:3px;">
						제목 &nbsp;&nbsp;&nbsp;<input type="text" style="width:90%;" value="<c:out value="${post.title}"></c:out>" name="title">
					</div>
				<br />
	 				<div class="in_block" style="margin-left:3px;">
						글쓴이 <input style="width:90%;" value="<c:out value="${post.id}"></c:out>" name="id" readonly>
					</div>
				<br />
					<div style="margin-left:3px;">
						<span>내용</span> &nbsp;&nbsp;&nbsp;
						<textarea rows="20" style="width:90%;" name="body"><c:out value="${post.body}"></c:out></textarea>
					</div>
				<br />				
					<div style="float:right; width:100px; margin-right:40px;">
						<input type="button" value="취소" onclick='back()'  class="login-button">				
					</div>
					<div style="float:right; width:100px; margin-right:40px;">								
						<input type="submit" value="완료"  class="login-button">		
					</div>
		</form>
</div>
 		<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/solid.js" integrity="sha384-+Ga2s7YBbhOD6nie0DzrZpJes+b2K1xkpKxTFFcx59QmVPaSA8c7pycsNaFwUK6l" crossorigin="anonymous"></script>
		<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/fontawesome.js" integrity="sha384-7ox8Q2yzO/uWircfojVuCQOZl+ZZBg2D2J5nkpLqzH1HY0C1dHlTKIbpRz/LG23c" crossorigin="anonymous"></script>		
  		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script> 	              	
		<script>		
		$("#registerForm").submit(function(event){
			event.preventDefault();
			if(checking_form() == false){
				return false;
			}else{
			var that = $(this);
			var str ="";
			$(".uploadedList .delbtn").each(function(index){
				 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
			});
			that.append(str);
			that.get(0).submit();
			}
		});	
			function back() {
				href = "/qna/read?bno=" + ${post.bno};
				href += "&currentPageNo=" + ${cri.currentPageNo} + "&maxPost=" + ${cri.maxPost};
				href += "&search=" + "${search.search}" + "&searchType=" + "${search.searchType}";
				location.href = href;
			}
			function checking_form() {
				var form = document.forms[0];
				if(form.title.value == "")
				{
					alert('제목을 적어주세요');
					return false;
				}
				if(form.id.value == "")
				{
					alert('작성자를 적어주세요.');
					return false;
				}
				if(form.body.value == "")
				{
					alert('내용을 채워주세요');
					return false;
				}
				return true;
			}			
		</script>
	</body>
</html>
