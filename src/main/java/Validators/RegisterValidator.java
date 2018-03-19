package Validators;

import Entities.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        UserEntity signupForm = (UserEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Username must not be empty.");
        String username = signupForm.getLogin();
        if ((username.length()) > 16) {
            errors.rejectValue("username", "username.tooLong", "Username must not more than 16 characters.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must not be empty.");
/*        if (!(signupForm.getPassword()).equals(signupForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.notValid", "Email address is not valid.");
        String email = signupForm.getEmail();
        if((email.length()) > 10){
            errors.rejectValue("email", "email.tooLong", "Email address too long.");
        }*/
    }
}
