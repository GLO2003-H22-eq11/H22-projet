package ulaval.glo2003.product.api;

import ulaval.glo2003.product.api.exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.product.api.exceptions.InvalidProductPriceException;
import ulaval.glo2003.product.api.exceptions.InvalidProductTitleException;

public class ProductRequestValidator {
  public void validate(ProductRequest productRequest) throws
          InvalidProductPriceException,
          InvalidProductDescriptionException,
          InvalidProductTitleException {
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
}
