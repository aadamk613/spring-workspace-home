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