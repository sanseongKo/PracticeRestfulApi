<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
<div>
	<a href="<c:url value="/onofflist?on_off=1" />">온라인</a> / 
	<a href="<c:url value="/onofflist?on_off=2" />">오프라인</a>
</div>
<br><a href="<c:url value="/write"/>">새글</a>
<br><br>
<button name="login" onClick="location.href='<c:url value="/login"/>'">로그인</button>
<button name="logout" onClick="location.href='<c:url value="/logout"/>'">로그아웃</button>
<button name="regForm" onClick="location.href='<c:url value="/register"/>'">회원가입</button>
<br><br>

<div>
	<c:forEach var="board" items="${list}" varStatus="loop">
      <div style='width: 20%; float: left'>
         <table border="1">
	         <c:choose>
	         	<c:when test="${board.cthumbnail ne null}">
	         		<tr>
	            		<td><img src="/board/imgRead/${board.cthumbnail}?fileName=${board.cthumbnail}" width=100 height=100></td>
	            	</tr>
	         	</c:when>
	         	<c:otherwise>
	         		<tr>
						<td><img src="/board/resources/images/basicPic.jpg" width=100 height=100></td>
					</tr>
	         	</c:otherwise>
	         </c:choose>
            <tr>
               <td>${board.cid}</td>
            </tr>
            <tr>
               <td><a href="<c:url value="/contentRead/${board.cid}" />">${board.title}
               </a></td>
            </tr>
            <tr>
               <td>${board.big_name}</td>
            </tr>
            <tr>
               <td>${board.small_name}</td>
            </tr>
            <tr>
               <td>${board.area}</td>
            </tr>
            
         </table>
      </div>
   </c:forEach>
</div>

   <a href="<c:url value="/write" />">새 글</a>
</body>

</html>