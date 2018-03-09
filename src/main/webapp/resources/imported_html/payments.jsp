<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="payments_wrapper">
    <c:forEach items="${payments}" var="payment">
        ${payment.id} ${payment.type}<br>
    </c:forEach>
</div>