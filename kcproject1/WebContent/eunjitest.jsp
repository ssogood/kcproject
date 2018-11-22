<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지점returnTest</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init(){
	$("#btRtnList").click(function(){
		$.ajax({
			url:"return/returnlist.do"
			,success:function(result){
				$("section").html(result)
			}
			
		});
		
		return false;
	});
	
	
	$("#btRtnAdd").click(function(){
		$.ajax({
			url:"returnadd.jsp"
			,success:function(result){
				$("section").html(result)
			}
			
		});
		return false;
	});
};
$(init);
</script>

</head>
<body>
<header>
지점returnTest
</header>

<button id="btRtnList">반품 목록</button>
<button id="btRtnAdd">반품 추가</button>
<section>

</section>
</body>
</html>