package page.ajax;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import page.AbstractPageModel;


public class AjaxModel extends AbstractPageModel {
    Locator loader;
    Locator clickMe;
    Locator wellDoneText;
    Locator wellDoneClose;

    public AjaxModel(Page page) {
        super(page);
        loader = page.locator("css=loader");
        clickMe = page.locator("css=#button1");
        wellDoneText = page.getByText("Well Done For Waiting....!!!");
        wellDoneClose = page.getByRole(AriaRole.BUTTON).getByText("Close");
    }

    public AjaxModel clickMe() {
        Locator.WaitForOptions waitForOptions = new Locator.WaitForOptions();
        waitForOptions.state = WaitForSelectorState.HIDDEN;
        loader.waitFor(waitForOptions);
        clickMe.click();
        return this;
    }

    public AjaxModel closePopup() {
        wellDoneText.waitFor();
        wellDoneClose.click();
        return this;
    }
}
