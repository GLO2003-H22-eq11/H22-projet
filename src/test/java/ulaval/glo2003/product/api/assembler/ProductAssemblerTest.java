package ulaval.glo2003.product.api.assembler;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.offer.api.assembler.BuyerAssembler;
import ulaval.glo2003.offer.api.assembler.OffersAssembler;
import ulaval.glo2003.offer.api.response.OffersSummaryResponse;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductSellerResponse;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.offer.domain.OffersSummary;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProductAssemblerTest {

  private final BuyerAssembler buyerAssembler = new BuyerAssembler();
  private final OffersAssembler offersAssembler = new OffersAssembler(buyerAssembler);
  private final ProductAssembler productAssembler = new ProductAssembler(offersAssembler);

  @Test
  public void givenAProductWithSeller_whenAssemblingToResponse_thenShouldAssembleWithCorrespondingParameters() {
    Product aProduct = new ProductBuilder().withOffers(new OffersSummary()).build();
    Seller aSeller = new SellerBuilder().build();
    ProductWithSeller aProductWithSeller = new ProductWithSeller(aProduct, aSeller);
    List<String> expectedCategories = aProduct.getProductCategories().stream().map(Category::getCategoryName).collect(Collectors.toList());
    ProductSellerResponse expectedProductSellerResponse = new ProductSellerResponse(aSeller.getStringId(), aSeller.getName());
    OffersSummaryResponse expectedOffersSummaryResponse = new OffersSummaryResponse(aProduct.getOffersSummary().getMeanAmount(), aProduct.getOffersSummary().getCount());
    ProductResponse expected = new ProductResponse(
            aProduct.getStringProductId(),
            aProduct.getStringCreatedAt(),
            aProduct.getTitle(),
            aProduct.getDescription(),
            aProduct.getSuggestedPriceAmountDoubleValue(),
            expectedOffersSummaryResponse,
            expectedCategories,
            expectedProductSellerResponse
    );

    ProductResponse actual = this.productAssembler.toResponse(aProductWithSeller);

    assertEquals(expected, actual);
  }
}
