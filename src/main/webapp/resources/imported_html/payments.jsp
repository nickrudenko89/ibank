<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="payments_wrapper">
    <c:if test="${userType == clientId}">
        <div>
            <a class="button_payment" id="button_history" href="/history">История платежей</a>
        </div>
        <div>
            <a class="button_payment" href="/transfer">Перевод со счета на счет</a>
        </div>
    </c:if>
    <c:forEach items="${payments}" var="payment">
        <div style="width: 350px">
            <a class="button_payment" href="${url}&id=${payment.id}">${payment.type}</a>
        </div>
    </c:forEach>
</div>