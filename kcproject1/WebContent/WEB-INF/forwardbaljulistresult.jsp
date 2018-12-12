<%-- <%@page import="com.kc.vo.BaljuLine"%>
<%@page import="com.kc.vo.BaljuInfo"%>
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="grandlist" value="${requestScope.grandall}" />



<!DOCTYPE html>
<html>
<head>
<title>본사-발주처리할곳</title>

<meta charset="UTF-8">


<style>

.headbalju>.table {display: table;	width: 100%;}
.headbalju>.table>.row {display: table-row;}
.headbalju>.table>.row:first-child {font-weight: bold;	text-align: center;	background-color: yellow;}

.branch_code
, .no
, .ingredient_no
, .ingredient_name
, .quantity
, .date
, .headwork {
	display: table-cell;
	height: 37px;
	margin:10px;
	padding: 5px;
	border-bottom: 1px solid;
	text-align: center;
	vertical-align: middle;
}

.info, .line, .ingredient {
	display: table;
	width: 100%;
}

.branch_code{width:10%;}
.no{width:10%;}
.date{width:10%;}
.ingredient_no{width:10%;}
.ingredient_name{width:20%;}
.quantity{width:10%;}
.headwork{width:30%;}

.headwork>table{margin: auto;}
</style>

<script>
	function init() {
		//발주add
		$(".btndeliver").click(function() {
			var branch_code = $(this).parents(".row").find(".branch_code_h").html();
			var balju_no = $(this).parents(".row").find(".no_h").html();		
			//console.log(branch_code + ":" + balju_no);
			$.ajax({
				url:'forwarding/addforwarding.do'
				,method:'post'
				,data : {"branch_code":branch_code,"balju_no":balju_no}
			    ,success: function(result) {
   					var jsonObj = JSON.parse(result);
					if(jsonObj.status == "ok"){ 
						alert('출고성공');			
						
						$.ajax({
							url : "balju/updatebaljuflag.do"
							,type : "post"
							,data : {
								"branch_code" : branch_code,
								"balju_no" : balju_no,
								"newflag" : 5
							}
						});
					}else{alert('출고실패');}
					
					$("#headsidemenubaljulist").trigger("click");
					//return false;
	            }
			}); 
		});

		$(".confirmornot").click(function() {
			var branch_code = $(this).parents(".row").find(".branch_code_h").html();
			//console.log("branch_code:"+ branch_code);

			var $state = $(this).html().trim();
			//console.log("state:" +$state);
			var newflag;
			if ($state == "승인") {
				newflag = "3";
			} else {
				newflag = "4";
			}

			var balju_no = $(this).parents(".row").find('.no_h').html();
			console.log(balju_no);

			alert(branch_code + ":" + balju_no + ":" + newflag);

			$.ajax({
				url : "balju/updatebaljuflag.do",
				type : "post",
				data : {
					"branch_code" : branch_code,
					"balju_no" : balju_no,
					"newflag" : newflag
				},
				success : function(result) {
					var jsonObj = JSON.parse(result);
					if (jsonObj.status == "ok") {
						alert("성공");
					} else {
						alert("실패");
					}
					$("#headsidemenubaljulist").trigger("click");
					return false;
				}
			});
			return false;
		});
	}
	$(init);
</script>
</head>










<body>
<div class="headbalju">
	<div class="table">
		<div class="row">
			<div class="branch_code">지점</div>
			<div class="info">
				<div class="no">발주번호</div>
				<div class="date">발주날짜</div>
				<div class="ingredient">
					<div class="ingredient_no">품번</div>
					<div class="ingredient_name">품명</div>
					<div class="quantity">발주수량</div>
				</div>
				<div class="headwork">처리상태</div>
			</div>
		</div>
		<!-- row -->

<c:set var="brc" value="" />
<c:set var="bn" value="" />
<c:set var="bdate" value="" />
<c:set var="bstate" value="" />

		<c:forEach var="map1" items="${grandlist}" varStatus="status">
			<div class="row">
				<div class="branch_code">
					<div class="branch_code_h" style="display: none;">${map1['branch_code']}</div>
					<c:if test="${brc != map1['branch_code']}">${map1['branch_code']}</c:if>
					<c:set var="brc" value="${map1['branch_code']}" />
				</div>

				<c:set var="info" value="${map1['baljuInfo']}" />
				<div class="info">
					<div class="no">
						<div class="no_h" style="display: none;">${map1['balju_no']}</div>
						<c:if test="${bn != map1['balju_no']}">${map1['balju_no']}</c:if>
						
					</div>

					<div class="date">
						<c:if test="${bdate != info.balju_date}">
							<fmt:formatDate pattern="yy/MM/dd" value="${info.balju_date}" />
						</c:if>
						<c:set var="bdate" value="${info.balju_date}" />
					</div>

					<c:forEach var="line" items="${info.baljuLines}">
						<div class="ingredient">
							<c:set var="ingr" value="${line.ingredient}"></c:set>
							<div class="ingredient_no">${ingr.ingred_no}</div>
							<div class="ingredient_name">${ingr.ingred_name}</div>
							<!-- 상품 -->
							<div class="quantity">${line.balju_quantity}</div>
							<!-- 수량 -->
						</div>
					</c:forEach>
					
					<div class="headwork">					
						<%-- ${info.balju_state_flag} --%>
						<c:if test="${bn != map1['balju_no'] or bstate != info.balju_state_flag}">
							<c:choose>
								<c:when test="${info.balju_state_flag eq 1}">
									<table>
										<tr>
											<td><button class="confirmornot" id="confirm">승인</button></td>
											<td>&nbsp;&nbsp;</td>
											<td><button class="confirmornot" id="cancle">취소</button></td>
										</tr>
									</table>
								</c:when>
								<c:when test="${info.balju_state_flag eq 2}">지점에서취소</c:when>
								<c:when test="${info.balju_state_flag eq 3}">내가승인
									<button class="btndeliver">배송시작</button>
								</c:when>
								<c:when test="${info.balju_state_flag eq 4}">내가취소</c:when>
								<c:when test="${info.balju_state_flag eq 5}">배송중</c:when>
								<c:otherwise>수취완료</c:otherwise>
							</c:choose>
						</c:if>
						<c:set var="bstate" value="${info.balju_state_flag}"/>
						<c:set var="bn" value="${map1['balju_no']}" />
					</div>
				</div><!-- end info -->
			</div>
		</c:forEach>
	</div><!-- end table -->
</div><!-- end headbalju -->



</body>
</html>