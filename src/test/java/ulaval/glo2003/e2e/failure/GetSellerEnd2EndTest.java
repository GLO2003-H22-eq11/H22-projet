package ulaval.glo2003.e2e.failure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;

import static org.hamcrest.Matchers.equalTo;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.failure.SellerEnd2EndTestUtils.getSellerWithSellerId;

public class GetSellerEnd2EndTest {

    @BeforeAll
    public static void startServer() {
        try {
            ApplicationMain.main(new String[0]);

        } catch (Exception ignored) {
        }
    }

    @AfterAll
    public static void stopServer() {
        stop();
    }

    @Test
    public void givenANonExistingSellerId_whenGetSeller_thenShouldReturnRightStatusCode() {
        getSellerWithSellerId(A_VALID_UUID_FORMAT)
                .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);

    }
    @Test
    public void givenABadSellerId_whenGetSeller_thenShouldReturnRightStatusCode() {
        getSellerWithSellerId(A_NON_VALID_UUID_FORMAT)
                .then().assertThat().statusCode(BAD_STATUS_CODE);

    }

    @Test
    public void givenABadSellerId_whenGetSeller_thenShouldReturnRightBody() {
        getSellerWithSellerId(A_VALID_UUID_FORMAT)
                .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
                .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

    }

    @Test
    public void givenANonExistingSellerId_whenGetSeller_thenShouldReturnRightBody() {
        getSellerWithSellerId(A_NON_VALID_UUID_FORMAT)
                .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
                .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

    }
}
