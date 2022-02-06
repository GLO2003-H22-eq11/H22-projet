package ulaval.glo2003.products.api;

import ulaval.glo2003.products.domain.Amount;
import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.domain.ProductId;
import ulaval.glo2003.seller.domain.SellerId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ProductFactory {
  private final ProductCategoryAssembler productCategoryAssembler;

  public ProductFactory(ProductCategoryAssembler productCategoryAssembler) {
    this.productCategoryAssembler = productCategoryAssembler;
  }

  public Product create(ProductRequest productRequest, SellerId sellerId) {
    return new Product(
            sellerId,
            new ProductId(),
            productRequest.title,
            productRequest.description,
            new Amount(BigDecimal.valueOf(productRequest.suggestedPrice)),
            null,
            productRequest.categories.stream().
                    map(this.productCategoryAssembler::toDomain).collect(Collectors.toList()),
            LocalDateTime.now()
    );
  }
}
