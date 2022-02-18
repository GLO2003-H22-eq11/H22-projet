package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.domain.product.ProductFilter;

import java.util.List;

public class ProductFilterFactory {

    public ProductFilter create(String sellerId, String title, List<String> categories, int minPrice, int maxPrice) {
        return new ProductFilter();
    }
}
