package ulaval.glo2003;

import dev.morphia.Datastore;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.product.api.assembler.BuyerAssembler;
import ulaval.glo2003.product.api.validator.OfferRequestValidator;
import ulaval.glo2003.product.api.assembler.OffersAssembler;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.product.api.validator.ProductRequestValidator;
import ulaval.glo2003.product.api.ProductFactory;
import ulaval.glo2003.product.api.ProductFiltersFactory;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.OfferIdFactory;
import ulaval.glo2003.product.domain.OfferRepository;
import ulaval.glo2003.product.domain.OffersInformationFactory;
import ulaval.glo2003.product.domain.ProductIdFactory;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductWithOffersFactory;
import ulaval.glo2003.product.domain.OfferFactory;
import ulaval.glo2003.product.domain.ProductFilterer;
import ulaval.glo2003.product.domain.ProductWithSellerFactory;
import ulaval.glo2003.product.infrastructure.mongodb.MongoDbOfferAssembler;
import ulaval.glo2003.product.infrastructure.mongodb.MongoDbProductAssembler;
import ulaval.glo2003.product.infrastructure.mongodb.repository.MongoDBProductRepository;
import ulaval.glo2003.product.infrastructure.mongodb.repository.MongoDbOfferRepository;
import ulaval.glo2003.seller.domain.SellerWithProductsDomainService;
import ulaval.glo2003.product.domain.ProductWithSellerDomainService;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.seller.api.SellerAssembler;
import ulaval.glo2003.seller.api.SellerFactory;
import ulaval.glo2003.seller.api.SellerRequestValidator;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.infrastructure.MongoDbSellerAssembler;
import ulaval.glo2003.seller.infrastructure.mongoDb.repository.MongoDBSellerRepository;
import ulaval.glo2003.seller.service.SellerService;

public class AppContext {

  public final CategoriesFactory categoriesFactory = new CategoriesFactory();
  public final SellerFactory sellerFactory = new SellerFactory();
  public final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  public final ProductIdFactory productIdFactory = new ProductIdFactory();
  public final OfferIdFactory offerIdFactory = new OfferIdFactory();
  public final ProductFactory productFactory = new ProductFactory(sellerIdFactory, productIdFactory, categoriesFactory);
  public final ProductFiltersFactory productFiltersFactory = new ProductFiltersFactory(
          sellerIdFactory,
          categoriesFactory
  );
  public final ProductWithSellerFactory productWithSellerFactory = new ProductWithSellerFactory();
  public final OffersInformationFactory offersInformationFactory = new OffersInformationFactory();
  public final ProductWithOffersFactory productWithOffersFactory = new ProductWithOffersFactory(
          offersInformationFactory
  );
  public final OfferFactory offerFactory = new OfferFactory(productIdFactory, offerIdFactory);


  public final BuyerAssembler buyerAssembler = new BuyerAssembler();
  public final OffersAssembler offersAssembler = new OffersAssembler(buyerAssembler);
  public final ProductAssembler productAssembler = new ProductAssembler(offersAssembler);
  public final SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);
  public final MongoDbSellerAssembler mongoDbSellerAssembler = new MongoDbSellerAssembler();
  public final MongoDbProductAssembler mongoDbProductAssembler = new MongoDbProductAssembler();
  public final MongoDbOfferAssembler mongoDbOfferAssembler = new MongoDbOfferAssembler();

  public final Datastore datastore = DatastoreProvider.getDatastore();

  public final SellerRepository sellerRepository = new MongoDBSellerRepository(
          datastore,
          mongoDbSellerAssembler
  );
  public final ProductRepository productRepository = new MongoDBProductRepository(
          datastore,
          mongoDbProductAssembler
  );
  public final OfferRepository offerRepository = new MongoDbOfferRepository(datastore, mongoDbOfferAssembler);


  public final ProductWithSellerDomainService productWithSellerDomainService = new ProductWithSellerDomainService(
          productWithSellerFactory,
          sellerRepository
  );
  public final ProductFilterer productFilterer = new ProductFilterer(productRepository);
  public final SellerWithProductsDomainService sellerWithProductsDomainService = new SellerWithProductsDomainService(
          productRepository,
          offerRepository,
          productWithOffersFactory
  );


  public final SellerService sellerService = new SellerService(
          sellerRepository,
          productRepository,
          sellerWithProductsDomainService
  );

  public final ProductService productService = new ProductService(
          productRepository,
          sellerRepository,
          productWithSellerDomainService,
          productFilterer,
          offerRepository
  );

  public final ConstraintsValidator constraintsValidator = new ConstraintsValidator();
  public final ProductRequestValidator productRequestValidator = new ProductRequestValidator(constraintsValidator);
  public final SellerRequestValidator sellerRequestValidator = new SellerRequestValidator(constraintsValidator);
  public final OfferRequestValidator offerRequestValidator = new OfferRequestValidator(constraintsValidator);
}
