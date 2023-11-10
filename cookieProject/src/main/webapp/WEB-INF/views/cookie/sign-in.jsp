<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 로그인</title>
</head>
<body>


	<%-- 
	이승철의 계획 : saveId라는 쿠키가 존재한다면 아이디를 저장한것으로 간주
	쿠키의 valu값을 불러와서 아이디 입력 Input요소의 value값으로 넣을 것 
	아이디 저장하기 체크박스에 체크--%>

	<form>
		<c:choose>
		<c:when test="${ not empty cookie.saveId }">
			아이디  : <input type="text" value="${ cookie.saveId.value }" /><br>
			<input type="checkbox" checked >아이디 저장<br>
		</c:when>
		<c:otherwise>
			아이디  : <input type="text" /><br>
			<input type="checkbox">아이디 저장<br>
		</c:otherwise>
		</c:choose>
	</form>
	
</body>
</html>