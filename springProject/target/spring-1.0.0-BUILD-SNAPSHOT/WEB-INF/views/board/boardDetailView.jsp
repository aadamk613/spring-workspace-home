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

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${ b.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ b.boardWriter }</td>
                    <th>작성일</th>
                    <td>${ b.createDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
	                <td colspan="3">
                    
                    <c:choose>
	                    <c:when test="${ empty b.originName }">
	                        	첨부파일 없음
	                    </c:when>
	                    <c:otherwise>
	                     	<a href="${ b.changeName }" download="${ b.originName }">${ b.originName }</a>
	                    </c:otherwise>
                    </c:choose>
                    
	             </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ b.boardContent }</p></td>
                </tr>
            </table>
            <br>

			<script>
				$(() => {
					
					$('#boardList > tbody > tr').click(() => {
						
					})
					
				})
			
			</script>
			<c:if test="${ loginUser.userId eq b.boardWriter }">
            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <a class="btn btn-primary" onclick="postFormSubmit(0);">수정하기</a>
                <a class="btn btn-danger" onclick="postFormSubmit(1);">삭제하기</a>
            </div>
            </c:if>
            <br><br>
            
            <form action="" method="post" id="postForm">
            	<input type="hidden" name="bno" value="${ b.boardNo }">
            	<input type="hidden" name="filePath" value="${ b.changeName }" >
            </form>
            
            <script>
            	function postFormSubmit(num){
            		if(num == 0){ // 수정하기 클릭 시
            			$('#postForm').attr('action', 'updateForm.bo').submit();
            		}
            		else{ // 삭제하기 클릭 시
            			$('#postForm').attr('action', 'delete.bo').submit();
            		}
            	}
            
            </script>

            <!-- 댓글 기능은 나중에 AJAX 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                	<!-- 로그인 전 -->
                	<c:choose>
	                	<c:when test="${ empty sessionScope.loginUser }">
	                    <tr>
	                        <th colspan="2">
	                            <textarea class="form-control" readonly cols="55" rows="2" style="resize:none; width:100%;">로그인 후 이용가능합니다.</textarea>
	                        </th>
	                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th>
	                    </tr>
	                    </c:when>
	                    <c:otherwise>
	                    <!-- 로그인 전 -->
	                    <tr>
	                        <th colspan="2">
	                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
	                        </th>
	                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="addRely();">등록하기</button></th>
	                    </tr>
						</c:otherwise>                    
                    </c:choose>
                    
                    <tr>
                        <td colspan="3">댓글(<span id="rcount">0</span>)</td>
                    </tr>
                </thead>
                <tbody>
                	<!-- 
                    <tr>
                        <th>user02</th>
                        <td>ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ꿀잼</td>
                        <td>2023-03-12</td>
                    </tr>
                    <tr>
                        <th>user01</th>
                        <td>재밌어요</td>
                        <td>2023-03-11</td>
                    </tr>
                    <tr>
                        <th>admin</th>
                        <td>댓글입니다!!</td>
                        <td>2023-03-10</td>
                    </tr>
                     -->
                     
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    
    <script>
    
    function addRely(){ // 댓글 작성용 함수
    	
    	if($('#content').val().trim() != ''){
    		
			$.ajax({
				
				url: 'rinsert.do',
				data: {
					refBoardNo : ${b.boardNo},
					replyContent : $('#content').val(),
					replyWriter : '${sessionScope.loginUser.userId}'
				},
				success: result => {
					console.log(result);
					
					if(result == 'success'){
						$('#content').val('');					
						selectReplyList();
					}
				},
				error: () => {
					console.log('댓글 작성에 실패했습니다');
				}
			});	
    	}
    	else{
    		alert('댓글 내용을 입력해주세요');
    	}
    }

	$(() => {
		selectReplyList();
	});
    
    function selectReplyList(){
    	$.ajax({
    		url: 'rlist.do',
    		data: {bno : ${b.boardNo}},
    		success: list => {
    			console.log(list);
    			
    			let value = '';
    			for(let i in list){
    				
    				value += '<tr>'
    				 	  + '<td>' + list[i].replyWriter + '</td>'
    				 	  + '<td>' + list[i].replyContent + '</td>'
    				 	  + '<td>' + list[i].createDate + '</td>'
    				 	  + '</tr>';
    				 	
    			}
    			$('#replyArea tbody').html(value);
    			$('#rcount').text(list.length);
    		},
    		error: () => {
    			console.log('댓글 불러오기 실패');
    		}
    	})
    }
    
    </script>
    
    <jsp:include page="../common/footer.jsp" />
    
</body>
</html>