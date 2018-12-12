<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function init(){
	$("#btWriteOk").click(function(){
		var text_title = $("input[name=text_title]").val();
		var text_content = $("textarea[name=text_content]").val();
		var text_pwd = $("input[name=text_pwd]").val();
		
		$.ajax({
			url:"notice/noticewrite.do"
			,method:'post'
			,data:{"text_title":text_title
				  ,"text_content":text_content
				  ,"text_pwd":text_pwd}
			,success:function(result){
				var jsonObj = JSON.parse(result);
				if(jsonObj.status=='ok'){
					alert("공지가 등록되었습니다");
					$(".has-not-sub a").each(function(index,element){
						
						if($(element).attr("href").indexOf("${pageContext.request.contextPath}/notice/noticeall.do")>-1){
							$(element).trigger("click");
							return false;
						}
					});
					
				}else if(jsonObj.status=='id_null'){
					alert("로그인하세요");
				}else{
					alert("빈칸 채우기");
				}
			}
		});
		
		
		return false;
	});
	
};

$(init);
</script>

<style>
th{
	background: rgb(230, 230, 230);
	padding: 10px 10px 10px;
	border: 1px solid rgb(204, 204, 204) !important;
}

input[type=text]{
	/* background: #f2f2f2; */
	width:300px;
	height: 41px;
	border:1px solid #f2f2f2 !important;
	
	/* border-top: 1px solid #f2f2f2 !important;
	border-right: 1px solid #f2f2f2 !important; */
}

textarea {
	/* background: #f2f2f2; */
	width:300px;
	border:1px solid #f2f2f2 !important;
}

input[type=password]{

	width:300px;
	height: 41px;
	border:1px solid #f2f2f2 !important;
	
	
}

</style>
</head>
<body>
<table>

  <tbody>
    <tr>
      <th scope="row">공지 제목</th>
      <td>
      <input type="text" name="text_title">
      </td>
    </tr>
    
    <tr>
      <th scope="row">공지 내용</th>
      <td>
      <textarea name="text_content" rows="10"></textarea>
      </td>
    </tr>
   <tr>
      <th scope="row">글 비밀번호</th>
      <td>
      <input type="password" name="text_pwd">
      </td>
    </tr>
    
  </tbody>
</table>
<button id="btWriteOk">
공지 등록
</button>

</body>
</html>