<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog</title>
    <Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
    <script>
        $(function () {
            $("#add_button").click((e) => {
                e.preventDefault();
                let name = $("#category_name").val();
                let desc = $("#category_desc").val();
                if (name === "") {
                    alert("카테고리명이 비어있을 수 없습니다.")
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/api/blog/category/add",
                    type: "POST",
                    dataType: "json",
                    data: {id: "${path}", name: name, description: desc},
                    complete: function (data) {
                        if (data.status === 200) {
                            window.location.reload();
                        }
                    }
                });
            });

            $('.del_button').click(function () {
                let row = $(this).closest('tr');
                let index = row.data('index');
                // let categoryId = row.find('td:eq(1)').text(); // 카테고리 이름을 ID로 사용

                $.ajax({
                    url: '${pageContext.request.contextPath}/api/blog/category/delete/' + index,
                    method: 'GET',
                    complete: function (data) {
                        switch (data.status) {
                            case 200:
                                row.remove();
                                break;
                            default:
                                alert(data.responseText);
                                window.location.reload();
                                break;
                        }
                    },
                    error: function (xhr, status, error) {
                        console.error('Error deleting category:', error);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/blog/includes/header.jsp"/>
    <div id="wrapper">
        <div id="content" class="full-screen">
            <c:import url="/WEB-INF/views/blog/includes/menu.jsp"/>
            <table class="admin-cat">
                <tr>
                    <th>번호</th>
                    <th>카테고리명</th>
                    <th>포스트 수</th>
                    <th>설명</th>
                    <th>삭제</th>
                </tr>
                <c:forEach items="${categories}" var="category" varStatus="index">
                    <tr data-index="${category.id}">
                        <td>${fn:length(categories) - index.index}</td>
                        <td>${category.name}</td>
                        <td>${category.count}</td>
                        <td>${category.description}</td>
                        <td><img class="del_button" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <h4 class="n-c">새로운 카테고리 추가</h4>
            <table id="admin-cat-add">
                <tr>
                    <td class="t">카테고리명</td>
                    <td><input id="category_name" type="text" name="name"></td>
                </tr>
                <tr>
                    <td class="t">설명</td>
                    <td><input id="category_desc" type="text" name="description"></td>
                </tr>
                <tr>
                    <td class="s">&nbsp;</td>
                    <td><input id="add_button" type="submit" value="카테고리 추가"></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="footer">
        <p>
            <strong>Spring 이야기</strong> is powered by JBlog (c)2016
        </p>
    </div>
</div>
</body>
</html>