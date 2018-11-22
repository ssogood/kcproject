<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eunjibonsatest</title>
<style>
article{display: inline-block; margin: 3px; border: 1px solid;}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init(){
	$("#btBonRtnList").click(function(){
		$.ajax({
			url:"return/returnlist.do"
			,success:function(result){
				$("section article[id=returnList]").html(result)
			}
			
		});
		
		return false;
	});
	
	$("#btBonRstList").click(function(){
		$.ajax({
			url:"restocking/restockinglist.do"
			,success:function(result){
				$("section article[id=restockingList]").html(result)
			}
			
		});
		
		return false;
	});
	
	$("#btBonDcList").click(function(){
		$.ajax({
			url:"discard/discardlist.do"
			,success:function(result){
				$("section article[id=discardList]").html(result)
			}
			
		});
		
		return false;
	});
};
$(init);
</script>
</head>
<body>
<header>본사return list, 본사 재입고, 본사 폐기 test</header>
<section>
<button id="btBonRtnList">본사 반품 목록</button>
<button id="btBonRstList">본사 재입고 목록</button>
<button id="btBonDcList">본사 폐기 목록</button>
<br>

<article id="returnList" style="width: 600px">하나</article>
<article id="restockingList" style="width: 500px">둘</article>
<article id="discardList" style="width: 500px">셋</article>

</section>
</body>
</html>