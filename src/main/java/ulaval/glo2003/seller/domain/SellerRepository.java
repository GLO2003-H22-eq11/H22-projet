package ulaval.glo2003.seller.domain;

public interface SellerRepository {
  void save(Seller seller);

  Seller findById(SellerId id);

}
