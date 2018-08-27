<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container text-center">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="messages" var="i18n"/>


    <form class="form-horizontal" action="frontController?command=change" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2" for="oldPassword"><fmt:message bundle="${i18n}"
                                                                                 key="login.oldpass"/>:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="oldPassword" id="oldPassword" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="newPassword"><fmt:message bundle="${i18n}"
                                                                                 key="login.newpass"/>:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="newPassword" id="newPassword" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="confirm"><fmt:message bundle="${i18n}"
                                                                             key="login.confirm"/>:</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="confirm" id="confirm" required>
            </div>
        </div>
        <button type="submit" class="btn btn-default"><fmt:message bundle="${i18n}"
                                                                   key="login.changepass.btn"/></button>

    </form>
    <div class="error">
        <c:if test="${not empty requestScope.successChange}">
            ${successChange}
        </c:if>
        <c:if test="${not empty requestScope.noSuccessChange}">
            ${noSuccessChange}
        </c:if>
    </div>
</div>
