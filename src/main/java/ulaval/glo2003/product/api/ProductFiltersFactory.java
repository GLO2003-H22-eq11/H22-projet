package ulaval.glo2003.product.api;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.util.List;

public class ProductFiltersFactory {

  private final SellerIdFactory sellerIdFactory;
  private final CategoriesFactory categoriesFactory;

  public ProductFiltersFactory(SellerIdFactory sellerIdFactory, CategoriesFactory categoriesFactory) {
    this.sellerIdFactory = sellerIdFactory;
    this.categoriesFactory = categoriesFactory;
  }

  public ProductFilters create(
          String stringSellerId,
          String title,
          List<String> stringCategories,
          int minPrice,
          int maxPrice
  ) throws GenericException {
    ProductFilters productFilters = new ProductFilters();

    if (!stringSellerId.isBlank()) {
      productFilters.setSellerId(this.sellerIdFactory.create(stringSellerId));
    }

    if (!title.isBlank()) {
      productFilters.setTitle(title);
    }

    if (stringCategories.size() >= 1) {
      productFilters.setCategories(this.categoriesFactory.create(stringCategories));
    }

    if (minPrice > 0) {
      productFilters.setMinimalPrice(Amount.fromInt(minPrice));
    }

    if (maxPrice > 0) {
      productFilters.setMaximumPrice(Amount.fromInt(maxPrice));
    }

    return productFilters;
  }
}
