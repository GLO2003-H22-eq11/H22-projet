package ulaval.glo2003;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.products.api.OffersAssembler;
import ulaval.glo2003.products.api.ProductAssembler;
import ulaval.glo2003.products.api.ProductCategoryAssembler;
import ulaval.glo2003.products.api.ProductFactory;
import ulaval.glo2003.products.domain.ProductRepository;
import ulaval.glo2003.products.infrastructure.inMemory.InMemoryProductRepository;
import ulaval.glo2003.products.service.ProductService;
import ulaval.glo2003.seller.api.SellerAssembler;
import ulaval.glo2003.seller.api.SellerFactory;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.infrastructure.inMemory.InMemorySellerRepository;
import ulaval.glo2003.seller.service.SellerService;

public class AppContext {
  //assemblers
  public final ProductCategoryAssembler productCategoryAssembler = new ProductCategoryAssembler();
  public final OffersAssembler offersAssembler = new OffersAssembler();
  public final ProductAssembler productAssembler = new ProductAssembler(offersAssembler);
  public final SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);

  //factories
  public final SellerFactory sellerFactory = new SellerFactory();
  public final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  public final ProductFactory productFactory = new ProductFactory(productCategoryAssembler, sellerIdFactory);

  //repositories
  public final SellerRepository sellerRepository = new InMemorySellerRepository();
  public final ProductRepository productRepository = new InMemoryProductRepository();

  //services
  public final SellerService sellerService = new SellerService(sellerRepository);
  public final ProductService productService = new ProductService(productRepository);


  public final ConstraintsValidator constraintsValidator = new ConstraintsValidator();
}
