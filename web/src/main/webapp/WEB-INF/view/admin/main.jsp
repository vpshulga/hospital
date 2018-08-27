<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="messages" var="i18n"/>
    <div class="col-md-4">
        <h3><fmt:message bundle="${i18n}" key="admin.adddoc"/>:</h3>
        <form class="form-horizontal" method="POST" action="frontController?command=admin">

            <div class="form-group">
                <label class="control-label col-sm-4" for="name"><fmt:message bundle="${i18n}" key="form.name"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="firstName" id="name" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-4" for="surname"><fmt:message bundle="${i18n}"
                                                                                 key="form.surname"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="lastName" id="surname" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="age"><fmt:message bundle="${i18n}" key="form.age"/>:</label>
                <div class="col-md-8">
                    <input type="number" class="form-control" name="age" id="age" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="sex"><fmt:message bundle="${i18n}" key="form.sex"/>:</label>
                <div class="col-md-8">
                    <select class="form-control" name="sex" id="sex">
                        <option value="MALE"><fmt:message bundle="${i18n}" key="form.enum.sex.male"/></option>
                        <option value="FEMALE"><fmt:message bundle="${i18n}" key="form.enum.sex.female"/></option>
                        <option selected value="UNDEFINED"><fmt:message bundle="${i18n}"
                                                                        key="form.enum.sex.undefined"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="education"><fmt:message bundle="${i18n}" key="admin.form.education"/>:</label>
                <div class="col-md-8">
                    <select class="form-control" name="education" id="education">
                        <option value="HIGH"><fmt:message bundle="${i18n}" key="admin.form.enum.education.high"/></option>
                        <option value="MIDDLE"><fmt:message bundle="${i18n}" key="admin.form.enum.education.middle"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="experience"><fmt:message bundle="${i18n}" key="admin.exp"/>:</label>
                <div class="col-md-8">
                    <input type="number" class="form-control" name="experience" id="experience" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="spetiality"><fmt:message bundle="${i18n}" key="form.speciality"/>:</label>
                <div class="col-md-8">
                    <select class="form-control" name="spetiality" id="spetiality">
                        <option value="THERAPIST"><fmt:message bundle="${i18n}" key="doc.enum.therapist"/></option>
                        <option value="OPERATOR"><fmt:message bundle="${i18n}" key="doc.enum.operator"/></option>
                        <option value="NEUROLOGIST"><fmt:message bundle="${i18n}" key="doc.enum.neurologist"/></option>
                        <option value="OPHTHALMOLOGIST"><fmt:message bundle="${i18n}" key="doc.enum.ophtalmologist"/></option>
                        <option value="DENTIST"><fmt:message bundle="${i18n}" key="doc.enum.dentist"/></option>
                        <option value="OTORHINOLARINGOLOGIST"><fmt:message bundle="${i18n}" key="doc.enum.otorhinoloringologist"/></option>
                        <option value="TRAUMATOLOGIST"><fmt:message bundle="${i18n}" key="doc.enum.traumatologyst"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="login"><fmt:message bundle="${i18n}" key="form.login"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="login" id="login" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="password"><fmt:message bundle="${i18n}" key="login.password"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="password" id="password" required>
                </div>
            </div>

            <input type="submit" value="<fmt:message bundle="${i18n}" key="form.submit"/>">
        </form>
        <c:if test="${not empty requestScope.repDoc}">
            <h3><fmt:message bundle="${i18n}" key="admin.repeateduser"/></h3>
        </c:if>
        <c:if test="${not empty requestScope.isCreatedDoc}">
            <h3><fmt:message bundle="${i18n}" key="admin.createddoc"/></h3>
        </c:if>
    </div>

    <div class="col-md-4">
        <h3><fmt:message bundle="${i18n}" key="admin.addreg"/>:</h3>
        <form class="form-horizontal" method="POST" action="frontController?command=admin">

            <div class="form-group">
                <label class="control-label col-sm-4" for="firstNameReg"><fmt:message bundle="${i18n}" key="form.name"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="firstNameReg" id="firstNameReg" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-4" for="lastNameReg"><fmt:message bundle="${i18n}"
                                                                                 key="form.surname"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="lastNameReg" id="lastNameReg" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="ageReg"><fmt:message bundle="${i18n}" key="form.age"/>:</label>
                <div class="col-md-8">
                    <input type="number" class="form-control" name="ageReg" id="ageReg" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="sexReg"><fmt:message bundle="${i18n}" key="form.sex"/>:</label>
                <div class="col-md-8">
                    <select class="form-control" name="sexReg" id="sexReg">
                        <option value="MALE"><fmt:message bundle="${i18n}" key="form.enum.sex.male"/></option>
                        <option value="FEMALE"><fmt:message bundle="${i18n}" key="form.enum.sex.female"/></option>
                        <option selected value="UNDEFINED"><fmt:message bundle="${i18n}"
                                                                        key="form.enum.sex.undefined"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="educationReg"><fmt:message bundle="${i18n}" key="admin.form.education"/>:</label>
                <div class="col-md-8">
                    <select class="form-control" name="educationReg" id="educationReg">
                        <option value="HIGH"><fmt:message bundle="${i18n}" key="admin.form.enum.education.high"/></option>
                        <option value="MIDDLE"><fmt:message bundle="${i18n}" key="admin.form.enum.education.middle"/></option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="experienceReg"><fmt:message bundle="${i18n}" key="admin.exp"/>:</label>
                <div class="col-md-8">
                    <input type="number" class="form-control" name="experienceReg" id="experienceReg" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="loginReg"><fmt:message bundle="${i18n}" key="form.login"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="loginReg" id="loginReg" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-4" for="passwordReg"><fmt:message bundle="${i18n}" key="login.password"/>:</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="passwordReg" id="passwordReg" required>
                </div>
            </div>

            <input type="submit" value="<fmt:message bundle="${i18n}" key="form.submit"/>">
            <c:if test="${not empty requestScope.repReg}">
                <h3><fmt:message bundle="${i18n}" key="admin.repeateduser"/></h3>
            </c:if>
            <c:if test="${not empty requestScope.isCreatedReg}">
                <h3><fmt:message bundle="${i18n}" key="admin.createreg"/></h3>
            </c:if>
        </form>


    </div>

    <div class="col-md-4">
        <form method="POST" action="frontController?command=admin">
            <div class="form-group">
                <h4><fmt:message bundle="${i18n}" key="admin.delete.doc"/></h4>
                <label for="delDoc"><fmt:message bundle="${i18n}" key="form.choosedoc"/></label>
                <select class="form-control" name="delDocId" id="delDoc">
                    <c:forEach var="doctor" items="${requestScope.doctors}" varStatus="status">
                        <option value="${doctor.id}">${status.index + 1} ${doctor.firstName} ${doctor.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <c:if test="${not empty isDocDeleted}">
            <h3>Доктор удален</h3>
        </c:if>

        <form method="POST" action="frontController?command=admin">
            <div class="form-group">
                <h4><fmt:message bundle="${i18n}" key="admin.delete.reg"/></h4>
                <label for="delReg"><fmt:message bundle="${i18n}" key="form.choosereg"/></label>
                <select class="form-control" name="delRegId" id="delReg">
                    <c:forEach var="reg" items="${requestScope.registryWorkers}" varStatus="status">
                        <option value="${reg.id}">${status.index + 1} ${reg.firstName} ${reg.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <c:if test="${not empty isRegDeleted}">
            <h3>Работник регистратуры удален</h3>
        </c:if>
    </div>
</div>

