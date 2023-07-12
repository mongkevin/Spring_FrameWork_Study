<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
</head>
<body>
	<br><br>
	<h1 align="center">실시간 대기 오염 정보</h1>
	
	<div align="center">
		지역:
		<select id="location">
			<option>서울</option>
			<option>부산</option>
			<option>대전</option>
		</select>
		
		<button id="btn1">해당 지역 대기 오염 정보1</button>
		<button id="btn2">해당 지역 대기 오염 정보2</button>
	</div>
	<br><br>
	<table border="1" id="result1" align="center">
		<thead>
			<tr>
				<th>측정소명</th>
				<th>측정일시</th>
				<th>통합대기환경수</th>
				<th>미세먼지농도</th>
				<th>아황산가농도</th>
				<th>일산화탄소농도</th>
				<th>이산화탄소농도</th>
				<th>오존농도</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<br><br>
	<script>
		$(function(){
			$("#btn1").click(function(){
				$("#result1 tbody").empty();
				$.ajax({
					url : "air.do",
					data : {
						location : $("#location").val()
					},
					success : function(result){
						var itemArr = result.response.body.items;
						var str = "";
						for(var i=0; i<itemArr.length;i++){
							str += "<tr>"+
									  "<td>"+itemArr[i].stationName+"</td>"+
									  "<td>"+itemArr[i].dataTime+"</td>"+
									  "<td>"+itemArr[i].khaiValue+"</td>"+
									  "<td>"+itemArr[i].pm10Value+"</td>"+
									  "<td>"+itemArr[i].so2Value+"</td>"+
									  "<td>"+itemArr[i].coValue+"</td>"+
									  "<td>"+itemArr[i].no2Value+"</td>"+
									  "<td>"+itemArr[i].o3Value+"</td>"+
								   "</tr>";
						}
						
						$("#result1 tbody").append(str); 
					},
					error : function(){
						console.log("통신 실패");
					}
				});
			});
			//-----------xml방식-------------
			$("#btn2").click(function(){
				$("#result1 tbody").empty();
				$.ajax({
					url : "air2.do",
					data : {
						location : $("#location").val()
					},
					success : function(result){
						var $result = $(result).find("item");
						var str = "";
						
						//배열 순차적 접근 each 메소드
						/* $result.each(function(){
							$(this).find("stationName").text();
						}); */
						for(var i=0; i<$result.length;i++){
							str += "<tr>"+
									  "<td>"+$(result).find("stationName").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("dataTime").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("khaiValue").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("pm10Value").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("so2Value").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("coValue").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("no2Value").eq(i).html()+"</td>"+
									  "<td>"+$(result).find("o3Value").eq(i).html()+"</td>"+
								   "</tr>";
						}
						$("#result1 tbody").append(str); 
					},
					error : function(){
						console.log("통신 실패");
					}
				});
			});
		});
	</script>
	
	<h1 align="center">영화찾기</h1>
	<div align="center">
		<input type="text" id="time">
		<button id="btn3">영화찾기</button>
	</div>
	<br><br>
	<table border="1" id="result2" align="center">
		<thead>
			<tr>
				<th>순위</th>
				<th>영화이름</th>
				<th>개봉일자</th>
				<th>관람객</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script>
		$(function(){
			$("#btn3").click(function(){
				$("#result2 tbody").empty();
				$.ajax({
					url : "movie.do",
					data : {
						time : $("#time").val()
					},
					success : function(result){
						var itemArr = result.boxOfficeResult.dailyBoxOfficeList;
						var str = "";
						for(var i=0; i<itemArr.length;i++){
							str += "<tr>"+
									  "<td>"+itemArr[i].rank+"</td>"+
									  "<td>"+itemArr[i].movieNm+"</td>"+
									  "<td>"+itemArr[i].openDt+"</td>"+
									  "<td>"+itemArr[i].salesInten+"</td>"+
								   "</tr>";
						}
						$("#result2 tbody").append(str);  
					},
					error : function(){
						console.log("통신 실패");
					}
				});
			});
		});
	</script>
</body>
</html>