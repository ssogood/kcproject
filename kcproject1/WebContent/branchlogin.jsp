<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>branchlogin.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
function init(){
	var $form = $("form");
	$form.submit(function(){
		$.ajax({
			url:"branch/login.do"
			,method:'POST'
			,data : $form.serialize()
			,success:function(result){
				var jsonObj = JSON.parse(result);
				if(jsonObj.result=='ok'){
					console.log('${pageContext.request.contextPath}/branchmain.jsp');
					location.href='${pageContext.request.contextPath}/branchmain.jsp';
					/*여기 어떻게 띄울지 물어보기!*/
				}else{
					alert("로그인실패");
				}
				
			}
		});
		return false;
	});
};

$(init);
</script>
</head>
<body>
로그인~<br>
<form>
지점코드<input type="text" name="branch_code" placeholder="대/소문자 구분">
비밀번호<input type="password" name="branch_pwd">
<button>로그인</button>
</form>
</body>
</html>