<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>returnbranchlistresult.jsp</title>
<style>
.rtntable{
		display: table;
		margin-left: 10px;
		margin-top: 10px;
		border-bottom: 1px solid;
		}
.rtnrow {display: table-row;
		}
.ingredient, .cate {display : table;}
.ingredient{width: 600px;}
.cate{width: 70px;}
.date,.ingred_no, .cateno, .catename, .quantity,.return_no,.ingred_name,.branch_code,.prod_state,.rtnState{display: table-cell;}
.date,.ingred_no{width: 100px}
.quantity,.prod_state{width: 100px}
.return_no{
	width: 60px;
	font-weight: bold;}
.branch_code{width:70px;}
.catename{width:70px;}
.cateno,.state_hidden{display: none;}
.ingred_name{width: 200px;}
.rtnState{width: 150px;}
#divReturn{
		height: 589px;
		overflow: scroll;
		}
.rtntable:first-child{
	padding:5px;
	background: rgb(230, 230, 230);
    color: #666;
}
button:not(.logout){
	background: #e6e6e6;
	border: none;
	border-radius: 5px;
	padding: 5px;
	margin: 1px;
}
select{
	padding:5px;
	border-radius: 5px;
	margin-left: 10px;
	margin-top: 10px;
}
	
</style>
<script>
function init(){
	$(".rtnState .rtnState_hidden").each(function(index,element){
		if($(this).text()==3){
			console.log("찾음?")
			var return_no = $(this).parents('.rtnrow').children('.return_no').text();
			 var branch_code = $(this).parents('.rtnrow').children('.branch_code').text();

			 
			setTimeout(function(){
			
			$.ajax({
				url:"return/updateflagtrs.do"
				,method:'POST'
				,data:{"branch_code":branch_code
					   ,"return_no":return_no}
				,success:function(result){
					$(".has-sub li a").each(function(index,element){
						
						if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnalllist.do")>-1){
							$(element).trigger("click");
							return false;
						}
					});
					
				}
			}); 
			}, 5000);
		}
	});
	
	$(".btRtnOk").click(function(){
		 var return_no = $(this).parents('.rtnrow').children('.return_no').text();
		 var branch_code = $(this).parents('.rtnrow').children('.branch_code').text();
		 $.ajax({
			url:"return/updateflagok.do"
			,method:'POST'
			,data:{"branch_code":branch_code
				   ,"return_no":return_no}
			,success:function(result){
				$(".has-sub li a").each(function(index,element){
					
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnalllist.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
				
			}
		}); 
		return false;
	});
	
	
	$(".btRtnX").click(function(){
		 var return_no = $(this).parents('.rtnrow').children('.return_no').text()
		 var branch_code = $(this).parents('.rtnrow').children('.branch_code').text();
		 $.ajax({
			url:"return/updateflagx.do"
			,method:'POST'
			,data:{"branch_code":branch_code
				   ,"return_no":return_no}
			,success:function(result){
				$(".has-sub li a").each(function(index,element){
					
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnalllist.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
				
			}
		}); 
		return false;
	});
	
	$(".btRtnCancel").click(function(){
		var return_no = $(this).parents('.rtnrow').children('.return_no').text();
		var branch_code = $(this).parents('.rtnrow').children('.branch_code').text();
		console.log("return:"+return_no+", branch_code:"+branch_code);
		 $.ajax({
				url:"return/updateflag2.do"
				,method:'POST'
				,data:{"branch_code":branch_code
					   ,"return_no":return_no}
				,success:function(result){
					$(".has-sub li a").each(function(index,element){
						
						if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnlistbranch.do")>-1){
							$(element).trigger("click");
							return false;
						}
					});
					
				}
			}); 
		return false;
	});
	
	$(".btRstOk").click(function(){//두가지 일 하기
								   //flag바꾸기와 restocking으로 보내기
		 var return_no = $(this).parents('.rtnrow').children('.return_no').text();
		 var branch_code = $(this).parents('.rtnrow').children('.branch_code').text();
		 $.ajax({
			url:"return/updateflagrst.do"
			,method:'POST'
			,data:{"branch_code":branch_code
				   ,"return_no":return_no}
			,success:function(result){
				$(".has-sub li a").each(function(index,element){
					
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnalllist.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}
		});
		
		//반품하는 line개수
		
		var rstJson = new Object(); //json형태 만들기
		//branch_code 1개, return_no 1개, ingred_no,rsl, quan 각각
		rstJson.branch_code =  $(this).parents('.rtnrow').children('.branch_code').text();
		rstJson.return_no =  $(this).parents('.rtnrow').children('.return_no').text();
		
		 $.ajax({
			 //restocking으로 저장하기
			 url:"restocking/addrestocking.do"
			 ,method:'POST'
			 ,data: rstJson
			 ,success : function(result){
				 
			 }
		});

		 
		return false;
	});
	
	
	$("#selectFlag").change(findBranchFlag);
	function findBranchFlag(){
		var flag_select=$("#selectFlag option:selected").val();
		if("${sessionScope.branchLoginInfo}"==""){
			var branch_code = $("#selectBranch option:selected").val();
			
			if(flag_select=='all'){
				$(".has-sub li a").each(function(index,element){
					
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnlistbranch.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}
		}else{
			var branch_code="${sessionScope.branchLoginInfo}";	
			if(flag_select=='all'){
				$(".has-sub li a").each(function(index,element){
					
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/return/returnlistbranch.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}else{
				
				
				$.ajax({
					url:"return/returnflag.do"
					,data:{"branch_code":branch_code
						   ,"return_state_flag":flag_select}
					,success:function(result){
						if(!("${requestScope.status}"=='error')){
						
							$("#divRtn").html(result);
						}else{
							alert("error");
						}
					}
				});  
				
				
			}
		
		}
		
		

			
	};
	
};
$(init);
</script>
</head>
<body>


<div id="divReturn">
<c:set var="returnList" value="${requestScope.returnList}"></c:set>
<c:choose>
<c:when test="${!empty sessionScope.branchLoginInfo}">
<select id="selectFlag" title="처리분류">
<optgroup>
<option value="all">전체상태</option>
<option value="1">승인대기</option>
<option value="2">반품취소</option>
<option value="3">승인완료</option>
<option value="4">승인취소</option>
<option value="5">배송중</option>
<option value="6">수취확인</option>
</optgroup>
</select>
</c:when>
</c:choose>
<div id="divRtn">
<div class="rtntable">
<div class=rtnrow>
<div class="return_no">번호</div>
<div class="branch_code">지점</div>
<div class="date">반품날짜</div>

<div class="ingredient">
<div class="ingred_no">재료 번호</div>
<div class="ingred_name">재료 이름</div>
<div class="cate">
<div class="catename">CATE</div>
</div>
<div class="quantity">반품 수량</div>
<div class="prod_state">재료 상태</div>
</div>
<div class="rtnState">반품 상태</div>

</div>
</div>

<div class="rtntable">


<c:forEach var="info" items="${returnList}">
<div class=rtnrow>
<div class="return_no">${info.return_no}</div>

<c:choose>
<c:when test="${!empty sessionScope.branchLoginInfo}"><!--지점일때-->
<div class="branch_code">${sessionScope.branchLoginInfo}</div>
</c:when>
<c:otherwise>
<div class="branch_code">${requestScope.branch_code}</div>
</c:otherwise>
</c:choose>
<div class="date"><fmt:formatDate pattern="yy/MM/dd" value="${info.return_date}"/></div>

	<c:forEach var="returnline" items="${info.return_lines}">
		<c:set var="ingred" value="${returnline.ingredient}"></c:set>
		<div class="ingredient">
		<div class="ingred_no">${ingred.ingred_no}</div>
		<div class="ingred_name">${ingred.ingred_name}</div>
		<c:set var="cate" value="${ingred.ingredCate}"></c:set>
		<div class="cate">
		<div class="cateno">${cate.ingred_cate_no}</div>
		<div class="catename">${cate.ingred_cate_name}</div>
		</div>
		<div class="quantity">${returnline.return_quantity}</div>
		<div class="state_hidden">${returnline.rtl_prod_state_flag}</div>
		
		<c:choose>
		<c:when test="${returnline.rtl_prod_state_flag==1}">
		<div class="prod_state">이상없음</div>
		</c:when>
		<c:when test="${returnline.rtl_prod_state_flag==2}">
		<div class="prod_state">파손</div>
		</c:when>
		<c:when test="${returnline.rtl_prod_state_flag==3}">
		<div class="prod_state">유통기한</div>
		</c:when>
		<c:otherwise>
		<div class="prod_state">기타</div>
		</c:otherwise>
		</c:choose>
		
		
		</div>
	</c:forEach> 
<div class="rtnState">
<c:choose>
  <c:when test="${!empty sessionScope.branchLoginInfo}"><!-- 지점일때 -->
  <c:choose>
	    <c:when test="${info.return_state_flag==1}">
	    승인대기
	<button class="btRtnCancel">반품취소</button>
	    </c:when>
	    <c:when test="${info.return_state_flag==2}">
	    반품취소
	    </c:when>
	     <c:when test="${info.return_state_flag==3}">
	    승인완료
	    </c:when>
	    <c:when test="${info.return_state_flag==4}">
	  승인취소
	    </c:when>
	    <c:when test="${info.return_state_flag==5}">
	    배송중
	    </c:when>
	    <c:when test="${info.return_state_flag==6}">
	    <p style="color: #ff0000">배송완료</p>
	    </c:when>
  </c:choose>
  </c:when>
  <c:when test="${!empty sessionScope.bonsaLoginInfo}"><!-- 본사일때 -->
	  <c:choose>
	    <c:when test="${info.return_state_flag==1}">
	    
	    <button class="btRtnOk">승인</button>
	    <button class="btRtnX">승인취소</button>
	    </c:when>
	     <c:when test="${info.return_state_flag==2}">
	    반품취소
	    </c:when>
	    <c:when test="${info.return_state_flag==3}">
	    승인완료
	    <button class="btRtnX">승인취소</button>
	    </c:when>
	    <c:when test="${info.return_state_flag==4}">
	    승인취소
	    </c:when>
	    <c:when test="${info.return_state_flag==5}">
	    배송중
	    <button class="btRstOk">수취확인</button>
	    </c:when>
	    <c:when test="${info.return_state_flag==6}">
	    <p style="color: #ff0000">처리완료</p>
	    </c:when>
	  </c:choose>
  </c:when>
  
</c:choose>
</div>

</div>
<br>
</c:forEach>

</div>
</div>
</div>
</body>
</html>