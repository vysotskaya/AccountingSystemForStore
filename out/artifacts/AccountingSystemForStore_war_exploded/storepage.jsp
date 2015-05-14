<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <div class="col-sm-7">
        <h3>
          <a href="/accountingsystem?command=showallrecords" class="link">Система учетов товаров</a>
        </h3>
        <h5>
          Склад временного хранения
        </h5>
      </div>
      <div class="col-sm-5" style="margin-top:10px;">
        <br>
        <form class="form-inline text-right" method="get" action="/accountingsystem" role="form">
          <div class="form-group">
            <input type="text" class="form-control" name="searchOption" placeholder="Search..." />
          </div>
          <%--<div class="form-group">--%>
            <%--<select class="form-control" name="searchOption">--%>
              <%--<option value="byMarking">по маркировке</option>--%>
              <%--<option value="byName">по наименованию</option>--%>
              <%--<option value="byEmployee">по ответственному</option>--%>
            <%--</select>--%>
          <%--</div>--%>
          <div class="form-group">
            <button title="Осуществить поиск" name="command" value="search" type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></button>
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
            <li><a href="periodpage.jsp" class="link">Сформировать отчёт за период</a></li>
          </c:if>

        </ul>
        </li>
        </ul>
      </nav>
    </div>
    <div class="col-sm-9 text-center" style="position: relative;">
      <table id="myTable" class="tablesorter table table-hover" style="margin-top: 50px;">
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
            <c:forEach var="record" items="${list}">

              <tr>
                <td>
                  <c:out value="${record.product.product_marking}"/>
                </td>
                <td>
                  <c:out value="${record.product.product_name}"/>
                </td>
                <td>
                  <c:out value="${record.product.acount}"/>&nbsp;
                  <c:out value="${record.product.measuring_unit}"/>
                </td>
                <td>
                  <c:out value="${record.retention_limit}"/>
                </td>
                <td>
                  <c:out value="${record.employee.employee_name}"/>
                </td>

                <c:if test="${not isAuthorized}">
                  <style>
                    #editbutton {
                      display: none;
                    }
                    #deletebutton {
                      display: none;
                    }
                  </style>
                </c:if>

                <td>

                  <a title="Редактировать запись" href="/accountingsystem?command=editproduct&record_id=${record.record_id}" id="editbutton"
                     class="btn btn-sm btn-warning">>
                    <span class="glyphicon glyphicon-pencil"></span>
                  </a>
                  <a title="Удалить запись" href="/accountingsystem?command=deleterecord&record_id=${record.record_id}" id="deletebutton"
                     onclick="return confirm('Вы действительно желаете удалить эту запись?')"
                     class="btn btn-sm btn-danger">><span class="glyphicon glyphicon-trash"></span></a>

                </td>
              </tr>

            </c:forEach>
          </c:if>
        </tbody>

      </table>

      <c:if test="${not isAuthorized}">
        <style>
          #addbutton{
            display: none;
          }
        </style>
      </c:if>

      <a href="/accountingsystem?command=addproduct" id="addbutton" class="btn btn-sm btn-primary">Добавить</a>

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