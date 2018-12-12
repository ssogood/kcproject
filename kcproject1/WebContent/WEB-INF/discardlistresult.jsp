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
.dctable{
		display: table;
		margin-left: 10px;
		margin-top: 10px;
		border-bottom: 1px solid;
		}
.dcrow {display: table-row;}
.ingredient, .cate {display : table;}
.ingredient{width: 500px;}
.cate{width: 70px;}
.date,.ingred_no,  .cateno, .catename, .quantity,.discard_no,.ingred_name,.prod_state{display: table-cell;}
.date,.ingred_no{width: 100px}
.discard_no{
	width: 60px;
	font-weight: bold;}
.quantity{width: 90px}
.prod_state{width: 200px}
.catename{width:70px;}
.cateno{display: none;}
.ingred_name{width: 200px;}

#divDiscard{
		height: 589px;
		overflow: scroll;
		}
.dctable:first-child{
	padding:5px;
	background: rgb(230, 230, 230);
    color: #666;
}

/* .modifyDC,.removeDC{
	width: 40px;
} */
</style>
<script>
function init(){
	$("#addDC").click(function(){
		$.ajax({
			url:'discard/discardadd.do'
			,success:function(result){
				$("#divDiscard").html(result);
			}
		});
		
		return false;
	});
	
	$(".removeDC").click(function(){
		
		var discard_no = $(this).parents('.dcrow').children('.discard_no').text();

		$.ajax({
			url:'discard/discardremove.do'
			,data:{"discard_no":discard_no}
			,success:function(result){
				var jsonObj = JSON.parse(result);
				
				if(jsonObj.status=='ok'){
					alert("삭제성공");
					$(".has-sub li a").each(function(index,element){
						if($(element).attr("href").indexOf("${pageContext.request.contextPath}/discard/discardlist.do")>-1){
							$(element).trigger("click");
							return false;
						}
					});
				}
			}
		});
		return false;
	});
	
	$(".modifyDC").click(function(){
		var discard_no = $(this).parents('.dcrow').children('.discard_no').text();
		console.log(discard_no);
		$.ajax({
			url:'discard/discardmodify.do'
			,data:{"discard_no":discard_no}
			,success:function(result){
				$("#divDiscard").html(result);
			}
		});
		
		return false;
	});
};
$(init);
</script>

</head>
<body>

<c:set var="discardList" value="${requestScope.discardList}"></c:set>
<button id="addDC">폐기추가</button>

<div id="divDiscard">
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
<div class="quantity">폐기 수량</div>

</div>
<div class="prod_state">상태</div>
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
		<div class="quantity">${info.discard_quantity}</div>

		</div>
				<c:choose>
		<c:when test="${info.dc_prod_state_flag=='2'}">
		<div class="prod_state">파손</div>
		</c:when>
		<c:when test="${info.dc_prod_state_flag=='3'}">
		<div class="prod_state">유통기한</div>
		</c:when>
		<c:when test="${info.dc_prod_state_flag=='A'}">
		<div class="prod_state">이상없음
		<button class="modifyDC">수정</button>
		<button class="removeDC">삭제</button></div>
		
		
		</c:when>
		<c:when test="${info.dc_prod_state_flag=='B'}">
		<div class="prod_state">파손
		<button class="modifyDC">수정</button>
		<button class="removeDC">삭제</button></div>
		
		
		</c:when>
		<c:when test="${info.dc_prod_state_flag=='C'}">
		<div class="prod_state">유통기한
		<button class="modifyDC">수정</button>
		<button class="removeDC">삭제</button>
		</div>
		
		
		</c:when>
		<c:when test="${info.dc_prod_state_flag=='D'}">
		<div class="prod_state">기타
		<button class="modifyDC">수정</button>
		<button class="removeDC">삭제</button>
		</div>
	
		
		</c:when>
		<c:otherwise>
		<div class="prod_state">기타</div>
		</c:otherwise>
		</c:choose>

</div>
<br>
</c:forEach>
</div>
</div>



</body>
</html>