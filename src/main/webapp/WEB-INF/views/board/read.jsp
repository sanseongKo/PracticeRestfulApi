<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${contentVO.title}&nbsp내용</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<!-- 달력 flatpickr -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<!--  step1 - 라이브러리 추가  -->
<!--  제이쿼리  -->
<script type="text/javascript"
						src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>

<body>

		<div>
		
				<c:choose>

					<c:when test="${contentVO.on_off == 1}">
						<%@ include file="/WEB-INF/views/board/onPay.jsp" %>
				
					</c:when>

					<c:when test="${contentVO.on_off == 2}">
						
						<%@ include file="/WEB-INF/views/board/offPay.jsp" %>
				
					</c:when>



					<c:otherwise>
						잘못된 정보입니다. 
					</c:otherwise>
					
				</c:choose>
	

		</div>
		
		<h3>클래스 내용</h3>
		
		<div>
			<table  border="1">
				<tr>
					<th>제목</th>
					<td>${contentVO.title}</td>
				</tr>

				<c:if test="${contentVO.file_name ne null}">
					<tr>
						<th>첨부파일</th>
						<td><a href="/board/down/${contentVO.file_name}?fileName=${contentVO.file_name}">${contentVO.file_name}</a></td>
					</tr>
				</c:if>

				<tr>
					<th>내용</th>
					<td><textarea name="content" cols="40" rows="10">${contentVO.content}</textarea></td>
				</tr>

				<tr>
					<th>판매자</th>
					<td>${contentVO.uid}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${contentVO.condate}</td>
				</tr>
				
			</table>
		</div>
		<div>
			<img src="/board/imgRead/${contentVO.pic_content}?fileName=${contentVO.pic_content}" style="width:300px; height:100px"/>
			<img src="${contentVO.picFile}" style="width:300px; height:100px"/>
		</div>
		<div>
			<c:choose>
					<c:when test="${contentVO.on_off == 1}">
						<%@ include file="/WEB-INF/views/board/onRead.jsp" %>
					</c:when>

					<c:when test="${contentVO.on_off == 2}">
						<%@ include file="/WEB-INF/views/board/offRead.jsp" %>
					</c:when>

					<c:otherwise>
						<tr>
							<th>무슨 클래스게~?</th>
						</tr>
					</c:otherwise>
				</c:choose>
		</div>

	<div>
		<c:choose>
			<c:when test="${memberVO.verification == 3}">
				<!-- 관리자일 경우	:	글 수정/삭제 버튼 + 목록 버튼 -->
				<a href="<c:url value="/edit/${contentVO.cid}"/>">수정</a> 
				<a href="<c:url value="/delete/${contentVO.cid}"/>">삭제</a> 
				<a href="<c:url value="/"/>">목록</a>
			</c:when>
			<c:otherwise>
				<!-- 판매자 및 구매자일 경우		:	 목록 버튼만 -->
				<button onClick="location.href='<c:url value="/edit/${contentVO.cid}"/>'">수정</button>
				<button onClick="location.href='<c:url value="/delete/${contentVO.cid}"/>'">삭제</button>
				<button onClick="history.go(-1)">이전 목록</button>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div>
		<form:form commandName="replyVO" method="POST">
			<%@ include file="/WEB-INF/views/reply/repWrite.jsp" %>
		</form:form>
	</div>
	
	<div>
		<%@ include file="/WEB-INF/views/reply/repList.jsp" %>
	</div>





</body>
</html>