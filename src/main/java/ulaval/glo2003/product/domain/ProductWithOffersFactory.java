package ulaval.glo2003.product.domain;

import java.util.List;

public class ProductWithOffersFactory {

  public ProductWithOffers create(Product product, List<Offer> productListOfOffer) {
    return new ProductWithOffers(
            product.getProductId(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            product.getCategories(),
            productListOfOffer,
            product.getOffers(),
            product.getStringCreatedAt()
    );
  }
}
