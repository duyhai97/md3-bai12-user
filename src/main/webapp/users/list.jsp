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
    <link rel="stylesheet" href="style.css">
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

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
        }
        button{
            border-radius: 8px;
            background: aqua;
        }
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<h1>List user</h1>


<%--<form action="/users?action=search" method="post">--%>
<%--    <input type="text" placeholder="Tìm kiếm" name="aaa">--%>
<%--    <select name="select" id="select">--%>
<%--        <option value="name">Name</option>--%>
<%--        <option value="email">Email</option>--%>
<%--        <option value="country">Country</option>--%>
<%--    </select>--%>
<%--    <button>Nhập</button>--%>
<%--</form>--%>

<h2><a href="/">Trở về tang chủ</a></h2>

<form action="/users?action=sortByName" method="post">
    <button>Sắp xếp theo tên</button>
</form>
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
