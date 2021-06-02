<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="spring" uri= "http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매&관리자 로그인</title>
</head>
<body>
<%@ include file="/WEB-INF/views/vendor/menu.jsp" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
판매자 혹은 관리자인 경우 로그인을 해주세요. 
	<form:form commandName="loginCommand">
	<form:errors />
	<p>
		<label>
		Email :
		<form:input path="email" />
		<form:errors path="email" />
		</label>
	</p>
	<p>
		<label>
			비밀번호 : 
			<form:password path="password" />
			<form:errors path="password" />
			
		
		</label>
	</p>
	
	<input type="submit" value="로그인">
	</form:form>

</main>


	</div>
	</div>
	

</body>
</html>