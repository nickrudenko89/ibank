<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iBank login</title>
</head>
<body>
    <form method="POST" action="/login">
        <input type="text" name="login" value placeholder="Login">
        <br>
        <input type="password" name="password" value placeholder="Password">
        <br>
        <input type="submit" value="Log In">
    </form>
</body>
</html>
