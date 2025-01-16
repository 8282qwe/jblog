<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<h1>Blog's Not Found</h1>
<p>
  요청하신 블로그를 찾을 수가 없습니다.
</p>
<a href="${pageContext.request.contextPath}/" style="text-decoration: none;">홈으로 돌아가기</a>
</body>
</html>