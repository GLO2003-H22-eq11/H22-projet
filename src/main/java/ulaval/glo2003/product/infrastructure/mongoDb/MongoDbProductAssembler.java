package ulaval.glo2003.product.infrastructure.mongoDb;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.product.domain.Categories;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.offer.domain.OffersSummary;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.offer.infrastructure.mongoDb.OffersEntity;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.Instant;
import java.util.stream.Collectors;

public class MongoDbProductAssembler {

  public ProductEntity toEntity(Product product) {
    return new ProductEntity(
            product.getStringProductId(),
            product.getStringSellerId(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmountDoubleValue(),
            new OffersEntity(
                    product.getOffersSummary().getMeanAmount(),
                    product.getOffersSummary().getCount(),
                    product.getOffersSummary().getMinAmount(),
                    product.getOffersSummary().getMaxAmount()
            ),
            product.getProductCategories().stream().map(Category::getCategoryName).collect(Collectors.toList()),
            product.getStringCreatedAt()
    );
  }

  public Product toDomain(ProductEntity productEntity) throws GenericException {
    return new Product(
            new SellerId(productEntity.getSellerId()),
            new ProductId(productEntity.getProductId()),
            productEntity.getTitle(),
            productEntity.getDescription(),
            Amount.fromDouble(productEntity.getSuggestedPrice()),
            new OffersSummary(
                    Amount.fromDouble(productEntity.getOffers().getMean()),
                    productEntity.getOffers().getCount(),
                    Amount.fromDouble(productEntity.getOffers().getMin()),
                    Amount.fromDouble(productEntity.getOffers().getMax())
            ),
            new Categories(productEntity.getCategories().stream().map(Category::new).collect(Collectors.toList())),
            Instant.parse(productEntity.getCreatedAt())
    );
  }
}
