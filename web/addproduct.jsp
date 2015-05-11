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
<title>Добавить товар</title>
<style>
  <jsp:include page="bootstrap-3.3.4-dist\js\bootstrap.min.js"></jsp:include>
  <jsp:include page="bootstrap-3.3.4-dist\css\bootstrap.min.css"></jsp:include>
  <jsp:include page="style.css"></jsp:include>
</style>
</head>
<body>

<c:set var="regimeList" value="${regimeList}"></c:set>
<c:set var="areaList" value="${areaList}"></c:set>

<div class="container">
  <div class="container col-md-4 col-md-offset-4 main">
    <form action="/accountingsystem" method="get">
      <!-- Modal for edit profile -->
      <div class="modal alert-info" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true">
        <div class="modal-dialog" style="width: 35%;">
          <div class="modal-content" >
            <div class="modal-header text-center">
              <a href="/accountingsystem?command=showallrecords" id="closebutton" style="margin-top: -10px;" class="btn close">×</a>
              <h4 class="modal-title" id="myModalLabel">Добавить товар</h4>
            </div>
            <div class="modal-body" style="height: 380px; overflow-y: scroll;">
              <form>
                <div class="form-inline text-center" role="form">
                  <div class="form-group text-left">
                    Маркировка* <br/> <input type="text"  name="markingInput" class="form-control"
                                             required="true" placeholder="1A2DW55"/><br/><br/>
                    Количесвто* <br/> <input type="text"  name="acountInput" class="form-control"
                                             required="true" placeholder="0"/><br/><br/>
                    Имя отправителя* <br/> <input type="text"  name="senderNameInput" class="form-control"
                                             required="true" placeholder="Имя отправителя"/><br/><br/>
                    Юридический адрес о.* <br/> <input type="text"  name="senderAddressInput" class="form-control"
                                             required="true" placeholder="Юридический адрес"/><br/><br/>
                    Телефон отправителя* <br/> <input type="text"  name="senderPhoneInput" class="form-control"
                                             required="true" placeholder="йцукенг"/><br/><br/>
                    Почта отправителя* <br/> <input type="text"  name="senderEmailInput" class="form-control"
                                             required="true" placeholder="Почта"/><br/><br/>
                    Срок хранения* <br/> <input type="date"  name="limitInput" class="form-control"
                                             required="true" placeholder="01.01.2015"/><br/><br/>
                    Особенности хранения <br/> <textarea rows="4" cols="18"  name="featuresInput" class="form-control"
                                             placeholder="Введите особенности хранения..."></textarea><br/>
                  </div>
                  <div class="form-group text-left">
                    Наименование* <br/> <input type="text" name="nameInput" class="form-control"
                                               required="true" placeholder="Наименование"/><br/><br/>
                    Единица измерения* <br/>
                    <select class="form-control" name="unitSelect">
                      <option>шт.</option>
                      <option>кг.</option>
                      <option>л.</option>
                    </select>	<br/><br/>
                    Имя получателя* <br/> <input type="text"  name="receiverNameInput" class="form-control"
                                                 required="true" placeholder="Имя получателя"/><br/><br/>
                    Юридический адрес п.* <br/> <input type="text" name="receiverAddressInput" class="form-control"
                                                 required="true" placeholder="Юридический адрес"/><br/><br/>
                    Телефон получателя* <br/> <input type="text"  name="receiverPhoneInput" class="form-control"
                                                 required="true" placeholder="йцукенгш"/><br/><br/>
                    Почта получателя* <br/> <input type="text"  name="receiverEmailInput" class="form-control"
                                                 required="true" placeholder="Почта"/><br/><br/>
                    Таможенный режим* <br/>
                    <select class="form-control" style="width: 200px;" name="regimeSelect">
                      <c:forEach var="regime" items="${regimeList}">
                        <option value="${regime.regime_id}"><c:out value="${regime.regime_name}"/></option>
                      </c:forEach>
                    </select>	<br/><br/>
                    Складская зона* <br/>
                    <select class="form-control" name="areaSelect">
                      <c:forEach var="area" items="${areaList}">
                        <option value="${area.storearea_id}"><c:out value="${area.storearea_name}"/></option>
                      </c:forEach>
                    </select> <br/><br/><br/><br/><br/>
                  </div>
                </div>

                <br/>
                <button type="submit" style="margin-left: 280px;" name="command" value="saveproduct" class="btn btn-primary text-center">Сохранить запись</button>

                <c:if test="${not isWrong}">
                  <style>
                    #modalblock {
                      display: none;
                      opacity: 0;
                    }
                  </style>
                </c:if>
                <c:if test="${isWrong}">
                  <style>
                    #modalblock {
                      display: block;
                      opacity: 1;
                    }
                  </style>
                </c:if>

                <div class="modal fade bs-example-modal-sm" id="modalblock" style="margin-top: 15%;"
                     tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                  <div class="modal-dialog modal-sm">
                    <div class="modal-content alert-danger" style="background: #e4b9b9;">
                      <button type="button" class="close" onclick="" id="modalclose" data-dismiss="modal"style="margin-right: 5px;" aria-hidden="true">×</button>&nbsp;
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

<c:if test="${not isWrong}">
  <style>
    #blackoutdiv {
      display: none;
    }
  </style>
</c:if>
<c:if test="${isWrong}">
  <style>
    #blackoutdiv {
      display: block;
    }
  </style>
</c:if>

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

