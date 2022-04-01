package ulaval.glo2003.product.domain.factory;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.seller.domain.factory.SellerIdFactory;

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
          Double minPrice,
          Double maxPrice
  ) throws GenericException {
    ProductFilters productFilters = new ProductFilters();

    if (stringSellerId != null) {
      productFilters.setSellerId(this.sellerIdFactory.create(stringSellerId));
    }

    if (title != null) {
      productFilters.setTitle(title);
    }

    if (stringCategories.size() >= 1) {
      productFilters.setCategories(this.categoriesFactory.create(stringCategories));
    }

    if (minPrice != null) {
      productFilters.setMinimalPrice(Amount.fromDouble(minPrice));
    }

    if (maxPrice != null) {
      productFilters.setMaximumPrice(Amount.fromDouble(maxPrice));
    }

    return productFilters;
  }
}
