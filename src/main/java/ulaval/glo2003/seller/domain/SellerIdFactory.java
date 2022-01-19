package ulaval.glo2003.seller.domain;

public class SellerIdFactory {

  public SellerId create(String id) {
    return new SellerId(id);
  }
}
