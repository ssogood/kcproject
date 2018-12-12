<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
alert("로그아웃성공");
location.href='${pageContext.request.contextPath}';
console.log('${pageContext.request.contextPath}');
</script>