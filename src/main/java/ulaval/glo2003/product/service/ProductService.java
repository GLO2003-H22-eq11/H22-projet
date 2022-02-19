package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.product.domain.product.ProductSorter;
import ulaval.glo2003.product.domain.product.ProductDomainService;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.productFilter.ProductFilterFactory;
import ulaval.glo2003.product.domain.product.productFilter.ProductFilter;
import ulaval.glo2003.product.domain.product.productId.ProductId;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSeller;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

import java.util.List;


public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;
  private final ProductSorter productSorter;
  private final ProductDomainService productDomainService;
  private final ProductFilterFactory productFilterFactory;

  public ProductService(ProductRepository productRepository,
                        SellerRepository sellerRepository,
                        ProductSorter productSorter,
                        ProductDomainService productDomainService,
                        ProductFilterFactory productFilterFactory) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
    this.productSorter = productSorter;
    this.productDomainService = productDomainService;
    this.productFilterFactory = productFilterFactory;
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

  public List<ProductWithSeller> getFilterProducts(String sellerId,
                                                   String title,
                                                   List<String> categories,
                                                   int minPrice, int maxPrice)
          throws SellerNotFoundException {
    List<Product> products = this.productRepository.getAll();
    ProductFilter productFilter = this.productFilterFactory.create(sellerId, title, categories, minPrice, maxPrice);
    List<Product> sortedProducts =  this.productSorter.sortProduct(productFilter, products);
    return this.productDomainService.getProductsWithSeller(sortedProducts);
  };
}
