<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="request">
    <c:if test="${action == 'open'}">
        <form action="/createNewAccount" method="post">
            <label for="currency_input">Открытие счета</label>
            <select id="currency_input" class="currency_input" name="currency">
                <option disabled selected>Выберите вылюту счета</option>
                <c:forEach items="${currencies}" var="currency">
                    <option>${currency}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Подать заявку" class="button_enter">
        </form>
    </c:if>
    <c:if test="${action == 'close'}">
        <span>${message}</span>
    </c:if>
</div>
