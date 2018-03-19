package Utils;

import Entities.UserEntity;
import Forms.ChangeProfileForm;

import java.text.SimpleDateFormat;

public class UserUtil {
    public static  String getUserName (UserEntity user) {
        return user.getProfile().getFirstName() + " " + user.getProfile().getLastName();
    }

    public static ChangeProfileForm setUserToChangeProfileForm(UserEntity user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ChangeProfileForm changeProfileForm = new ChangeProfileForm();
        changeProfileForm.setLogin(user.getLogin());
        changeProfileForm.setPassword("");
        changeProfileForm.setPasswordConfirm("");
        changeProfileForm.setFirstName(user.getProfile().getFirstName());
        changeProfileForm.setLastName(user.getProfile().getLastName());
        changeProfileForm.setAddress(user.getProfile().getAddress());
        changeProfileForm.setBirthDate(dateFormat.format(user.getProfile().getBirthDate()));
        changeProfileForm.setEmail(user.getProfile().getEmail());
        changeProfileForm.setPassportNumber(user.getProfile().getPassportNumber());
        changeProfileForm.setTelephoneNumber(user.getProfile().getTelephoneNumber());
        return changeProfileForm;
    }
}
