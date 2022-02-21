package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.ProductFilterer;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.product.domain.ProductSellerDomainService;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.List;

public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;
  private final ProductSellerDomainService productSellerService;
  private final ProductFilterer productFilterer;

  public ProductService(
          ProductRepository productRepository,
          SellerRepository sellerRepository,
          ProductSellerDomainService productSellerService,
          ProductFilterer productFilterer
  ) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
    this.productSellerService = productSellerService;
    this.productFilterer = productFilterer;
  }

  public ProductWithSeller getProductWithSeller(ProductId productId) throws GenericException {
    Product product = this.productRepository.findById(productId);
    return this.productSellerService.getProductWithSeller(product);
  }

  public void addProduct(Product product) throws GenericException {
    this.sellerRepository.verifyIfSellerExists(product.getSellerId());
    this.productRepository.save(product);
  }

  public List<ProductWithSeller> getFilteredProducts(ProductFilters productFilters) throws GenericException {
    if (productFilters.getSellerId() != null) {
      this.sellerRepository.verifyIfSellerExists(productFilters.getSellerId());
    }
    List<Product> products = this.productFilterer.findFilteredProducts(productFilters);
    return this.productSellerService.getProductsWithSeller(products);
  }
}
