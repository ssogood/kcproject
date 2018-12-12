<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
th{
	background: rgb(230, 230, 230);
	padding: 10px 10px 10px;
	border: 1px solid rgb(204, 204, 204) !important;
}




</style>
<script>
function init(){
	$("#btUpdateOk").click(function(){
		var text_no = $("input[name=text_no]").val();
		var text_title = $("input[name=text_title]").val();
		var text_content = $("textarea[name=text_content]").val();
		var text_pwd = $("input[name=text_pwd]").val();
		console.log(text_title+":"+text_content+":"+text_pwd);
		$.ajax({
			url:"notice/noticemodify.do"
			,method:'post'
			,data:{"text_no":text_no
				  ,"text_title":text_title
				  ,"text_content":text_content
				  ,"text_pwd":text_pwd}
			,success:function(result){
				var jsonObj = JSON.parse(result);
				if(jsonObj.status=='ok'){
					alert("공지가 수정되었습니다");
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

</head>
<body>
<c:set var="notice" value="${requestScope.oldNotice}"></c:set>
<table>

  <tbody>
    <tr>
      <th scope="row">공지 번호</th>
      <td>
      <input type="text" name="text_no" value="${notice.text_no}" readonly>
      </td>
    </tr>
    <tr>
      <th scope="row">공지 제목</th>
      <td>
      <input type="text" name="text_title" value="${notice.text_title}">
      </td>
    </tr>
    
    <tr>
      <th scope="row">공지 내용</th>
      <td>
      <textarea name="text_content" rows="10" >${notice.text_content}</textarea>
      </td>
    </tr>
   <tr>
      <th scope="row">글 비밀번호</th>
      <td>
      <input type="password" name="text_pwd" value="${notice.text_pwd}">
      </td>
    </tr>
    
  </tbody>
</table>
<button id="btUpdateOk">
수정완료
</button>

</body>
</html>