<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<html>
<head>
<link rel="stylesheet" href="/resources/css/main.css" type="text/css" media="screen"/>
	<title>market</title>
	<style>
		.fileDrop {
			  width: 71%;
			  height: 100px;
			  border: 1px dotted gray;
			  background-color: lightslategrey;
			  margin: auto; 
		}
	</style>
</head>
<body>
<%@ include file="../header/head.jsp" %>
	<form id="registerForm" method="POST" >
		<div style="margin:0 auto; margin-left:27%; margin-top:50px;">
			<div>제품분류 :
				<select name="category">
					<option value="healthy" selected="selected">건강 보조제</option>
					<option value="object">스포츠 용품</option>
					<option value="mass">보충제</option>
				</select>	
			</div>
			<div>제품명 :
				<input type="text" name="pname" id="pname" class="pwrite">
			</div>
			<div>판매가 :
				<input type="text" name="price" id="price" class="pwrite">
			</div>
			<div>재고 :
				<input type="text" name="quantity" id="quantity" class="pwrite" style="margin-left: 33px;">
			</div>
			<div style="width:70%;">내용 :
				<textarea row="5" class="form-control" name="text"></textarea>
			</div>
			<div>파일첨부
				<div class="fileDrop" style="margin-top: 20;margin-left: -1%;">
		 			<div class="box-footer" style="margin:110px;">
						<ul class="mailbox-attachments clearfix uploadedList" style="display:inline-block; margin-left:-151px;">
						</ul>	
					</div>					
				</div>
			<div style="margin-left: 800px; margin-top:-100px;"><button type="submit">등록</button></div>	
			</div>
		</div>			
	</form>
		<script id="template" type="text/x-handlebars-template">
			<li style="display:inline-block; list-style-type: none;">
	  			<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  				<div class="mailbox-attachment-info">{{fileName}}
					<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><img src="/../../resources/images/xicon3.png" style="width: 13;" /></a>
  				</div>
			</li>                
		</script>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script type="text/javascript" src="/resources/js/upload.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script> 
	    <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
		<script>
		var template = Handlebars.compile($("#template").html());
 		$(".fileDrop").on("dragenter dragover", function(event){
			event.preventDefault();
		});
		$(".fileDrop").on("drop", function(event){
			event.preventDefault();
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			var formData = new FormData();
			formData.append("file", file);	
			$.ajax({
				  url: '/uploadAjax',
				  data: formData,
				  dataType:'text',
				  processData: false,
				  contentType: false,
				  type: 'POST',
				  success: function(data){
					  var fileInfo = getFileInfo(data);
					  var html = template(fileInfo);		  
					  $(".uploadedList").append(html);
				  }
				});	
		});
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
		$(".uploadedList").on("click", ".delbtn", function(event){
			event.preventDefault();
			var that = $(this);
			$.ajax({
			   url:"/deleteFile",
			   type:"post",
			   data: {fileName:$(this).attr("href")},
			   dataType:"text",
			   success:function(result){
				   if(result == 'deleted'){
					   that.closest("li").remove();
				   }
			   }
		   });
		});
		function checking_form() {
			var form = document.forms[0];
			var text = CKEDITOR.instances['text'].getData();
			if(form.pname.value == "")
			{
				alert('상품명을 적어주세요.');
		        $("#pname").focus();
				return false;
			}
			if(form.price.value == "")
			{
				alert('가격을 적어주세요.');
				$("#price").focus();
				return false;
			}
			if(form.quantity.value == "")
			{
				alert('수량을 적어주세요.');
				$("#quantity").focus();
				return false;
			}
			if(text == "")
			{
				alert('상세내용을 적어주세요.');
				$("#text").focus();
				return false;
			}
			return true;
		}
		</script>
		<script>
			CKEDITOR.replace("text", {    
				filebrowserUploadUrl :"/imageUpload"
			    //filebrowserImageUploadUrl: '/resources/upload/'
			});
		</script>
</body>
</html>