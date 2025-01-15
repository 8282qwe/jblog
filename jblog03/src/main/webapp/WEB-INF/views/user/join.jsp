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
	<script>
		$(function() {
			$("#btn-checkemail").click(() => {
				let id = $("#blog-id").val();
				if (id === "") {
					return;
				}

				$.ajax({
					url: "${pageContext.request.contextPath}/api/user/"+id,
					type: "GET",
					dataType: "json",
					complete: function (data) {
						if (data.status === 210) {
							alert("이미 존재하는 아이디 입니다.");
							$("#blog-id").val("").focus();
							return;
						}
						$("#img-checkemail").show();
						$("#btn-checkemail").hide();
					}
				});
			});

			$("#blog-id").keypress(function(){
				$("#img-checkemail").hide();
				$("#btn-checkemail").show();
			})
		});
	</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/main_header.jsp"/>
		<form:form class="join-form" id="join-form" method="post" action="" modelAttribute="userVo">
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/>
			<p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
				<form:errors path="name"/>
			</p>
			
			<label class="block-label" for="id">아이디</label>
			<form:input path="id"/>
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
			<form:errors path="id"/>
			</p>

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<p style="padding: 5px 0; margin: 10px 0; font-size: 12px; color: red">
				<form:errors path="password"/>
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
