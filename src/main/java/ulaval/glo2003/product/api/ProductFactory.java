package ulaval.glo2003.product.api;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.request.ProductRequest;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductIdFactory;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.time.LocalDateTime;

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
            Amount.fromInt(productRequest.suggestedPrice),
            new Offers(Amount.fromInt(0), 0),
            this.categoriesFactory.create(productRequest.categories),
            LocalDateTime.now()
    );
  }
}
