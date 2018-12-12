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
.rtnAddtable{display:table}
.row1{display:table-row;}
.no,.name,.quantity,.prod_state{display: inline-block;}

#divRtnAdd{
	margin-left:10px;
	margin-top:10px;
	
}

#btRtnCart,#btRtnApply{
	position:relative;
	left:200px;
}

table{
	margin:10px;
}

tbody tr th{
	padding: 10px 10px 10px;
	background: rgb(230, 230, 230);
    color: #666;
}

tbody tr td{
	padding: 10px 10px 10px;
	/* background: rgb(250, 250, 250);
    color: #666; */
}

#divRtnAdd:nth-child(2) tr:nth-child(1){
	/* height: 200px; */
	background: red;
}
input[name=ingred_no]{
	width:60px;
}

</style>
<script>
function init(){
	
	
	//여러개 묶는거 먼저
	$("#btRtnApply").click(function(){
		$.ajax({
			url:"return/addreturn.do"
			,method:'get'//server에 이미 장바구니 있으니까 data요청안해두됨
			,success : function(result){
				var jsonObj = JSON.parse(result);
				if(jsonObj.status=='error'){
					alert("로그인하세요");
				}else{
					$(".has-sub li a").each(function(index,element){	
						if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnlistbranch.do")>-1){
							$(element).trigger("click");
							return false;
						}
					});
				}
			}
			
		});
	
		return false;
	});
	
	$("#btRtnCart").click(function(){
		
		
		$.ajax({
			url:"return/addrtncart.do"
			,method:'post'
			,data:{"ing_no":$("input[name=ingred_no]").val(),
				   "quantity":$("input[type=number]").val(),
				   "prod_state":$("input[name=prod_state]:checked").val()}
			,success:function(){
				$(".has-sub li a").each(function(index,element){
					
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnadd.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}
		});
		return false;
		
	});
	
	$("#btFindIng").click(function(){
		window.open('searchingred.html','wing','width=300px, height=400px, left=250px, top=100px');
		
		return false;
	});
	



}

$(init);

</script>

</head>
<body>
<script>
function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		return false;
}
function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
}
</script>
<div id="divRtnAdd">
<table>
  <tbody>
    <tr>
      <th scope="row">반품 재료</th>
      <td>
      <input type="text" name="ingred_no"
      onkeydown="return onlyNumber(event)" onkeyup="removeChar(event)" 
      style="ime-mode:disabled;" placeholder="재료번호">
      <input type="text" name="ingred_name" placeholder="재료이름">
      <button id="btFindIng">재료찾기</button><br>
      </td>
    </tr>
    
    <tr>
      <th scope="row">반품 개수</th>
      <td>
      <input type="number" min="1" value="1" onkeydown="return onlyNumber(event)" onkeyup="removeChar(event)" style="ime-mode:disabled;"><br>
      </td>
    </tr>
    <tr>
      <th scope="row">재료 상태</th>
      <td>
      <input type="radio" name="prod_state" value="1" checked>이상 없음
      <input type="radio" name="prod_state" value="2" >파손
      <input type="radio" name="prod_state" value="3">유통기한 지남
      <input type="radio" name="prod_state" value="4">기타
      
      </td>
    </tr>
    
  </tbody>
</table>
<button id="btRtnCart">반품리스트 추가</button>
<table>
  <tbody>
    <tr>
      <th scope="row">반품 리스트</th>
      <td>
      <c:set var="cart" value="${sessionScope.cart}"></c:set>
		<c:choose>
		  <c:when test="${empty cart}">
		  <div>반품리스트가 비었습니다.</div>
		  </c:when>
		  <c:otherwise>
		  <c:forEach var="item" items="${cart}">
		  <c:set var="line" value="${item.key}"></c:set>
		  <c:set var="quantity" value="${item.value}"></c:set>
		  <div class="rtnAddtable">
		  <div class="row1">
		  <div class="no">${line.ingredient.ingred_no}번</div>
		  
		  <div class="quantity">${quantity}개</div>
		  <div class="prod_state">상태${line.rtl_prod_state_flag}</div>
		  </div>
		  </div>
		  </c:forEach>
		  </c:otherwise>
		</c:choose>
		
      </td>
    </tr>
  </tbody>
</table>


<button id="btRtnApply">반품신청</button>


</div>				  
</body>
</html>