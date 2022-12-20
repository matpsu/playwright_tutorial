package model.automationteststore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    String firstName;
    String lastName;
    String email;
    String address;
    String city;
    String postalCode;
    String state;
    String country;
}
