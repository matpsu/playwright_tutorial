package page.contactus;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.AbstractPageModel;

public class ContactUsThankYouModel extends AbstractPageModel {
    Locator message;

    public ContactUsThankYouModel(Page page) {
        super(page);
        message = page.getByText("Thank You for your Message!");
    }

    public String getMessage() {
        return message.textContent();
    }
}
