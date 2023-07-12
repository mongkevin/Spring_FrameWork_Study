<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
    <%-- <jsp:include page="../common/menubar.jsp" /> --%>
	<%@include file="../common/menubar.jsp" %>
    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post" id="insertForm">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="userId" required> <br>
					<div id="checkResult" style="font-size:0.8em; display:none"></div>
                    
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
                    <button type="submit" class="btn btn-primary">회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>
	<script>
		$(function(){
			//아이디를 입력하는 input 요소 객체를 추출해두기
			const $idInput = $("#insertForm input[name=userId]");
			
			$idInput.keyup(function(){
				
				//최소 5글자 이상 입력되었을때 ajax 요청해보기
				if($idInput.val().trim().length>=5){
					
					$.ajax({
						url : "idCheck.me",
						data : {
							checkId : $idInput.val()
						},
						success : function(result){
							if(result=='NNNNY'){
								$("#checkResult").show();
								$("#checkResult").css("color","blue").html("사용가능한 아이디입니다.");
								//전송버튼 비활성화
								$("#insertForm button[type=submit]").attr("disabled",false);
							}else{
								$("#checkResult").show();
								$("#checkResult").css("color","red").html("중복된 아이디입니다.")
								$("#insertForm button[type=submit]").attr("disabled",true);
							}
						},
						error : function(){
							console.log("통신 실패");
						}
					});
				}else{ //5글자 미만일때 -버튼 비활성화, 메세지 숨기기
					$("#checkResult").hide();
					$("#insertForm button[type-submit]").attr("disabled",true);
				}
			});
		});
	</script>
    <!-- 푸터바 -->
    <jsp:include page="../common/footer.jsp" />

</body>
</html>