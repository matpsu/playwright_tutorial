package page.automationteststore;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import model.automationteststore.UserData;
import page.AbstractPageModel;

import java.util.List;

public class CheckoutInformationPage extends AbstractPageModel {
    private static final String SHIPPING_TABLE_LOCATOR = "css=table[class='table confirm_shippment_options']>tbody>tr>td";
    public CheckoutInformationPage(Page page) {
        super(page);
        page.waitForLoadState();
    }

    public UserData getShippingData() {
        List<ElementHandle> allTRs = page.querySelectorAll(SHIPPING_TABLE_LOCATOR);
        String[] name = allTRs.get(0).textContent().trim().split(" ");
        String[] address = allTRs.get(1).textContent().trim().split("\n");
        String[] addressDetails = address[1].split(" ");
        return UserData.builder()
                .firstName(name[0].trim())
                .lastName(name[1].trim())
                .country(address[2].trim())
                .address(address[0].trim())
                .city(addressDetails[0].trim())
                .state(addressDetails[1].trim())
                .postalCode(addressDetails[2].trim())
                .build();
    }
}
