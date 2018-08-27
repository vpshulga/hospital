<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-6">
        <fmt:setLocale value="${sessionScope.locale}"/>
        <fmt:setBundle basename="messages" var="i18n"/>
        <c:if test="${not empty requestScope.errorMsg}">
            <h3 class="error"><fmt:message bundle="${i18n}" key="form.input.error"/></h3>
        </c:if>
        <h3><fmt:message bundle="${i18n}" key="reg.form"/>:</h3>
        <form class="form-horizontal" method="POST" action="frontController?command=registration">

            <div class="form-group">
                <label class="control-label col-sm-2" for="name"><fmt:message bundle="${i18n}"
                                                                              key="form.name"/>:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="firstName" id="name" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="surname"><fmt:message bundle="${i18n}"
                                                                                 key="form.surname"/>:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="lastName" id="surname" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="age"><fmt:message bundle="${i18n}" key="form.age"/>:</label>
                <div class="col-md-10">
                    <input type="number" class="form-control" name="age" id="age" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="sex"><fmt:message bundle="${i18n}" key="form.sex"/>:</label>
                <div class="col-md-10">
                    <select class="form-control" name="sex" id="sex">
                        <option value="MALE"><fmt:message bundle="${i18n}" key="form.enum.sex.male"/></option>
                        <option value="FEMALE"><fmt:message bundle="${i18n}" key="form.enum.sex.female"/></option>
                        <option selected value="UNDEFINED"><fmt:message bundle="${i18n}"
                                                                        key="form.enum.sex.undefined"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="city"><fmt:message bundle="${i18n}"
                                                                              key="form.city"/>:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="city" id="city" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="street"><fmt:message bundle="${i18n}"
                                                                                key="form.street"/>:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="street" id="street" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="house"><fmt:message bundle="${i18n}"
                                                                               key="form.house"/>:</label>
                <div class="col-md-10">
                    <input type="number" class="form-control" name="house" id="house" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="apartament"><fmt:message bundle="${i18n}"
                                                                                    key="form.apartament"/>:</label>
                <div class="col-md-10">
                    <input type="number" class="form-control" name="apartament" id="apartament" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="complaint"><fmt:message bundle="${i18n}"
                                                                                   key="form.complaint"/>:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="complaint" id="complaint" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2" for="doctorId"><fmt:message bundle="${i18n}"
                                                                                  key="form.doctor"/>:</label>
                <div class="col-md-10">
                    <select class="form-control" name="doctorId" id="doctorId">
                        <c:forEach var="doctor" items="${applicationScope.doctors}" varStatus="status">
                            <option value="${doctor.id}">${doctor.firstName} ${doctor.lastName}
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
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="submit" value="<fmt:message bundle="${i18n}" key="form.submit"/>">
        </form>
    </div>
    <div class="col-md-6">
        <div>
            <c:if test="${not empty userName and not empty password}">
                <h3><fmt:message bundle="${i18n}" key="reg.succsessreg"/>, login = ${requestScope.userName}, password
                    = ${requestScope.password}</h3>
            </c:if>

        </div>
    </div>
</div>