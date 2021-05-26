<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/26/2021
  Time: 8:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fielset>
    <legend>Thông tin người dùng</legend>
    <table>
        <tr>
            <td>Tên người dùng:</td>
            <td>${requestScope["user"].getName()}</td>
        </tr>
        <tr>
            <td>Địa chỉ email:</td>
            <td>${requestScope["user"].getEmail()}</td>
        </tr>
        <tr>
            <td>Quốc tịch:</td>
            <td>${requestScope["user"].getCountry()}</td>
        </tr>
    </table>
</fielset>
</body>
</html>
