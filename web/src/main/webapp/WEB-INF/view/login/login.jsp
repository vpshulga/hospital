<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container text-center">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="messages" var="i18n"/>
    <div class="error">
        <c:if test="${not empty requestScope.errorMsg}">
            <fmt:message bundle="${i18n}" key="login.error.msg"/>
        </c:if>
    </div>


    <form class="form-inline" action="frontController?command=login" method="post">
        <div class="form-group">
            <label for="login"><fmt:message bundle="${i18n}" key="login.login"/>:</label>
            <input type="text" class="form-control" name="login" id="login">
        </div>
        <div class="form-group">
            <label for="password"><fmt:message bundle="${i18n}" key="login.password"/>:</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>
        <button type="submit" class="btn btn-default"><fmt:message bundle="${i18n}" key="login.submit"/></button>

    </form>
</div>
