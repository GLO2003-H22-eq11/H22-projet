package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;

public class ProductSorter implements Sorter {

    public List<Product> sortProduct(ProductFilter productFilter, List<Product> products) {
        return this.sortProductByFilter(productFilter, products);
    }

    private List<Product> sortProductByFilter(ProductFilter productFilter, List<Product> products) {
        List<Product> sortProducts = products;

        sortProducts = sortByTitle(productFilter.getTitle(), sortProducts);
        sortProducts = sortBySellerId(productFilter.getSellerId(), sortProducts);
        sortProducts = sortByAmount(productFilter.getMinimalPrice(), productFilter.getMaximumPrice(), sortProducts);
        sortProducts = sortByCategories(productFilter.getCategories(), sortProducts);

        return sortProducts;
    }

    private List<Product> sortByCategories(Categories categories, List<Product> sortProducts) {
        if (categories == null) {
            return sortProducts;
        }

        sortProducts.forEach(product -> {
            Categories productCategories = product.getCategories();

            if (!productCategories.equals(categories)) {
                sortProducts.remove(product);
            }
        });

        return sortProducts;
    }

    private List<Product> sortByAmount(Amount minimalPrice, Amount maximumPrice, List<Product> sortProducts) {
        sortProducts = sortByMinimumPrice(minimalPrice, sortProducts);
        sortProducts = sortByMaximumPrice(maximumPrice, sortProducts);
        return sortProducts;
    }

    private List<Product> sortByMaximumPrice(Amount maximumPrice, List<Product> sortProducts) {
        if (maximumPrice == null) {
            return sortProducts;
        }

        sortProducts.forEach(product -> {
            if ((maximumPrice.getAmount() < product.getSuggestedPriceAmount())) {
                sortProducts.remove(product);
            }
        });

        return sortProducts;
    }

    private List<Product> sortByMinimumPrice(Amount minimalPrice, List<Product> sortProducts) {
        if (minimalPrice == null) {
            return sortProducts;
        }

        sortProducts.forEach(product -> {
            if ((minimalPrice.getAmount() > product.getSuggestedPriceAmount())) {
                sortProducts.remove(product);
            }
        });

        return sortProducts;
    }

    private List<Product> sortBySellerId(SellerId sellerId, List<Product> sortProducts) {

        if (sellerId == null) {
            return sortProducts;
        }

        sortProducts.forEach(product -> {
            if (!(product.getSellerId().equals(sellerId))) {
                sortProducts.remove(product);
            }
        });

        return sortProducts;
    }

    private List<Product> sortByTitle(String title, List<Product> sortProducts) {
        if (title == null) {
            return sortProducts;
        }

        sortProducts.forEach(product -> {
            if (!(product.getTitle().equals(title))) {
                sortProducts.remove(product);
            }
        });

        return sortProducts;
    }
}
