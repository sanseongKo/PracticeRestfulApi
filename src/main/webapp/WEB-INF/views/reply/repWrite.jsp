<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="reply" method="POST">
	<table border="1">
		<h3>댓글창</h3>
		<tr>
			<th><label>내 용</label></th>
			<td><textarea name="content" cols="40" rows="5">${reply.repcontent}</textarea></td>
		</tr>
		<tr>
			<th><label>작성자</label></th>
			<td><input type="text" name="uid" />${reply.uid}</td>
		</tr>

	</table>
<input type="submit" value="댓글등록">

</form>

