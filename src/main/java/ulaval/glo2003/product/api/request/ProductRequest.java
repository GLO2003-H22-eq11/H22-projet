package ulaval.glo2003.product.api.request;

import jakarta.validation.constraints.NotNull;
import ulaval.glo2003.exception.GenericRequest;

import java.util.List;

public class ProductRequest extends GenericRequest {
  @NotNull
  public String title;

  @NotNull
  public String description;

  @NotNull
  public Double suggestedPrice;

  @NotNull
  public List<String> categories;
}
