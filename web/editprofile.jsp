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

  <div class="container">
      <div class="container col-md-4 col-md-offset-4 main">
          <form>
            <!-- Modal for edit profile -->
            <div class="modal text-left alert-info" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
              <div class="modal-dialog" style="width: 30%;">
                <div class="modal-content" >
                  <div class="modal-header">
                    <a href="/accountingsystem?command=showallrecords" id="closebutton" style="margin-top: -10px;" class="btn close">×</a>
                    <h4 class="modal-title" id="myModalLabel">Редактировать профиль</h4>
                  </div>
                  <div class="modal-body">
                    <form class="text-left">
                      Фамилия* <input type="text"  name="surnameInput" class="form-control"/><br/>
                      Имя* <input type="text" name="nameInput" class="form-control"/><br/>
                      Отчество* <input type="text" name="lastNameInput" class="form-control"/><br/>
                      Должность* <br/>
                      <select class="form-control">
                        <option>Инженер</option>
                        <option>Администратор</option>
                      </select>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-primary">Сохранить изменения</button>
                  </div>
                </div>
              </div>
            </div>
          </form>

      </div>
      <div class="container"></div>
  </div>
  <div class="modal-backdrop fade in" id="blackoutdiv"></div>
</body>
</html>
