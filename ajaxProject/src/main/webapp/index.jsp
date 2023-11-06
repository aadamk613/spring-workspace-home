<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

	<h1>Spring에서의 AJAX사용법</h1>
	
	<h3>1. 요청 시 값 전달, 응답 결과 받아보기</h3>
	
	이름 : <input type="text" id="name"> <br>
	나이 : <input type="number" id="age"> <br>
	
	<div id="result"></div>
	
	<button onclick="test1();">전송</button>
	
	<script>
		function test1(){
			
			$.ajax({
				
				url : 'ajax.do', // 필수 정의 속성(url매핑값)
				type : 'get',
				data : { // 요청 시 전달값
					name : $('#name').val(), //  value속성값에 접근했을 경우!! 무조건 String타입!!
					age : $('#age').val() // data 받을 때 int로 받고, 아무것도 쓰지 않고 보내면 numberFormatException발생 할 수 있으니 조심!
				},
				success : function(result){ // 해당 통신에 성공했을 때
					console.log(result);
					
					// 응답데이터가 배열의 형태! => 인덱스로 접근가능[인덱스]
					//const value = '이름 : ' + result[0] + "<br>나이 : " + result[1];
					
					// ** 응답데이터가 객체의 형태! => 속성에 접근가능.속성명 -> 숫자보다 키값이 알아보기 편하기 때문에 이 방법이 더 좋음
					const value = '이름 : ' + result.name + "<br>나이 : " + result.age; 
					
					$('#result').html(value);
				},
				error : function(){ // 해당 통신에 실패했을 때
					
					console.log('ajax통신 실패')
				}
				
			})
			
		}
	</script>
	
	<h3>2. 조회요청 후 조회된 한 회원 객체를 응답받아서 출력해보자</h3>

	조회할 회원번호 : <input type="number" id="userNo" />
	<button id="btn2">조회</button> 
	<div id="result2"></div>
	
	<script>
		$(function(){
			$('#btn2').click(function(){
			
				var msg = '하하';
				
				$.ajax({
					url : 'ajax2.do',
					data : {num : $('#userNo').val()},
					// async : false,
					success : function(result){
						console.log(result);
						const value = '<ul>'
									+ '<li>' + result.userName + '</li>'
									+ '<li>' + result.userId + '</li>'
									+ '<li>' + result.phoneNumber + '</li>'
									+ '</ul>';
						$('#result').html(value);
						console.log(result.userId);
						msg = '히히';
						
					},
					error : function(){
						msg = '호호';						
					}
				});
				
				// 위의 수행과는 상관없이 그냥 '하하'가 호출  ->async : false속성 추가하면 성공 시 '히히', 실패시 '호호'가 뜸
				//alert(msg);
			});
			
		});
	
	</script>
	
	<h3>3. 조회처리 후 조회된 회워 리스트를 응답받아서 출력</h3>
	
	
	<button onclick="test3();">회원 목록 조회</button>
	<br><br>
	
	<table>
		<thead>
			<tr>
				<th>이름</th>
				<th>아이디</th>
				<th>전화번호</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	
	</table>
	
	<script>
		function test3(){
			$.ajax({
			
				url: 'ajax3.do',
				success: function(result){
					
					console.log(result);
					
					let value='';
					for(let i in result){
						value += '<tr>'
							  + '<td>' + result[i].userName + '</td>'
							  + '<td>' + result[i].userId + '</td>'
							  + '<td>' + result[i].phoneNumber + '</td>'
							  + '</tr>'
 					}
					$('tbody').html(value);
						
					
				},
				error: function(){
					console.log('통신 실패');
				}
				
			})
			
			
			
			
		}
	
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>