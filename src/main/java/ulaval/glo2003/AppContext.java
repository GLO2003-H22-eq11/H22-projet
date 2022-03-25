package ulaval.glo2003;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.product.api.validator.OfferRequestValidator;
import ulaval.glo2003.product.api.assembler.OffersAssembler;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.product.api.validator.ProductRequestValidator;
import ulaval.glo2003.product.api.ProductFactory;
import ulaval.glo2003.product.api.ProductFiltersFactory;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.OfferRepository;
import ulaval.glo2003.product.domain.ProductIdFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductWithOffersFactory;
import ulaval.glo2003.product.domain.SellerWithProductsOffersFactory;
import ulaval.glo2003.product.domain.OfferFactory;
import ulaval.glo2003.product.domain.ProductFilterer;
import ulaval.glo2003.product.domain.ProductWithSellerFactory;
import ulaval.glo2003.product.domain.ProductOfferDomainService;
import ulaval.glo2003.product.domain.ProductSellerDomainService;
import ulaval.glo2003.product.infrastructure.inMemory.InMemoryOfferRepository;
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

  public final CategoriesFactory categoriesFactory = new CategoriesFactory();
  public final SellerFactory sellerFactory = new SellerFactory();
  public final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  public final ProductIdFactory productIdFactory = new ProductIdFactory();
  public final ProductFactory productFactory = new ProductFactory(sellerIdFactory, productIdFactory, categoriesFactory);
  public final ProductFiltersFactory productFiltersFactory = new ProductFiltersFactory(
          sellerIdFactory,
          categoriesFactory
  );
  public final ProductWithSellerFactory productWithSellerFactory = new ProductWithSellerFactory();
  public final OfferFactory offerFactory = new OfferFactory(productIdFactory);
  public final SellerWithProductsOffersFactory sellerWithProductsOffersFactory = new SellerWithProductsOffersFactory();
  public final ProductWithOffersFactory productWithOffersFactory = new ProductWithOffersFactory();


  public final OffersAssembler offersAssembler = new OffersAssembler();
  public final ProductAssembler productAssembler = new ProductAssembler(offersAssembler);
  public final SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);

  public final SellerRepository sellerRepository = new InMemorySellerRepository();
  public final ProductRepository productRepository = new InMemoryProductRepository();
  public final OfferRepository offerRepository = new InMemoryOfferRepository();

  public final ProductSellerDomainService productSellerDomainService = new ProductSellerDomainService(
          productWithSellerFactory,
          sellerRepository
  );
  public final ProductFilterer productFilterer = new ProductFilterer(productRepository);
  public final ProductOfferDomainService productOfferDomainService = new ProductOfferDomainService(
          productRepository,
          offerRepository,
          productWithOffersFactory,
          sellerWithProductsOffersFactory);


  public final SellerService sellerService = new SellerService(
          sellerRepository,
          productRepository,
          productOfferDomainService);

  public final ProductService productService = new ProductService(
          productRepository,
          sellerRepository,
          productSellerDomainService,
          productFilterer,
          offerRepository);


  public final ConstraintsValidator constraintsValidator = new ConstraintsValidator();
  public final ProductRequestValidator productRequestValidator = new ProductRequestValidator(constraintsValidator);
  public final SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(constraintsValidator);
  public final OfferRequestValidator offerRequestValidator = new OfferRequestValidator(constraintsValidator);
}
