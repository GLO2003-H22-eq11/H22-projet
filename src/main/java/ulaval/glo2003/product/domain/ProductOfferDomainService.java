package ulaval.glo2003.product.domain;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerWithProductsOffers;
import ulaval.glo2003.seller.domain.SellerWithProductsOffersFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductOfferDomainService {

  private final ProductRepository productRepository;
  private final OfferRepository offerRepository;
  private final ProductWithOffersFactory productWithOffersFactory;
  private final SellerWithProductsOffersFactory sellerWithProductsOffersFactory;

  public ProductOfferDomainService(
                       ProductRepository productRepository,
                       OfferRepository offerRepository,
                       ProductWithOffersFactory productWithOffersFactory,
                       SellerWithProductsOffersFactory sellerWithProductsOffersFactory) {
    this.productRepository = productRepository;
    this.offerRepository = offerRepository;
    this.productWithOffersFactory = productWithOffersFactory;
    this.sellerWithProductsOffersFactory = sellerWithProductsOffersFactory;
  }

  public SellerWithProductsOffers assembleProductsWithOffersToSeller(Seller seller) {
    List<Product> products = this.productRepository.findBySellerId(seller.getSellerId());

    Map<Product, List<Offer>> productsWithOffers = this.getMapOfProductsWithTheirOffers(products);

    return this.assembleOffersToSeller(seller, productsWithOffers);

  }

  private SellerWithProductsOffers assembleOffersToSeller(Seller seller, Map<Product, List<Offer>> offers) {
    List<ProductWithOffers> productsWithOffers = getProductWithOffers(offers);

    return this.sellerWithProductsOffersFactory.create(seller, productsWithOffers);
  }

  private Map<Product, List<Offer>> getMapOfProductsWithTheirOffers(List<Product> products) {
    Map<Product, List<Offer>> productsWithOffers = new HashMap<>();

    products.forEach((product) -> {
      productsWithOffers.put(product, this.offerRepository.findByProduct(product));
    });
    return productsWithOffers;
  }

  private List<ProductWithOffers> getProductWithOffers(Map<Product, List<Offer>> offers) {
    List<ProductWithOffers> productsWithOffers = new ArrayList<>();

    for (Map.Entry<Product, List<Offer>> productWithOffer : offers.entrySet()) {
      productsWithOffers.add(this.productWithOffersFactory.create(productWithOffer.getKey(),
              productWithOffer.getValue()));
    }
    return productsWithOffers;
  }

}
