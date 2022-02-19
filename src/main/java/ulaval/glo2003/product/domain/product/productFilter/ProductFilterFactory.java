package ulaval.glo2003.product.domain.product.productFilter;

import ulaval.glo2003.product.api.product.ProductFilterRequest;
import ulaval.glo2003.product.domain.AmountFactory;
import ulaval.glo2003.product.domain.product.productCategories.CategoriesFactory;
import ulaval.glo2003.seller.domain.SellerIdFactory;

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

  public ProductFilter create(ProductFilterRequest productFilterRequest) {
    ProductFilter productFilter = new ProductFilter();

    if (!productFilterRequest.sellerId.isBlank()) {
      productFilter.setSellerId(this.sellerIdFactory.create(productFilterRequest.sellerId));
    }

    if (!productFilterRequest.title.isBlank()) {
      productFilter.setTitle(productFilterRequest.title);
    }

    if (!(productFilterRequest.categories.size() == 1 && productFilterRequest.categories.get(0).equals("null"))) {
      productFilter.setCategories(this.categoriesFactory.create(productFilterRequest.categories));
    }

    if (productFilterRequest.minPrice > 0) {
      productFilter.setMinimalPrice(this.amountFactory.create(productFilterRequest.minPrice));
    }

    if (productFilterRequest.maxPrice > 0) {
      productFilter.setMaximumPrice(this.amountFactory.create(productFilterRequest.maxPrice));
    }

    return productFilter;
  }
}
