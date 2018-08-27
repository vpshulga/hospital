<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="messages" var="i18n"/>
    <div class="col-md-8">
        <div class="table-responsive">
            <div class="work-tab">

                <table>
                    <caption align="center"><fmt:message bundle="${i18n}" key="workers.doctors"/>:</caption>
                    <tr>
                        <th><fmt:message bundle="${i18n}" key="form.name"/></th>
                        <th><fmt:message bundle="${i18n}" key="form.surname"/></th>
                        <th><fmt:message bundle="${i18n}" key="form.speciality"/></th>
                    </tr>
                    <c:forEach var="doctor" items="${applicationScope.doctors}" varStatus="status">
                        <tr>
                            <td>${doctor.firstName}</td>
                            <td>${doctor.lastName}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${doctor.spetialty eq 'THERAPIST'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.therapist"/>
                                    </c:when>
                                    <c:when test="${doctor.spetialty eq 'OPERATOR'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.operator"/>
                                    </c:when>
                                    <c:when test="${doctor.spetialty eq 'NEUROLOGIST'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.neurologist"/>
                                    </c:when>
                                    <c:when test="${doctor.spetialty eq 'OPHTHALMOLOGIST'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.ophtalmologist"/>
                                    </c:when>
                                    <c:when test="${doctor.spetialty eq 'DENTIST'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.dentist"/>
                                    </c:when>
                                    <c:when test="${doctor.spetialty eq 'OTORHINOLARINGOLOGIST'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.otorhinoloringologist"/>
                                    </c:when>
                                    <c:when test="${doctor.spetialty eq 'TRAUMATOLOGIST'}">
                                        <fmt:message bundle="${i18n}" key="doc.enum.traumatologyst"/>
                                    </c:when>
                                </c:choose>
                            </td>


                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <!--
    <div class="col-md-4">
        <div class="table-responsive">
            <h3><fmt:message bundle="${i18n}" key="workers.nurses"/>:</h3>
            <table class="table">
                <tr>
                    <th>Имя</th>
                    <th>Фамилия</th>
                </tr>
                <c:forEach var="nurse" items="${applicationScope.nurses}" varStatus="status">
                    <tr>
                        <td>${nurse.firstName}</td>
                        <td>${nurse.lastName}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    -->

    <div class="col-md-4" align="center">
        <div class="table-responsive">
            <div class="work-tab">
                <table>
                    <caption align="center"><fmt:message bundle="${i18n}" key="workers.reg"/>:</caption>
                    <tr>
                        <th><fmt:message bundle="${i18n}" key="form.name"/></th>
                        <th><fmt:message bundle="${i18n}" key="form.surname"/></th>
                    </tr>
                    <c:forEach var="reg" items="${applicationScope.regWorkers}" varStatus="status">
                        <tr>
                            <td>${reg.firstName}</td>
                            <td>${reg.lastName}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
