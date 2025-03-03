<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
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
			<div id="content">
				<div class="blog-content">
					<h4>${post.title}</h4>
					<p>
						${fn:replace(post.contents, newline, "<br>")}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${boards}" var="board">
						<li><a href="${pageContext.request.contextPath}/${path}/${board.categoryId}/${board.id}">${board.title}</a> <span>${board.regDate}</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogTitleFactory.blogTitle[requestScope.path].profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categories}" var="category">
					<li><a href="${pageContext.request.contextPath}/${path}/${category.id}">${category.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="includes/footer.jsp"/>
	</div>
</body>
</html>