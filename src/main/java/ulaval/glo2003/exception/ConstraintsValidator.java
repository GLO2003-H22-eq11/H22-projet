package ulaval.glo2003.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;

import java.util.Set;

public class ConstraintsValidator {
  private final Validator validator;

  public ConstraintsValidator() {
    ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
            .buildValidatorFactory();
    this.validator = validatorFactory.getValidator();
  }

  public void validate(GenericRequest genericRequest) throws GenericException {
    Set<ConstraintViolation<GenericRequest>> violations = validator.validate(genericRequest);

    if (!violations.isEmpty() || genericRequest == null) {
      throw new MissingParameterException();
    }
  }

}
