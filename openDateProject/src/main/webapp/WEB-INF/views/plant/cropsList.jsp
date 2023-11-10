<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작물의 날씨를 봅시당</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<h1>작물 키우기 날씨 보기</h1>
	<input type="text" placeholder="페이지 번호를 입력해주세요" id="pageNo" value="1" >
	<button onclick="cropsWt();">클릭</button>
	
	<table border="1 solid green">
		<thead>
			<tr>
				<th>작물명</th>
				<th>지역 이름</th>
				<th>월 최고기온</th>
				<th>월 최저기온</th>
				<th>월 평균상대습도</th>
				<th>월 최저상대습도</th>
				<th>월 강수량</th>
				<th>지역 평균풍속</th>
				<th>월 누적일조시간</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td>작물명</td> <!-- areaName -->
				<td>지역 이름</td> <!--  -->
				<td>월 최고기온</td> <!--  -->
				<td>월 최저기온</td> <!--  -->
				<td>월 평균상대습도</td> <!--  -->
				<td>월 최저상대습도</td> <!--  -->
				<td>월 강수량</td> <!--  -->
				<td>지역 평균풍속</td> <!--  -->
				<td>월 누적일조시간</td> <!--  -->
			</tr>
		</tbody>
	</table>
	
	<script>
	
	function cropsWt(){
		
		$.ajax({
			
			url: 'crops.wt',
			data: {pageNo : $('#pageNo').val()},
			success: data => {
				console.log(data);
				
				
		
			},
			error: () => {
				console.log('힝');
			}
		});
	};

	</script>
	
	
	
</body>
</html>