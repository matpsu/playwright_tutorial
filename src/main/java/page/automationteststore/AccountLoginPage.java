package page.automationteststore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.AbstractPageModel;

public class AccountLoginPage extends AbstractPageModel {
    Locator continueAsGuestCheckbox;
    Locator continueButton;

    public AccountLoginPage(Page page) {
        super(page);
        continueAsGuestCheckbox = page.locator("css=input[id='accountFrm_accountguest']");
        continueButton = page.getByTitle("Continue");
    }

    public GuestCheckoutPage continueAsGuest() {
        continueAsGuestCheckbox.check();
        continueButton.click();
        return new GuestCheckoutPage(page);
    }
}
