package ulaval.glo2003.product.api;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.request.ProductRequest;
import ulaval.glo2003.product.domain.OffersSummary;
import ulaval.glo2003.product.domain.ProductIdFactory;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.time.Instant;

public class ProductFactory {
  private final SellerIdFactory sellerIdFactory;
  private final ProductIdFactory productIdFactory;
  private final CategoriesFactory categoriesFactory;

  public ProductFactory(
          SellerIdFactory sellerIdFactory, ProductIdFactory productIdFactory, CategoriesFactory categoriesFactory
  ) {
    this.sellerIdFactory = sellerIdFactory;
    this.productIdFactory = productIdFactory;
    this.categoriesFactory = categoriesFactory;
  }

  public Product create(ProductRequest productRequest, String sellerId) throws GenericException {
    return new Product(
            this.sellerIdFactory.create(sellerId),
            this.productIdFactory.create(),
            productRequest.title,
            productRequest.description,
            Amount.fromDouble(productRequest.suggestedPrice),
            new OffersSummary(),
            this.categoriesFactory.create(productRequest.categories),
            Instant.now()
    );
  }
}
