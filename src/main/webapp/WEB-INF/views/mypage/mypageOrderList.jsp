<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<%@ include file="/WEB-INF/views/mypage/test.jsp" %>

<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div class="container">
					<div class="row">
						<table border="1">

	<tr>
						<th>NO</th>
						<th>구매상품</th>
						<th>개수</th>
						<th>결제금액</th>
						<th>예약일자</th>
						<th>구매일자</th>
						<th>결제상태</th>
					</tr>
<div>
	<% int count = 1; %>
		<c:forEach var="order" items="${orderlist}" varStatus="loop">
	<div>
				
				
					<tr>
						<td><%=count++ %></td>
						
						<td>${order.title}</td>
						
						<td>${order.quantity}</td>
					
						<td>${order.amount}</td>
						
						<td>${order.resday}</td>
						
						<td>${order.paydate}</td>
					
						<td><a href="/board/cancelResult?oid=${order.oid}">환불신청</a></td>
						
					</tr>

		</div>
	</c:forEach>
			</table>
	
</div>
	
	
			</main>
			
			
			
		</div>
	</div>
	
	

</body>

</html>