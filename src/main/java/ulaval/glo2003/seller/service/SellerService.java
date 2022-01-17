package ulaval.glo2003.seller.service;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

public class SellerService {
  private final SellerRepository sellerRepository;

  public SellerService(SellerRepository sellerRepository) {
    this.sellerRepository = sellerRepository;
  }

  public void addSeller(Seller seller) {
    if (seller.isMajor()) {
      this.sellerRepository.save(seller);
    }
  }
}
