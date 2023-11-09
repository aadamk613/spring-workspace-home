<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<body>

	<h1>안녕 나는 파파고 번역기야!</h1>
	
	<!-- 1. source / 2. target / 3. text-->
	
<select id="source">
		<option value="ko">한국어</option>
		<option value="ja">일본어</option>
		<option value="en">영어</option>
	</select>
	→
	<select id="target">
		<option value="en">영어</option>
		<option value="ko">한국어</option>
		<option value="ja">일본어</option>
	</select>
	
	<br><br>
	
	<div id="area1" style="width:500px">
		<textarea style="resize:none; width:100%; height:200px;" id="translate"></textarea>
		
		<br><br>
		<button id="btn1" style="width:100%; height:40px">번역해줘!</button>
		
		<textarea style="resize:none; width:100%; height:200px;" placeholder="번역결과☆" readonly id="result"></textarea>	
	
	
	</div>

	<script>
		$(function(){
			
			$('#btn1').click(() => {
				
				$.ajax({
					url : 'papago.do',
					data : {
						text : $('#translate').val(),
						source : $('#source').val(),
						target : $('#target').val()
					},
					success : result => {
						console.log(result);
						$('#result').html(result.message.result.translatedText);
					},
					error : () => {
						console.log('오잉~');
						$('#result').html('파파고가 고장났어요 ㅠ.ㅠ');
					}
					
				});
				
			});
			
		});
		
	
	
	</script>



</body>
</html>