package ulaval.glo2003.e2e;

import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.seller.api.SellerRequest;

import java.util.List;

public abstract class End2EndConfig {

  public static final String URL = "http://localhost:8080/";
  public static final String SELLERS_SLASH = "sellers/";
  public static final String LOCATION = "Location";
  public static final String URL_PRODUCTS_END_POINT = URL + "products/";
  public static final String URL_SELLERS_END_POINT = URL + "sellers/";
  public static final String URL_PRODUCTS_END_POINT_WITHOUT_SLASH = "http://localhost:8080/products";
  public static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
  public static final String SELLER_END_POINT = "/sellers";
  public static final String PRODUCTS_END_POINT = "/products";

  public static final String X_SELLER_ID_HEADERS_PARAMS = "X-Seller-Id";

  public static final String CONTENT_TYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application/json";
  public static final String A_RANDOM_TITLE = "random";
  public static final String A_RANDOM_ID = "random";
  public static final String A_RANDOM_MIN = "random";
  public static final String A_RANDOM_MAX = "random";
  public static final String A_RANDOM_CATEGORIE = "random";
  public static final String A_PRODUCT_TITLE = "TITLE";
  public static final String A_PRODUCT_DESCRIPTION = "description";
  public static final int A_VALID_SUGGESTED_PRICE = 10;
  public static final List<String> A_CATEGORIES = List.of("A", "B", "C");

  public static final String A_SELLER_NAME = "Marin Beauchesne";
  public static final String A_BIO = "I love chicken nuggets";
  public static final String A_SELLER_DATE = "1999-07-08";

  public static final int CREATED_STATUS_CODE = 201;
  public static final int OK_STATUS_CODE = 200;

  public SellerRequest givenAValidSellerRequest() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }

  public ProductRequest givenAProductRequest() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return productRequest;
  }
}
