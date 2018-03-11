<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function changeCurrency() {
        var element = document.getElementById('payment_account');
        var currency = element.options[element.selectedIndex].value.substring(0, 3);
        document.getElementById('payment_currency').innerHTML = currency;
    }
</script>
<div id="payments_wrapper" style="width: 424px">
    <form method="POST" action="/paymentConfirm">
        <input type="hidden" name="previous_request_uri" value="${requestUri}">
        <input type="hidden" name="payment_id" value="${payment.id}">
        <table cellpadding="5" width="390px">
            <tr>
                <td>
                    <label for="payment_type" class="payment_label"><b>Получатель: </b></label>
                </td>
                <td>
                    <input id="payment_type" class="payment_input" type="text" name="payment_type"
                           value="${payment.type}" disabled>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="payment_account" class="payment_label"><b>Счет: </b></label>
                </td>
                <td>
                    <select id="payment_account" class="payment_input" name="payment_account"
                            onchange="changeCurrency()">
                        <option disabled selected>Выберите счет</option>
                        <c:forEach items="${accounts}" var="account">
                            <c:if test="${account.status == openedAccountStatus}">
                                <option>${account.accountNumber}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="payment_sum" class="payment_label"><b>Сумма: </b></label>
                </td>
                <td>
                    <span class="payment_currency" id="payment_currency"></span>
                    <input id="payment_sum" class="payment_input" style="width: 250px" type="text" name="payment_sum"
                           value placeholder="Введите сумму платежа">
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
                    <div style="font-family: Arial, sans-serif; font-size: 14px; color: red; margin-left: 255px">
                        <span>${error}</span>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>