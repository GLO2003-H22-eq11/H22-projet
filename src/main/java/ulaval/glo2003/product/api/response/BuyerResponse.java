package ulaval.glo2003.product.api.response;

public class BuyerResponse {
  public String name;
  public String email;
  public String phoneNumber;

  public BuyerResponse() {
  }

  public BuyerResponse(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
}
