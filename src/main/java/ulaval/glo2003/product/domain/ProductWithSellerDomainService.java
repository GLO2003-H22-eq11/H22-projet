package ulaval.glo2003.product.domain;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.LinkedList;
import java.util.List;

public class ProductWithSellerDomainService {
  private final ProductWithSellerFactory productWithSellerFactory;
  private final SellerRepository sellerRepository;

  public ProductWithSellerDomainService(
          ProductWithSellerFactory productWithSellerFactory,
          SellerRepository sellerRepository
  ) {
    this.productWithSellerFactory = productWithSellerFactory;
    this.sellerRepository = sellerRepository;
  }

  public List<ProductWithSeller> getProductsWithSeller(List<Product> products) throws GenericException {
    List<ProductWithSeller> productWithSellers = new LinkedList<>();

    for (Product product : products) {
      productWithSellers.add(this.getProductWithSeller(product));
    }

    return productWithSellers;
  }

  public ProductWithSeller getProductWithSeller(Product product) throws GenericException {
    Seller seller = this.sellerRepository.findById(product.getSellerId());
    return this.productWithSellerFactory.createFrom(product, seller);
  }
}
