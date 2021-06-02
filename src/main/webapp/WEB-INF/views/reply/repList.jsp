<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border="1">
	<tr>
		<th>작성자</th>
		<th>댓글 내용</th>
		<th>작성일</th>
	</tr>

	<c:forEach var="reply" items="${repList}" varStatus="loop">
		<tr>
			<td>${reply.uid}</td>
			<td>${reply.repcontent}</td>
			<td>${reply.repdate}</td>
		</tr>
	</c:forEach>

</table>
