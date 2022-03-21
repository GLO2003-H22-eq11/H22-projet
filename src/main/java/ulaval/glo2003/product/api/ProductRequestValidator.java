package ulaval.glo2003.product.api;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.product.api.exceptions.InvalidProductPriceException;
import ulaval.glo2003.product.api.exceptions.InvalidProductTitleException;
import ulaval.glo2003.product.api.request.ProductRequest;

public class ProductRequestValidator {
  private final ConstraintsValidator constraintsValidator;

  public ProductRequestValidator(ConstraintsValidator constraintsValidator) {
    this.constraintsValidator = constraintsValidator;
  }

  public void validate(ProductRequest productRequest) throws GenericException {
    this.constraintsValidator.validate(productRequest);
    if (productRequest.suggestedPrice < 1) {
      throw new InvalidProductPriceException();
    }
    if (productRequest.description.isBlank()) {
      throw new InvalidProductDescriptionException();
    }
    if (productRequest.title.isBlank()) {
      throw new InvalidProductTitleException();
    }
  }

  public void validatePrices(Integer minPrice, Integer maxPrice) throws InvalidProductPriceException {
    if (minPrice != null && minPrice < 0 || maxPrice != null && maxPrice < 0) {
      throw new InvalidProductPriceException();
    }
  }
}
