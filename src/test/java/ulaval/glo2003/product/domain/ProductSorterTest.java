package ulaval.glo2003.product.domain;

import jakarta.ws.rs.core.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductSorter;
import ulaval.glo2003.product.domain.product.productFilter.ProductFilter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductSorterTest {

  public final String A_PRODUCT_TITLE = "MARIN_TITLE";
  private ProductBuilder productBuilder = new ProductBuilder();

  private final Product A_PRODUCT_WITH_NOTHING = productBuilder.build();
  private final Product A_PRODUCT_WITH_TITLE = productBuilder.withTitle(A_PRODUCT_TITLE).build();

  private ProductSorter productSorter;


  @BeforeEach
  public void setUp() {
    this.productSorter = new ProductSorter();
  }

  @Test
  public void givenTwoProductsWithOneWithATitleAndAProductFilterWithTitle_whenSort_thenShouldReturnOnlyOneProduct() {
    List<Product> products = new LinkedList<>();
    products.add(A_PRODUCT_WITH_TITLE);
    products.add(A_PRODUCT_WITH_NOTHING);
    ProductFilter productFilter = givenAnProductFilterWithTitle();

    List<Product> actualProducts = this.productSorter.sortProduct(productFilter, products);

    assertEquals(actualProducts.size(), 1);
    assertEquals(actualProducts.get(0), A_PRODUCT_WITH_TITLE);
  }

  public ProductFilter givenAnProductFilterWithTitle() {
    ProductFilter productFilter = new ProductFilter();
    productFilter.setTitle(A_PRODUCT_TITLE);
    return productFilter;
  }

}
