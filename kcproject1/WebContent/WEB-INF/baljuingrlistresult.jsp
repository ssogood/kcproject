<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>발주요청</title>
<style>

/* .searchbar{
position:fixed;
top:600px;  
left:10px;
background:skyblue;
/* transform:translate(-50%, -50%); */

.baljurequest{display: table; width:70%;}
.baljurequest>.row{display:table-row;}
.baljurequest>.row:first-child{font-weight:bold; text-align:center;}

.btn
,.ingred_no
,.ingred_name		
,.ingred_price	
,.ingred_quantity		
,.ingred_cate
{ display: table-cell; 
margin:10px; 
padding:5px; 
border-bottom: 1px solid; 
text-align:center;}

.btn{width:10%;}
.ingred_no{width:10%;}
.ingred_cate{ width:10%;}
.ingred_name{ width:40%;}	
.ingred_price{ width:10%;}
.ingred_quantity{ width:20%;}	

#baljucartresult{ display: none; }
.show {
  display: inline-block;
  width: 200px;  height:150px;
  position: absolute;  left: 300px; top:300px;  
}

#other .ingred_no{ width:10%;}
#other .ingred_name{ width:30%;}	
#other .ingred_price{ width:10%;}
#other .ingred_quantity{ width:10%;}

#other{
width:400px;
border:1px solid;
position:fixed;
top:50%;
right:0%;

overflow:scroll;
background-color:white;
opacity: 0.8;
display:none;

}
</style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function init(){
	
	$('.chkbox1').click(function(){//클릭
		if(this.checked){//클릭한게 체크된거면
			$("#other").show();
			//복사
			var $parent = $(this).parent();
			var ingred_no = $parent.siblings('.ingred_no').html().trim();
			var ingred_cate=$parent.siblings('.ingred_cate').html().trim();
			var ingred_name=$parent.siblings('.ingred_name').html().trim();
			var ingred_price=$parent.siblings('.ingred_price').html().trim();
			//var balju_quantitiy = $parent.siblings('.ingred_quantity').children('input.balju_quantitiy').val().trim();
			
			var $other_ul = $("#other>ul");				
			var $li = $("<li>"); 
			$parent.siblings('.ingred_no').clone().appendTo($li);
			$parent.siblings('.ingred_name').clone().appendTo($li);
			$parent.siblings('.ingred_price').clone().appendTo($li);
			//$parent.siblings('.ingred_quantity').clone().appendTo($li);
			var input_quantity = '<div class="ingred_quantity"><input type="number" class="balju_quantitiy" min="1" max="${ingr.quantity}" value="1"></div>';
			$li.append(input_quantity);
			$other_ul.append($li);
			
			
		}else{//클릭한게 체크된게 아니면
			var $parent = $(this).parent();
			var ingred_no = $parent.siblings('.ingred_no').html().trim();
			var $other_li = $("#other>ul>li");
			for(var i=0; i<$other_li.length; i++){
				element = $other_li[i];
				if(ingred_no == $(element).find(".ingred_no").html()){
					$(element).remove();
					break;
				}
			};
		}
		
		//버튼 생겼다 없어졌다
		if($("#other>ul>li").length > 0){
			$("#other>button").show();
		}else{
			$("#other>button").hide();
			$("#other").hide();
		}
		
	});

	
	/* 
	//전체 체크박스수
	var a1= $("input:checkbox[class=chkbox1]").length;
	//선택된 체크박스개수
	var a2= $("input:checkbox[class=chkbox1]:checked").length;
	//전체 checkbox 순회하기
	$("input:checkbox[class=chkbox1]").each(function() {
	      this.checked = true;//전체체크
	});
	//console.log(a2 +"/" +a1);
 */	
	
	$("#other>button[name=baljustart]").click(function(){
		
	      var jsonArr = new Array();   //Object를 배열로 저장할 Array         
	      var $mini = $("#other");
	      var $mini_nos = $mini.find('.ingred_no');
	      //#other > ul > li:nth-child(3) > div.ingred_no
	      var $mini_bqs = $mini.find('.ingred_quantity>input');
	      //#other > ul > li:nth-child(3) > div.ingred_quantity > input
	      
	      console.log($mini_bqs);
	      for(var i=0; i<$mini_nos.length; i++){
	         var div_no = $mini_nos[i];
	         //var div_bq = $mini_bqs[i];
	         
	         var jsonObj = new Object();   //key, value형태로 저장할 Object
	         jsonObj.branch_code = 'S0001';
	         jsonObj.ingred_no = $(div_no).html();
	         jsonObj.balju_quantity = $($mini_bqs[i]).val();
	         //jsonObj.balju_quantity = parseInt($("#other>ul>li>div.ingred_quantity>input").val());
	         //<input type="number" class="balju_quantitiy" min="1" max="" value="1">
	         
	         
	         jsonArr.push(jsonObj);
	      }
	      alert(JSON.stringify(jsonArr));


	      
		
		var con_test = confirm("해당 내용으로 발주하시겠습니까?");
		if(con_test == true){//확인  
			
			$.ajax({
				url:'balju/addbalju.do'
				,method:'post'
				,data: JSON.stringify(jsonArr)
	            ,dataType: "json"
	            ,contentType: "application/json"
	            ,success: function(result) {
	            				var jsonObj = JSON.parse(result);
									if(jsonObj.status == "ok"){ 
										alert('발주완료');					
									}else{
										alert('발주실패');
									}
					            }
				,error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		       }
			});
		}
		else if(con_test == false){//취소
			return false;
		}
		return false;
	});
}
$(init);
</script>
</head>
<body>
<c:set var="ingrlist" value="${requestScope.list}" />
<div id="balju" style="clear:both;position: relative;">
<c:choose>
  <c:when test="${empty ingrlist}">상품이 존재하지 않습니다.</c:when>
  <c:otherwise>
<h1 align="left">지점명</h1>
<div class="searchbar"><jsp:include page="baljusearchingr.jsp"></jsp:include></div>
<div class=baljurequest><!-- table -->
	<div class="row">
		<div class="btn">선택</div>
		<div class="ingred_no">품번</div>
		<div class="ingred_cate">카테고리</div>
		<div class="ingred_name">품명</div>		
		<div class="ingred_price">가격</div>	
		<div class="ingred_quantity">비고</div>		
	</div><!-- row -->
	
<c:forEach var="ingr" items="${ingrlist}" varStatus="">
	<div class="row">		
		<div class="btn"><input type="checkbox" class="chkbox1" ></div>
		<div class="ingred_no">${ingr.ingred_no}</div>
		<div class="ingred_cate">${ingr.ingredCate.ingred_cate_name}</div>
		<div class="ingred_name">${ingr.ingred_name}</div>		
		<div class="ingred_price"><fmt:formatNumber pattern="#,##0" value="${ingr.branch_forwarding_price}"/></div>
		<div class="ingred_quantity">
		<c:choose>
			<c:when test="${ingr.quantity eq 0}"><font color="red">본사재고없음</font></c:when>
			<c:otherwise>
				<%-- <input type="number" name="" class="balju_quantitiy" min="1" max="${ingr.quantity}" value="1"> --%>
			</c:otherwise>
		</c:choose>
		</div>		
	</div><!-- row -->
</c:forEach>
</div>

<div id="other">
	<ul style="list-style: none">    
	</ul>
	<!-- <button style="display:none; background-color:green">전체선택</button> -->
	<!-- <button style="display:none; background-color:green">해제</button> -->
	<button name="baljustart" style="display:none; background-color:red">발주하기</button>
</div> <!--  other end -->

</div> <!--  balju end -->
</c:otherwise>
</c:choose>

</body>


