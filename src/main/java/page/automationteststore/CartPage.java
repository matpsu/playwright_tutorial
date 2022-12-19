package page.automationteststore;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import model.Product;
import page.AbstractPageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CartPage extends AbstractPageModel {
    List<ElementHandle> elements;
    @Getter
    List<Product> products = new ArrayList<>();
    private static final String PRODUCT_NAME_LOCATOR = "css=td:nth-child(2)>a";
    private static final String PRODUCT_PRICE_LOCATOR = "css=td:nth-child(4)";
    Locator checkout;

    public CartPage(Page page) {
        super(page);
        checkout = page.locator("css=#cart_checkout1");
        elements = page.querySelectorAll("css=div[class='container-fluid cart-info product-list']>table>tbody>tr");
        // skip column names
        elements.remove(0);
        readProducts();
    }

    private void readProducts() {
        for(ElementHandle handle : elements) {
            products.add(Product.builder()
                    .name(handle.querySelector(PRODUCT_NAME_LOCATOR).textContent())
                    .price(Float.parseFloat(handle.querySelector(PRODUCT_PRICE_LOCATOR).textContent().replace("$", "")))
                    .isAvailable(!Objects.isNull(handle.querySelector(PRODUCT_NAME_LOCATOR)))
                    .build());
        }
    }

    public AccountLoginPage checkout() {
        checkout.click();
        return new AccountLoginPage(page);
    }
}
