<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Record" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.05.2015
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel = 'stylesheet' href="style.css">
  <title>Система учёта</title>
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
  <div class="container" style="margin-right: 120px;">
    <a href="/accountingsystem?command=signin" class="pull-right" style="margin-top:25px">
      <c:if test="${empty login}"><c:out value="Войти"/></c:if>
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
        <a href="#" class="link">Персонал</a>
      </h3>
      <h5>
        Склад временного хранения
      </h5>
    </div>
    <div class="col-sm-4" style="margin-top:10px;">
      <br>
      <form class="form-inline text-right" role="form">
        <div class="form-group">
          <input type="text" id="textToFind" class="form-control" placeholder="Search..." />
        </div>
        <div class="form-group">
          <a title="Осуществить поиск" href="#" type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></a>
        </div>
      </form>
    </div>
  </div>
</div>
<div class="container">
  <div class="col-sm-1 text-center"></div>
    <div class="col-sm-10 text-center">
      <table id="myTable" class="tablesorter table table-hover" style="margin-top: 50px;">
        <thead style="display: block; width:100%;">
          <tr>
            <th style="width: 25%;">
              ФИО
            </th>
            <th style="width: 25%;">
              Должность
            </th>
            <th style="width: 25%;">
              Почта
            </th>
            <th style="width: 25%;">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </th>
          </tr>
        </thead>
        <tbody>
        <c:if test="${list.size() == 1}">
          <tr>
            <td>
              <div class="alert alert-warning text-center" style="margin-top: 30px;">
                <h5>База данных сотрудников пуста.</h5>
              </div>
            </td>
          </tr>
        </c:if>
        <c:if test="${not empty list}">

          <c:forEach var="employee" items="${list}">

            <c:if test="${employee.position.position_id != 2}">
              <tr>
                <td style="width: 25%;">
                  <c:out value="${employee.employee_name}"/>
                </td>
                <td style="width: 25%;">
                  <c:out value="${employee.position.position_name}"/>
                </td>
                <td style="width: 25%;">
                  <c:out value="${employee.email}"/>
                </td>
                <td style="width: 25%;">
                    <a title="Редактировать данные сотрудника" href="/accountingsystem?command=editprofile&emloyee_id=${employee.employee_id}" id="editbutton" class="btn btn-sm btn-warning">>
                      <span class="glyphicon glyphicon-pencil"></span>
                    </a>
                    <a title="Удалить данные о сотруднике" href="/accountingsystem?command=deleteemployee&employee_id=${employee.employee_id}"
                       onclick="return confirm('Вы действительно желаете удалить пользователя?')"
                       class="btn btn-sm btn-danger">><span class="glyphicon glyphicon-trash"></span></a>

                </td>
              </tr>
            </c:if>
          </c:forEach>
        </c:if>
        </tbody>
      </table>

      <a href="/accountingsystem?command=addemployee" id="addbutton" class="btn btn-sm btn-primary">Добавить пользователя</a>

    </div>
</div>
<div id="footer" style="background: #D3D3D3;">
  <div class="container text-center">
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