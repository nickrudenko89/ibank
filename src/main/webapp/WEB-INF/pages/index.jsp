<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="/resources/js/timer.js"></script>
    <link rel="SHORTCUT ICON" href="/resources/icons/favicon.ico" type="image/x-icon">
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
            <div class="logout">
                <a class="button_exit" href="/logout">Выход</a>
            </div>
        </div>
    </div>
    <div>
        <hr>
    </div>
    <div class="left_menu">
        <table width="100%">
            <tr>
                <td>
                    <p><span>Добро пожаловать,</span></p>
                    <span style="font-size: 14px; font-weight: bold; text-transform: uppercase">${userName}</span>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="button_menu" style="margin-top: 10px" href="/accounts"><span
                            class="button_text">Счета</span></a>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="button_menu" href="/payments"><span class="button_text">Платежи</span></a>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="button_menu" href="/profile"><span class="button_text">Профиль</span></a>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <iframe src="http://www.nbrb.by/publications/wmastersd.asp?lan=ru&datatype=0&fnt=Verdana&fntsize=12px&fntcolor=black
&lnkcolor=black&bgcolor=white&brdcolor=green" width=250 height=95 scrolling=no frameborder=0></iframe>
                </td>
            </tr>
        </table>
    </div>
    <div class="index_content" id="main_content">
        <c:if test="${path==null}">
            <c:set var="path" value="/resources/imported_html/blank.html"/>
        </c:if>
        <jsp:include page="${path}"/>
    </div>
</div>
</body>
</html>
