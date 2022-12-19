package page.automationteststore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
        lastName = page.locator("css=input[name='firstname']");
        email = page.locator("css=input[name='firstname']");
        address = page.locator("css=input[name='firstname']");
        city = page.locator("css=input[name='firstname']");
        postalCode = page.locator("css=input[name='firstname']");
        state = page.locator("css=input[name='firstname']");
        country = page.locator("css=input[name='firstname']");
        continueButton = page.getByTitle("Continue");
    }

    public int getNumberOfValidationErrors() {
        return (int) page.querySelectorAll(VALIDATION_ERROR_LOCATOR).stream()
                .filter(x -> !x.textContent().isEmpty())
                .count();
    }

    public GuestCheckoutPage continueWithEmptyData() {
        continueButton.click();
        page.waitForLoadState();
        return this;
    }
}
