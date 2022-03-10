package com.rdlab.marketplace.validator;

import com.rdlab.marketplace.domain.Item;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ItemValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Item.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "item.title.empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "item.description.empty");
    var item = (Item) target;
    validateTitle(item, errors);
    validateDescription(item, errors);
  }

  private void validateTitle(Item item, Errors errors) {
    if (item.getTitle().length() < ValidatorConst.MIN_LENGTH_ITEM_TITLE
        || item.getTitle().length() > ValidatorConst.MAX_LENGTH_ITEM_TITLE) {
      errors.rejectValue("title", "item.title.invalid.length");
    }
  }

  private void validateDescription(Item item, Errors errors) {
    if (item.getDescription().length() < ValidatorConst.MIN_LENGTH_ITEM_DESCRIPTION
        || item.getDescription().length() > ValidatorConst.MAX_LENGTH_ITEM_DESCRIPTION) {
      errors.rejectValue("description", "item.description.invalid.length");
    }
  }
}
