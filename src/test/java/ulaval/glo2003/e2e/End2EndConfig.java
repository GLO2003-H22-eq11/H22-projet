package ulaval.glo2003.e2e;


import java.util.List;

public abstract class End2EndConfig {

  public static final String LOCATION = "Location";
  public static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
  public static final String SELLER_END_POINT = "/sellers";
  public static final String PRODUCTS_END_POINT = "/products";
  public static final String URL_PRODUCTS_END_POINT = "http://localhost:8080/products";
  public static final String URL_SELLERS_END_POINT = "http://localhost:8080/sellers";

  public static final String X_SELLER_ID_HEADERS_PARAMS = "X-Seller-Id";

  public static final String CONTENT_TYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application/json";
  public static final String A_PRODUCT_TITLE = "TITLE";
  public static final String A_PRODUCT_DESCRIPTION = "product description";
  public static final String A_VALID_UUID_FORMAT = "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5";
  public static final String A_VALID_NON_EXISTING_UUID_FORMAT = "d3815eac-bf01-49bf-b570-3a8f376b8c15";
  public static final String A_NON_VALID_UUID_FORMAT = "0ce7f24b";
  public static final int A_VALID_SUGGESTED_PRICE = 10;
  public static final List<String> A_CATEGORIES = List.of("A", "B", "C");

  public static final String AN_ERROR_DESCRIPTION = "description";
  public static final String A_MISSING_PARAMETER = "MISSING_PARAMETER";
  public static final String AN_ITEM_NOT_FOUND = "ITEM_NOT_FOUND";
  public static final String AN_INVALID_PARAMETER = "INVALID_PARAMETER";
  public static final String AN_ERROR = "code";
  public static final String A_MISSING_PARAMETER_DESCRIPTION = "un paramètre (URL, header, JSON, etc.) est manquant";
  public static final String AN_INVALID_PARAMETER_DESCRIPTION = "un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)";
  public static final String AN_ITEM_NOT_FOUND_DESCRIPTION = "un élément est introuvable (id invalide ou inexistant)";

  public static final String A_SELLER_NAME = "Marin Beauchesne";
  public static final String A_BIO = "I love chicken nuggets";
  public static final String A_SELLER_DATE = "1999-07-08";
  public static final String A_BAD_SELLER_DATE = "2006-07-08";

  public static final int CREATED_STATUS_CODE = 201;
  public static final int OK_STATUS_CODE = 200;
  public static final int BAD_STATUS_CODE = 400;
  public static final int NOT_FOUND_STATUS_CODE = 404;
}
