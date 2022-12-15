package page;

import com.microsoft.playwright.Page;

public abstract class AbstractPageModel {
    protected final Page page;

    public AbstractPageModel(Page page) {
        this.page = page;
        PageFactory.init(this, page);
    }
}
