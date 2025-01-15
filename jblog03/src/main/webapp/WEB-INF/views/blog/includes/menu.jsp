<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<ul class="admin-menu">
    <li><a href="${pageContext.request.contextPath}/${sessionScope.get("user").id}/admin">기본설정</a></li>
    <li><a href="${pageContext.request.contextPath}/${sessionScope.get("user").id}/admin/category">카테고리</a></li>
    <li><a href="${pageContext.request.contextPath}/${sessionScope.get("user").id}/admin/write">글작성</a></li>
</ul>