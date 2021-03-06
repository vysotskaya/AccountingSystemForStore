<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Record" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.04.2015
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel = 'stylesheet' href="style.css">
  <title>Отправители товаров</title>
  <style>
    <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
    <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
    <jsp:include page="style.css"></jsp:include>
  </style>
</head>
<body>
<c:set var="list" value="${list}"/>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <a href="" class="pull-right" style="margin-top:25px">| Редактировать профиль</a>
    <a href="/accountingsystem?command=authorizedafterlogin" class="pull-right" style="margin-top:25px">Войти&nbsp;</a>
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
        <li class="active"><a href="/accountingsystem?command=showallsenders" class="link">Отправители товаров</a></li>
        <li><a href="/accountingsystem?command=showallreceivers" class="link">Получатели товаров</a></li>
        <li><a href="#" class="link">Полная информация о товарах</a></li>
        <li><a href="#" class="link">Запрещённые товары на складе</a></li>
        <li><a href="#" class="link">Сформировать отчёт за период</a></li>
      </ul>
      </li>
      </ul>
    </nav>
  </div>
  <div class="col-sm-9 text-center">
    <table class="table table-hover" style="margin-top: 50px;">
      <thead style="display: block; width:100%;">
      <tr>
        <th style="width: 20%;">
          Имя отправителя
        </th>
        <th style="width: 40%;">
          Юридический адрес
        </th>
        <th style="width: 20%;">
          Телефон
        </th>
        <th style="width: 20%;">
          Почта
        </th>
      </tr>
      </thead>
      <tbody>

      <c:if test="${empty list}">
        <tr>
          <td>
            <div class="alert alert-warning text-center" style="margin-top: 30px;">
              <h5>База данных отправителей пуста.</h5>
            </div>
          </td>
        </tr>
      </c:if>
      <c:if test="${not empty list}">
        <c:forEach var="sender" items="${list}">

          <tr>
            <td style="width: 20%;">
              <c:out value="${sender.sender_name}"/>
            </td>
            <td style="width: 40%;">
              <c:out value="${sender.legal_address}"/>
            </td>
            <td style="width: 20%;">
              <c:out value="${sender.phone}"/>
            </td>
            <td style="width: 20%;">
              <c:out value="${sender.email}"/>
            </td>
          </tr>

        </c:forEach>
      </c:if>

      </tbody>
    </table>
  </div>
</div>
<div id="footer" style="background: #D3D3D3;">
  <div class="container text-center" style="">
    <p class="text-muted">Accounting system &copy; 2015</p>
  </div>
</div>
</body>
</html>
