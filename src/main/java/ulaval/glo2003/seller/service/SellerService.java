package ulaval.glo2003.seller.service;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.ProductOfferDomainService;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerWithProductsOffers;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.List;

public class SellerService {

  private final SellerRepository sellerRepository;
  private final ProductRepository productRepository;
  private final ProductOfferDomainService productOfferDomainService;


  public SellerService(SellerRepository sellerRepository,
                       ProductRepository productRepository,
                       ProductOfferDomainService productOfferDomainService) {
    this.sellerRepository = sellerRepository;
    this.productRepository = productRepository;
    this.productOfferDomainService = productOfferDomainService;
  }

  public void addSeller(Seller seller) throws GenericException {
    seller.verifyIfMajor();
    this.sellerRepository.save(seller);
  }

  public Seller getSellerById(SellerId id) throws GenericException {
    Seller seller = this.sellerRepository.findById(id);
    List<Product> products = this.productRepository.findBySellerId(seller.getSellerId());
    seller.setProducts(products);

    return seller;
  }

  public SellerWithProductsOffers getSellerWithProducts(SellerId sellerId) throws GenericException {
    Seller seller = this.sellerRepository.findById(sellerId);

    return this.productOfferDomainService.assembleProductsWithOffersToSeller(seller);
  }
}
