<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>발주내역</title>
<style>
.baljuhistory>.table{display: table; width:90%; }
.baljuhistory>.table>.row{display:table-row;}
.baljuhistory>.table>.row:first-child{font-weight: bold; text-align: center; background-color: yellow;}

.balju_no
,.balju_date
,.balju_state
,.receiving_date
,.ingred_no
,.ingred_name
,.ingred_quantity{ 
	display: table-cell; 
	height: 50px;
	margin:10px; 
	padding:5px; 
	border-bottom: 1px solid; 
	text-align:center;
	vertical-align: middle;
}

.balju_no{width:10%;}
.balju_date{width:10%;}
.receiving_date{width:10%;}
.ingr{display:table;width:100%;}
.ingred_no{width:10%;}
.ingred_name{width:30%;}
.ingred_quantity{width:10%;}
.balju_state{width:20%;}
</style>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script>
/* 
function updateflag(newflag) {
	//alert(newflag);
	
	var branch_code = "S0001";
	var balju_no = "";
	//var newflag
	alert(branch_code + "," + balju_no + "," newflag);
	
	$.ajax({
		url: "balju/updateflag.do"
			{branch_code, int balju_no, String newflag}
		,data: {newflag:newflag}
		,success:function(result){	
			aleart("성공");
			//$("section").html(result); 
		}
	}); 
	return false;
}
 */
function init(){
	 $(".btn").click(function(){
		 var $parent = $(this).parent().parent();
		 var branch_code = "S0001";
		 var $state = $(this).html().trim();
		 var newflag;
		 var balju_no = $(this).parent().siblings('.balju_no').html().trim();
		 
		 if($state=="발주취소"){
			 newflag="2";
			 
			 $.ajax({			 
					url: "balju/updatebaljuflag.do"
					,type: "post"
					,data: {"branch_code":branch_code,"balju_no":balju_no,"newflag":newflag}
					,success:function(result){
						var jsonObj = JSON.parse(result);					
						
						if(jsonObj.status == "ok"){ 
							alert("성공");			
						}else{
							alert("실패");
						}					
						$("#branchindexbaljulist").trigger("click");
						return false;
					}
			});
			 
		 }else{
			 newflag="6";			 
			 $.ajax({			 
				url: "balju/updatebaljuflag.do"
				,type: "post"
				,data: {"branch_code":branch_code,"balju_no":balju_no,"newflag":newflag}
				,success:function(result){
					var jsonObj = JSON.parse(result);					
					
					if(jsonObj.status == "ok"){ 
						alert("수취확인성공");
						$.ajax({			 
							url: "forwarding/updateforwardingflag.do"
							,type: "post"
							,data: {"branch_code":branch_code,"balju_no":balju_no,"newflag":newflag}							
						});
						
					}else{alert("수취확인실패");}					
					
					$("#branch_baljuhistory").trigger("click");
					//return false;
				}
			});
			 
			 
			 
		 }
		 
		 
		 /* $.ajax({			 
				url: "balju/updatebaljuflag.do"
				,type: "post"
				,data: {"branch_code":branch_code,"balju_no":balju_no,"newflag":newflag}
				,success:function(result){
					var jsonObj = JSON.parse(result);					
					
					if(jsonObj.status == "ok"){ 
						alert("성공");			
					}else{
						alert("실패");
					}					
					$("#branchindexbaljulist").trigger("click");
					return false;
				}
		}); */
		//return false;
	 });
}$(init);	
</script>
</head>



<body>
<div class="baljuhistory">
	<div class="table">
		<div class="row">
			<div class="balju_no">NO</div>
			<div class="balju_date">발주일</div>
			
			<div class="receiving_date">수취일</div>	
			<div class="ingr">
				<div class="ingred_no">품번</div>
				<div class="ingred_name">품명</div>
				<div class="ingred_quantity">발주수량</div>
			</div>
			
			<div class="balju_state">발주상태</div>
		</div><!-- row -->
		
		<c:forEach var="info" items="${requestScope.all}" varStatus="">	
			<div class="row">
				<div class="balju_no">${info.balju_no}</div>				
				<div class="balju_date"><fmt:formatDate pattern="yy/MM/dd" value="${info.balju_date}"/> </div>
				
				<div class="receiving_date"><fmt:formatDate pattern="yy/MM/dd" value="${info.receiving_date}"/> </div>
				
				
				<c:forEach var="line" items="${info.baljuLines}">
				<div class="ingr">
					<c:set var="ingr" value="${line.ingredient}"/>
					<div class="ingred_no">${ingr.ingred_no}</div>					
					<div class="ingred_name">${ingr.ingred_name}</div>
					<div class="ingred_quantity">${line.balju_quantity}${ingr.measurement}</div>	
				</div>
				</c:forEach>
						
				
				<%-- <div class="balju_state">${info.balju_state_flag}</div> --%>
				<div class="balju_state" hidden="${info.balju_state_flag}">
				
					<c:choose>
						<c:when test="${info.balju_state_flag eq 1}">발주요청함
							<!-- <a href="javascript:;">발주취소</a> -->
							<button class="btn" id="st1to2" style="background-color: pink; color:white;">발주취소</button>
							<!-- <a href="javascript:;" onclick="updateflag(2);">발주취소</a> -->
						</c:when>
						<c:when test="${info.balju_state_flag eq 2}">내가취소함</c:when>
						<c:when test="${info.balju_state_flag eq 3}">본사승인</c:when>
						<c:when test="${info.balju_state_flag eq 4}">본사승인취소</c:when>
						<c:when test="${info.balju_state_flag eq 5}">배송중
							<button class="btn" id="st5to6" style="background-color: #4CAF50; color:white;">수취확인</button>
						</c:when>	
						<c:otherwise>수취완료</c:otherwise>
					</c:choose>
				</div>
			</div><!-- row -->
		</c:forEach>
		
	</div><!-- end table -->
</div><!-- end baljuhistory -->
</body>
</html>