package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSeller;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSellerAssembler;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

import java.util.LinkedList;
import java.util.List;

public class ProductDomainService {

  private final ProductWithSellerAssembler productWithSellerAssembler;
  private final SellerRepository sellerRepository;

  public ProductDomainService(ProductWithSellerAssembler productWithSellerAssembler,
                              SellerRepository sellerRepository) {
    this.productWithSellerAssembler = productWithSellerAssembler;
    this.sellerRepository = sellerRepository;
  }

  public List<ProductWithSeller> getProductsWithSeller(List<Product> products) throws SellerNotFoundException {
    List<ProductWithSeller> productWithSellers = new LinkedList<>();

    for (Product product : products) {
      productWithSellers.add(this.getProductWithSeller(product));
    }

    return productWithSellers;
  }

  private ProductWithSeller getProductWithSeller(Product product) throws SellerNotFoundException {
    Seller seller = this.sellerRepository.findById(product.getSellerId());
    ProductWithSeller productWithSeller = this.productWithSellerAssembler.toProductWithSeller(product, seller);
    return productWithSeller;
  }
}
