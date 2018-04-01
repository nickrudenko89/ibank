<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="cheque_wrapper">
    <div id="cheque_header">
        <span>Интернет-банк</span><br>
        <span><b>ЧЕК</b></span><br>
    </div>
    <div id="cheque_body">
        <hr style="width: 390px; color: black; background-color: black">
        <span>${chequeDate}</span><br>
        <span>Чек № ${cheque.id}</span><br>
        <span>Получатель: ${cheque.payment.type}</span><br>
        <span>Счет плательщика: ${account.accountNumber}</span><br>
        <span style="font-size: 16px; float: right">СУММА    ${cheque.sum} ${account.currency}</span><br>
        <hr style="width: 390px; color: black; background-color: black">
    </div>
    <div id="cheque_footer">
        <span style="float: left">Остаток после операции: ${account.balance}</span><br><br>
        <span>Спасибо, что выбрали наш банк!</span><br>
        <span>${chequeDate}</span>
    </div>
    <div>
    <a class="button_apply"
       style="margin: 5px 0 -5px 5px"
       href="/payments">Готово</a>
    </div>
</div>