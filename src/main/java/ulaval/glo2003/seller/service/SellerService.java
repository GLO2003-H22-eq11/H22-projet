package ulaval.glo2003.seller.service;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.exception.GenericException;

public class SellerService {
  private final SellerRepository sellerRepository;

  public SellerService(SellerRepository sellerRepository) {
    this.sellerRepository = sellerRepository;
  }

  public void addSeller(Seller seller) throws GenericException {
    seller.verifyIfSellerIsMajor();
    this.sellerRepository.save(seller);
  }
}
