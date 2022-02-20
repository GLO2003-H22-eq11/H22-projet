package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;

public class ProductFilterer {
  private final ProductRepository productRepository;

  public ProductFilterer(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> findFilteredProducts(ProductFilters productFilters) {
    List<Product> products = this.productRepository.findAll();

    this.filterByTitle(productFilters.getTitle(), products);
    this.filterBySellerId(productFilters.getSellerId(), products);
    this.filterByAmount(productFilters.getMinimalPrice(), productFilters.getMaximumPrice(), products);
    this.filterByCategories(productFilters.getCategories(), products);

    return products;
  }


  private void filterByCategories(Categories categories, List<Product> products) {
    if (categories != null) {
      products.forEach(product -> {
        if (!product.hasAtLeastOneCategoryInCommon(categories)) {
          products.remove(product);
        }
      });
    }
  }

  private void filterByAmount(Amount minimalPrice, Amount maximumPrice, List<Product> products) {
    filterByMinimumPrice(minimalPrice, products);
    filterByMaximumPrice(maximumPrice, products);
  }

  private void filterByMaximumPrice(Amount maximumPrice, List<Product> products) {
    if (maximumPrice != null) {
      products.forEach(product -> {
        if (product.getSuggestedPriceAmount().isHigher(maximumPrice)) {
          products.remove(product);
        }
      });
    }
  }

  private void filterByMinimumPrice(Amount minimalPrice, List<Product> products) {
    if (minimalPrice != null) {
      products.forEach(product -> {
        if (minimalPrice.isHigher(product.getSuggestedPriceAmount())) {
          products.remove(product);
        }
      });
    }
  }

  private void filterBySellerId(SellerId sellerId, List<Product> products) {
    if (sellerId != null) {
      products.forEach(product -> {
        if (!product.hasSameSellerId(sellerId)) {
          products.remove(product);
        }
      });
    }
  }

  private void filterByTitle(String title, List<Product> products) {
    if (title != null) {
      products.forEach(product -> {
        if (!product.isInTitle(title)) {
          products.remove(product);
        }
      });
    }
  }
}
