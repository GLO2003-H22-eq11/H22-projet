package ulaval.glo2003.product.domain;

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

  private void filterByTitle(String title, List<Product> products) {
    if (title != null) {
      products.removeIf(product -> !product.isInTitle(title));
    }
  }


  private void filterBySellerId(SellerId sellerId, List<Product> products) {
    if (sellerId != null) {
      products.removeIf(product -> !product.hasSameSellerId(sellerId));
    }
  }

  private void filterByAmount(Amount minimalPrice, Amount maximumPrice, List<Product> products) {
    filterByMinimumPrice(minimalPrice, products);
    filterByMaximumPrice(maximumPrice, products);
  }


  private void filterByCategories(Categories categories, List<Product> products) {
    if (categories != null) {
      products.removeIf(product -> !product.hasAtLeastOneCategoryInCommon(categories));
    }
  }


  private void filterByMaximumPrice(Amount maximumPrice, List<Product> products) {
    if (maximumPrice != null) {
      products.removeIf(product -> product.getSuggestedPriceAmount().isHigher(maximumPrice));
    }
  }

  private void filterByMinimumPrice(Amount minimalPrice, List<Product> products) {
    if (minimalPrice != null) {
      products.removeIf(product -> minimalPrice.isHigher(product.getSuggestedPriceAmount()));
    }
  }
}
