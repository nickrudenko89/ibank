<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="profile_name">
    <span>${user.profile.firstName} ${user.profile.lastName}</span>
</div>
<div id="profile_info">
    <form method="POST" action="/saveProfile">
        <table cellpadding="5" width="765px">
            <tr>
                <td>
                    <label for="login_input" class="edit_profile_label"><b>Логин: </b></label>
                </td>
                <td>
                    <input id="login_input" class="edit_profile_input" type="text" name="login" value="${user.login}"
                           disabled>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password_input" class="edit_profile_label"><b>Пароль: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[0]==true}">
                        <input id="password_input" tabindex="1" class="edit_profile_input" type="password"
                               name="password" value placeholder="Введите новый пароль">
                    </c:if>
                    <c:if test="${checkedFields[0]==false}">
                        <input id="password_input" tabindex="1" class="edit_profile_input" style="border-color: red"
                               type="password" name="password" value placeholder="Введите новый пароль">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <c:if test="${checkedFields[0]==true}">
                        <input id="password_confirm_input" tabindex="2" class="edit_profile_input" type="password"
                               name="password_confirm" value placeholder="Подтверждение пароля">
                    </c:if>
                    <c:if test="${checkedFields[0]==false}">
                        <input id="password_confirm_input" tabindex="2" class="edit_profile_input"
                               style="border-color: red" type="password" name="password_confirm" value
                               placeholder="Подтверждение пароля">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="first_name_input" class="edit_profile_label"><b>Имя: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[1]==true}">
                        <input id="first_name_input" tabindex="3" class="edit_profile_input" type="text"
                               name="first_name" value="${user.profile.firstName}">
                    </c:if>
                    <c:if test="${checkedFields[1]==false}">
                        <input id="first_name_input" tabindex="3" class="edit_profile_input" style="border-color: red"
                               type="text" name="first_name" value="${user.profile.firstName}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="last_name_input" class="edit_profile_label"><b>Фамилия: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[2]==true}">
                        <input id="last_name_input" tabindex="4" class="edit_profile_input" type="text" name="last_name"
                               value="${user.profile.lastName}">
                    </c:if>
                    <c:if test="${checkedFields[2]==false}">
                        <input id="last_name_input" tabindex="4" class="edit_profile_input" style="border-color: red"
                               type="text" name="last_name" value="${user.profile.lastName}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="birth_date_input" class="edit_profile_label"><b>Дата рождения: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[3]==true}">
                        <input id="birth_date_input" tabindex="5" class="edit_profile_input" type="date"
                               name="birth_date" value="${birthDate}">
                    </c:if>
                    <c:if test="${checkedFields[3]==false}">
                        <input id="birth_date_input" tabindex="5" class="edit_profile_input" style="border-color: red"
                               type="date" name="birth_date" value="${birthDate}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="passport_input" class="edit_profile_label"><b>Номер паспорта: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[4]==true}">
                        <input id="passport_input" tabindex="6" class="edit_profile_input" type="text"
                               name="passport_number" value="${user.profile.passportNumber}">
                    </c:if>
                    <c:if test="${checkedFields[4]==false}">
                        <input id="passport_input" tabindex="6" class="edit_profile_input" style="border-color: red"
                               type="text" name="passport_number" value="${user.profile.passportNumber}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="address_input" class="edit_profile_label"><b>Адрес: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[5]==true}">
                        <input id="address_input" tabindex="7" class="edit_profile_input" type="text" name="address"
                               value="${user.profile.address}">
                    </c:if>
                    <c:if test="${checkedFields[5]==false}">
                        <input id="address_input" tabindex="7" class="edit_profile_input" style="border-color: red"
                               type="text" name="address" value="${user.profile.address}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="telephone_input" class="edit_profile_label"><b>Телефон: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[6]==true}">
                        <input id="telephone_input" tabindex="8" class="edit_profile_input" type="text"
                               name="telephone_number" value="${user.profile.telephoneNumber}">
                    </c:if>
                    <c:if test="${checkedFields[6]==false}">
                        <input id="telephone_input" tabindex="8" class="edit_profile_input" style="border-color: red"
                               type="text" name="telephone_number" value="${user.profile.telephoneNumber}">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email_input" class="edit_profile_label"><b>Почта: </b></label>
                </td>
                <td>
                    <c:if test="${checkedFields[7]==true}">
                        <input id="email_input" tabindex="9" class="edit_profile_input" type="text" name="email"
                               value="${user.profile.email}">
                    </c:if>
                    <c:if test="${checkedFields[7]==false}">
                        <input id="email_input" tabindex="9" class="edit_profile_input" style="border-color: red"
                               type="text" name="email" value="${user.profile.email}">
                    </c:if>
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
    </form>
</div>