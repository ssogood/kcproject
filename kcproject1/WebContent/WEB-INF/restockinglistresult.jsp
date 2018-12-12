<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>restockinglistresult</title>
<style>
.rsttable{display: table; width: 500px;}
.rstrow {display: table-row;}
.ingredient, .cate {display : table;}
.ingredient{width: 400px;}
.cate{width: 70px;}
.date,.ingred_no,  .cateno, .catename, .quantity,.restocking_no,.ingred_name,.prod_state{display: table-cell;}
.date,.ingred_no{width: 100px}
.restocking_no, .quantity{width: 50px}
.catename{width:70px;}
.prod_state{width:100px;}
.cateno,.state_hidden{display: none;}
.ingred_name{width: 200px;}
</style>
<script>
function init(){
	$(".btDcAdd").click(function(){
		var rstJson = new Object();
		rstJson.restocking_no = $(this).parent().parent().parent().children('.restocking_no').text();
		
		rstJson.ingred_no = $(this).parent().parent().children('.ingred_no').text();
		rstJson.rsl_prod_state_flag = $(this).parent().parent().children('.state_hidden').text(); 
		
		$.ajax({
			url:"restocking/updateflagdc.do"
			,method:'POST'
			,data: rstJson
			,success:function(result){
				$("button").each(function(index,element){
					if($(element).attr("id").indexOf("btBonRstList")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}
		});
		
		var dcJson = new Object();
		dcJson.ingred_no = $(this).parent().parent().children('.ingred_no').text();
		dcJson.discard_quantity = $(this).parent().parent().children('.quantity').text();
		dcJson.dc_prod_state_flag = $(this).parent().parent().children('.state_hidden').text();
		
		$.ajax({
			url:"discard/adddiscard.do"
			,method:'POST'
			,data : dcJson
			,success:function(result){
				$("button").each(function(index,element){
					if($(element).attr("id").indexOf("btBonDcList")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}
		});
		
	});
	
};

$(init);
</script>

</head>

<body>
<c:set var="restockingList" value="${requestScope.restockingList}"></c:set>

<div class="rsttable">
<div class="rstrow">
<div class="restocking_no">번호</div>
<div class="date">재입고날짜</div>
<div class="ingredeint">
<div class="ingred_no">재료 번호</div>
<div class="ingred_name">이름</div>
<div class="cate">
<div class="catename">CATE</div>
</div>
<div class="quantity">수량</div>
<div class="prod_state">상태</div>
</div>
</div>
</div>

<div class="rsttable">
<c:forEach var="info" items="${restockingList}">
	<div class="rstrow">
	<div class="restocking_no">${info.restocking_no}</div>
	<div class="date"><fmt:formatDate pattern="yy/MM/dd" value="${info.restocking_date}"/></div>
	<c:forEach var="restockingline" items="${info.restocking_lines}">
		<c:set var="ingred" value="${restockingline.ingredient}"></c:set>
		<div class="ingredeint">
		<div class="ingred_no">${ingred.ingred_no}</div>
		<div class="ingred_name">${ingred.ingred_name}</div>
		<c:set var="cate" value="${ingred.ingredCate}"></c:set>
		<div class="cate">
		<div class="cateno">${cate.ingred_cate_no}</div>
		<div class="catename">${cate.ingred_cate_name}</div>
		</div>
		<div class="quantity">${restockingline.restocking_quantity}</div>개
		<div class="state_hidden">${restockingline.rsl_prod_state_flag}</div>
		<div class="prod_state">
		<c:choose>
		  <c:when test="${restockingline.rsl_prod_state_flag==1}">
		  이상없음
		  </c:when>
		  <c:otherwise>
		  <c:choose>
		    <c:when test="${restockingline.dc_flag==1}">
		    <button class="btDcAdd">폐기</button>
		    </c:when>
		    <c:otherwise>
		    폐기 완료
		    </c:otherwise>
		  </c:choose>
		  </c:otherwise>
		</c:choose>
		</div>
		</div>
	</c:forEach>
	</div>
</c:forEach>
</div>
</body>
</html>