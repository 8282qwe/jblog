<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/main_header.jsp"/>
		<form:form class="login-form" method="post" action="${pageContext.request.contextPath}/user/auth">
      		<label>아이디</label> <input type="text" name="id" value="${id}"/>
      		<label>패스워드</label> <input type="text" name="password">
			<c:if test="${'fail'.equals(result)}">
				<p style="color: red">로그인에 실패하셨습니다.</p>
			</c:if>
      		<input type="submit" value="로그인">
		</form:form>
	</div>
</body>
</html>
