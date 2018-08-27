<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

    <div class="col-md-12">
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="messages" var="i18n"/>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th><fmt:message bundle="${i18n}" key="card.entrancedate"/></th>
                    <th><fmt:message bundle="${i18n}" key="form.name"/></th>
                    <th><fmt:message bundle="${i18n}" key="form.surname"/></th>
                    <th><fmt:message bundle="${i18n}" key="form.complaint"/></th>
                    <th><fmt:message bundle="${i18n}" key="card.app"/></th>
                    <th><fmt:message bundle="${i18n}" key="card.dia"/></th>
                </tr>

                <tr>
                    <td>${requestScope.patient.id}</td>
                    <td>${requestScope.patient.entranceDate}</td>
                    <td>${requestScope.patient.firstName}</td>
                    <td>${requestScope.patient.lastName}</td>
                    <td>${requestScope.patient.complaint}</td>
                    <td>
                        <c:forEach var="app" items="${requestScope.appointments}">
                            ${app.text}(
                            <c:choose>
                                <c:when test="${app.type eq 'PROCEDURES'}">
                                    <fmt:message bundle="${i18n}" key="app.procedures"/>
                                </c:when>
                                <c:when test="${app.type eq 'OPERATIONS'}">
                                    <fmt:message bundle="${i18n}" key="app.operations"/>
                                </c:when>
                                <c:when test="${app.type eq 'DRUGS'}">
                                    <fmt:message bundle="${i18n}" key="app.drugs"/>
                                </c:when>
                            </c:choose>
                            )<br/>
                        </c:forEach>
                    </td>
                    <td>${requestScope.diagnosys.text}</td>
                </tr>

            </table>
        </div>
    </div>

</div>

