package ulaval.glo2003.seller.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.seller.domain.exceptions.SellerIsMinorException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

  @Test
  public void givenASellerWithAgeOf18_whenGettingIsMajor_thenShouldNotThrow() {
    LocalDate aBirthDateOfA18YearsOld = LocalDate.of(2004, 1, 2);
    Seller seller = new SellerBuilder().withBirthDate(aBirthDateOfA18YearsOld).build();

    assertDoesNotThrow(seller::verifyIfMajor);
  }

  @Test
  public void givenASellerWithAgeOf22_whenGettingIsMajor_thenShouldNotThrow() {
    LocalDate aBirthDateOfA22YearsOld = LocalDate.of(2000, 1, 2);
    Seller seller = new SellerBuilder().withBirthDate(aBirthDateOfA22YearsOld).build();

    assertDoesNotThrow(seller::verifyIfMajor);
  }

  @Test
  public void givenASellerWithAgeOf17_whenGettingIsMajor_thenShouldThrow() {
    LocalDate aBirthDateOfA17YearsOld = LocalDate.of(2005, 1, 2);
    Seller seller = new SellerBuilder().withBirthDate(aBirthDateOfA17YearsOld).build();

    assertThrows(SellerIsMinorException.class, seller::verifyIfMajor);
  }

  @Test
  public void givenASellerWithNoProducts_whenSetProducts_thenShouldSet() {
    Seller seller = new SellerBuilder().build();
    Product aProduct = new ProductBuilder().build();

    seller.setProducts(List.of(aProduct));

    List<Product> actual = seller.getProducts();

    assertEquals(List.of(aProduct), actual);
  }
}
