<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="profile_name">
    <table style="height: 50px; width: 765px; text-align: center; font-size: 20px">
        <tr>
            <td style="width: 725px">
                <span>${user.profile.firstName} ${user.profile.lastName}</span>
            </td>
            <td>
                <a href="/editProfile"><img src="/resources/images/edit.jpg" width="40px"/></a>
            </td>
        </tr>
    </table>
</div>
<div id="profile_info">
    <table cellpadding="5">
        <tr>
            <td style="padding-right: 100px">
                <span><b>Дата рождения: </b></span>
            </td>
            <td>
                <span>${birthDate}</span>
            </td>
        </tr>
        <tr>
            <td style="padding-right: 100px">
                <span><b>Номер пасспорта: </b></span>
            </td>
            <td>
                <span>${user.profile.passportNumber}</span>
            </td>
        </tr>
        <tr>
            <td style="padding-right: 100px">
                <span><b>Адрес: </b></span>
            </td>
            <td>
                <span>${user.profile.address}</span>
            </td>
        </tr>
        <tr>
            <td style="padding-right: 100px">
                <span><b>Телефон: </b></span>
            </td>
            <td>
                <span>${user.profile.telephoneNumber}</span>
            </td>
        </tr>
        <tr>
            <td style="padding-right: 100px">
                <span><b>Почта: </b></span>
            </td>
            <td>
                <span>${user.profile.email}</span>
            </td>
        </tr>
    </table>
</div>