<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>returnadd.jsp</title>
<style>
#divRtnCart{
	display: none;
			}
.table{display:table}
.row{display:table-row;}
.no,.name,.quantity,.prod_state{display: inline-block;}
</style>
<script>
function init(){
	$("#btFindIng").click(function(){
		window.open('searchingred.html','wing','width=300px, height=400px, left=250px, top=100px');
		return false;
	});
	
	
	//여러개 묶는거 먼저
	$("#btRtnApply").click(function(){
		$.ajax({
			url:"return/addreturn.do"
			,method:'get'//server에 이미 장바구니 있으니까 data요청안해두됨
			,success : function(){
				$("button").each(function(index,element){
					if($(element).attr("id").indexOf("btRtnList")>-1){
						$(element).trigger("click");
						return false;
					}
				}); 
			}
			
		});
	
		return false;
	});
	
	$("#btRtnCart").click(function(){
		
		console.log("음식상태"+$("input[name=prod_state]:checked").val());
		$.ajax({
			url:"return/addrtncart.do"
			,method:'post'
			,data:{"ing_no":$("input[name=ingred_no]").val(),
				   "quantity":$("input[type=number]").val(),
				   "prod_state":$("input[name=prod_state]:checked").val()}
			,success:function(){
			 	$("button").each(function(index,element){
					if($(element).attr("id").indexOf("btRtnAdd")>-1){
						$(element).trigger("click");
						return false;
					}
				}); 
			}
		});
		return false;
		
	});
}

$(init);

</script>

</head>
<body>
추가페이지
<br>

<label>반품 재료</label><input type="text" name="ingred_no" readonly>
					 <input type="text" name="ingred_name" readonly>
<button id="btFindIng">재료찾기</button><br>
<label>반품 개수</label><input type="number" value="1" min="1"><br>
<label>재료 상태</label>

<input type="radio" name="prod_state" value="1" checked>이상 없음
<input type="radio" name="prod_state" value="2" >파손
<input type="radio" name="prod_state" value="3">유통기한 지남
<input type="radio" name="prod_state" value="4">기타

<br>
<button id="btRtnCart">반품리스트 추가</button>
<c:set var="cart" value="${sessionScope.cart}"></c:set>
<c:choose>
  <c:when test="${empty cart}">
  <div>반품리스트가 비었습니다.</div>
  </c:when>
  <c:otherwise>
  <c:forEach var="item" items="${cart}">
  <c:set var="line" value="${item.key}"></c:set>
  <c:set var="quantity" value="${item.value}"></c:set>
  <div class="table">
  <div class="row">
  <div class="no">${line.ingredient.ingred_no}번</div>
  
  <div class="quantity">${quantity}개</div>
  <div class="prod_state">상태${line.rtl_prod_state_flag}</div>
  </div>
  </div>
  </c:forEach>
  </c:otherwise>
</c:choose>

<br>
<button id="btRtnApply">반품신청</button>
					  
</body>
</html>