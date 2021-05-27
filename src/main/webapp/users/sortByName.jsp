<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/27/2021
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sắp xếp theo tên</title>
    <style>
        body{
            text-align: center;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
        }
        button{
            border-radius: 8px;
            background: aqua;
        }
    </style>
</head>
<body>
<h1>Sắp xếp theo tên</h1>
<h3>
    <a href="/users">Quay lại</a>
</h3>
<table>
    <tr>
        <th>Tên người dùng</th>
        <th>Địa chỉ email</th>
        <th>Quốc tịch</th>
        <th></th>
        <th></th>
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
