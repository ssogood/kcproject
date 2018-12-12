<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점 공지사항</title>
<link rel="stylesheet" type="text/css" href="css/notice_util.css">
<link rel="stylesheet" type="text/css" href="css/notice_main.css">


<!-- <style>
.wrap-table100 {
  width: 600px;
  border-radius: 10px;
  overflow: hidden;
}
.table {
  width: 100%;
 
  display: table;
  margin: 0;
}
.row1 {
  display: table-row;
  background: #b5d6a8;
}

.row1.header {
  color: #ffffff;
  background: #6aa84f;
}



</style> -->

<script>
function init(){
	
	$(".table .row1").click(function(){
		var text_no = $(this).children().eq(0).text();
		
		$.ajax({
			url:"notice/noticereaddetail.do"
			,data:{"text_no":text_no}
			,success:function(result){
				$(".wrap-table100").html(result);
			}
		});
		
		return false;
	});
}

$(init);
</script>
</head>
<body>

	<!-- <div class="limiter">
		<div class="container-table100"> -->
			<div class="wrap-table100">
					<div class="table">

						<div class="row1 header">
							<div class="cell">글 번호</div>
							<div class="cell">작성날짜</div>
							<div class="cell">글 제목</div>
							<div class="cell">글쓴이</div>
							<div class="cell">비밀번호</div>
						</div>
						<c:set var="noticeList" value="${requestScope.allNoticeList}"></c:set>
						<c:forEach var="notice" items="${noticeList}">
						<div class="row1">
							<div class="cell" data-title="text_no">
								${notice.text_no}
							</div>
							<div class="cell" data-title="text_date">
							<fmt:formatDate pattern="yy/MM/dd" value="${notice.text_date}"/>
							</div>
							<div class="cell" data-title="text_title">
								${notice.text_title}
							</div>
							<div class="cell" data-title="employee_id">
								${notice.employee.employee_id}
							</div>
							<div class="cell" data-title="text_pwd">
								${notice.text_pwd}
							</div>
						</div>
						</c:forEach>
					
					</div>
			</div>
			
			
<!-- 		</div>
	</div>
 -->


</body>
</html>