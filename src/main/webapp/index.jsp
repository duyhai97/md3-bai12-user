<%--
  Created by IntelliJ IDEA.
  User: TruongDubai
  Date: 5/26/2021
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<h1>Trang quản lí</h1>
  <div>
    <h2>
      <a href="/users">Danh sách người dùng</a>
    </h2>

    <h2>
      <a href="/users?action=create">Thêm mới người dùng</a>
    </h2>

    <h2>
      Tìm kiếm người dùng
    </h2>
    <form action="/users?action=search" method="post">
      <input type="text" placeholder="Tìm kiếm" name="aaa">
      <select name="select" id="select">
        <option value="name">Name</option>
        <option value="email">Email</option>
        <option value="country">Country</option>
      </select>
      <button>Nhập</button>
    </form>

    <h3>tim kiem</h3>
    <form action="/users?action=timkiem" method="post">
      <input type="text" name="value" id = "value" placeholder="tìm kiếm">
      <button>Nhập</button>
    </form>

  </div>
  </body>
</html>
