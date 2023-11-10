<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		 이승철의 계획
		if(쿠키가 없으면){
			광고창을 띄우는 코드
		}
	- saveId라는 쿠키가 있는지 검사!
	- 쿠키가 없을 경우 광고 띄우기!
	- window.open();
	--%>
	
	<c:if test="${ empty cookie.saveId }">
	<script>
		window.open("https://www.naver.com",  "나는 광고라고 해!" , "width=400 , height=200");
		
	</script>
	</c:if>

</body>
</html>