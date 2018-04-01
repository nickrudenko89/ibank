<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    $( document ).ready(function() {
        var element = document.getElementById('payment_account');
        if (element.selectedIndex !== 0) {
            var currency = element.options[element.selectedIndex].value.substring(0, 3);
            document.getElementById('payment_currency').innerHTML = currency;
        }
    });
    function changeCurrency() {
        var element = document.getElementById('payment_account');
        var currency = element.options[element.selectedIndex].value.substring(0, 3);
        document.getElementById('payment_currency').innerHTML = currency;
    }
</script>
<div id="payments_wrapper" style="width: 424px">
    <form:form method="POST" action="/paymentConfirm" commandName="makePaymentForm">
        <form:input path="id" type="hidden" name="payment_id"/>
        <table cellpadding="5" width="390px">
            <tr>
                <td>
                    <label for="payment_type" class="payment_label"><b>Получатель: </b></label>
                </td>
                <td>
                    <form:input path="type" id="payment_type" class="payment_input" type="text" name="payment_type"
                                disabled="true"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="do_payment_field_error"><form:errors path="account"/></div>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="payment_account" class="payment_label"><b>Счет: </b></label>
                </td>
                <td>
                    <form:select path="account" id="payment_account" class="payment_input" name="payment_account"
                                 onchange="changeCurrency()">
                        <form:option value="Выберите счет" disabled="true" selected="true"/>
                        <c:forEach items="${accounts}" var="account">
                            <c:if test="${account.status == openedAccountStatus}">
                                <form:option value="${account.accountNumber}"/>
                            </c:if>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="do_payment_field_error"><form:errors path="sum"/></div>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="payment_sum" class="payment_label"><b>Сумма: </b></label>
                </td>
                <td>
                    <span class="payment_currency" id="payment_currency"></span>
                    <form:input path="sum" pattern="\d+(\.\d{1,2})?" id="payment_sum" class="payment_input"
                                style="width: 250px" type="text" name="payment_sum"
                                placeholder="Введите сумму платежа" required="required"/>
                </td>
            </tr>
        </table>
        <table cellpadding="5" width="390px">
            <tr>
                <td>
                    <input class="button_save"
                           style="margin: 5px 0 -5px 0; display: inline-block; float: left; width: 120px"
                           type="submit" value="Оплатить">
                    <a class="button_cancel"
                       style="margin: 5px 0 -5px 5px; display: inline-block; float: left; width: 120px"
                       href="/paymentCancel">Отменить</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>