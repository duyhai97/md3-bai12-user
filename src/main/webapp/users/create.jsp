<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/26/2021
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create use</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<h1>Thêm người dùng</h1>
<p><a href="/users"> Quay lại </a></p>
<c:if test='${requestScope["message"] != null}'>
    <span class="message">${requestScope["message"]}</span>
</c:if>

<form action="" method="post">
    <table>
        <tr>
            <td>Tên người dùng:</td>
            <td><input type="text" name="name" id="name"></td>
        </tr>
        <tr>
            <td>Địa chỉ email:</td>
            <td><input type="text" name="email" id="email"></td>
        </tr>
        <tr>
            <td>Quốc tịch:</td>
            <td><input type="text" name="country" id="country"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Thêm mới"></td>
        </tr>
    </table>
</form>

</body>
</html>
