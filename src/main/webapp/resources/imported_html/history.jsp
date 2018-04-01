<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function showHideDetails(id) {
        var element = document.getElementById(id);
        if (element.style.display === "none") {
            element.style.display = "block";
        } else {
            element.style.display = "none";
        }
    }
</script>
<div id="history_wrapper">
    <div style="color: #018435; padding: 10px; text-align: center">
        <span>Выписка по счету ${account} с ${startDate} по ${endDate}</span>
    </div>
    <div style="color: black; padding-left: 10px; padding-bottom: 10px; border-bottom: 1px #018435 solid; font-size: 12px">
        <a href="#" class="history_details_button" onclick="showHideDetails('history_date_interval')"><b>Изменить
            даты</b></a>
        <div id="history_date_interval" style="display: none">
            <form method="post" action="/showHistory">
                <input type="hidden" name="history_account" value="${account}"/>
                <table>
                    <tr>
                        <td><label for="history_start_date" class="history_label"><b>От: </b></label></td>
                        <td><input id="history_start_date" class="history_input" style="width: 140px" type="date"
                                   name="history_start_date" value="${startDate}" required/></td>
                        <td><label for="history_end_date" class="history_label"><b>До: </b></label></td>
                        <td><input id="history_end_date" class="history_input" style="width: 140px" type="date"
                                   name="history_end_date" value="${endDate}" required/></td>
                        <td><input class="button_save" id="show_history_button"
                                   style="margin: 0 0 0 0; height: 24px; line-height: 24px; font-size: 12px; border-bottom: none"
                                   type="submit" value="Показать"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <c:forEach items="${history}" var="historyItem">
        <div class="history_item">
            <a class="history_details_button" href="#"
               onclick="showHideDetails(${historyItem.id})"><span>Оплата: ${historyItem.date}</span><span
                    style="float: right">${historyItem.sum} ${historyItem.account.currency}</span></a>
            <div class="history_item_details" id="${historyItem.id}" style="display: none">
                <table style="font-size: 12px">
                    <tr>
                        <td colspan="2"><span>${historyItem.date}</span></td>
                    </tr>
                    <tr>
                        <td><span>Номер платежа:</span></td>
                        <td><span>#${historyItem.id}</span></td>
                    </tr>
                    <tr>
                        <td><span>Получатель:</span></td>
                        <td><span>${historyItem.payment.type}</span></td>
                    </tr>
                    <tr>
                        <td><span>СУММА:</span></td>
                        <td><span>${historyItem.sum} ${historyItem.account.currency}</span></td>
                    </tr>
                </table>
            </div>
        </div>
    </c:forEach>
</div>