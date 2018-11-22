<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>discardlistresult</title>
<style>
.dctable{display: table;}
.dcrow {display: table-row;}
.ingredient, .cate {display : table;}
.ingredient{width: 350px;}
.cate{width: 70px;}
.date,.ingred_no,  .cateno, .catename, .quantity,.discard_no,.ingred_name,.prod_state{display: table-cell;}
.date,.ingred_no{width: 100px}
.discard_no, .quantity,.prod_state{width: 50px}
.catename{width:70px;}
.cateno{display: none;}
.ingred_name{width: 200px;}
</style>
<script>

</script>

</head>
<body>

<c:set var="discardList" value="${requestScope.discardList}"></c:set>

<br>
<div class="dctable">
<div class=dcrow>
<div class="discard_no">번호</div>
<div class="date">반품날짜</div>
<div class="ingredient">
<div class="ingred_no">재료 번호</div>
<div class="ingred_name">재료 이름</div>
<div class="cate">
<div class="catename">CATE</div>
</div>
<div class="prod_state">상태</div>
<div class="quantity">반품수량</div>
</div>

</div>
</div>

<div class="dctable">
<c:forEach var="info" items="${discardList}">
<div class=dcrow>
<div class="discard_no">${info.discard_no}</div>
<div class="date"><fmt:formatDate pattern="yy/MM/dd" value="${info.discard_date}"/></div>
	
		<c:set var="ingred" value="${info.ingredient}"></c:set>
		<div class="ingredient">
		<div class="ingred_no">${ingred.ingred_no}</div>
		<div class="ingred_name">${ingred.ingred_name}</div>
		<c:set var="cate" value="${ingred.ingredCate}"></c:set>
		<div class="cate">
		<div class="cateno">${cate.ingred_cate_no}</div>
		<div class="catename">${cate.ingred_cate_name}</div>
		</div>
		<div class="prod_state">${info.dc_prod_state_flag}</div>
		<div class="quantity">${info.discard_quantity}</div>개
		</div>

</div>
<br>
</c:forEach>
</div>




</body>
</html>