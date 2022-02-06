package ulaval.glo2003.products.service;

import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.domain.ProductRepository;

public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void addProduct(Product product) {
    this.productRepository.save(product);
  }
}
