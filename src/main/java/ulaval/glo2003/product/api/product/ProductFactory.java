package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.api.product.assembler.CategoryAssembler;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.seller.domain.SellerIdFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class ProductFactory {
  private final CategoryAssembler categoryAssembler;
  private final SellerIdFactory sellerIdFactory;

  public ProductFactory(CategoryAssembler categoryAssembler, SellerIdFactory sellerIdFactory) {
    this.categoryAssembler = categoryAssembler;
    this.sellerIdFactory = sellerIdFactory;
  }

  public Product create(ProductRequest productRequest, String sellerId) {
    return new Product(
            this.sellerIdFactory.create(sellerId),
            new ProductId(),
            productRequest.title,
            productRequest.description,
            new Amount(BigDecimal.valueOf(productRequest.suggestedPrice)),
            new Offers(new Amount(BigDecimal.valueOf(0)), 0),
            productRequest.categories.stream().
                    map(this.categoryAssembler::toDomain).collect(Collectors.toList()),
            LocalDateTime.now()
    );
  }
}
