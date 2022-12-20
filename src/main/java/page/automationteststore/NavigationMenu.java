package page.automationteststore;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.AbstractPageModel;

public class NavigationMenu extends AbstractPageModel {
    Locator categories;
    Locator search;
    Locator searchButton;
    Locator cartQuantity;
    Locator cartAmount;
    private static final String SUBCATEGORIES_LOCATOR = "css=div[class='subcategories']>ul>li";
    public NavigationMenu(Page page) {
        super(page);
        categories = page.locator("css=ul[class='nav-pills categorymenu']>li");
        search = page.getByPlaceholder("Search Keywords");
        searchButton = page.locator("css=div[title='Go']>i[class='fa fa-search']");
        cartQuantity = page.locator("css=a[class='dropdown-toggle']>span[class='label label-orange font14']");
        cartAmount = page.locator("css=a[class='dropdown-toggle']>span[class='cart_total']");
    }

    public SubcateogryListing goTo(String category, String subcategory) {
        Locator categoryItem = categories.filter(new Locator.FilterOptions().setHasText(category));
        categoryItem.hover();

        categoryItem
                .locator(SUBCATEGORIES_LOCATOR)
                .filter(new Locator.FilterOptions().setHasText(subcategory))
                .click();

        return new SubcateogryListing(page);
    }

    public SubcateogryListing search(String forItem) {
        search.fill(forItem);
        searchButton.click();
        page.waitForLoadState();
        return new SubcateogryListing(page);
    }

    public int getNumberOfCartItems() {
        return Integer.parseInt(cartQuantity.textContent());
    }

    public float getCartAmount() {
        return Float.parseFloat(cartAmount.textContent().replace("$", ""));
    }
}
