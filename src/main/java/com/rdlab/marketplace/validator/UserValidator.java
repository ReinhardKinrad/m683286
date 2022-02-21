package com.rdlab.marketplace.validator;

import com.rdlab.marketplace.domain.User;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty");
    var user = (User) target;
    validateUsername(user, errors);
    validatePassword(user, errors);
    validateEmail(user, errors);
    validateFirstname(user, errors);
    validateLastname(user, errors);
  }

  private void validateUsername(User user, Errors errors) {
    if (user.getUsername().length() < 5 || user.getUsername().length() > 30) {
      errors.rejectValue("username", "username.length.error");
    }
  }

  private void validatePassword(User user, Errors errors) {
    if (user.getPassword().length() < 8 || user.getPassword().length() > 100) {
      errors.rejectValue("password", "password.length.error");
    }
  }

  private void validateEmail(User user, Errors errors) {
    if (!(EmailValidator.getInstance().isValid(user.getEmail())
        && (user.getEmail().length() < 8
        || user.getEmail().length() > 50))) {
      errors.rejectValue("email", "email.not.valid");
    }
  }

  private void validateFirstname(User user, Errors errors) {
    if (user.getFirstname().length() < 5 || user.getFirstname().length() > 25) {
      errors.rejectValue("firstname", "firstname.length.error");
    }
  }

  private void validateLastname(User user, Errors errors) {
    if (user.getLastname().length() < 5 || user.getLastname().length() > 35) {
      errors.rejectValue("lastname", "lastname.length.error");
    }
  }
}
