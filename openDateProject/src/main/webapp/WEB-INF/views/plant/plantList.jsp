<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식물을 봅시당</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<button id="btn">식물 조회하기</button>
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>물주는 주기</th>
				<th>광도</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>

	<script>
		$(function(){
			$('#btn').click(() => {
				$.ajax({
					url: 'plant.do',
					//data : {location : $('#location').val()},
					success : data => {
						// jQuery에서의 find메소드 : 기준이되는 요소의 하위요소들 중 특정 요소를 찾을 때 사용(HTML, XML)
						 console.log(data.find('item'));
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
				/*
				$.ajax({
					url: 'plant.do',
					data: {location : $('#location').val()},
					success: function(data){
						
						console.log(data);
						//console.log(data.response.body.items);
						
						
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
				
				
			})
		})
	
	</script>
	
</body>

</html>