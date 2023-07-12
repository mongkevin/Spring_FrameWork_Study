<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="common/menubar.jsp"/>
	<div class="content">
		<br><br>
		<div class="innerOuter">
			<h4>게시글 top5</h4>
			<br>
			<a href="list.bo" style="float:right">더보기&raquo;</a>
			
			<table id="boardList" class="table table-hover" align="center">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>첨부파일</th>
					</tr>
				</thead>
				<tbody>
					<!-- 현재 조회수가 가장 높은 상위 5개의 게시글 조회해서 뿌려주기(Ajax) -->
				</tbody>
			</table>
		</div>
	</div>
	<script>
		$(function(){
			boardtoplist()
			setInterval(boardtoplist,90000);
			
			//동적으로 만들어진 요소에 이벤트를 부여하려면 아래 방식으로 상위 요소 선택하여야한다.
			$(document).on("click","#boardList>tbody tr",function(){
				location.href="detail.bo?bno="+$(this).children().eq(0).text();
			});
		});
		function boardtoplist(){
			
			$.ajax({
				url : "toplist.bo",
				success : function(result){
					var str = "";
					for(var i in result){
						str+="<tr>"+
								"<th>"+result[i].boardNo+"</th>"+
								"<th>"+result[i].boardTitle+"</th>"+
								"<th>"+result[i].boardWriter+"</th>"+
								"<th>"+result[i].count+"</th>"+
								"<th>"+result[i].createDate+"</th>"+
							 "</tr>"
						if(result[i].originName!=null){
							str.append("<th>"+"★"+"</th>");
						}
					}
					$("tbody").html(str);
				},
				error : function(){
					console.log("통신 실패");
				}
			});
		};
	</script>
	<jsp:include page="common/footer.jsp"/>
</body>
</html>