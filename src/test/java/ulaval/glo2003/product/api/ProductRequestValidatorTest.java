package ulaval.glo2003.product.api;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.product.api.exceptions.InvalidProductPriceException;
import ulaval.glo2003.product.api.exceptions.InvalidProductTitleException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRequestValidatorTest {
  private static final String A_TITLE = "aTitle";
  private static final String A_DESCRIPTION = "aDescription";
  private static final int A_PRICE = 10;

  private final ProductRequestValidator productRequestValidator = new ProductRequestValidator();

  @Test
  public void givenAValidProductRequest_whenCreateProduct_thenShouldNotThrow() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, A_DESCRIPTION, A_PRICE);

    assertDoesNotThrow(() -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithAMinus5$Price_whenCreateProduct_thenShouldThrowInvalidProductPriceException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, A_DESCRIPTION, -5);

    assertThrows(InvalidProductPriceException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAValidProductRequestWithA1$Price_whenCreateProduct_thenShouldNotThrowInvalidProductPriceException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, A_DESCRIPTION, 1);

    assertDoesNotThrow(() -> this.productRequestValidator.validate(aProductRequest));
  }


  @Test
  public void givenAnInvalidProductRequestWithAnEmptyTitle_whenCreateProduct_thenShouldThrowInvalidProductTitleException() {
    ProductRequest aProductRequest = this.givenAProductRequest("", A_DESCRIPTION, A_PRICE);

    assertThrows(InvalidProductTitleException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithASpaceOnlyTitle_whenCreateProduct_thenShouldThrowInvalidProductTitleException() {
    ProductRequest aProductRequest = this.givenAProductRequest("  ", A_DESCRIPTION, A_PRICE);

    assertThrows(InvalidProductTitleException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithAnEmptyDescription_whenCreateProduct_thenShouldThrowInvalidProductDescriptionException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, "", A_PRICE);

    assertThrows(InvalidProductDescriptionException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithASpaceOnlyDescription_whenCreateProduct_thenShouldThrowInvalidProductDescriptionException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, "  ", A_PRICE);

    assertThrows(InvalidProductDescriptionException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  private ProductRequest givenAProductRequest(String title, String description, int price) {
    ProductRequest productRequest = new ProductRequest();
    productRequest.title = title;
    productRequest.description = description;
    productRequest.suggestedPrice = price;

    return productRequest;
  }

}