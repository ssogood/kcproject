<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>discardaddresult</title>
<script>
function init(){
	
	
	
	
	$("#btFindIng").click(function(){
		window.open('searchingred.html','wing','width=300px, height=400px, left=250px, top=100px');
		
		return false;
	});
	
	$("#btDcApply").click(function(){
		$.ajax({
			url:"discard/adddiscard.do"
			,method:'post'
			,data:{"ingred_no":$("input[name=ingred_no]").val()
				  ,"discard_quantity":$("input[type=number]").val()
				  ,"dc_prod_state_flag":$("input[name=prod_state]:checked").val()}
			,success:function(result){
				$(".has-sub li a").each(function(index,element){
					if($(element).attr("href").indexOf("${pageContext.request.contextPath}/discard/discardlist.do")>-1){
						$(element).trigger("click");
						return false;
					}
				});
			}
		});
		
		return false;
	});
};
$(init);
</script>
<style>
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
input[name=ingred_no]{
	width:60px;
}
</style>
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
<table>
  <tbody>
    <tr>
      <th scope="row">폐기 물품</th>
      <td>
      <input type="text" name="ingred_no"
      onkeydown="return onlyNumber(event)" onkeyup="removeChar(event)" 
      style="ime-mode:disabled;" placeholder="재료번호">
      <input type="text" name="ingred_name" placeholder="재료이름">
      <button id="btFindIng">재료찾기</button><br>
      </td>
    </tr>
    
    <tr>
      <th scope="row">폐기 개수</th>
      <td>
      <input type="number" min="1" value="1" onkeydown="return onlyNumber(event)" onkeyup="removeChar(event)" style="ime-mode:disabled;"><br>
      </td>
    </tr>
    <tr>
      <th scope="row">폐기 상태</th>
      <td>
      <input type="radio" name="prod_state" value="A" checked>이상 없음
      <input type="radio" name="prod_state" value="B" >파손
      <input type="radio" name="prod_state" value="C">유통기한 지남
      <input type="radio" name="prod_state" value="D">기타
      
      </td>
    </tr>
    
  </tbody>
</table>
<button id="btDcApply">폐기신청</button>
</body>
</html>