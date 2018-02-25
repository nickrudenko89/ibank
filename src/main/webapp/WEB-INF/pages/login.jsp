<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--<link rel="SHORTCUT ICON" href="/favicon.ico" type="image/x-icon">-->
    <link href="<c:url value="/resources/css/styles.css"/>" type="text/css" rel="stylesheet">
    <title>iBank login</title>
</head>
<body>
<div id="login_wrapper">
    <div id="login_header">
        <div class="logo">
            <table width="400">
                <tr width="400">
                    <td width="79">
                        <img src="/resources/images/logo.png" width="76" height="76">
                    </td>
                    <td width="320">
                        <label id="app_label">Интернет-банкинг</label>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="login_body">
        <div style="height: 20px; margin-top: 5px; margin-left: 79px">
            <label id="error_label">${errorMsg}</label>
        </div>
        <div style="margin-left: 79px">
            <form method="POST" action="/login" class="login_form">
                <input tabindex="1" class="input" id="user_input" type="text" name="login" value placeholder="Логин">
                <br>
                <input tabindex="2" class="input" id="password_input" type="password" name="password" value placeholder="Пароль">
                <br>
                <input tabindex="3" class="button" type="submit" value="Войти">
            </form>
        </div>
    </div>
</div>
</body>
</html>
