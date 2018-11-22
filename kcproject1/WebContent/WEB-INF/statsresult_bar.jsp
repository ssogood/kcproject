<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="statsList" value="${requestScope.statsList}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계 페이지</title>
<style>
	table, th, td { border: 1px solid; }
	.total { display: none;  }
	
	svg { width: 100%; height: 500px; border: 1px solid black; }
	.bar { fill : orange; }
	
	/* label 그리기 위해서 */
	.axis_x line {
		fill : none;
		stroke : black;
	}
	
	/* label 그리기 위해서 */
	.barName {
		font-size: 9pt;
		text-anchor: middle;
	}
	
</style>
<script src="${pageContext.request.contextPath}/js/myd3.js" charset="utf-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script> -->

<script>
	//d3test1119.jsp의 init부분 가져와서 테스트하기 (길어서 지워둠)
	function init(){
		var jsondataset = [];
		jsondataset = ${statsList};
		
		console.log(jsondataset);
		console.log("legth : "+jsondataset.length);
		
		
		//tr class=each 에 있는 값
		var trdata = $(".each");										//	console.log(trdata);
		
		
		
		var cntData = trdata.children(".count");						console.log(cntData);
		var pnameData = trdata.children(".prod_cate_name"); 	 console.log(pnameData);
		
		
		var dataSet = [];
		var labelName = [];
		
	/* 	for(var i =0; i<jsondataset.length; i++){
		 	var Dbrn = jsondataset[i].branch_code ;
			var Ddate = jsondataset[i].order_date ;
			var Dcate = jsondataset[i].prod_cate_name;
			var Dpname = jsondataset[i].prod_name;
			var Dsum = jsondataset[i].prod_sum;
			var Dcnt = jsondataset[i].count;
			
			//if(Dbrn==null & Ddate !=null & Dcate==null & Dpname==null)
			//	dataSet.push(Dsum);		 
			dataSet.push(jsondataset[i].count);
			
		} */
		
		
		for(var i=0; i<cntData.length; i++){
			var d = cntData[i].firstChild.nodeValue;
			
			dataSet.push(d);
			labelName.push(pnameData[i].firstChild.nodeValue);
			
		}
		
		console.log("dataset" + dataSet);
		console.log("labelName" + labelName);

		var svgHeight = 500;
		var barE ;
		
		barE =d3.select("#myGraph")
		.selectAll("rect")
		.data(dataSet)
		
		barE.enter()
		.append("rect")
		.attr("class", "bar")
		.attr("width", 20)
		.attr("height", function (d,i){
			return d * 35;
		})
		.attr("x", function(d, i ){
			return i *15;
		})
		.attr("y", function(d, i ){
			return svgHeight- (d*15);
		})
		
		
		/* 막대 값 */
		barE.enter()
		.append("text")
		.attr("class", "barNum")
		.attr("x", function (d, i){
			return i * 15;
		})
		.attr("y", svgHeight-5)
		.text(function (d,i){
			return d;
		})
		
		var offsetX = 30;
		var offsetY = 20;
		
		/* 가로 방향 선 표시 */
		d3.select("#myGraph")
			.append("rect")
			.attr("class", "axis_x")
			.attr("width", 320)
			.attr("height", 1)
			.attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY)+")")
			
		/* 막대의 레이블 표시 */
		barE.enter()
			.append("text")
			.attr("class", "barName")
			.attr("x", function (d, i){
				return i *15 + offsetX;
			})
			.attr("y", svgHeight-offsetY+25)
			.text(function (d,i){
				return labelName[i];
			})
		
		
		
		
	};
	$(init);
	
</script>
</head>
<body>


	<div>
	<svg id="myGraph"></svg>
	</div>

<div>
	

	<table>
		<tr>
			<th>branch_code</th>
			<th>order_date</th>
			<th>prod_cate_name</th>
			<th>prod_name</th>
			<th>prod_sum</th>
			<th>count</th>
		</tr>
		
	<c:forEach var="stats" items="${statsList}">
	
		<c:choose>
		<c:when test="${stats.branch_code==null 
							|| stats.order_date==null 
							|| stats.prod_cate_name ==null 
							|| stats.prod_name ==null}">
			<tr class="total">
				<td class="branch_code">${stats.branch_code}</td>
				<td class="order_date">${stats.order_date}</td>
				<td class="prod_cate_name">${stats.prod_cate_name}</td>
				<td class="prod_name">${stats.prod_name}</td>
				<td class="prod_sum">${stats.prod_sum}</td>
				<td class="count">${stats.count}</td>
			</tr>			
							
		</c:when>
		<c:otherwise>
			<tr class="each">
				<td class="branch_code">${stats.branch_code}</td>
				<td class="order_date">${stats.order_date}</td>
				<td class="prod_cate_name">${stats.prod_cate_name}</td>
				<td class="prod_name">${stats.prod_name}</td>
				<td class="prod_sum">${stats.prod_sum}</td>
				<td class="count">${stats.count}</td>
			</tr>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</table>
</div>
</body>
</html>