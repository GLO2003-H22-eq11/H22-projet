package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.ProductFilterer;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.product.domain.product.ProductWithSeller;
import ulaval.glo2003.product.domain.product.ProductSellerService;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductFilters;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.List;

public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;
  private final ProductSellerService productSellerService;
  private final ProductFilterer productFilterer;

  public ProductService(
          ProductRepository productRepository,
          SellerRepository sellerRepository,
          ProductSellerService productSellerService,
          ProductFilterer productFilterer
  ) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
    this.productSellerService = productSellerService;
    this.productFilterer = productFilterer;
  }

  public ProductWithSeller getProductSeller(ProductId productId) throws GenericException {
    Product product = this.productRepository.findById(productId);
    return this.productSellerService.getProductWithSeller(product);
  }

  public void addProduct(Product product) throws GenericException {
    this.verifyIfSellerExists(product.getSellerId());
    this.productRepository.save(product);
  }

  private void verifyIfSellerExists(SellerId sellerId) throws GenericException {
    this.sellerRepository.findById(sellerId);
  }

  public List<ProductWithSeller> getFilteredProducts(ProductFilters productFilters) throws GenericException {
    List<Product> products = this.productFilterer.findFilteredProducts(productFilters);
    return this.productSellerService.getProductsWithSeller(products);
  }
}
