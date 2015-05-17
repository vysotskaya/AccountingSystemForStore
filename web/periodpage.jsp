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
<title>Период для отчёта</title>
<style>
  <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
  <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
  <jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>
  <c:set var="role" value="${role}" scope="session" />
  <c:set var="regimeList" value="${regimeList}"></c:set>
  <c:set var="areaList" value="${areaList}"></c:set>

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
    <c:when test="${role == 2}">
      <div align="center" style="margin-top: 100px;">
        <h3>No access</h3>
        <script type="text/javascript">
          setTimeout('location.replace("http://localhost:8080/accountingsystem?command=showallemployees")', 1000);
        </script>
      </div>
      <jsp:forward page="index.jsp" />
    </c:when>
  </c:choose>

<div class="container">
  <div class="container col-md-4 col-md-offset-4 main">
    <form action="/accountingsystem" method="get">
      <!-- Modal for edit profile -->
      <div class="modal alert-info" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
        <div class="modal-dialog" style="width: 30%;">
          <div class="modal-content" >
            <div class="modal-header text-center">
              <a href="/accountingsystem?command=showallrecords" id="closebutton" style="margin-top: -10px;"
                 class="btn close">×</a>
              <h4 class="modal-title" id="myModalLabel">Отчёт за период</h4>
            </div>
            <div class="modal-body">
              <form>
                <div class="form-inline text-center" role="form">
                  <div class="form-group text-left">
                    Начало периода <br/> <input type="text"  name="periodBeginInput" class="form-control"
                                             required="true" placeholder="01.01.2014"/><br/>
                  </div>
                  <div class="form-group text-left">
                    Конец периода <br/> <input type="text" name="periodEndInput" class="form-control"
                                               required="true" placeholder="01.02.2015"/><br/>
                  </div>
                </div>

                <br/>
                <button type="submit" style="margin-left: 200px;" name="command" value="generatereport"
                        class="btn btn-primary text-center">Сформировать отчёт</button>

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
                    <div class="modal-content alert-danger" style="background: #e4b9b9;">
                      <button type="button" class="close" onclick="" id="modalclose" data-dismiss="modal"
                              style="margin-right: 5px;" aria-hidden="true">×</button>&nbsp;
                      <br/>
                      <b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;qwertyui!</b>
                      <br/>
                      <br/>
                    </div>
                  </div>
                </div>

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

