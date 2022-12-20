package page.automationteststore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import model.automationteststore.Product;
import page.AbstractPageModel;


public class ProductPage extends AbstractPageModel {

    Locator addToCart;
    @Getter
    Product model;
    private static final String SIZE_RADIO_BUTTONS_LOCATOR = "css=div[class='input-group col-sm-10']>label>input[type='radio']";
    public ProductPage(Page page) {
        super(page);
        addToCart = page.locator("css=a[class='cart']");
    }

    public ProductPage(Page page, Product model) {
        this(page);
        this.model = model;
    }

    public CartPage add() {
        page.waitForLoadState();
        var radioButtons = page.querySelectorAll(SIZE_RADIO_BUTTONS_LOCATOR);
        if(!radioButtons.isEmpty()) {
            radioButtons.get(0).check();
        }
        addToCart.click();
        return new CartPage(page);
    }
}
