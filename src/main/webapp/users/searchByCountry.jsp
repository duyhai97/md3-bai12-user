<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/27/2021
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            text-align: center;
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }


        th {
            background-color: #4CAF50;
            color: white;
        }

    </style>
</head>
<body>

<h3>
    <a href="/">Trở về trang chủ</a>
</h3>

<table>
    <tr>
        <th>Tên người dùng</th>
        <th>Địa chỉ email</th>
        <th>Quốc tịch</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items='${requestScope["userList2"]}' var="u">
        <tr>
            <td>${u.getName()}</td>
            <td>${u.getEmail()}</td>
            <td>${u.getCountry()}</td>
            <td><a href="/users?action=edit&id=${u.getId()}">Chỉnh sửa</a></td>
            <td><a href="/users?action=delete&id=${u.getId()}">Xóa</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
