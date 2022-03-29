package ulaval.glo2003.seller.domain;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferRepository;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductWithOffers;
import ulaval.glo2003.product.domain.ProductWithOffersFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerWithProductsDomainService {

  private final ProductRepository productRepository;
  private final OfferRepository offerRepository;
  private final ProductWithOffersFactory productWithOffersFactory;

  public SellerWithProductsDomainService(
          ProductRepository productRepository,
          OfferRepository offerRepository,
          ProductWithOffersFactory productWithOffersFactory
  ) {
    this.productRepository = productRepository;
    this.offerRepository = offerRepository;
    this.productWithOffersFactory = productWithOffersFactory;
  }

  public SellerWithProducts getSellerWithProducts(Seller seller) throws GenericException {
    List<Product> products = this.productRepository.findBySellerId(seller.getSellerId());

    List<ProductWithOffers> productsWithOffers = this.getProductWithOffers(products);

    return new SellerWithProducts(
            seller,
            productsWithOffers
    );
  }

  private List<ProductWithOffers> getProductWithOffers(List<Product> products) throws GenericException {
    Map<Product, List<Offer>> productsWithOffers = new HashMap<>();

    for (Product product : products) {
      productsWithOffers.put(product, this.offerRepository.findByProductId(product.getProductId()));
    }

    return this.createProductWithOffers(productsWithOffers);
  }

  private List<ProductWithOffers> createProductWithOffers(Map<Product, List<Offer>> offers) {
    List<ProductWithOffers> productsWithOffers = new ArrayList<>();

    for (Map.Entry<Product, List<Offer>> productWithOffer : offers.entrySet()) {
      productsWithOffers.add(
              this.productWithOffersFactory.create(productWithOffer.getKey(), productWithOffer.getValue())
      );
    }
    return productsWithOffers;
  }
}
