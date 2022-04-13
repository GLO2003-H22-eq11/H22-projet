package ulaval.glo2003.product.domain.factory;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.offer.domain.OffersSummary;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.domain.factory.SellerIdFactory;

import java.time.Instant;
import java.util.List;

public class ProductFactory {
  private final SellerIdFactory sellerIdFactory;
  private final ProductIdFactory productIdFactory;
  private final CategoriesFactory categoriesFactory;

  public ProductFactory(
          SellerIdFactory sellerIdFactory,
          ProductIdFactory productIdFactory,
          CategoriesFactory categoriesFactory
  ) {
    this.sellerIdFactory = sellerIdFactory;
    this.productIdFactory = productIdFactory;
    this.categoriesFactory = categoriesFactory;
  }

  public Product create(
          String sellerId,
          String title,
          String description,
          Double suggestedPrice,
          List<String> categories
  ) throws GenericException {
    return new Product(
            this.sellerIdFactory.create(sellerId),
            this.productIdFactory.create(),
            title,
            description,
            Amount.fromDouble(suggestedPrice),
            new OffersSummary(),
            this.categoriesFactory.create(categories),
            Instant.now(),
            0
    );
  }
}
