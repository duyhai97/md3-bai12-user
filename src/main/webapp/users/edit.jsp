<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/26/2021
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .message{
            color: darkmagenta;
        }
    </style>
</head>
<body>
<h1>Sửa thông tin người dùng</h1>
<h3><a href="/users">Quay lại</a></h3>
<c:if test='${requestScope["message"] != null}'>
    <span class="message">${requestScope["message"]}</span>
</c:if>

<form  method="post">
    <table>
        <tr>
            <td>Tên người dùng:</td>
            <td><input type="text" name="name" id="name" value="${requestScope["user"].getName()}"></td>
        </tr>
        <tr>
            <td>Địa chỉ email:</td>
            <td><input type="text" name="email" id="email" value="${requestScope["user"].getEmail()}"></td>
        </tr>
        <tr>
            <td>Quốc tịch:</td>
            <td><input type="text" name="country" id="country" value="${requestScope["user"].getCountry()}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Nhấn đê :v"></td>
        </tr>
    </table>
</form>

</body>
</html>
