<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.04.2015
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel = 'stylesheet' href="style.css">
  <title>Авторизация</title>
  <style>
    <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
    <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
    <jsp:include page="style.css"></jsp:include>
  </style>
</head>
<body>

  <div class="container" style="margin-top : 10%;">
    <div class="jumbotron text-center alert-info">
      <div class="container col-md-4 col-md-offset-4 main text-center">
        <h3>Вход в систему учёта товаров</h3>
        <br/>
        <form action="/accountingsystem" method="post">
          <input type="text" placeholder="Login" required="true"
                 name="loginInput" style="width: 105%;" class="form-control"/><br/>
          <input type="password" placeholder="Password" required="true"
                 name="passwordInput" style="width: 105%;" class="form-control"/><br/><br/>
          <div class="form-inline text-center form-group" role="form" style="margin-left : 20px;">
            <button class="btn btn-sm btn-primary" style="width: 48%;" type="submit"
                    data-toggle="modal" data-target=".bs-example-modal-sm"
                    name="command" value="authorization">
              Авторизоваться
            </button>
            <button class="btn btn-sm btn-primary" style="width: 50%;" type="submit" name="command"
                    value="login">Войти без авторизации</button>

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
                <div class="modal-content">
                  <button type="button" class="close" onclick="" id="modalclose" data-dismiss="modal"
                          style="margin-right: 5px;" aria-hidden="true">×</button>&nbsp;
                  <br/>
                  <b>Некорректные данные!</b>
                  <br/><br/>
                </div>
              </div>
            </div>

          </div>
        </form>
      </div>
      <div class="container"></div>
    </div>
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
