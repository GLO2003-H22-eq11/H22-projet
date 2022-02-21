package ulaval.glo2003.product.domain;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

import java.util.LinkedList;
import java.util.List;

public class ProductSellerService {
  private final ProductWithSellerFactory productWithSellerFactory;
  private final SellerRepository sellerRepository;

  public ProductSellerService(
          ProductWithSellerFactory productWithSellerFactory,
          SellerRepository sellerRepository
  ) {
    this.productWithSellerFactory = productWithSellerFactory;
    this.sellerRepository = sellerRepository;
  }

  public List<ProductWithSeller> getProductsWithSeller(List<Product> products) throws SellerNotFoundException {
    List<ProductWithSeller> productWithSellers = new LinkedList<>();

    for (Product product : products) {
      productWithSellers.add(this.getProductWithSeller(product));
    }

    return productWithSellers;
  }

  public ProductWithSeller getProductWithSeller(Product product) throws SellerNotFoundException {
    Seller seller = this.sellerRepository.findById(product.getSellerId());
    return this.productWithSellerFactory.createFrom(product, seller);
  }
}
