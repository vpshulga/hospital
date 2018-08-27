<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

    <div class="col-md-6">
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="messages" var="i18n"/>
        <div class="table-responsive">
            <h4><fmt:message bundle="${i18n}" key="doc.patients"/></h4>
            <table class="table">
                <tr>
                    <th>№</th>
                    <th>ID</th>
                    <th><fmt:message bundle="${i18n}" key="form.name"/></th>
                    <th><fmt:message bundle="${i18n}" key="form.surname"/></th>
                    <th><fmt:message bundle="${i18n}" key="form.complaint"/></th>
                    <th><fmt:message bundle="${i18n}" key="form.patientcard"/></th>
                </tr>
                <c:forEach var="patient" items="${patients}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${patient.id}</td>
                        <td>${patient.firstName}</td>
                        <td>${patient.lastName}</td>
                        <div class="form-group">
                            <td><textarea class="form-control" cols="15" readonly>${patient.complaint}</textarea></td>
                        </div>
                        <td>
                            <a href="${pageContext.request.contextPath}/frontController?command=card&id=${patient.id}"
                               class="btn btn-default btn-block" role="button"><fmt:message bundle="${i18n}" key="form.showbutton"/></a>
                        </td>

                    </tr>
                </c:forEach>


            </table>
        </div>
    </div>


    <div class="col-md-2">
        <form method="POST" action="frontController?command=doctor">
            <div class="form-group">
                <h4><fmt:message bundle="${i18n}" key="doc.app"/></h4>
                <label for="patId"><fmt:message bundle="${i18n}" key="form.choosepat"/></label>
                <select class="form-control" name="patientAppId" id="patId">
                    <c:forEach var="patient" items="${requestScope.patients}" varStatus="status">
                        <option value="${patient.id}">${status.index + 1} ${patient.firstName} ${patient.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="appType"><fmt:message bundle="${i18n}" key="form.chooseapptype"/></label>
                <select class="form-control" name="appointmentType" id="appType">
                    <option value="PROCEDURES">Процедура</option>
                    <option value="OPERATIONS">Операция</option>
                    <option value="DRUGS">Лекарство</option>
                </select>
            </div>
            <div class="form-group">
                <label for="appointment"><fmt:message bundle="${i18n}" key="form.takeapp"/></label>
                <input type="text" class="form-control" name="appointment" id="appointment">
            </div>
            <button type="submit" class="btn btn-default"><fmt:message bundle="${i18n}" key="form.submit"/></button>
        </form>
        <div>
            <c:if test="${not empty doneAppointment}">
                <h3><fmt:message bundle="${i18n}" key="appointment.sucssess"/>(${requestScope.doneAppointment.text})</h3>
            </c:if>
        </div>
    </div>


    <div class="col-md-2">
        <form method="POST" action="frontController?command=doctor">
            <div class="form-group">
                <h4><fmt:message bundle="${i18n}" key="doc.diagnosys"/></h4>
                <label for="patientId"><fmt:message bundle="${i18n}" key="form.choosepat"/></label>
                <select class="form-control" name="patientDiaId" id="patientId">
                    <c:forEach var="patient" items="${patients}" varStatus="status">
                        <option value="${patient.id}">${status.index + 1} ${patient.firstName} ${patient.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="diagnosys"><fmt:message bundle="${i18n}" key="form.takedia"/></label>
                <input type="text" class="form-control" name="diagnosys" id="diagnosys">
            </div>
            <button type="submit" class="btn btn-default"><fmt:message bundle="${i18n}" key="form.submit"/></button>
        </form>
        <div>
            <c:choose>
                <c:when test="${not empty doneDiagnosys}">
                    <div>
                        <h3><fmt:message bundle="${i18n}" key="diagnosys.done"/>(${doneDiagnosys.text})</h3>
                    </div>
                </c:when>
                <c:when test="${not empty updatedDiagnosys}">
                    <h3><fmt:message bundle="${i18n}" key="diagnosys.updated"/>(${updatedDiagnosys.text})</h3>
                </c:when>
            </c:choose>
        </div>
    </div>

    <div class="col-md-2">
        <form method="POST" action="frontController?command=doctor">
            <div class="form-group">
                <h4><fmt:message bundle="${i18n}" key="doc.delete"/></h4>
                <label for="delPat"><fmt:message bundle="${i18n}" key="form.choosepat"/></label>
                <select class="form-control" name="delPatId" id="delPat">
                    <c:forEach var="patient" items="${patients}" varStatus="status">
                        <option value="${patient.id}">${status.index + 1} ${patient.firstName} ${patient.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default"><fmt:message bundle="${i18n}" key="form.submit"/></button>
        </form>
        <c:if test="${not empty isDeleted}">
            <h3><fmt:message bundle="${i18n}" key="patient.deleted"/></h3>
        </c:if>
    </div>
</div>

