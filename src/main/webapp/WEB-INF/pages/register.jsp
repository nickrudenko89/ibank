
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
    <title>Sign Up</title>
    <style type="text/css">
        span.error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Sign Up</h1>

<form:form method="post" commandName="user">
    <form:hidden path="id" />
    <table>
        <tr>
            <td>Username:</td>
            <td><form:input path="login" /></td>
            <td><span class="error"><form:errors path="login" /></span></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td><form:password path="password" /></td>
            <td><span class="error"><form:errors path="password" /></span></td>
        </tr>

        <form:hidden path="userType" value="2" />

        <tr>
            <td>Username:</td>
            <td><form:input path="profile.firstName" /></td>
            <td></td>
        </tr>

        <tr>
            <td colspan="3"><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form:form>

<a href="${pageContext.request.contextPath}/" title="Home">Home</a>
</body>
</html>