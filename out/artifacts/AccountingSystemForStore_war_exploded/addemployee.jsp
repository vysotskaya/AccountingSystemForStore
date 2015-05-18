<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="utf-8" />
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.05.2015
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel = 'stylesheet' href="style.css">
<title>Добавить пользователя</title>
<style>
  <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
  <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
  <jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>

<c:set var="list" value="${list}"></c:set>
<c:set var="employee" value="${employee}"></c:set>
<c:set var="role" value="${role}" scope="session" />

<c:choose>
  <c:when test="${empty role}">
    <div align="center" style="margin-top: 100px;">
      <h3>No access</h3>
      <script type="text/javascript">
        setTimeout('location.replace("http://localhost:8080/index.jsp")', 1000);
      </script>
    </div>
    <jsp:forward page="index.jsp" />
  </c:when>
  <c:when test="${role != 2}">
    <div align="center" style="margin-top: 100px;">
      <h3>No access</h3>
      <script type="text/javascript">
        setTimeout('location.replace("http://localhost:8080/accountingsystem?command=showallrecords")', 1000);
      </script>
    </div>
    <jsp:forward page="index.jsp" />
  </c:when>
</c:choose>

<div class="container">
  <div class="container col-md-4 col-md-offset-4 main">
    <form action="/accountingsystem" method="get">
      <!-- Modal for edit profile -->
      <div class="modal text-left alert-info" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal"
           aria-hidden="true">
        <div class="modal-dialog" style="width: 30%;">
          <div class="modal-content" >
            <div class="modal-header">
              <a href="/accountingsystem?command=showallemployees" id="closebutton" style="margin-top: -10px;"
                 class="btn close">×</a>
              <h4 class="modal-title" id="myModalLabel">Добавить пользователя</h4>
            </div>
            <div class="modal-body">
              <form class="text-left">
                Фамилия И. О.* <input type="text" pattern="^[А-Я]{1}[а-я]{2,}\s[А-Я]{1}.[А-Я]{1}."
                                      title="Формат Фамилия И.О." name="surnameInput" class="form-control"
                                      maxlength="50" required="true" placeholder="Иванов И.И."
                                      value="${employee.employee_name}"/><br/>
                Должность* <br/>
                <select class="form-control" name="positionSelect">
                  <c:forEach var="position" items="${list}">
                    <c:if test="${position.position_id != 2}">
                      <c:choose>
                        <c:when test="${position.position_id == employee.position.position_id}">
                          <option value="${position.position_id}" selected><c:out value="${position.position_name}"/></option>
                        </c:when>
                        <c:otherwise>
                          <option value="${position.position_id}"><c:out value="${position.position_name}"/></option>
                        </c:otherwise>
                      </c:choose>
                    </c:if>
                  </c:forEach>
                </select><br/>
                Почта* <input type="email" name="emailInput" class="form-control" required="true" placeholder="Почта"
                              title="Почтовый адрес должен содержать @. Максимальная длина - 100." maxlength="100"
                              value="${employee.email}"/><br/>
                Логин* <input type="text" pattern="^[A-Za-z0-9._]{3,}"
                              title="Минимальная длина - 3, максимальная - 30. Русские символы недопустимы."
                              name="loginInput" class="form-control" required="true" placeholder="Login" maxlength="30"
                              value="${employee.login}"/><br/>
                Пароль* <input type="password" pattern="^[A-Za-z0-9._]{3,}"
                               title="Минимальная длина - 3, максимальная - 30. Русские символы недопустимы."
                               name="passwordInput" class="form-control" required="true" placeholder="Password"
                               maxlength="30" value="${employee.password}"/><br/>
                <button type="submit" name="command" value="saveemployee" class="btn btn-primary text-center">Сохранить запись</button>

                <c:choose>
                  <c:when test="${not isWrong}">
                    <style>
                      #modalblock {
                        display: none;
                        opacity: 0;
                      }
                      #blackoutdiv {
                        display: none;
                      }
                    </style>
                  </c:when>
                  <c:otherwise>
                    <style>
                      #modalblock {
                        display: block;
                        opacity: 1;
                      }
                      #blackoutdiv {
                        display: block;
                      }
                    </style>
                  </c:otherwise>
                </c:choose>

                  <div class="modal fade bs-example-modal-sm" id="modalblock" style="margin-top: 15%;"
                       tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content text-center alert-danger" style="background: #e4b9b9;">
                        <button type="button" class="close" onclick="" id="modalclose" data-dismiss="modal"
                                style="margin-right: 5px;" aria-hidden="true">×</button>&nbsp;
                        <br/>
                        <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Такой логин уже существует!</b>
                        <br/><br/>
                      </div>
                    </div>
                  </div>

              </form>
            </div>
          </div>
        </div>
      </div>
    </form>

  </div>
  <div class="container"></div>
</div>
<div class="modal-backdrop fade in" id="blackoutdiv"></div>

<div class="modal-backdrop fade in " id="blackoutdiv"></div>

<script type="text/javascript">
  document.getElementById('modalclose').onclick = function () {
    document.getElementById('blackoutdiv').style.display = 'none';
    document.getElementById('modalblock').style.display = 'none';
    document.getElementById('modalblock').style.opacity = '0';
  }
</script>

</body>
</html>

