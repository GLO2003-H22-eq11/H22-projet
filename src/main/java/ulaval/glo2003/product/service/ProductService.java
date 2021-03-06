package ulaval.glo2003.product.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductFilterer;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductWithSellerDomainService;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.List;


public class ProductService {
  private final ProductRepository productRepository;
  private final SellerRepository sellerRepository;
  private final ProductWithSellerDomainService productSellerService;
  private final ProductFilterer productFilterer;
  private final OfferRepository offerRepository;

  public ProductService(
          ProductRepository productRepository,
          SellerRepository sellerRepository,
          ProductWithSellerDomainService productSellerService,
          ProductFilterer productFilterer,
          OfferRepository offerRepository
  ) {
    this.productRepository = productRepository;
    this.sellerRepository = sellerRepository;
    this.productSellerService = productSellerService;
    this.productFilterer = productFilterer;
    this.offerRepository = offerRepository;
  }

  public ProductWithSeller getProductWithSeller(ProductId productId) throws GenericException {
    Product product = this.productRepository.findById(productId);
    product.addView();
    this.productRepository.save(product);
    return this.productSellerService.getProductWithSeller(product);
  }

  public List<Product> getProductsBySellerId(SellerId sellerId) throws GenericException {
    return this.productRepository.findBySellerId(sellerId);
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

  public void createOffer(Offer offer) throws GenericException {
    Product product = this.productRepository.findById(offer.getProductId());

    product.addOfferAmount(offer.getAmount());

    this.productRepository.save(product);
    this.offerRepository.save(offer);
  }


  public void deleteAll() {
    if (System.getenv("DATABASE_NAME").equals("floppa-dev")) {
      this.productRepository.clear();
      this.offerRepository.clear();
    }
  }
}
