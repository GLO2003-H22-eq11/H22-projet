package ulaval.glo2003.product.domain;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerWithProducts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferDomainService {

  private final ProductRepository productRepository;
  private final OfferRepository offerRepository;

  public OfferDomainService(
                       ProductRepository productRepository,
                       OfferRepository offerRepository) {
    this.productRepository = productRepository;
    this.offerRepository = offerRepository;
  }

  public SellerWithProducts assembleProductWithOfferToSeller(Seller seller) {
    List<Product> products = this.productRepository.findBySellerId(seller.getSellerId());

    Map<Product, List<Offer>> offersWithProduct = new HashMap<>();

    for (Product product : products) {
      List<Offer> offers = this.offerRepository.findByProduct(product);
      offersWithProduct.put(product, offers);
    }

    return this.assembleSellerInformation(seller, offersWithProduct);

  }

  public SellerWithProducts assembleSellerInformation(Seller seller, Map<Product, List<Offer>> offers) {
    List<ProductWithOffers> productInformations = new ArrayList<>();

    for (Map.Entry<Product, List<Offer>> productWithOffer : offers.entrySet()) {
      Product product = productWithOffer.getKey();
      List<Offer> productListOfOffer = productWithOffer.getValue();

      ProductWithOffers productInformation = new ProductWithOffers(
              product.getProductId(),
              product.getTitle(),
              product.getDescription(),
              product.getSuggestedPriceAmount(),
              product.getCategories(),
              productListOfOffer,
              product.getOffers()
      );
      productInformations.add(productInformation);
    }


    return new SellerWithProducts(
            seller.getSellerId(),
            seller.getName(),
            seller.getBio(),
            seller.getBirthDate(),
            seller.getCreatedAt(),
            productInformations
    );
  }
}
