<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.kc.vo.BaljuInfo"%>
<%@page import="com.kc.vo.BaljuLine"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="list" value="${requestScope.list}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title></title>

<style>
forwardinghistory{width:100%}
forwardinghistory.table{display: table;	width: 100%;}

#forwardinghistory>div>.row{display: table-row;}
#forwardinghistory>div>.row:first-child{
	font-weight: bold;
	text-align: center;	
	background-color: yellow;
}



.forwarding_no,.forwarding_date,.branch_name,.forwarding_state_flag,.forwarding_total
,.forwarding_quantity,.ingred_no,.ingred_name,.branch_forwarding_price{
	display: table-cell;
	text-align: center;
	border-bottom: 1px solid;	
}

 
.forwarding_no{width:10%;}
.forwarding_date{width:10%;}
.branch_name			{width:10%;}
.forwarding_state_flag {width:10%;}
.forwarding_total		{width:10%;}

.fingr{display: table; width:100%;}
.forwarding_quantity{width:20%;}
.ingred_no{width:20%;}
.ingred_name{width:40%;}
.branch_forwarding_price{width:20%;}
	
	
	
	
#forwardinghistory.message{
/* position: absolute; */
left:78px; top:165px; width:200px; height:62px; z-index:1; border-width:1px; border-style:none;
}
	
	
	
</style>



<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init() {
	
};
$(init);
/* function msghide(){
	message.innerHTML=''
}
function msgposit(){
	message.style.posLeft = event.x + 40 + document.body.scrollLeft
	message.style.posTop = event.y - 40 + document.body.scrollTop
	} */
function msgset(str,str2){
	var text
	text ='<table border="0" cellpadding="6" cellspacing="0" bgcolor="#FDFFF2" style="font-size:9pt; border-width:1; border-color:black; border-style:solid;">'
	text += '<tr><td><p><b>' + str + '<br><br></b>' + str2 + '</td></tr></table>'
	message.innerHTML=text
	}

</script>
</head>


<body>
<div id="forwardinghistory">
<%-- ${list}
{"forwarding_no":48
, "forwarding_date":Fri Nov 30 10:10:28 KST 2018
, "branch":{"branch_code":S0001
			, "branch_name":압구정R
			, "branch_address":서울특별시 강남구 언주로 861 (신사동)
			, "branch_pwd":null
			, "branch_phone":02-541-3622
			, "owner_name":나래
			, "owner_phone":010-1111-1111
			, "closed_day":화}
, "forwarding_state_flag":5
, "forwardingLines":[{"forwarding_no":48
					, "ingredient":{"ingred_no":107
									, "ingred_name":"리프 파이"
									, "bonsa_receiving_price":1800
									, "commission":0.1
									, "branch_forwarding_price":1980
									, "ingredCate":{"ingred_cate_no":1
													, "ingred_cate_name":"원자재"}
									, "quantity":1
									, "measurement":"개"}
					, "forwarding_quantity":22}]
, "balju_no":1} --%>

<h1>출고한내역이다</h1>
<div class="table">
	<div class="row">
		<div class="forwarding_no">출고번호</div>
		<div class="forwarding_date">출고날짜</div>
		<div class="branch_name">출고지점</div>
 
	
		<div class="fingr">
			<div class="forwarding_quantity">출고개수</div>
			<div class="ingred_no">품번</div>
			<div class="ingred_name">품명</div>
			<div class="branch_forwarding_price">출고가</div>
		</div>
		
		<div class="forwarding_total">출고비용</div>
		<div class="forwarding_state_flag">출고상태</div>
	</div><!-- end row1 -->
	
	
	
	
	
	<c:forEach var="finfo" items="${list}">
	<div class="row">
		<div class="forwarding_no">${finfo.forwarding_no}</div>
		<div class="forwarding_date"><fmt:formatDate pattern="yy/MM/dd" value="${finfo.forwarding_date}"/></div>
		<div class="branch_name"><a href="#">${finfo.branch.branch_name}</a></div>
		
		
		 
		<c:set var="total" value="0"></c:set>
		<c:forEach var="fline" items="${finfo.forwardingLines}">
			<div class="fingr">
				<div class="forwarding_quantity">${fline.forwarding_quantity}</div>
				<c:set var="fingr" value="${fline.ingredient}"/>
				<div class="ingred_no">
					<a href="#" 
						<%--onmousemove="msgposit()" 
						onmouseout="msghide()" --%> 
						onmouseover="msgset('${fingr.ingred_no}','${fingr.ingred_name}')">
					${fingr.ingred_no}</a>
				</div>
				
				
				<div class="ingred_name">${fingr.ingred_name}</div>
				<div class="branch_forwarding_price">
					<fmt:formatNumber pattern="#,###,##0" value="${fingr.branch_forwarding_price}"/>
				</div>
			</div>
			
			<c:set var="total" value="${total + (fingr.bonsa_receiving_price * fline.forwarding_quantity)}"></c:set>
			
		</c:forEach> 
		 
		<div class="forwarding_total">
			<fmt:formatNumber pattern="#,###,###,###,##0" value="${total}"/>
		</div>
		<div class="forwarding_state_flag">
			<%-- ${finfo.forwarding_state_flag} --%>
			<c:choose>
				<c:when test="5">배송중</c:when>
				<c:otherwise>받았다고함</c:otherwise>
				
			</c:choose>
		</div>
		
	</div><!-- end row2 -->
	</c:forEach>
	
</div><!-- end table -->


<!-- 메시창 -->
<div id="message"> 
<p> </p>
</div>

</div>
</body>
</html> 