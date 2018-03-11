package Services;

import Entities.UserEntity;
import Utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {
    public boolean checkNewProfile(String password, String passwordConfirm, String firstName, String lastName,
                                   String birthDate, String passportNumber, String address, String telephoneNumber,
                                   String email, Model model) {
        ArrayList<Boolean> checkedFields = new ArrayList<Boolean>();
        if ((password != null && passwordConfirm != null) && !Constants.Strings.EMPTY_STRING.equals(password)) {
            if (password.equals(passwordConfirm)) {
                checkedFields.add(true);
            } else {
                checkedFields.add(false);
            }
        }
        checkedFields.add(checkForEmptyField(firstName));
        checkedFields.add(checkForEmptyField(lastName));
        checkedFields.add(checkForEmptyField(birthDate));
        checkedFields.add(checkForEmptyField(passportNumber));
        checkedFields.add(checkForEmptyField(address));
        checkedFields.add(checkForEmptyField(telephoneNumber));
        //TODO проверка мэйла по регулярке
        checkedFields.add(checkForEmptyField(email));
        model.addAttribute("checkedFields", checkedFields);
        for (Boolean isFilled : checkedFields) {
            if (!isFilled)
                return false;
        }
        return true;
    }

    private boolean checkForEmptyField(String field) {
        return !Constants.Strings.EMPTY_STRING.equals(field);
    }

    public void changeField(UserEntity user, String oldValue, String newValue, int id) {
        if (oldValue != null && newValue != null) {
            if (!oldValue.equals(newValue)) {
                switch (id) {
                    case 1:
                        user.getProfile().setFirstName(newValue);
                        break;
                    case 2:
                        user.getProfile().setLastName(newValue);
                        break;
                    case 3:
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date birthDate = dateFormat.parse(newValue);
                            user.getProfile().setBirthDate(birthDate);
                            break;
                        } catch (Exception ex) {
                            break;
                        }
                    case 4:
                        user.getProfile().setPassportNumber(newValue);
                        break;
                    case 5:
                        user.getProfile().setAddress(newValue);
                        break;
                    case 6:
                        user.getProfile().setTelephoneNumber(newValue);
                        break;
                    case 7:
                        user.getProfile().setEmail(newValue);
                        break;
                }
            }
        }
    }

    public void changePassword(UserEntity user, String newPassword, String newPasswordConfirm) {
        if ((newPassword != null && newPasswordConfirm != null) && (!Constants.Strings.EMPTY_STRING.equals(newPassword))) {
            if (newPassword.equals(newPasswordConfirm) && !newPassword.equals(user.getPassword())) {
                user.setPassword(newPassword);
            }
        }
    }
}
