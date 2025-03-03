<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="header">
  <h1><a href="${pageContext.request.contextPath}/${path}" style="text-decoration: none; color: white">${blogTitleFactory.blogTitle[requestScope.path].title}</a></h1>
  <ul>
    <c:if test="${sessionScope.get('user') == null}">
      <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
    </c:if>
    <c:if test="${sessionScope.get('user') != null}">
      <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
      <c:if test="${admin == true}">
        <li><a href="${pageContext.request.contextPath}/${sessionScope.get("user").id}/admin">블로그 관리</a></li>
      </c:if>
    </c:if>
  </ul>
</div>
