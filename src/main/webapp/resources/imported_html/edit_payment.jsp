<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="payments_wrapper">
    <form method="POST" action="/editPaymentConfirm">
        <input type="hidden" name="payment_id" value="${payment.id}">
        <table cellpadding="5" width="390px">
            <tr>
                <td>
                    <label for="payment_type" class="payment_label"><b>Имя: </b></label>
                </td>
                <td>
                    <input id="payment_type" class="payment_input" type="text" name="payment_type"
                           value="${payment.type}" required>
                </td>
            </tr>
        </table>
        <table cellpadding="5" width="390px">
            <tr>
                <td>
                    <input class="button_save"
                           style="margin: 5px 0 -5px 0; display: inline-block; float: left; width: 120px"
                           type="submit" value="Сохранить">
                    <a class="button_cancel"
                       style="margin: 5px 0 -5px 5px; display: inline-block; float: left; width: 120px"
                       href="/paymentCancel">Отменить</a>
                </td>
            </tr>
        </table>
    </form>
</div>