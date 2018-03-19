package Validators;

import Forms.ChangeProfileForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChangeProfileValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return ChangeProfileForm.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ChangeProfileForm changeProfileForm = (ChangeProfileForm) o;
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Username must not be empty.");
        //TODO проверка мэйла по регулярке и все остальное
    }
}
