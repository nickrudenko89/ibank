package Services;

import Daos.ProfileDao;
import Daos.UserDao;
import Entities.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;

    public ProfileEntity getUserProfile(int id) {
        return profileDao.getProfileById(id);
    }

}
