<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<ul class="menu">
    <c:if test="${sessionScope.get('user') == null}">
        <li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
        <li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
    </c:if>
    <c:if test="${sessionScope.get('user') != null}">
        <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
        <li><a href="${pageContext.request.contextPath}/${sessionScope.get("user").id}">내블로그</a></li>
    </c:if>
</ul>
