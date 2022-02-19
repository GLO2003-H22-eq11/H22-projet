package ulaval.glo2003;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.product.api.offers.OffersAssembler;
import ulaval.glo2003.product.api.product.assembler.ProductAssembler;
import ulaval.glo2003.product.api.product.assembler.ProductCategoryAssembler;
import ulaval.glo2003.product.api.product.ProductRequestValidator;
import ulaval.glo2003.product.api.product.ProductFactory;
import ulaval.glo2003.product.api.product.assembler.ProductFilterAssembler;
import ulaval.glo2003.product.domain.product.productFilter.ProductFilterFactory;
import ulaval.glo2003.product.domain.AmountFactory;
import ulaval.glo2003.product.domain.product.productCategories.CategoriesFactory;
import ulaval.glo2003.product.domain.product.productId.ProductIdFactory;
import ulaval.glo2003.product.domain.product.ProductSorter;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.product.domain.product.ProductDomainService;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSellerAssembler;
import ulaval.glo2003.product.infrastructure.inMemory.InMemoryProductRepository;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.seller.api.SellerAssembler;
import ulaval.glo2003.seller.api.SellerFactory;
import ulaval.glo2003.seller.api.SellerRequestValidator;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.infrastructure.inMemory.InMemorySellerRepository;
import ulaval.glo2003.seller.service.SellerService;

public class AppContext {

  // sorter
  public final ProductSorter productSorter = new ProductSorter();

  //assemblers
  public final ProductCategoryAssembler productCategoryAssembler = new ProductCategoryAssembler();
  public final OffersAssembler offersAssembler = new OffersAssembler();
  public final ProductAssembler productAssembler = new ProductAssembler(offersAssembler);
  public final SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);
  public final ProductWithSellerAssembler productWithSellerAssembler = new ProductWithSellerAssembler();
  public final ProductFilterAssembler productFilterAssembler = new ProductFilterAssembler(offersAssembler);

  //factories
  public final CategoriesFactory categoriesFactory = new CategoriesFactory(productCategoryAssembler);
  public final SellerFactory sellerFactory = new SellerFactory();
  public final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  public final ProductFactory productFactory = new ProductFactory(productCategoryAssembler, sellerIdFactory);
  public final ProductIdFactory productIdFactory = new ProductIdFactory();
  public final AmountFactory amountFactory = new AmountFactory();
  public final ProductFilterFactory productFilterFactory = new ProductFilterFactory(
          sellerIdFactory,
          amountFactory,
          categoriesFactory);

  //repositories
  public final SellerRepository sellerRepository = new InMemorySellerRepository();
  public final ProductRepository productRepository = new InMemoryProductRepository();

  // domain service
  public final ProductDomainService productDomainService = new ProductDomainService(
          productWithSellerAssembler,
          sellerRepository);

  //services
  public final SellerService sellerService = new SellerService(sellerRepository, productRepository);
  public final ProductService productService = new ProductService(
          productRepository,
          sellerRepository,
          productSorter,
          productDomainService,
          productFilterFactory);


  //validators
  public final ConstraintsValidator constraintsValidator = new ConstraintsValidator();
  public final ProductRequestValidator productRequestValidator = new ProductRequestValidator(constraintsValidator);
  public final SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(constraintsValidator);
}
