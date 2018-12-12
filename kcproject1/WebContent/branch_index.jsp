<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장용 메인 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" 
crossorigin="anonymous">	<!-- layout css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"><!-- 메뉴앞 icons -->
<!-- <link rel="stylesheet" href="css/main.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <script src="https://d37gvrvc0wt4s1.cloudfront.net/js/v1.4/rollbar.min.js" async=""></script>
<script type="text/javascript" async="" src="https://www.google-analytics.com/analytics.js"></script>
<script type="text/javascript" async="" src="https://cdn.amplitude.com/libs/amplitude-4.4.0-min.gz.js"></script>
<script type="text/javascript" async="" src="https://cdn.segment.com/analytics.js/v1/oCfx7i0KhuPsJiuMLkgvLkTWay1Te5NP/analytics.min.js"></script>
<script src="https://js.braintreegateway.com/js/braintree-2.21.0.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.5.3/less.min.js"></script>
<script src="/js/bundle.js"></script> -->
<link rel="stylesheet" href="css/main_layout.css">
<link rel="stylesheet" href="css/left_menu.css">

<script>
function init(){
	
	if("${sessionScope.branchLoginInfo}"==""){
		$('a').click(function(){
			alert("로그인하세요");
			$("button").each(function(index,element){
				if($(element).attr("class").indexOf("login")>-1){
					$(element).trigger("click");
					return false;
				}
			});
		});

	}
	
	
	$('#cssmenu > ul > li > a').click(function() {
		
		
		  $('#cssmenu li').removeClass('active');
		  $(this).closest('li').addClass('active');	
		  var checkElement = $(this).next();
		  
		  
		  if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
			  /* 펼쳐진 메뉴(ul) 또 누르면 비활성화 */
		    $(this).closest('li').removeClass('active');	/* 현재 펼쳐진 메뉴 active 해제 */
		    checkElement.slideUp('normal');
		    
		    console.log(" 첫번째 if : "+$(this));
		    
		  }
		  
		  if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
			  /* 이미 펼쳐진 메뉴가 있는데 다른 메뉴(ul) 누른 경우
			   또는 아무 메뉴도 펼쳐져 있지 않은 경우 */
		    $('#cssmenu ul ul:visible').slideUp('normal');		/* 펼쳐진 메뉴 닫고 */
		    checkElement.slideDown('normal');				/* 지금 누른 메뉴 펼침 */
		    
		    console.log(" 두번째 if : "+$(this));
		    
		  }
		  
		  if($(this).closest('li').find('ul').children().length == 0) {
			  
			  console.log(" 세번째 if : "+$(this));
		    return true;
		  } else {			
			  console.log(" else : "+$(this));
		    return false;	
		  }		
		  
		 
			
		});
	
	
	
	$(".has-sub li a").click(function(){
		//sub 메뉴들만 link 타도록
		 // li가 눌린 경우
		  var url = $(this).attr("href");			//this.href
			
			console.log(url);
			
			$.ajax({
				url : url
				,success : function(result){ //result:응답결과
					$("#maincol").html(result);
				}
			});
			
		return false; //기본이벤트 막기, 이벤트전달 중지
	});
	
	$(".has-not-sub a").click(function(){
		//sub 메뉴들만 link 타도록
		 // li가 눌린 경우
		  var url = $(this).attr("href");			//this.href
		  
			console.log(url);
			
			$.ajax({
				url : url
				,success : function(result){ //result:응답결과
					$("#maincol").html(result);
				}
			});
			
		return false; //기본이벤트 막기, 이벤트전달 중지
	}); 
	
	$(".logout").click(function(){
		$.ajax({
			url : "branch/logout.do"
			,success : function(result){ //result:응답결과
				alert("로그아웃되었습니다");
				location = "${pageContext.request.contextPath}/branchlogin.jsp"
			}
		});
		return false;
	});
	
	$(".login").click(function(){
		console.log("login클릭");
		location.href="${pageContext.request.contextPath}/branchlogin.jsp";
		return false;
	});
	
}

$(init);

</script>
</head>
<body>
<div id="pagewidth">
	<header id="header">
		<h2>Head</h2> 
		
	 </header>
	 
	<div id="wrapper" class="clearfix">
		<section id="maincol">
			<h1>Main Content Column</h1>
			<h2>링크 누르면 jsp 표시 될 부분</h2>
		 </section>
	 
		<div id="leftcol">
		
		<c:choose>
		  <c:when test="${!empty sessionScope.branchLoginInfo}">
		    <button class="logout">로그아웃</button>
		  </c:when>
		  <c:otherwise>
		    <button class="login">로그인</button>
		  </c:otherwise>
		</c:choose>

			 <h2>Left Column</h2>
	
	
	<div class="row preview-html" ng-show="screen == 'preview'" ng-hide="loading">
		        <div class="col-md-12">
			        <div id="cssmenu">
					  <ul>
					     <li class=""><a href="${pageContext.request.contextPath}/branch_index.jsp"><span><i class="fa fa-fw fa-home"></i> Home</span></a></li>
					     <li class="has-sub"><a><!-- <a href="#"> --><span><i class="fa fa-fw fa-bar-chart-o"></i> 매출현황</span></a>
					        <ul >
					           <li><a href="${pageContext.request.contextPath}/stats/all.do"><span>통계1</span></a></li>
					           <li><a href="#"><span>영수증</span></a></li>
					           <li><a href="${pageContext.request.contextPath}/stats/sbtest.do"><span>SELECTBOX TEST</span></a></li>
					        </ul>
					     </li>
					     <li class="has-sub"><a href="#"><span><i class="fa fa-fw fa-cube"></i> 재고</span></a>
					        <ul>
					           <li><a href="#"><span>재고현황</span></a></li>
					           <li><a href="#"><span>사용등록</span></a></li>
					        </ul>
					     </li>
					     <li class="has-sub"><a href="#"><span><i class="fa fa-fw fa-cart-arrow-down"></i> 발주</span></a>
					        <ul>
					        	<li><a id="branch_baljuhistory" href="${pageContext.request.contextPath}/balju/baljulist.do"><span>발주내역</span></a></li>
					            <li><a href="${pageContext.request.contextPath}/balju/baljuingrlist.do"><span>발주요청</span></a></li>
					        	<li><a href="${pageContext.request.contextPath}/S0001baljureq.jsp"><span>발주요청2</span></a></li>
					        </ul>
					     </li>
					     <li class="has-sub"><a href="#"><span><i class="fa fa-fw fa-share-square-o"></i> 반품</span></a>
					        <ul>
					           <li><a href="${pageContext.request.contextPath}/return/returnlistbranch.do"><span>반품내역</span></a></li>
					           <li><a href="${pageContext.request.contextPath}/return/returnadd.do"><span>반품요청</span></a></li>
					        </ul>
					     </li>
					     <li class="has-not-sub"><a href="${pageContext.request.contextPath}/notice/noticereadonly.do"><span><i class="fa fa-fw fa-bullhorn"></i> 공지사항</span></a></li>
					     <li class="has-not-sub"><a href="#"><span><i class="fa fa-fw fa-cog"></i> Settings</span></a></li>
					     <li class="has-not-sub"><a href="#"><span><i class="fa fa-fw fa-phone"></i> Contact</span></a></li>
					</ul>
			</div>
		</div>
      </div>

					 
			 
			
			 
	     </div>
	</div><!-- <div id="wrapper" class="clearfix"> -->
	
	<footer id="footer">
		<h2>Footer</h2>
		<p></p>
	</footer>

</div><!-- div id= pagewidth  -->
</body>
</html>