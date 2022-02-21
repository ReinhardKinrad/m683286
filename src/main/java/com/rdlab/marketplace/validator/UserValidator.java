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
    User user = (User) target;
    if (!(EmailValidator.getInstance().isValid(user.getEmail())
        && (user.getEmail().length() < 8
        || user.getEmail().length() > 50))) {
      errors.rejectValue("email", "email.not.valid");
    }
  }
}
