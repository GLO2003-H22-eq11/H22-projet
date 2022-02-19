package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.ProductFilter;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.product.domain.product.ProductSorter;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

import java.util.List;


public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;
  private final ProductSorter productSorter;

  public ProductService(ProductRepository productRepository,
                        SellerRepository sellerRepository,
                        ProductSorter productSorter) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
    this.productSorter = productSorter;
  }

  public Seller getProductOwner(SellerId sellerId) throws SellerNotFoundException {
    return this.sellerRepository.findById(sellerId);
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

  public List<Product> getFilterProducts(ProductFilter productFilter) {
    List<Product> products = this.productRepository.getAll();
    return this.productSorter.sortProduct(productFilter, products);
  };
}
