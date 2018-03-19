<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="profile_name">
    <span>${userName}</span>
</div>
<div id="profile_info">
    <form:form method="POST" action="/saveProfile" commandName="changeProfileForm">
        <table cellpadding="5" width="765px">
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="login"/></div></td></tr>
            <tr>
                <td>
                    <label for="login_input" class="edit_profile_label"><b>Логин: </b></label>
                </td>
                <td>
                    <form:input path="login" id="login_input" class="edit_profile_input" type="text" name="login"
                                disabled="true"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="password"/></div></td></tr>
            <tr>
                <td>
                    <label for="password_input" class="edit_profile_label"><b>Пароль: </b></label>
                </td>
                <td>
                    <form:input path="password" id="password_input" tabindex="1" class="edit_profile_input"
                                type="password" name="password" placeholder="Введите новый пароль"/>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <form:input path="passwordConfirm" id="password_confirm_input" tabindex="2"
                                class="edit_profile_input" type="password" name="password_confirm"
                                placeholder="Подтверждение пароля"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="firstName"/></div></td></tr>
            <tr>
                <td>
                    <label for="first_name_input" class="edit_profile_label"><b>Имя: </b></label>
                </td>
                <td>
                    <form:input path="firstName" id="first_name_input" tabindex="3" class="edit_profile_input"
                                type="text" name="first_name"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="lastName"/></div></td></tr>
            <tr>
                <td>
                    <label for="last_name_input" class="edit_profile_label"><b>Фамилия: </b></label>
                </td>
                <td>
                    <form:input path="lastName" id="last_name_input" tabindex="4" class="edit_profile_input"
                                type="text" name="last_name"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="birthDate"/></div></td></tr>
            <tr>
                <td>
                    <label for="birth_date_input" class="edit_profile_label"><b>Дата рождения: </b></label>
                </td>
                <td>
                    <form:input path="birthDate" id="birth_date_input" tabindex="5" class="edit_profile_input"
                                type="date" name="birth_date"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="passportNumber"/></div></td></tr>
            <tr>
                <td>
                    <label for="passport_input" class="edit_profile_label"><b>Номер паспорта: </b></label>
                </td>
                <td>
                    <form:input path="passportNumber" id="passport_input" tabindex="6" class="edit_profile_input"
                                type="text" name="passport_number"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="address"/></div></td></tr>
            <tr>
                <td>
                    <label for="address_input" class="edit_profile_label"><b>Адрес: </b></label>
                </td>
                <td>
                    <form:input path="address" id="address_input" tabindex="7" class="edit_profile_input"
                                type="text" name="address"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="telephoneNumber"/></div></td></tr>
            <tr>
                <td>
                    <label for="telephone_input" class="edit_profile_label"><b>Телефон: </b></label>
                </td>
                <td>
                    <form:input path="telephoneNumber" id="telephone_input" tabindex="8" class="edit_profile_input"
                                type="text" name="telephone_number"/>
                </td>
            </tr>
            <tr><td></td><td><div class="edit_profile_field_error"><form:errors path="email"/></div></td></tr>
            <tr>
                <td>
                    <label for="email_input" class="edit_profile_label"><b>Почта: </b></label>
                </td>
                <td>
                    <form:input path="email" id="email_input" tabindex="9" class="edit_profile_input" type="text"
                                name="email"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input tabindex="10" class="button_save" type="submit" value="Сохранить">
                </td>
                <td>
                    <a class="button_cancel" href="/cancelProfile">Отменить</a>
                </td>
            </tr>
        </table>
    </form:form>
</div>