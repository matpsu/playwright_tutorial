package model.automationteststore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    String name;
    float price;
    boolean isAvailable;
}
