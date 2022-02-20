package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.AmountFactory;
import ulaval.glo2003.product.domain.product.Categories;
import ulaval.glo2003.product.domain.product.CategoriesFactory;
import ulaval.glo2003.product.domain.product.ProductFilters;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.util.List;

public class ProductFiltersFactory {

  private final SellerIdFactory sellerIdFactory;
  private final AmountFactory amountFactory;
  private final CategoriesFactory categoriesFactory;

  public ProductFiltersFactory(SellerIdFactory sellerIdFactory,
                               AmountFactory amountFactory,
                               CategoriesFactory categoriesFactory) {
    this.sellerIdFactory = sellerIdFactory;
    this.amountFactory = amountFactory;
    this.categoriesFactory = categoriesFactory;
  }

  public ProductFilters create(
          String stringSellerId, String title, List<String> stringCategories, int minPrice, int maxPrice
  ) {
    SellerId sellerId = null;
    Categories categories = null;
    Amount minimumPrice = null;
    Amount maximumPrice = null;

    if (!stringSellerId.isBlank()) {
      sellerId = this.sellerIdFactory.create(stringSellerId);
    }

    if (title.isBlank()) {
      title = null;
    }

    if (stringCategories.size() >= 1) {
      categories = this.categoriesFactory.create(stringCategories);
    }

    if (minPrice > 0) {
      minimumPrice = this.amountFactory.create(minPrice);
    }

    if (maxPrice > 0) {
      maximumPrice = this.amountFactory.create(maxPrice);
    }

    return new ProductFilters(sellerId, title, categories, minimumPrice, maximumPrice);
  }
}
