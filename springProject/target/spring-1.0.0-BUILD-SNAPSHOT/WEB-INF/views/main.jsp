<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진짜 메인 페이지</title>
</head>
<body>

	<jsp:include page="common/header.jsp"/>
	
	<div style="height : 500px">
	
	<div class="innerOuter"> 
		<h4>게시글 TOP 5</h4>
		<br>
		<a href="list.bo" style="float:right; color:lightgray;">더보기>></a>
		<br><br>
		
		<table id="boardList" class="table table-hover" align="center">
			<thead>
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
					<td>첨부파일</td>
				</tr>
				
			</thead>
			<tbody>
				<!-- 조회수가 가장 높은 상위 5개의 게시글을 조회해서 뿌려줄 것! -->
			</tbody>
		
		</table>
		
	</div>
	
	
	</div>
	
	
	<script>
		$(function(){
			topBoardList();
			
			/*
			$('#boardList > tbody > tr').click(function(){
				
				location.href = 'detail.bo?bno' + $(this).children().eq(0).text();
			})
			동적으로 만들어진 요소에 이벤트 부여
			*/
			$(document).on('click', '#boardList > tbody > tr', function(){
				location.href = 'detail.bo?boardNo=' + $(this).children().eq(0).text();
			})
			
		})
		
		function topBoardList(){
			
			$.ajax({
				url : 'topList.do',
				success: function(result){
					console.log(result);
					
					let value = '';
					for(let i in result){
						value += '<tr>'
						 	  + '<td>' + result[i].boardNo + '</td>'
						 	 + '<td>' + result[i].boardTitle + '</td>'
						 	 + '<td>' + result[i].boardWriter + '</td>'
						 	 + '<td>' + result[i].count + '</td>'
						 	 + '<td>' + result[i].createDate + '</td>'
						 	 + '<td>'; 
						 	 
						 	 if(result[i].originName != null) {
						 		 value += '★';
						 	 }
						 			 
						 	 + '</td>'
						 	 + '</tr>';
					}
					
					$('#boardList>tbody').html(value);
				},
				error: function(){
					console.log('실패');
				}
			})
			
		}
	
	
	</script>
	<jsp:include page="common/footer.jsp"/>


</body>
</html>