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
    checkEmptyOrWhitespace(errors);
    var user = (User) target;
    validateUsername(user, errors);
    validatePassword(user, errors);
    validateEmail(user, errors);
    validateFirstname(user, errors);
    validateLastname(user, errors);
  }

  private void checkEmptyOrWhitespace(Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty");
  }

  private void validateUsername(User user, Errors errors) {
    if (user.getUsername().length() < ValidatorConst.MIN_LENGTH_USERNAME
        || user.getUsername().length() > ValidatorConst.MAX_LENGTH_USERNAME) {
      errors.rejectValue("username", "username.length.error");
    }
  }

  private void validatePassword(User user, Errors errors) {
    if (user.getPassword().length() < ValidatorConst.MIN_LENGTH_PASSWORD
        || user.getPassword().length() > ValidatorConst.MAX_LENGTH_PASSWORD) {
      errors.rejectValue("password", "password.length.error");
    }
  }

  private void validateEmail(User user, Errors errors) {
    if (!(EmailValidator.getInstance().isValid(user.getEmail())
        || (user.getEmail().length() < ValidatorConst.MIN_LENGTH_EMAIL
        || user.getEmail().length() > ValidatorConst.MAX_LENGTH_EMAIL))) {
      errors.rejectValue("email", "email.not.valid");
    }
  }

  private void validateFirstname(User user, Errors errors) {
    if (user.getFirstname().length() < ValidatorConst.MIN_LENGTH_FIRSTNAME
        || user.getFirstname().length() > ValidatorConst.MAX_LENGTH_FIRSTNAME) {
      errors.rejectValue("firstname", "firstname.length.error");
    }
  }

  private void validateLastname(User user, Errors errors) {
    if (user.getLastname().length() < ValidatorConst.MIN_LENGTH_LASTNAME
        || user.getLastname().length() > ValidatorConst.MAX_LENGTH_LASTNAME) {
      errors.rejectValue("lastname", "lastname.length.error");
    }
  }
}
