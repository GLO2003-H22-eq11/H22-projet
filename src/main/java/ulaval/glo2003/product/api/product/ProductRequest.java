package ulaval.glo2003.product.api.product;

import jakarta.validation.constraints.NotNull;
import ulaval.glo2003.exception.GenericRequest;

import java.util.List;

public class ProductRequest extends GenericRequest {
  @NotNull
  public String title;

  @NotNull
  public String description;

  @NotNull
  public int suggestedPrice;

  @NotNull
  public List<String> categories;
}
