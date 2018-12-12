<%@page contentType="text/html; charset=UTF-8"%>    
<%@page import="com.kc.vo.IngredientInfo"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
baljurequest.table {
	display: table;
	width: 100%;
}

baljurequest.row {
	display: table-row;
}

baljurequest.row:first-child {
	font-weight: bold;
	text-align: center;
	background-color: yellow;
}

.check, .ingr_no, .ingr_name, .ingr_quantity, .ingr_price{
	display: table-cell;
	text-align: center;
	border-bottom: 1px solid;
	width:;
}
</style>


<script>
function init(){
	
	$.ajax({
		url:'/ingredient/ingrlist.do'
		,method:'post'		
		,success:function(result){	
			$("#baljurequest").empty();
			$("#baljurequest").html(result);
		}	
	});

	<%--
	//ingred_no(링크) 클릭하면 동작  
	$("#btaddcart").click(function(){
		var ingred_no = $("input[name=ingred_no]").val();
		var quantity = $("input[type=number]").val();		
		$.ajax({
			url:"return/addbaljucart.do"
			,method:'post'
			,data:{"ingred_no":ingr_no,"quantity":quantity}
			,success:function(result){
				
			}
		});
		return false;
	});
	--%>
	
	
	<%--	
	$("#detail  button").click(function(){		
		$.ajax({
			url:'addcart.do'
			,method:'post'
			,data:{prod_no:"${p.prod_no}=p.getProd_no()"
				, quantity:$("input[type=number]").val()}
			,success:function(result){
				var jsonObj = JSON.parse(result);
				if(jsonObj.status == 'ok'){ 
					alert('장바구니 넣기 성공');
					$("#cartresult").addClass("show").show();
				}else{
					alert('장바구니 넣기 실패');
				}
			}	
		});
	});
	--%>
	
	<%--
	장바구니로 이동
	$("#cartresult>.yes").click(function(){		
		 $("nav>ul>li>a").each(function(index, element){
				if($(element).attr("href").indexOf('/cartlist.do')>-1){
					$(element).trigger("click");
					return false;
				} 
			}); 
	});
	$("#cartresult>.no").click(function(){
		 $("nav>ul>li>a").each(function(index, element){
			if($(element).attr("href").indexOf(
					'/product/productlist.do')> -1 ){
				$(element).trigger("click");
				return false;
			} 
		}); 
	}); --%>
}
$(init);

</script>
</head>





<body>
<div id="baljurequest">

<h1>발주요청페이지(ingr4balju.jsp)</h1>

<div id="searchingr">
	<jsp:include page="../balju_searchingr.jsp"></jsp:include>
</div>

<c:set var="list" value="${requestScope.list}" />
<div class="table">
	<div class="row">
		<div class="check">선택</div>
		<div class="ingr_no">품번</div>
		<div class="ingr_name">품명</div>
		<div class="ingr_quantity">수량</div>
		<div class="ingr_price">가격</div>
	</div><!-- end row1 -->
	
	<c:forEach var="ingr" items="${list}">
	<div class="row">
		<div class="check"><input type="checkbox" name="food_no" value=""></div>
		<div class="ingr_no">${ingr.ingred_no}</div>
		<div class="ingr_name">${ingr.ingred_name}</div>
		<div class="ingr_quantity"><input type="number" min="1" max="99" value="1"></div>
		<div class="ingr_price"><fmt:formatNumber pattern="#,##0" value="${ingr.branch_forwarding_price}"/></div>		
	</div><!-- end row2 -->
	</c:forEach>
	<div class="row">	
		<div class="total_price"><fmt:formatNumber pattern="#,##0" value="${ingr.branch_forwarding_price}"/></div>
	</div><!-- end row3 -->
</div><!-- end table -->


<%-- 
<div id="cartresult">
  장바구니로 이동하시겠습니까?
   <button class="yes">예</button>
   <button class="no">아니오</button>
</div>
--%>
		
		
		
		
		
		
		
		
		
		
		
</div>					  
</body>
</html>