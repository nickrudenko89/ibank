package Services;

import Daos.UserDao;
import Entities.UserEntity;
import Enums.UserTypeEnum;
import Forms.ChangeProfileForm;
import Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getLoggedUser(int id) {
        return userDao.getUserById(id);
    }

    public UserEntity logUser(String login, String password) {
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public void saveChangesToProfile(ChangeProfileForm changeProfileForm, UserEntity user) {
        if (!Constants.Strings.EMPTY_STRING.equals(changeProfileForm.getPassword())) {
            user.setPassword(changeProfileForm.getPassword());
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        user.getProfile().setFirstName(changeProfileForm.getFirstName());
        user.getProfile().setLastName(changeProfileForm.getLastName());
        try {
            user.getProfile().setBirthDate(dateFormat.parse(changeProfileForm.getBirthDate().split(" ")[0]));
        } catch (Exception ex) {
        }
        user.getProfile().setPassportNumber(changeProfileForm.getPassportNumber());
        user.getProfile().setAddress(changeProfileForm.getAddress());
        user.getProfile().setTelephoneNumber(changeProfileForm.getTelephoneNumber());
        user.getProfile().setEmail(changeProfileForm.getEmail());
        userDao.update(user);
    }

    public UserTypeEnum getUserTypeByIndex(int userTypeIndex) {
        for (UserTypeEnum userType : UserTypeEnum.values()) {
            if ((userType.ordinal() + 1) == userTypeIndex)
                return userType;
        }
        return UserTypeEnum.CLIENT;
    }
}
