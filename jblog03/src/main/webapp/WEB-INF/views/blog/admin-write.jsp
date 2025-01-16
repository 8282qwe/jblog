<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/blog/includes/header.jsp"/>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <c:import url="/WEB-INF/views/blog/includes/menu.jsp"/>
            <form:form action="${pageContext.request.contextPath}/${path}/admin/write" method="post"
                       modelAttribute="postRequestDto">
                <table class="admin-cat-write">
                    <tr>
                        <td class="t">제목</td>
                        <td>
                            <form:input path="title" size="60"/>
                            <select name="category">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}" ${category.id == postRequestDto.category ? "selected":""}>${category.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="t">내용</td>
                        <td><form:textarea path="content"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td class="s"><input type="submit" value="포스트하기"></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
    <c:import url="includes/footer.jsp"/>
</div>
</body>
</html>