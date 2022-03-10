package com.rdlab.marketplace.validator;

import com.rdlab.marketplace.domain.Lot;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class LotValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Lot.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stopDate", "lot.stopDate.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startPrice", "lot.startPrice.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidInc", "lot.bidInc.empty");
    var lot = (Lot) target;
    validateStopDate(lot, errors);
    validateStartPrice(lot, errors);
    validateBidInc(lot, errors);
  }

  private void validateStopDate(Lot lot, Errors errors) {
    if (lot.getStopDate().isBefore(LocalDate.now())) {
      errors.rejectValue("stopDate", "lot.stopDate.is.before.current.date");
    }
  }

  private void validateStartPrice(Lot lot, Errors errors) {
    if (lot.getStartPrice() <= 0) {
      errors.rejectValue("startPrice", "lot.startPrice.less.or.equals.null");
    }
  }

  private void validateBidInc(Lot lot, Errors errors) {
    if (lot.getBidInc() < lot.getStartPrice()) {
      errors.rejectValue("bidInc", "lot.bidInc.less.startPrice");
    } else if (lot.getBidInc() <= 0) {
      errors.rejectValue("bidInc", "lot.bidInc.less.or.equals.null");
    }
  }
}
