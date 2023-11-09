<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<h1>도망쳐~~~~ 어디로??</h1>
	<br><br>
	<h3><label>페이지 번호</label></h3>
	<input type="number" value="1" class="form-control" id="pageNo">
	<br><br>
	<button id="btn" class="btn btn-danger" type="button">탈출버튼</button>
	
	<div id="result" class="container text-center">
		흐흐@
	
	</div>
	
	<script>
		$(() => {
			
			$('#btn').click(()=>{
				$.ajax({
					url: 'run.do',
					data: {pageNo : $('#pageNo').val()},
					success: data => {
						console.log(data.TsunamiShelter[1].row);
						
						const shelters = data.TsunamiShelter[1].row;
						
						let value = '';
						for(let i in shelters){
							shelter = shelters[i];
							value += '<div class="row">' 
								  + '<div class="col-5">'
								  + shelter.address
							 	  + '</div>'
								  + '<div class="col-2">'
								  + shelter.shel_nm
								  + '</div>'
								  + '<div class="col-2">'
								  + shelter.shel_div_type
								  + '</div>'
								  + '<div class="col-3">'
								  + '<button class="btn btn-sm btn-warning" '
								  + 'onclick="hachiware(\''
								  + shelter.shel_nm + '\', ' + shelter.lat + ',' + shelter.lon
								  + ');">여기야</button>'
								  + '</div>'
							      + '</div> <br>';
						}
						$('#result').html(value);
						
						
					},
					error: ()=>{
						console.log('실패!!');
					}
					
				})
			})
			
		}) 
		
		function hachiware(name, lat, lon){
			
			location.href= 'map/' + lat + "/" + lon + "/" + name;
			
		};
		

	
	
	</script>
	
	
</body>
</html>