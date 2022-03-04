package ulaval.glo2003.product.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.exceptions.InvalidProductDescriptionException;
import ulaval.glo2003.product.api.exceptions.InvalidProductPriceException;
import ulaval.glo2003.product.api.exceptions.InvalidProductTitleException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductRequestValidatorTest {
  private static final String A_TITLE = "aTitle";
  private static final String A_DESCRIPTION = "aDescription";
  private static final int A_PRICE = 10;

  private final ProductRequest A_VALID_PRODUCT_REQUEST = this.givenAProductRequest(A_TITLE, A_DESCRIPTION, A_PRICE);

  @Mock
  private ConstraintsValidator constraintsValidator;

  private ProductRequestValidator productRequestValidator;

  @BeforeEach
  public void setUp() {
    this.productRequestValidator = new ProductRequestValidator(this.constraintsValidator);
  }

  @Test
  public void givenAProductRequest_whenValidate_thenShouldValidateWithConstraintsValidator() throws GenericException {
    this.productRequestValidator.validate(A_VALID_PRODUCT_REQUEST);

    verify(this.constraintsValidator).validate(A_VALID_PRODUCT_REQUEST);
  }

  @Test
  public void givenAValidProductRequest_whenValidate_thenShouldNotThrow() {
    assertDoesNotThrow(() -> this.productRequestValidator.validate(A_VALID_PRODUCT_REQUEST));
  }

  @Test
  public void givenAnInvalidProductRequestWithAMinus5$Price_whenValidate_thenShouldThrowInvalidProductPriceException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, A_DESCRIPTION, -5);

    assertThrows(InvalidProductPriceException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAValidProductRequestWithA1$Price_whenValidate_thenShouldNotThrowInvalidProductPriceException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, A_DESCRIPTION, 1);

    assertDoesNotThrow(() -> this.productRequestValidator.validate(aProductRequest));
  }


  @Test
  public void givenAnInvalidProductRequestWithAnEmptyTitle_whenValidate_thenShouldThrowInvalidProductTitleException() {
    ProductRequest aProductRequest = this.givenAProductRequest("", A_DESCRIPTION, A_PRICE);

    assertThrows(InvalidProductTitleException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithASpaceOnlyTitle_whenValidate_thenShouldThrowInvalidProductTitleException() {
    ProductRequest aProductRequest = this.givenAProductRequest("  ", A_DESCRIPTION, A_PRICE);

    assertThrows(InvalidProductTitleException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithAnEmptyDescription_whenValidate_thenShouldThrowInvalidProductDescriptionException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, "", A_PRICE);

    assertThrows(InvalidProductDescriptionException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenAnInvalidProductRequestWithASpaceOnlyDescription_whenValidate_thenShouldThrowInvalidProductDescriptionException() {
    ProductRequest aProductRequest = this.givenAProductRequest(A_TITLE, "  ", A_PRICE);

    assertThrows(InvalidProductDescriptionException.class, () -> this.productRequestValidator.validate(aProductRequest));
  }

  @Test
  public void givenANegativeMinPriceAndAValidMaxPrice_whenValidatePrice_thenShouldThrowInvalidProductPrice() {
    int aNegativeMinPrice = -20;
    int aMaxPrice = 20;

    assertThrows(InvalidProductPriceException.class, () -> this.productRequestValidator.validatePrices(aNegativeMinPrice, aMaxPrice));
  }

  @Test
  public void givenAValidMinPriceAndANegativeMaxPrice_whenValidatePrice_thenShouldThrowInvalidProductPrice() {
    int aMinPrice = 20;
    int aNegativeMaxPrice = -20;

    assertThrows(InvalidProductPriceException.class, () -> this.productRequestValidator.validatePrices(aMinPrice, aNegativeMaxPrice));
  }

  @Test
  public void givenANullMinPriceAndValidMaxPrice_whenValidatePrice_thenShouldNotThrowInvalidProductPrice() {
    assertDoesNotThrow(() -> this.productRequestValidator.validatePrices(null, A_PRICE));
  }

  @Test
  public void givenANullMaxPriceAndValidMinPrice_whenValidatePrice_thenShouldNotThrowInvalidProductPrice() {
    assertDoesNotThrow(() -> this.productRequestValidator.validatePrices(A_PRICE, null));
  }

  private ProductRequest givenAProductRequest(String title, String description, int price) {
    ProductRequest productRequest = new ProductRequest();
    productRequest.title = title;
    productRequest.description = description;
    productRequest.suggestedPrice = price;

    return productRequest;
  }
}
