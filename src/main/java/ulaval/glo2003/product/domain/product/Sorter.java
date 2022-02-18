package ulaval.glo2003.product.domain.product;

import java.util.List;

public interface Sorter {
    List<Product> sortProduct(ProductFilter productFilter, List<Product> products);
}
