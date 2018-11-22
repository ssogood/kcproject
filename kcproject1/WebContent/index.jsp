<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init(){
	$("a").click(function(){
		//ajax기술로 현재객체의 href속성값을 URL로 요청
		//응답결과를 section 영역에 채우기
		var url = $(this).attr("href");//this.href
		$.ajax({
			url : url
			,success : function(result){ //result:응답결과
				$("section").html(result);
			}
		});
		return false; //기본이벤트 막기, 이벤트전달 중지
	});
	
	
};
$(init);
</script>
</head>
<body>
시작이다

<section>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<ui>
	<li><a href="${contextPath}/return/returnlist.do">반품목록보기</a></li>
	<li><a href="${pageContext.request.contextPath}/stats/all.do">통계</a></li>
	<li><a href="${pageContext.request.contextPath}/stats/sbtest.do">통계2</a></li> 
</ui>
</section>

</body>
</html>