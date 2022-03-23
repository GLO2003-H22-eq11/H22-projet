package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.exceptions.InvalidMessageException;
import ulaval.glo2003.product.domain.exceptions.InvalidOfferNameException;

import static org.junit.jupiter.api.Assertions.*;

class OfferFactoryTest {
  private final ProductIdFactory productIdFactory = new ProductIdFactory();
  private final OfferFactory offerFactory = new OfferFactory(productIdFactory);

  private static final String A_NAME = "A NAME";
  private static final String AN_EMAIL = "allo@email.ca";
  private static final String A_PHONE_NUMBER = "14181234567";
  private static final Double AN_AMOUNT = 20.0;
  private static final String A_VALID_MESSAGE_WITH_100_CHARACTERS = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";
  private static final String A_PRODUCT_ID = "b0f42289-44c6-46b1-8db1-c4c508f9752a";

  @Test
  public void givenABlankName_whenCreate_thenShouldThrowInvalidOfferNameException() {
    String aBlankName = "";

    assertThrows(InvalidOfferNameException.class, () ->
            this.offerFactory.create(
                    aBlankName, AN_EMAIL, A_PHONE_NUMBER, AN_AMOUNT, A_VALID_MESSAGE_WITH_100_CHARACTERS, A_PRODUCT_ID
            ));
  }

  @Test
  public void givenAnInvalidOfferRequestWithAMessageWithLessThan100Characters_whenValidate_thenShouldThrowInvalidMessageException() {
    String aSmallMessage = "Bonjour je suis intéressé à acheter votre maison";

    assertThrows(
            InvalidMessageException.class, () -> this.offerFactory.create(
                    A_NAME, AN_EMAIL, A_PHONE_NUMBER, AN_AMOUNT, aSmallMessage, A_PRODUCT_ID
            )
    );
  }

  @Test
  public void givenAnInvalidOfferRequestWithAMessageWithMoreThan100Characters_whenValidate_thenShouldNotThrow() {
    String aMessageWith110Characters = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet yeet skee.";

    assertDoesNotThrow(() -> this.offerFactory.create(
                    A_NAME, AN_EMAIL, A_PHONE_NUMBER, AN_AMOUNT, aMessageWith110Characters, A_PRODUCT_ID
            )
    );
  }
}
