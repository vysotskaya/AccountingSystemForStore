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
<title>Выбор ответственного</title>
<style>
  <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
  <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
  <jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
<c:set var="role" value="${role}" scope="session" />
<c:set var="list" value="${list}"></c:set>

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

<c:if test="${list.size() == 2}">
  <div align="center" style="margin-top: 100px;">
    <h3>Вы не можете удалить единственного ответственного сотрудника!</h3>
    <script type="text/javascript">
      setTimeout('location.replace("http://localhost:8080/accountingsystem?command=showallemployees")', 4000);
    </script>
  </div>
  <jsp:forward page="index.jsp" />
</c:if>

<div class="container">
  <div class="container col-md-4 col-md-offset-4 main">
    <form action="/accountingsystem" method="get">
      <!-- Modal for edit profile -->
      <div class="modal alert-info" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
        <div class="modal-dialog" style="width: 30%;">
          <div class="modal-content" >
            <div class="modal-header text-center">
              <a href="/accountingsystem?command=showallemployees" id="closebutton" style="margin-top: -10px;"
                 class="btn close">×</a>
              <h4 class="modal-title" id="myModalLabel">Выбор нового ответственного за товары удаляемого сотрудника</h4>
            </div>
            <div class="modal-body">
              <form>
                <input type="hidden" name="deleteEmployee_id" value="${employee_id}">
                <div class="form-inline text-center" role="form">
                  <div class="form-group text-left" style="width: 80%;">
                    Фамилия <br/> <select class="form-control" name="employeeSelect" style="width: 100%;">
                                    <c:set var="deleteEmployeeId" value="${employee_id}"></c:set>
                                    <c:forEach var="employee" items="${list}">
                                      <c:if test="${employee.position.position_id != 2
                                        && employee.employee_id != deleteEmployeeId}">
                                        <option value="${employee.employee_id}">
                                          <c:out value="${employee.employee_name}"/>
                                        </option>
                                      </c:if>
                                    </c:forEach>
                                  </select><br/>
                  </div>
                </div>

                <br/>
                <button type="submit" style="margin-left: 200px;" name="command" value="selectemployee"
                        class="btn btn-primary text-center">Выбрать</button>
              </form>
            </div>
            <br/>
          </div>
        </div>
      </div>
    </form>

  </div>
  <div class="container"></div>
</div>
<div class="modal-backdrop fade in" id="blackoutdiv"></div>

<script type="text/javascript">
  document.getElementById('modalclose').onclick = function () {
    document.getElementById('blackoutdiv').style.display = 'none';
    document.getElementById('modalblock').style.display = 'none';
    document.getElementById('modalblock').style.opacity = '0';
  }
</script>

</body>
</html>

