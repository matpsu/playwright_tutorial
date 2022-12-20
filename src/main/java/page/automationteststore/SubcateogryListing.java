package page.automationteststore;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import lombok.Getter;
import model.automationteststore.Product;
import page.AbstractPageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubcateogryListing extends AbstractPageModel {
    List<ElementHandle> elements;
    @Getter
    List<Product> products = new ArrayList<>();
    private static final String ADD_TO_CART_LOCATOR = "css=i[class='fa fa-cart-plus fa-fw']";
    private static final String PRODUCT_NAME_LOCATOR = "css=a[class='prdocutname']";
    private static final String PRODUCT_PRICE_LOCATOR = "css=div[class='oneprice']";

    public SubcateogryListing(Page page) {
        super(page);
        elements = page.querySelectorAll("css=div[class='col-md-3 col-sm-6 col-xs-12']");
        readProducts();
    }

    private void readProducts() {
        for(ElementHandle handle : elements) {
            products.add(Product.builder()
                    .name(handle.querySelector(PRODUCT_NAME_LOCATOR).getAttribute("title"))
                    .price(Float.parseFloat(handle.querySelector(PRODUCT_PRICE_LOCATOR).textContent().replace("$", "")))
                    .isAvailable(!Objects.isNull(handle.querySelector(ADD_TO_CART_LOCATOR)))
                    .build());
        }
    }

    public ProductPage addToCart(Product product) {
        elements.get(products.indexOf(product))
                .querySelector(ADD_TO_CART_LOCATOR)
                .click();
        return new ProductPage(page, product);
    }

    public ProductPage addToCartByName(String productName) throws Exception {
        Product product = products.stream()
                .filter(x -> x.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new Exception("No such product <%s>".formatted(productName)));

        elements.get(products.indexOf(product))
                .querySelector(PRODUCT_NAME_LOCATOR)
                .click();
        return new ProductPage(page, product);
    }
}
