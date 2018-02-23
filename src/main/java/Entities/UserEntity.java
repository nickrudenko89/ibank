package Entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login", length = 45, unique = true, nullable = false)
    private String login;

    @Column(name = "password", length = 45, nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    private int userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
