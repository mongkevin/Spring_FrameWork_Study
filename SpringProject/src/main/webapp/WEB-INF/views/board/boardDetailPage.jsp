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

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <%-- <jsp:include page="" /> --%>
    <%@ include file="../common/menubar.jsp" %>

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="list.bo">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${b.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${b.boardWriter }</td>
                    <th>작성일</th>
                    <td>${b.createDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                    	<c:if test="${b.changeName!=null }">
                    		<img src="${b.changeName }" width="100%" height="100%">
                    	</c:if>
                        <a href="${b.changeName }" download>${b.originName }</a>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${b.boardContent }</p></td>
                </tr>
            </table>
            <br>

            <c:if test="${loginUser.userId eq b.boardWriter }">
            	<div align="center">
	                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
	                <button class="btn btn-primary" onclick="formSubmit(1)">수정하기</button>
	                <button class="btn btn-danger" onclick="formSubmit(2)">삭제하기</button>
            	</div>
            </c:if>
            <br><br>

			<%-- <form id="postForm" action="" method="post">
				<input type="hidden" name="bno" value="${b.boardNo }">
			</form> --%>
			
			<script>
				function formSubmit(num){
					//넘겨받은 숫자에 따라 action에 들어갈 값을 달리하여 전송하기
					/* if(num==1){
						$("#postForm").attr("action","updateForm.bo").submit();
					}else{
						$("#postForm").attr("action","delete.bo").submit();
					}
				} */
				//----------------------------------------------
				//form태그 직접 생성
					var formObj = $("<form>");
					
					var bno = $("<input>").prop("type","hidden").prop("name","bno").prop("value","${b.boardNo}");
					var filePath = $("<input>").prop("type","hidden").prop("name","filePath").prop("value","${b.changeName}");
					var obj = formObj.append(bno);
					
					if(num==1){
						obj.attr("action","updateForm.bo").attr("method","get");
					}else{
						obj.append(filePath);
						obj.attr("action","delete.bo").attr("method","post");
					}
					
					$("body").append(obj);
					
					obj.submit();
				}
			</script>
            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                    <tr>
                        <th colspan="2">
                        	<input type="hidden" name="replyBno" id="replyBno" value="${b.boardNo }">
                        	<input type="hidden" name="replyWriter" id="replyWriter" value="${loginUser.userId }">
                            <textarea class="form-control" name="replyContent" id="content" cols="55" rows="2" style="resize:none; width:100%;" readonly></textarea>
                        </th>
                        <th style="vertical-align:middle"><button class="btn btn-secondary" id="insertreply" disabled>등록하기</button></th>
                    </tr>
                    <tr>
                        <td colspan="3">댓글(<span id="rcount">${rList.size() }</span>)</td>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="r" items="${rList }">
	                    <tr>
	                        <th>${r.replyWriter }</th>
	                        <td>${r.replyContent }</td>
	                        <td>${r.createDate }</td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    <c:if test="${not empty loginUser }">
	    <script>
	    	$(function(){
	    		$("#replyArea #content").removeAttr("readonly");
	    		$("#content").keyup(function(){
	    			if($("#content").val().trim().length>0){
	    				$("#insertreply").attr("disabled",false);
	    			}else{
	    				$("#insertreply").attr("disabled",true);
	    			}
	    		});
	    		$("#insertreply").click(function(){
	    			$.ajax({
	    				url : "insertReply.bo",
	    				data : {
	    					replyContent : $("#replyArea #content").val(),
	    					refBno : $("#replyBno").val(),
	    					replyWriter : $("#replyWriter").val()
	    				},
	    				success : function(result){
	    					console.log(result);
	    					if(result=="success"){
	    						alert("댓글 작성 성공");
	    						location.reload(true);
	    					}else{
	    						alert("댓글 작성 실패");
	    					}
	    				},
	    				error : function(){
	    					console.log("통신 실패");
	    				}
	    			});
	    		});
	    	});
	    </script>
    </c:if>
    <jsp:include page="../common/footer.jsp" />
    
</body>
</html>