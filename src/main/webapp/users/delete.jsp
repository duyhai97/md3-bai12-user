<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/26/2021
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<h1>Xóa người dùng</h1>
<h3><a href="/users">Quay lại </a></h3>

<h4>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</h4>

<form action="" method="post">
    <h3>Bạn chắc chắn muốn xóa</h3>
    <fieldset>
        <table>
            <tr>
                <td>Tên người dùng</td>
                <td>${requestScope["user"].getName()}</td>
            </tr>
            <tr>
                <td>Địa chỉ email</td>
                <td>${requestScope["user"].getEmail()}</td>
            </tr>
            <tr>
                <td>Quốc tịch</td>
                <td>${requestScope["user"].getCountry()}</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Xóa"></td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>
