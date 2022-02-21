package ulaval.glo2003;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.product.api.assembler.OffersAssembler;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.product.api.assembler.CategoryAssembler;
import ulaval.glo2003.product.api.ProductRequestValidator;
import ulaval.glo2003.product.api.ProductFactory;
import ulaval.glo2003.product.api.ProductFiltersFactory;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.ProductFilterer;
import ulaval.glo2003.product.domain.ProductIdFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductSellerService;
import ulaval.glo2003.product.domain.ProductWithSellerFactory;
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

  //assemblers
  public final CategoryAssembler categoryAssembler = new CategoryAssembler();
  public final OffersAssembler offersAssembler = new OffersAssembler();
  public final ProductAssembler productAssembler = new ProductAssembler(offersAssembler);
  public final SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);

  //factories
  public final CategoriesFactory categoriesFactory = new CategoriesFactory();
  public final SellerFactory sellerFactory = new SellerFactory();
  public final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  public final ProductFactory productFactory = new ProductFactory(categoryAssembler, sellerIdFactory);
  public final ProductIdFactory productIdFactory = new ProductIdFactory();
  public final ProductFiltersFactory productFiltersFactory = new ProductFiltersFactory(
          sellerIdFactory,
          categoriesFactory
  );
  public final ProductWithSellerFactory productWithSellerFactory = new ProductWithSellerFactory();

  //repositories
  public final SellerRepository sellerRepository = new InMemorySellerRepository();
  public final ProductRepository productRepository = new InMemoryProductRepository();

  // domain
  public final ProductSellerService productSellerService = new ProductSellerService(
          productWithSellerFactory,
          sellerRepository
  );
  public final ProductFilterer productFilterer = new ProductFilterer(productRepository);

  //services
  public final SellerService sellerService = new SellerService(sellerRepository, productRepository);
  public final ProductService productService = new ProductService(
          productRepository,
          sellerRepository,
          productSellerService,
          productFilterer
  );


  //validators
  public final ConstraintsValidator constraintsValidator = new ConstraintsValidator();
  public final ProductRequestValidator productRequestValidator = new ProductRequestValidator(constraintsValidator);
  public final SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(constraintsValidator);
}
