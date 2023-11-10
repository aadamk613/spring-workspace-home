<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 보자~</title>
</head>
<body>
	<h1>쿠키 보기!</h1>
	
	<%--
		EL을 이용한 쿠키접근
		${cookie.쿠키명}
	
	 --%>
	<div>
		쿠키 : ${ cookie.newJeans }
	</div>	
	
	<div>
		<!-- 쿠키 있나요?? -->
		뉴진스 쿠키 있나요?? : ${ cookie.newJeans != null } <br>
		뉴진스 쿠키 있나요?? : ${ not empty cookie.newJeans } <br> <!-- cookie.newJeans가 != null && cookie.newJeans != '' -->
	</div>
	
	<div>
		쿠키 값 : ${ cookie.newJeans.value }
	</div>
</body>
</html>