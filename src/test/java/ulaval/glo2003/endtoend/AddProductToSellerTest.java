package ulaval.glo2003.endtoend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.seller.api.SellerRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductToSellerTest {

  private final String LOCATION = "Location";
  private final String LOCATION_PRODUCTS_URL = "http://localhost:8080/products/";
  private final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

  private final String SELLER_END_POINT = "/sellers";
  private final String END_POINT = "/products";

  private final String X_SELLER_ID_HEADERS_PARAMS = "X-Seller-Id";

  private final String A_PRODUCT_TITLE = "TITLE";
  private final String A_PRODUCT_DESCRIPTION = "description";
  private final int A_VALID_SUGGESTED_PRICE = 10;
  private final List<String> A_CATEGORIES = List.of("A", "B", "C");

  private final String A_SELLER_NAME = "Marin Beauchesne";
  private final String A_BIO = "I love chicken nuggets";
  private final String A_SELLER_DATE = "1999-07-08";

  private final int OK_STATUS_CODE = 201;

  @Test
  public void givenAProductRequest_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    String sellerId = addSellerAndGetSellerId();

    ProductRequest productRequest = givenAProductRequest();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header(X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(this.END_POINT)
            .then()
            .statusCode(this.OK_STATUS_CODE);

  }

  @Test
  public void givenAProductRequest_whenAddProduct_thenShouldReturnAProductId() {
    String sellerId = addSellerAndGetSellerId();

    ProductRequest productRequest = givenAProductRequest();

    String URL_LOCATION = RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header(X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(this.END_POINT).getHeader(LOCATION);

    String productId = URL_LOCATION.split("products/")[1];

    assertTrue(URL_LOCATION.contains(LOCATION_PRODUCTS_URL));
    assertTrue(productId.matches(UUID_REGEX));
  }

  private ProductRequest givenAProductRequest() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return productRequest;
  }


  private String addSellerAndGetSellerId() {
    SellerRequest sellerRequest = givenAValidSellerRequest();

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    String URL_LOCATION  = request.post(this.SELLER_END_POINT).getHeaders().getValue("Location");
    return URL_LOCATION.split("sellers/")[1];
  }

  private SellerRequest givenAValidSellerRequest() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }


}
