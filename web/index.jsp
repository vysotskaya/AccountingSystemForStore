<%@ page import="entity.Record" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 21.04.2015
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel = 'stylesheet' href="style.css">

  <title>Система учёта</title>
  <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>--%>
  <%--<script src="${pageContext.request.contextPath}\bootstrap-3.3.4-dist\js\bootstrap.min.js"></script>--%>
  <%--<link rel = 'stylesheet' href="${pageContext.request.contextPath}\bootstrap-3.3.4-dist\css\bootstrap.min.css">--%>
  <style>
    <%@include file="bootstrap-3.3.4-dist\js\bootstrap.min.js" %>
  </style>

  <style>
    <%@include file="bootstrap-3.3.4-dist\css\bootstrap.min.css" %>
  </style>

  <style>
    .navmenu {
      width: 200px;
    }

    table thead tr th{
      text-align : center;
    }

    table tbody tr td{
      text-align : center;
    }

    body {
      /* Margin bottom by footer height */
      margin-bottom: 60px;
    }
    #footer {
      position: absolute;
      bottom: 0;
      width: 100%;
      /* Set the fixed height of the footer here */
      height: 60px;
      background-color: #f5f5f5;
    }

    .text-muted {
      margin-top: 20px;
      overflow: auto;
    }

    .link {
      color: #000000;
    }

    table tr {
      width: 100%;
      display: table;
    }

    table tbody {
      height: 240px;
      overflow: auto;
      display: block;
      width:100%;
    }

    .menu {
      margin-top:50px;
      border:1px solid;
      border-color: #A9A9A9;
      background-color: #F5F5F5;
    }

    table th, td {
      width: 17%;
    }

  </style>
</head>
<body>
  <% List<Record> list = (List)request.getAttribute("records");
    if (list == null){
  %>
  <div class="alert alert-info text-center" style="margin-top: 200px; margin-right: 400px; margin-left: 400px;">
    <h5>Please, follow <b><a href="/accountingsystem" class="alert-link">http://localhost:8080/accountingsystem</a></b > </h5>
  </div>
    <%
  } else {
    %>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <a href="" class="pull-right" style="margin-top:25px">| Редактировать профиль</a>
    <a href="" class="pull-right" style="margin-top:25px">Войти&nbsp;</a>
  </div>
</div>
<div class="jumbotron" style="margin-bottom: 0px;padding-bottom: 10px;">
  <div class="container">
    <div class="col-sm-8">
      <h3>
        <a href="" class="link">Система учетов товаров</a>
      </h3>
      <h5>
        Склад временного хранения
      </h5>
    </div>
    <div class="col-sm-4" style="margin-top:10px;">
      <br>
      <form class="form-inline text-right" role="form">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search..." />
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></button>
        </div>
      </form>
    </div>
  </div>
</div>
<div class="container">
  <div class="col-sm-3 text-center">
    <nav class="navmenu navmenu-default menu" role="navigation">
      <ul class="nav navmenu-nav">
        <li class="active"><a href="#" class="link">Отправители товаров</a></li>
        <li><a href="#" class="link">Получатели товаров</a></li>
        <li><a href="#" class="link">Полная информация о товарах</a></li>
        <li><a href="#" class="link">Запрещённые товары на складе</a></li>
        <li><a href="#" class="link">Сформировать отчёт за период</a></li>
      </ul>
      </li>
      </ul>
    </nav>
  </div>
  <div class="col-sm-9 text-center" style="position: relative;">
    <table class="table table-hover" style="margin-top: 50px;">
      <thead style="display: block; width:100%;">
      <tr>
        <th>
          Маркировка
        </th>
        <th>
          Наименование
        </th>
        <th>
          Количество
        </th>
        <th>
          Срок хранения
        </th>
        <th>
          Ответственный
        </th>
        <th>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </th>
      </tr>
      </thead>
      <tbody>

      <%
        for (Record record : list )
        {
          Product product = record.getProduct();
      %>

      <tr>
        <td>
          <%= product.getProduct_marking() %>
        </td>
        <td>
          <%= product.getProduct_name() %>
        </td>
        <td>
          <%= product.getAcount() %>&nbsp;<%= product.getMeasuring_unit() %>
        </td>
        <td>
          <%= record.getRetention_limit() %>
        </td>
        <td>
          <%= record.getEmployee().getEmployee_name() %>
        </td>
        <td>
          <a href="" class="btn btn-sm btn-warning">><span class="glyphicon glyphicon-pencil"></span></a>
          <a href="" class="btn btn-sm btn-danger">><span class="glyphicon glyphicon-trash"></span></a>
        </td>
      </tr>

      <% } %>

      </tbody>
    </table>
    <a href="" class="btn btn-sm btn-primary">Добавить</a>
  </div>
</div>
<div id="footer" style="background: #D3D3D3;">
  <div class="container text-center">
    <p class="text-muted">Accounting system &copy; 2015</p>
  </div>
</div>

<% } %>

</body>
</html>