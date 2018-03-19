<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="accounts_wrapper">
    <c:if test="${userType == employeeId}">
        <div class="request">
            <span>Заявки на открытие счета</span>
        </div>
        <c:forEach items="${accountsToOpen}" var="account">
            <div class="request_user">
                <span><b>Пользователь: </b>${account.user.profile.lastName} ${account.user.profile.firstName}</span>
            </div>
            <div class="account" id="${account.id}" style="display: inline-block">
                <span style="margin-left: 10px">${account.accountNumber}</span>
                <a class="button_close_account" href="/accountOperationQuery?action=refuse_to_open&id=${account.id}">×</a>
                <a class="button_open_account" href="/accountOperationQuery?action=apply_to_open&id=${account.id}">✓</a>
            </div>
        </c:forEach>
        <div class="request">
            <span>Заявки на закрытие счета</span>
        </div>
        <c:forEach items="${accountsToClose}" var="account">
            <div class="request_user">
                <span><b>Пользователь: </b>${account.user.profile.lastName} ${account.user.profile.firstName}</span>
            </div>
            <div class="account" id="${account.id}" style="display: inline-block">
                <span style="margin-left: 10px">${account.accountNumber}</span>
                <span style="margin-left: 230px; font-size: 14px">Остаток: ${account.balance}</span>
                <a class="button_close_account" href="/accountOperationQuery?action=refuse_to_close&id=${account.id}">×</a>
                <a class="button_open_account" href="/accountOperationQuery?action=apply_to_close&id=${account.id}">✓</a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${userType == clientId}">
        <c:forEach items="${accounts}" var="account">
            <c:if test="${account.status == openedAccountStatus}">
                <div class="account" id="${account.id}" style="display: inline-block">
                    <span style="margin-left: 10px">${account.accountNumber}</span>
                    <span style="margin-left: 250px; font-size: 14px">Остаток: ${account.balance}</span>
                    <span>
                        <a class="button_close_account" href="/closeAccount?id=${account.id}" onclick="return confirm('Вы уверены, что хотите закрыть счет?')">×</a>
                    </span>
                </div>
            </c:if>
        </c:forEach>
        <div>
            <a class="button_register" href="/openAccount">Открыть счет</a>
        </div>
    </c:if>
</div>
