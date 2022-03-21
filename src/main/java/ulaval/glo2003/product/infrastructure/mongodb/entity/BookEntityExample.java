package ulaval.glo2003.product.infrastructure.mongodb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

import java.util.concurrent.ThreadLocalRandom;

@Entity("Books")
public class BookEntityExample {
  @Id
  private String isbn;
  private String title;
  private String author;
  @Property("price")
  private double cost;


  public BookEntityExample() {
    this.isbn = "cadfsgfv";
    this.title = "aie aie aie aie";
    this.author = "dsvgbnhbfdsfdx bfgvs";
    this.cost = ThreadLocalRandom.current().nextDouble(0, 2);
  }
}
