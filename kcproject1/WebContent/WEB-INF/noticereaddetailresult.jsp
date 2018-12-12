<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.title,.value{
	font-size:14px;
	display: table-cell;
	text-align: center;
	padding: 5px;
	border-radius: 5px;
	
}
.title{
	width: 80px;
	background: #b5d6a8;
}

.value:not(.value[data-title=text_content]){
	width:100px;
	background: #f2f2f2;
}



.notice_row{
	width:600px;
	display: table-row;
	
}

#divDHidden,#divUHidden{
	display: none;
}

</style>
<script>
function init(){
	$("#btDelete").click(function(){
		
		$("#divDHidden").toggle();
		$(".btOk").click(function(){
			var text_no = $(".notice_row .value[data-title=text_no]").text();
			var text_pwd = $("#divDHidden input[type=password]").val();
			$.ajax({
				url:'notice/noticedelete.do'
				,method:"post"
				,data:{"text_no":text_no
					  ,"text_pwd":text_pwd}
				,success:function(result){
					var jsonObj = JSON.parse(result);
					if(jsonObj.status=='ok'){
						alert("공지가 삭제되었습니다");
						$(".has-not-sub a").each(function(index,element){
							if($(element).attr("href").indexOf("${pageContext.request.contextPath}/notice/noticeall.do")>-1){
								$(element).trigger("click");
								return false;
							}
						});
					}else if(jsonObj.status=='pwd_null'){
						alert("비밀번호를 입력해주세요");
					}else{
						alert("비밀번호가 틀렸습니다");
					}
				}
			});
			return false;
		});
		
		
		return false;
	});
	
	 $("#btUpdate").click(function(){
		$("#divUHidden").toggle();
		
		$(".btOk").click(function(){
			var text_no = $(".notice_row .value[data-title=text_no]").text();
			var text_pwd = $("#divUHidden input[type=password]").val();
			$.ajax({
				url:'notice/noticeupdate.do'
				,method:"post"
				,data:{"text_no":text_no
					  ,"text_pwd":text_pwd}
				,success:function(result){
					var jsonObj = JSON.parse(result);
					if(jsonObj.status=='ok'){
						
						 $.ajax({
							url:'notice/noticeupdatepage.do'
							,data:{"text_no":text_no}
							,success:function(result){
								$(".wrap-table100").html(result);
							}
						});
					}else if(jsonObj.status=='pwd_null'){
						alert("비밀번호를 입력해주세요");
					}else{
						alert("비밀번호가 틀렸습니다");
					}
				}
			});
			return false;
		});
		
		return false;
	}); 
};

$(init);
</script>
</head>
<body>
<c:set var="notice" value="${requestScope.notice }"></c:set>
<div class="notice_row">
<div class="title">제목</div>
<div class="value" data-title="text_title">${notice.text_title}</div>
</div>
<br>
<div class="table">
<div class="notice_row">
	<div class="title">글번호</div>
	<div class="value" data-title="text_no">${notice.text_no}</div>
	<div class="title">등록일</div>
	<div class="value" data-title="text_date"><fmt:formatDate pattern="yy/MM/dd" value="${notice.text_date}"/></div>
	<div class="title">글쓴이</div>
	<div class="value" data-title="employee_id">${notice.employee.employee_id}</div>
</div>
</div>
<div class="value" data-title="text_content" style="display: block; height: 300px;">
${notice.text_content}

</div>





</body>
</html>