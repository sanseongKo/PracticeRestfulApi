<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 후기</title>
</head>
<body>
	

	<%@ include file="/WEB-INF/views/mypage/test.jsp" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
						<br>
						<h2>준비중입니다*^^*<h2>
				<div class="container">
					<div class="row">
						<table border="1">
	<div>
		<c:forEach var="reply" items="${replylist}" varStatus="loop">
			<div style='width: 20%; float: left'>
				<table border="1">
					<tr>
						<td>${reply.rid}</td>
					</tr>
					<tr>
						<td>${reply.uid}</td>
					</tr>
					<tr>
						<td>${reply.cid}</td>
					</tr>
					<tr>
						<td>${reply.repcontent}</td>
					</tr>
					<tr>
						<td>${reply.repdate}</td>
					</tr>

				
			</div>
		</c:forEach>
	</div>
	</table>
				</div>
	
			</div>
	
	
			</main>

	
	
	
		</div>
	</div>
	
</body>

</html>