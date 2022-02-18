package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.domain.product.ProductFilter;

import java.util.List;

public class ProductFilterFactory {

    public ProductFilter create(String sellerId, String title, List<String> categories, int minPrice, int maxPrice) {
        System.out.println(sellerId);
        System.out.println(categories);
        System.out.println(minPrice);

        return new ProductFilter();
    }
}
