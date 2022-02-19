package ulaval.glo2003.product.domain.product.productFilter;

import ulaval.glo2003.product.domain.AmountFactory;
import ulaval.glo2003.product.domain.product.productCategories.CategoriesFactory;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.util.List;

public class ProductFilterFactory {

  private final SellerIdFactory sellerIdFactory;
  private final AmountFactory amountFactory;
  private final CategoriesFactory categoriesFactory;

  public ProductFilterFactory(SellerIdFactory sellerIdFactory,
                              AmountFactory amountFactory,
                              CategoriesFactory categoriesFactory) {
    this.sellerIdFactory = sellerIdFactory;
    this.amountFactory = amountFactory;
    this.categoriesFactory = categoriesFactory;
  }

  public ProductFilter create(String sellerId, String title, List<String> categories, int minPrice, int maxPrice) {
    ProductFilter productFilter = new ProductFilter();

    if (!sellerId.isBlank()) {
      productFilter.setSellerId(this.sellerIdFactory.create(sellerId));
    }

    if (!title.isBlank()) {
      productFilter.setTitle(title);
    }

    if (!(categories.size() == 1 && categories.get(0).equals("null"))) {
      productFilter.setCategories(this.categoriesFactory.create(categories));
    }

    if (minPrice > 0) {
      productFilter.setMinimalPrice(this.amountFactory.create(minPrice));
    }

    if (maxPrice > 0) {
      productFilter.setMaximumPrice(this.amountFactory.create(maxPrice));
    }

    return productFilter;
  }
}
