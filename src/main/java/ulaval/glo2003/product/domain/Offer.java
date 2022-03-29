package ulaval.glo2003.product.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Offer {

  private final OfferId offerId;
  private final ProductId productId;
  private final Amount amount;
  private final String message;
  private final LocalDateTime createdAt;
  private final Buyer buyer;

  public Offer(
          OfferId offerId,
          ProductId productId,
          Amount amount,
          String message,
          LocalDateTime createdAt,
          Buyer buyer) {
    this.offerId = offerId;
    this.productId = productId;
    this.buyer = buyer;
    this.amount = amount;
    this.message = message;
    this.createdAt = createdAt;
  }

  public String getCreatedAt() {
    return this.createdAt.toString();
  }

  public String getStringId() {
    return this.offerId.toString();
  }

  public ProductId getProductId() {
    return productId;
  }

  public Amount getAmount() {
    return this.amount;
  }

  public Double getAmountDoubleValue() {
    return this.amount.getDoubleValue();
  }

  public String getMessage() {
    return this.message;
  }

  public Buyer getBuyer() {
    return this.buyer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Offer offer = (Offer) o;
    return offerId.equals(offer.offerId)
            && productId.equals(offer.productId)
            && amount.equals(offer.amount)
            && message.equals(offer.message)
            && createdAt.equals(offer.createdAt)
            && buyer.equals(offer.buyer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offerId, productId, amount, message, createdAt, buyer);
  }
}
