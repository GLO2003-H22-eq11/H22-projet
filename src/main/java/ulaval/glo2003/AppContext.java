package ulaval.glo2003;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.product.api.offers.OffersAssembler;
import ulaval.glo2003.product.api.product.assembler.ProductAssembler;
import ulaval.glo2003.product.api.product.assembler.CategoryAssembler;
import ulaval.glo2003.product.api.product.ProductRequestValidator;
import ulaval.glo2003.product.api.product.ProductFactory;
import ulaval.glo2003.product.api.product.ProductFiltersFactory;
import ulaval.glo2003.product.api.product.assembler.ProductSellerAssembler;
import ulaval.glo2003.product.domain.AmountFactory;
import ulaval.glo2003.product.domain.product.CategoriesFactory;
import ulaval.glo2003.product.domain.product.ProductFilterer;
import ulaval.glo2003.product.domain.product.ProductIdFactory;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.product.domain.product.ProductSellerService;
import ulaval.glo2003.product.domain.product.ProductWithSellerFactory;
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
  public final ProductSellerAssembler productSellerAssembler = new ProductSellerAssembler();
  public final CategoryAssembler categoryAssembler = new CategoryAssembler();
  public final OffersAssembler offersAssembler = new OffersAssembler();
  public final ProductAssembler productAssembler = new ProductAssembler(offersAssembler, productSellerAssembler);
  public final SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);

  //factories
  public final CategoriesFactory categoriesFactory = new CategoriesFactory();
  public final SellerFactory sellerFactory = new SellerFactory();
  public final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  public final ProductFactory productFactory = new ProductFactory(categoryAssembler, sellerIdFactory);
  public final ProductIdFactory productIdFactory = new ProductIdFactory();
  public final AmountFactory amountFactory = new AmountFactory();
  public final ProductFiltersFactory productFiltersFactory = new ProductFiltersFactory(
          sellerIdFactory,
          amountFactory,
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
