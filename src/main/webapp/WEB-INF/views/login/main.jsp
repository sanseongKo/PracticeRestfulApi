<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NAVER LOGIN TEST</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<body>

	<h1>Login Form</h1>
	<hr>
	<br>
	<!-- Naver Login -->
	<center>
		<c:choose>
			<c:when test="${sessionId != null}">
				<h2>네이버 아이디 로그인 성공하셨습니다!!</h2>
				<h3>'${sessionId}' 님 환영합니다!</h3>
				<h3>
					<a href="logout">로그아웃</a>
				</h3>
			</c:when>
			<c:otherwise>

				<h2>로그인</h2>
				<input type="text" name="id" id="id" class="w3-input w3-border"
					placeholder="아이디" value="${id}">
				<br>
				<input type="password" id="pwd" name="pwd"
					class="w3-input w3-border" placeholder="비밀번호">
				<br>
				<input type="submit" value="로그인" onclick="#">
				<br>

				<br>
				<!-- 네이버 로그인 창으로 이동 -->
				<div id="naver_id_login" style="text-align: center">
					<a href="${url}"> <img width="232"
						src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" /></a>
				</div>
				<br>
				<a
					href="https://kauth.kakao.com/oauth/authorize?client_id=f4978b1a2bf3652715a55bc2538a5dc9&redirect_uri=http://localhost:8080/login/kakaoLogin&response_type=code">
					kakao 로그인</a>
			</c:otherwise>
		</c:choose>
	</center>

</body>
</html>

<script>
	console.log(${url});
</script>