<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        var date = new Date();
        document.getElementById('history_end_date').valueAsDate = date;
        date.setMonth(date.getMonth() - 1);
        document.getElementById('history_start_date').valueAsDate = date;
    });
    function showHideMenu() {
        var element = document.getElementById('history_menu');
        if (element.style.display === "none") {
            element.style.display = "block";
        } else {
            element.style.display = "none";
        }
    }
    $(function () {
        $('form').submit(function () {
            var element = document.getElementById('history_account');
            if (element.selectedIndex === 0) {
                element.style.borderColor = "red";
                return false;
            }
            else {
                return true;
            }
        });
    });
</script>
<div id="payments_wrapper">
    <c:if test="${userType == clientId}">
        <div>
            <a class="button_payment" id="button_history" href="#" onclick="showHideMenu()">История платежей</a>
        </div>
        <div id="history_menu" style="display: none">
            <form method="POST" action="/showHistory">
                <table cellpadding="5">
                    <tr>
                        <td>
                            <label for="history_account" class="history_label"><b>Счет: </b></label>
                        </td>
                        <td colspan="3">
                            <select id="history_account" class="history_input" name="history_account">
                                <option disabled="true" selected="true">Выберите счет</option>
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
                            <label for="history_start_date" class="history_label"><b>От: </b></label>
                        </td>
                        <td>
                            <input id="history_start_date" class="history_input" style="width: 140px" type="date"
                                   name="history_start_date" required/>
                        </td>
                        <td>
                            <label for="history_end_date" class="history_label"><b>До: </b></label>
                        </td>
                        <td>
                            <input id="history_end_date" class="history_input" style="width: 140px" type="date"
                                   name="history_end_date" required/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <input class="button_save" id="show_history_button" style="float: right; margin: 0 0 0 0"
                                   type="submit" value="Показать">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!--
        <div>
        <a class="button_payment" href="/transfer">Перевод со счета на счет</a>
        </div>
        -->
    </c:if>
    <c:forEach items="${payments}" var="payment">
        <div style="width: 350px">
            <a class="button_payment" href="${url}&id=${payment.id}">${payment.type}</a>
        </div>
    </c:forEach>
</div>