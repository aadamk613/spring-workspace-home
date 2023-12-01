<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <style>
        .content {
            background-color:rgb(247, 245, 245);
            width:80%; 
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }
    </style>
</head>
<body>
    
    <!-- 메뉴바 -->
    <jsp:include page="../common/header.jsp"/>

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post" id="enroll-form">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="userId" required> <br>
                    <div id="checkResult" style="color">
                    
                    </div>
                    
                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="userPwd" required> <br>

                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" placeholder="Please Enter Age" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" placeholder="Please Enter Phone (-없이)" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" placeholder="Please Enter Address" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender" checked>
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="disabled btn btn-primary">회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>
        
        <script>
        	$(function(){
        		const $idInput = $('.form-group #userId');
        		const $checkResult = $('#checkResult');
        		const $enrollFormSubmit = $('#enroll-form:submit');
        		
        		
        		$idInput.keyup(() => {
        			//console.log($idInput.val());
					
        			// 최소 다섯 글자 이상으로 입력했을 때만 AJAX요청을 보내서 중복체크
        			if($idInput.val().length >= 5){
        				$.ajax({
        					
        					url : 'idCheck.me',
        					data : {checkId : $idInput.val()},
        					success : result => {
        						console.log(result);
        						if(result.substr(4) == 'N'){
        							$checkResult.show().css('color', 'skyblue').text('중복된 아이디가 존재합니다.');
        							$enrollFormSubmit.removeAttr('disabled', true);
        						}
        						else{ // 사용가능
        							$checkResult.show().css('color', 'skyblue').text('사용가능합니다');
        							$enrollFormSubmit.removeAttr('disabled');
        						}
        					},
        					error : () => {
        						console.log('아이디 중복체크 통신 실패');
        					}
        				});
        			}
        			
        			else {
        				$checkResult.hide();
        				$enrollFormSubmit.attr('disabled');
        			}
        			
        		})
        	})
        	
        </script>

    </div>

    <!-- 푸터바 -->
    <jsp:include page="../common/footer.jsp"/>

</body>
</html>