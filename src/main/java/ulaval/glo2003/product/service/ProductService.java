package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.seller.domain.SellerRepository;

public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;

  public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
  }

  public void addProduct(Product product) throws GenericException {
    this.sellerRepository.findById(product.getSellerId());
    this.productRepository.save(product);
  }
}
