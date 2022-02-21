package com.rdlab.marketplace.validator;

import com.rdlab.marketplace.domain.Lot;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LotValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Lot.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

  }
}
