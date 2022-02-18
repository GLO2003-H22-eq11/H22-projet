package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;


public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;

  public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
  }

  public void addProduct(Product product) throws GenericException {
    this.verifyIfSellerExists(product.getSellerId());
    this.productRepository.save(product);
  }

  public Product getProductById(ProductId id) throws GenericException {
    return this.productRepository.findById(id);
  }

  private void verifyIfSellerExists(SellerId sellerId) throws GenericException {
    this.sellerRepository.findById(sellerId);
  }
}
