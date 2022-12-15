package page.contactus;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import page.AbstractPageModel;

public class ContactUsErrorModel extends AbstractPageModel {
    Locator errorLocator;
    @Getter
    String error;
    @Getter
    String reason;

    public ContactUsErrorModel(Page page) {
        super(page);
        errorLocator = page.locator("body");
        var splitResults = errorLocator.textContent().trim().split("\n");

        if(splitResults.length > 1) {
            error = splitResults[0].trim();
            reason = splitResults[1].trim();
        } else {
            error = "";
            reason = splitResults[0];
        }
    }
}
