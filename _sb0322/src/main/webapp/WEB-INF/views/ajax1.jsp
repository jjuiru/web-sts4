<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ajax</h1>
	<select id="selectBox">
		<option value="" selected disabled>선택</option>
		<option value="list2">list2</option>
		<option value="list" >list</option>
	</select>
	<div id="here"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
$(function(){
	//alert("나온다");
	$.ajax({
		url: '/', // 슬래시 요청을 넣어 겟 매핑으로 데이터를 가져와서 here 에 넣기
		method: 'GET',
		success: function(data){
			$('#here').html(data);
		}
	});
	
	$("#selectBox").on("change", funtion(){
		console.log(&(this).val());
		
		let sel = $(this).val();
		if (sel=='list') {
			$.ajax({
				url : '/',
				method : 'GET',
				success : function(data) {
					$('#here').html(data);
				}
			});
		} else if (sel == 'list2') {
			$.ajax({
				url : '/list2',
				method : 'GET',
				success : function(data) {
					$('#here').html(data);
				}
			});
		}
	});
	
});

</script>

</body>
</html>