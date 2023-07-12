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
	<h1>Spring에서의 AJAX 사용법</h1>
	
	<h3>1.오청시 전달값,응답 결과 받아보기</h3>
	이름: <input type="text" id="name"><br>
	나이: <input type="number" id="age"><br>
	<button onclick="test1();">전송</button>
	
	<div id="result1"></div>
	
	<script>
		function test1(){
			
			$.ajax({
				url : "ajax1.do",
				data : {
					name : $("#name").val(),
					age : $("#age").val()
				},
				success : function(result){
					/* console.log(result); */
					$("#result1").html(result);
				},
				error : function(){
					console.log("통신오류");
				}
			});
		};
	</script>
	
	<h3>2.조회요청 후 조회된 회원 객체를 받아 출력해보기</h3>
	조회할 회원번호: <input type="number" id="userNo">
	<button id="btn">조회</button>
	
	<div id="result2"></div>
	
	<script>
		$(function(){
			$("#btn").click(function(){
				$.ajax({
					url:"ajax2.do",
					data:{userNo:$("#userNo").val()},
					success: function(result){
						var resultStr = "<ul>"+
											"<li>이름: "+result.userName+"</li>"+
											"<li>아이디: "+result.userId+"</li>"+
											"<li>나이: "+result.age+"</li>"+
										"</ul>";
						$("#result2").html(resultStr);
					},
					error:function(){
						console.log("통신 실패");
					}
				})
			});
		});
	</script>
	
	<h3>3.조회요청 후 조회도니 리스트를 응답 받아 출력하기</h3>
	<button onclick="test3();">버튼</button>
	
	<div id="result3"></div>
	
	<script>
		function test3(){
			$.ajax({
				url:"ajax3.do",
				success:function(result){
					var str="";
					for(var i in result){
						str+="<ul>"+
								"<li>이름: "+result[i].userName+"</li>"+
								"<li>아이디: "+result[i].userId+"</li>"+
								"<li>나이: "+result[i].age+"</li>"+
							 "</ul>";
					}
					$("#result3").html(str);
				},
				error:function(){
					console.log("통신 실패");
				}
			});
		}
	</script>
</body>
</html>