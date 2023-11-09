<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부산 맛집</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<h1>공무원들이 인정한 부산의 맛집</h1>
	
	<br><br>
	
	<input placeholder="페이지 번호를 입력해주세요" id="pageNo" value="1" >
	<button onclick="taste();">맛집을 열려주세요</button>
	
	<table border="1" align="center">
	
		<thead>
			<tr>
				<th>가게명</th>
				<th>주소</th>
				<th>연락처</th>
				<th>운영시간</th>
				<th>이미지</th>
				<th>위치보기</th>
			</tr>
		</thead>
	
		<tbody>
		</tbody>
	</table>
	
	
	<script>
	
		function taste(){
			
			$.ajax({
				
				url: 'busan.taste',
				data: {pageNo : $('#pageNo').val()},
				success: data => {
					console.log(data.getFoodKr.item);
					
					const itemArr = data.getFoodKr.item;
					let value = "";
					
					for(let i in itemArr){
						
						const item = itemArr[i];
						console.log(item);
						//console.log(item.LAT);
						
						value += '<tr>'
							  +  '<td>' + item.MAIN_TITLE + '</td>'
							  +  '<td>' + item.ADDR1 + '</td>'
							  +  '<td>' + item.CNTCT_TEL + '</td>'
							  +  '<td>' + item.USAGE_DAY_WEEK_AND_TIME + '</td>'
							  +  '<td>' 
							  +  '<img src="' + item.MAIN_IMG_THUMB + '"/>'
							  + '</td>'
							  + '<td>'
							  + '<form action="busanmap">'
							  + '<input type="hidden" name="lat" value="' + item.LAT + '"/>'
							  + '<input type="hidden" name="lon" value="' + item.LNG + '"/>'
							  + '<input type="hidden" name="name" value="' + item.MAIN_TITLE + '"/>'
							  + "<button>어디있지??</button>"
							  + '</form>'
							  + '</td>'
							  + '</tr>'
					}
					
					$('tbody').html(value);
			
				},
				error: () => {
					console.log('ㅜㅜ');
				}
			});
		};
	
	</script>
</body>
</html>