package ulaval.glo2003.product.api;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Categories;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ProductFactory {
  private final SellerIdFactory sellerIdFactory;

  public ProductFactory(SellerIdFactory sellerIdFactory) {
    this.sellerIdFactory = sellerIdFactory;
  }

  public Product create(ProductRequest productRequest, String sellerId) {
    return new Product(
            this.sellerIdFactory.create(sellerId),
            new ProductId(),
            productRequest.title,
            productRequest.description,
            Amount.fromInt(productRequest.suggestedPrice),
            new Offers(Amount.fromInt(0), 0),
            new Categories(productRequest.categories.stream().
                    map(Category::new).collect(Collectors.toList())),
            LocalDateTime.now()
    );
  }
}
