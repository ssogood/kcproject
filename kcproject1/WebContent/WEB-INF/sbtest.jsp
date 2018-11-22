<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/multiple-select.js"></script>
<link rel="stylesheet" href="css/multiple-select.css" />
<style>
	select {
		width: inherit;		/* auto는 option의 text값에 맞춰짐 너무 작음 */
	}

</style>
<script>
    $("select").multipleSelect({
    	//selectAll: false,			//맨첫문장 전체선택 없앰 : default=true 이므로 false하지 말아야 allChecked, allUnchecked 가능
    	
    	isOpen: true,				//처음부터 selectbox 펼쳐짐
         keepOpen: true 		//선택후 다른 곳 눌러도 열려있게 할 것인지(=계속 열려있게 할것인지)
    });
    
    $("#setSelectsBtn").click(function() {
    	/* default 값 설정할 때 사용 */
        $("select").multipleSelect("setSelects", [1, 3]);
    });
    
    
    $("#getSelectsBtn").click(function() {
    	/* 현재값 넘겨줄때 사용 */
        alert("Selected values: " + $("select").multipleSelect("getSelects"));			/* option value값 */
        															//method
        alert("Selected texts: " + $("select").multipleSelect("getSelects", "text"));		/* select의 option (보여지는) text 값 */
    });																//method		가져올타입
    
    $("#disabledBtn").click(function() {
	  //활용하면 좋을 예 => 선택하면 옆의 다른 select 막기
	  
	  ////////다시하기
	  $("select").multipleSelect("getSelects")
	      $opt.attr("disabled", true);
	   
	    $input.val("");
	    $select.append($opt).multipleSelect("refresh");
    });
    
</script>

</head>
<body>
	<button id="setSelectsBtn">SetSelects</button>
	<button id="getSelectsBtn">GetSelects</button>
    <button id="disabledBtn">Disabled</button>
    
  <select multiple="multiple">
        <option value="1">aaa</option>
        <option value="2">bbb</option>
        <option value="3">ccc</option>
        <option value="4">ddd</option>
        <option value="31">789</option>
   </select>
    </body>
</html>