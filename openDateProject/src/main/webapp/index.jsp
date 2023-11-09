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

	<h1>실시간 대기오염 정보</h1>	
	
	지역 : 
	<select id="location">
		<option>경기</option>
		<option>부산</option>
		<option>강원</option>
		<option>충북</option>
		<option>충남</option>
		<option>경남</option>
		<option>경북</option>
	</select>
	
	<button id="btn1">해당 지역 대기오염정보</button>
	<br>
	<br>
	
	<table border="1px solid skyblue;">
		<thead>
			<tr>
				<th>측정소명</th>
				<th>측정 일시</th>
				<th>아황산가스 농도</th>
				<th>미세먼지 농도</th>
				<th>오존농도</th>
				<th>통합 대기환경 수치</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	
	</table>
	
	<script>
		$(function(){
			$('#btn1').click(() => {
				/*
				$.ajax({
					url: 'air.do',
					data: {location : $('#location').val()},
					success: function(data){
						
						console.log(data.response.body.items);
						
						const itemArr = data.response.body.items;
						
						let value = "";
						
						for(let i in itemArr){
							let item = itemArr[i];
							
							value += '<tr>'
								  + '<td>' + item.stationName + '</td>'
								  + '<td>' + item.dataTime + '</td>'
								  + '<td>' + item.so2Value + '</td>'
								  + '<td>' + item.pm10Value + '</td>'
								  + '<td>' + item.o3Value + '</td>'
								  + '<td>' + item.khaiValue + '</td>'
								  + '</tr>'
						}
						$('tbody').html(value);
						
					},
					error: function(){
						console.log('실패');
					}
					
				})
				*/
				
				/*
					익명함수를 화살표함수로 작성할 수 있음(가시성이 좋음)
					"function(){     }"를    "() => {    }" 이런식으로 작성 가능
					"function(a){     }"를   "a => {    }" 이런식으로 작성 가능
					"function(a, b){     }"를   "(a, b) => {    }" 이런식으로 작성 가능
					"function(a, b){return 'a'}"를   "(a, b) => 'a'" 이런식으로 작성 가능
				*/
				
				$.ajax({
					url: 'air.do',
					data : {location : $('#location').val()},
					success : data => {
						// jQuery에서의 find메소드 : 기준이되는 요소의 하위요소들 중 특정 요소를 찾을 때 사용(HTML, XML)
						// console.log(data.find('item'));
						//console.log($(data).find('item'));
						
						// XML형식의 응답데이터를 받았을 때
						// 1. 응답 데이터 안에 실제 데이터가 담겨있는 요소 선택
						const itemArr = $(data).find('item');
						
						// 2. 반복문을 통해 실제 데이터가 담긴 요소들에 접근해서 동적으로 요소 만들기!
						let value = '';
						itemArr.each((i, item) => { // 1번 매개변수(i)에는 인덱스, 2번 매개변수(item)
							console.log($(item).find('stationName').text());
						
							value += '<tr>'
								  + '<td>' + $(item).find('stationName').text() + '</td>'
								  + '<td>' + $(item).find('dataTime').text() + '</td>'
								  + '<td>' + $(item).find('so2Value').text() + '</td>'
								  + '<td>' + $(item).find('pm10Value').text() + '</td>'
								  + '<td>' + $(item).find('o3Value').text() + '</td>'
								  + '<td>' + $(item).find('khaiValue').text() + '</td>'
								  + '</tr>'
						});
						$('tbody').html(value);
					},
					error: () => {
						console.log('통신 실패');
					}
				})
			})
		})
	
	</script>
	
	
	
	

</body>
</html>