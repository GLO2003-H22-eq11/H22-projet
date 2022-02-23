package ulaval.glo2003.endtoend;

import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.seller.api.SellerRequest;

import java.util.List;

public abstract class EndToEndConfig {

  public final String SELLERS_SLASH = "sellers/";
  public final String LOCATION = "Location";
  public final String URL_PRODUCTS_END_POINT = "http://localhost:8080/products/";
  public final String URL_SELLERS_END_POINT = "http://localhost:8080/sellers/";
  public final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
  public final String SELLER_END_POINT = "/sellers";
  public final String PRODUCTS_END_POINT = "/products";

  public final String X_SELLER_ID_HEADERS_PARAMS = "X-Seller-Id";

  public final String A_PRODUCT_TITLE = "TITLE";
  public final String A_PRODUCT_DESCRIPTION = "description";
  public final int A_VALID_SUGGESTED_PRICE = 10;
  public final List<String> A_CATEGORIES = List.of("A", "B", "C");

  public final String A_SELLER_NAME = "Marin Beauchesne";
  public final String A_BIO = "I love chicken nuggets";
  public final String A_SELLER_DATE = "1999-07-08";

  public final int NO_CONTENT_STATUS_CODE = 201;
  public final int GET_STATUS_CODE = 200;

  public SellerRequest givenAValidSellerRequest() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = this.A_SELLER_NAME;
    sellerRequest.bio = this.A_BIO;
    sellerRequest.birthDate = this.A_SELLER_DATE;
    return sellerRequest;
  }

  public ProductRequest givenAProductRequest() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = this.A_PRODUCT_DESCRIPTION;
    productRequest.title = this.A_PRODUCT_TITLE;
    productRequest.suggestedPrice = this.A_VALID_SUGGESTED_PRICE;
    productRequest.categories = this.A_CATEGORIES;

    return productRequest;
  }


}
