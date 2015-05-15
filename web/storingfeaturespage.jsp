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
  <title>Особенности хранения</title>
  <style>
    <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
    <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
    <jsp:include page="style.css"></jsp:include>
  </style>
  <script src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery.tablesorter.js"></script>
</head>
<body>

<c:set var="list" value="${list}"/>
<c:set var="login" value="${login}" scope="session" />
<c:set var="isAuthorized" value="${isAuthorized}" scope="session" />

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <a href="/accountingsystem?command=signin" class="pull-right" style="margin-top:25px">
      <c:if test="${empty login}"><c:out value="Войти" /></c:if>
      <c:if test="${not empty login}">
        <c:out value="${login}" />
        <c:out value=" | Выход" />
      </c:if>
      &nbsp;
    </a>
  </div>
</div>
<div class="jumbotron" style="margin-bottom: 0px;padding-bottom: 10px;">
  <div class="container">
    <div class="col-sm-8">
      <h3>
        <a href="/accountingsystem?command=showallrecords" class="link">Система учетов товаров</a>
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
          <button title="Осуществить поиск" type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></button>
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
        <li><a href="/accountingsystem?command=showallproducts" class="link">Полная информация о товарах</a></li>
        <li><a href="/accountingsystem?command=showforbiddenproducts" class="link">Запрещённые товары на складе</a></li>
        <li><a href="/accountingsystem?command=showstoringfeatures" class="link">Особенности хранения товаров</a></li>
        <li><a href="/accountingsystem?command=showproductstodetention" class="link">Товары к задержанию</a></li>

        <c:if test="${not empty login}">
          <li><a href="/accountingsystem?command=enterperiod" class="link">Сформировать отчёт за период</a></li>
        </c:if>

      </ul>
      </li>
      </ul>
    </nav>
  </div>
  <div class="col-sm-9 text-center">
    <table id="myTable" class="tablesorter table table-hover" style="margin-top: 50px;">
      <thead style="display: block; width:100%;">
      <tr>
        <th style="width: 20%;">
          Маркировка
        </th>
        <th style="width: 20%;">
          Наименование
        </th>
        <th style="width: 60%;">
          Особенности хранения
        </th>
      </tr>
      </thead>
      <tbody>

      <c:if test="${empty list}">
        <tr>
          <td>
            <div class="alert alert-warning text-center" style="margin-top: 30px;">
              <h5>База данных товаров пуста.</h5>
            </div>
          </td>
        </tr>
      </c:if>
      <c:if test="${not empty list}">
        <c:forEach var="product" items="${list}">

          <tr>
            <td style="width: 20%;">
              <c:out value="${product.product_marking}"/>
            </td>
            <td style="width: 20%;">
              <c:out value="${product.product_name}"/>
            </td>
            <td style="width: 60%;">
              <c:out value="${product.storing_features}"/>
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
<script>
  $(document).ready(function(){
    $("#myTable").tablesorter();
  });
</script>
</body>
</html>
