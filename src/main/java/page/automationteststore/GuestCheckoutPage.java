package page.automationteststore;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import model.automationteststore.UserData;
import page.AbstractPageModel;

public class GuestCheckoutPage extends AbstractPageModel {
    Locator firstName;
    Locator lastName;
    Locator email;
    Locator address;
    Locator city;
    Locator postalCode;
    Locator state;
    Locator country;
    Locator continueButton;
    private static final String VALIDATION_ERROR_LOCATOR = "span[class='help-block']";

    public GuestCheckoutPage(Page page) {
        super(page);
        firstName = page.locator("css=input[name='firstname']");
        lastName = page.locator("css=input[name='lastname']");
        email = page.locator("css=input[id='guestFrm_email']");
        address = page.locator("css=input[name='address_1']");
        city = page.locator("css=input[name='city']");
        postalCode = page.locator("css=input[name='postcode']");
        state = page.locator("css=select[name='zone_id']");
        country = page.locator("css=select[name='country_id']");
        continueButton = page.getByTitle("Continue");
    }

    public int getNumberOfValidationErrors() {
        return (int) page.querySelectorAll(VALIDATION_ERROR_LOCATOR).stream()
                .filter(x -> !x.textContent().isEmpty())
                .count();
    }

    public CheckoutInformationPage continueWithFilledForm() {
        continueButton.click();
        page.waitForLoadState();
        return new CheckoutInformationPage(page);
    }

    public GuestCheckoutPage continueWithEmptyData() {
        continueButton.click();
        page.waitForLoadState();
        return this;
    }

    public GuestCheckoutPage fillUserData(UserData userData) {
        firstName.fill(userData.getFirstName());
        lastName.fill(userData.getLastName());
        email.fill(userData.getEmail());
        address.fill(userData.getAddress());
        selectByValue(userData.getCountry(), country)
                .selectByValue(userData.getState(), state);
        postalCode.fill(userData.getPostalCode());
        city.fill(userData.getCity());
        return this;
    }

    private GuestCheckoutPage selectByValue(String value, Locator handle) {
        SelectOption option = new SelectOption().setLabel(value);
        handle.selectOption(option);
        return this;
    }
}
