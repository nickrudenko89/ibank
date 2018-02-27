<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/resources/js/timer.js"></script>
    <link href="<c:url value="/resources/css/styles.css"/>" type="text/css" rel="stylesheet">
    <title>Система «Интернет-банкинг»</title>
</head>
<body onload="startTimer()">
<div id="index_wrapper">
    <div id="index_header">
        <div class="logo">
            <table width="400">
                <tr>
                    <td width="79">
                        <img src="/resources/images/logo.png" width="76" height="76">
                    </td>
                    <td width="320">
                        <label id="app_label">Интернет-банкинг</label>
                    </td>
                </tr>
            </table>
        </div>
        <div class="header_right">
            <div class="time">
                <span class="user_session"> До окончания сеанса <span id="timer">00:10:01</span></span>
            </div>
            <div class="profile">
                <a class="button_profile" href="/profile">Профиль</a>
            </div>
            <div class="logout">
                <a class="button_exit" href="/logout">Выход</a>
            </div>
        </div>
    </div>
    <div>
        <hr>
    </div>
</div>
</body>
</html>
