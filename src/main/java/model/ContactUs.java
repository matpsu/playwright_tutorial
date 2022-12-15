package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactUs {
    String firstName ;
    String lastName;
    String email;
    String comments;

    public boolean isEmpty() {
        return firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && comments.isEmpty();
    }
}
