<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/26/2021
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List user</title>
</head>
<body>
<h1>List user</h1>
<p>
    <a href="/users?action=create">Thêm mới người dùng</a>
</p>

<table border="1px">
    <tr>
        <td>Tên người dùng</td>
        <td>Địa chỉ email</td>
        <td>Quốc tịch</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items='${requestScope["users"]}' var="u">
        <tr>
            <td><a href="/users?action=view&id=${u.getId()}">${u.getName()}</a></td>
            <td>${u.getEmail()}</td>
            <td>${u.getCountry()}</td>
            <td><a href="/users?action=edit&id=${u.getId()}">Chỉnh sửa</a></td>
            <td><a href="/users?action=delete&id=${u.getId()}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
