package page.contactus;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class ContactUsFormReusltModel {
    ContactUsThankYouModel ok;
    ContactUsErrorModel fail;

    public boolean isOk() {
        return Objects.isNull(fail);
    }
}
