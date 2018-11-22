<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>branch main 화면</title>
<style>
#divRtnCart{
	display: none;
			}
.table{display:table}
.row{display:table-row;}
.no,.name,.quantity,.prod_state{display: inline-block;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init(){
	$("#btBranch").click(function(){
		var $radio = $("input[name=branch_main]:checked").val();
		//console.log($radio);
		if($radio==1){
			location.href='${pageContext.request.contextPath}/eunjitest.jsp';
		}else if($radio==2){
			console.log("주방가세요");
		}else{
			console.log("키오스크가세요");
		}
		
		return false;
	});
}

$(init);

</script>
</head>
<body>
<c:set var="branch_code" value="${sessionScope.branchLoginInfo}"></c:set>
${branch_code}님!<br>
<input type="radio" name="branch_main" value="1" checked>관리자<br>
<input type="radio" name="branch_main" value="2">주방<br>
<input type="radio" name="branch_main" value="3">키오스크<br>
<button id="btBranch">선택</button>
</body>
</html>