<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
	<head>
<link rel="stylesheet" href="/resources/css/info.css" type="text/css" media="screen"/>
		<title>글 읽기</title>
		<style>		
			form > .in_block {text-align:left;}
			span {position:relative;top:-270px;}
 			input[type="button"] {width:70px;height:35px;}
		</style>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
		<script src="//code.jquery.com/jquery.min.js"></script>
	</head>
	<body>
<%@ include file="../header/head.jsp" %>	
<div style="width: 70%; margin:30 auto;">
		<p style="width:30%;margin:0 auto;text-align:center;font-size:20px;">QnA게시판</p>	
				<div>
					<c:if test="${post.id != id && id != 'root'}">
						<div id="btn" style="float: right; margin-top: 0px; margin-right: 132px;">
						<input type="button" value="뒤로" onclick='back()'   class="login-button">
						</div>
					</c:if>
				</div>
				
		<form>
			<div style="margin:0 auto; width:90%;">	
				<div style="float:right; position:relative; left:-7%;">
					<c:choose>		
						<c:when test="${post.id == id}">
							<input type="button" value="수정" onclick='alter()'   class="login-button">
							&nbsp;&nbsp;&nbsp;
							<input type="button" value="삭제" onclick='del_post(<c:out value="${post.bno}"></c:out>)' class="login-button">
	 						&nbsp;&nbsp;&nbsp;
	 						<input type="button" value="뒤로" onclick='back()' class="login-button">									
						</c:when>
						<c:when test="${post.id != id && id == 'root'}">
							<input type="button" value="삭제" onclick='del_post(<c:out value="${post.bno}"></c:out>)' class="login-button">
	 						&nbsp;&nbsp;&nbsp;
	 						<input type="button" value="뒤로" onclick='back()' class="login-button">									
						</c:when>
					</c:choose>
				</div>
					<div style="clear:both;"></div>
						<div class="in_block">
							<table>
								<tr>
									<td style="height:40px;">날짜:<fmt:formatDate value="${post.regdate}" pattern="yyyy-MM-dd"/></td>
								</tr>
							</table>
						</div>			
						<input type="hidden" value = "<c:out value="${post.id}"></c:out>">
							<div class="in_block" style="margin-left:3px;">
								제목 &nbsp;&nbsp;&nbsp;<input type="text" style="width:90%;" readonly="readonly" value="<c:out value="${post.title}"></c:out>">
							</div>
					<br />
							<div class="in_block" style="margin-left:3px;">
								글쓴이 <input type="text" style="width:90%;" readonly="readonly" value="<c:out value="${post.id}"></c:out>">
							</div>
					<br />		
							<div style="margin-left:3px;">
								<span>내용</span> &nbsp;&nbsp;&nbsp;
								<textarea rows="20" style="width:90%;" readonly="readonly"><c:out value="${post.body}"></c:out></textarea>
							</div>
					<br />					 
			</div>
		</form>
</div>
		<script>		
			function del_post(no) {
				var form = document.createElement("form");
				form.setAttribute("charset", "UTF-8");
				form.setAttribute("method", "Post");
				form.setAttribute("action", "delete");

				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "hidden");
				hiddenField.setAttribute("name", "bno");
				hiddenField.setAttribute("value", no);
				form.appendChild(hiddenField);
				
				document.body.appendChild(form);
				
				form.submit();
			}
			function back(){
				href = "/qna/qna"
				href +="?currentPageNo=" + ${cri.currentPageNo} + "&maxPost=" + ${cri.maxPost};
				href += "&search=" + "${search.search}" + "&searchType=" + "${search.searchType}";
				location.href = href;
			}			
			function alter() {
				href = "modify?bno=" + ${post.bno};
				href += "&currentPageNo=" + ${cri.currentPageNo} + "&maxPost=" + ${cri.maxPost};
				href += "&search=" + "${search.search}" + "&searchType=" + "${search.searchType}";
				location.href = href;
			}			
		</script>
	</body>
</html>
