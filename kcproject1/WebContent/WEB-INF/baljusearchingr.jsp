<%@ page  contentType="text/html; charset=UTF-8"%>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 -->
<script>
function init(){
  $("form").submit(function(){
	  $.ajax({
		  url:"ingredient/searchingrlist.do"
		  ,method:"post"
		  ,data: $(this).serialize()
		  ,success: function(result){
			  $("#balju").empty().html(result);
			  //$(".baljurequest").empty().html(result);
		  }
	  });
	  return false;
  });	
}
$(init);
</script>
<style>
</style>

<form name="baljuingrsearch" method="post"> <%--action속성이 생략되면 현재사용중인 URL로 전송됨 --%>
<table>
<tr>
<td>
  <select name="searchItem" style="height: 26px">
     <option value="no" ${param.searchItem == 'no'? "selected":""}>번호로 찾기</option>
     <option value="name" ${param.searchItem == 'name'? "selected": ""}>이름으로찾기</option>
  </select>
</td>
  <td><input type="search" name="searchValue" value="${param.searchValue}"></td>
  <td><input type="submit" value="검색"></td>
  </tr>
</table>  
</form>





