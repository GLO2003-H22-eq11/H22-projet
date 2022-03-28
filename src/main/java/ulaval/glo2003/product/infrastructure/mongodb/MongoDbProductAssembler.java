package ulaval.glo2003.product.infrastructure.mongodb;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Categories;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.infrastructure.mongodb.entity.OffersEntity;
import ulaval.glo2003.product.infrastructure.mongodb.entity.ProductEntity;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.util.DateParser;

import java.util.stream.Collectors;

public class MongoDbProductAssembler {

  public ProductEntity toEntity(Product product) {
    return new ProductEntity(
            product.getStringProductId(),
            product.getStringSellerId(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmountDoubleValue(),
            new OffersEntity(product.getOffers().getMeanAmount(), product.getOffers().getCount()),
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
            productEntity.getOffers() != null ? productEntity.getOffers().getMean() != null
                    ? new Offers(
                            Amount.fromDouble(productEntity.getOffers().getMean()),
                            productEntity.getOffers().getCount()
                    ) : new Offers() : new Offers(),
            new Categories(productEntity.getCategories().stream().map(Category::new).collect(Collectors.toList())),
            DateParser.formatLocalDateTime(productEntity.getCreatedAt())
    );
  }
}
