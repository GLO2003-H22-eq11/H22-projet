package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.seller.domain.SellerId;

public class ProductFilter {
    private SellerId sellerId;
    private String title;
    private Categories categories;
    private Amount minimalPrice;
    private Amount maximumPrice;

    public Amount getMaximumPrice() {
        return maximumPrice;
    }

    public Amount getMinimalPrice() {
        return minimalPrice;
    }

    public Categories getCategories() {
        return categories;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public String getTitle() {
        return title;
    }
}
