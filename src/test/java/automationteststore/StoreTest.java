package automationteststore;

import common.AbstractTest;
import model.Product;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.automationteststore.CartPage;
import page.automationteststore.NavigationMenu;
import page.automationteststore.ProductPage;
import page.automationteststore.SubcateogryListing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreTest extends AbstractTest {
    private static final String URL = "https://automationteststore.com/";
    private NavigationMenu navigationMenu;
    List<Product> expectedProducts = new ArrayList<>();
    private static final int EXPECTED_VALIDATION_ERRORS = 7;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
    }

    @DataProvider(name = "test1")
    public static Object[][] data() {
        return new Object[][] {
                {"Apparel & accessories", "Shoes", "T-shirts", "BODY CREAM BY BULGARI"},
                };
    }

    @Test(dataProvider = "test1")
    public void make_order_and_validate_results(String category, String sub1, String sub2, String productName) throws Exception {
        CartPage p1 = addToCart(category, sub1);
        assertThat(p1.getProducts()).isEqualTo(expectedProducts);
        validateCartItems();

        CartPage p2 = addToCart(category, sub2);
        assertThat(p2.getProducts()).isEqualTo(expectedProducts);
        validateCartItems();

        navigationMenu = new NavigationMenu(page);
        ProductPage productPage = navigationMenu
                .search(productName)
                .addToCartByName(productName);
        expectedProducts.add(productPage.getModel());
        CartPage p3 = productPage.add();
        assertThat(p3.getProducts()).isEqualTo(expectedProducts);
        validateCartItems();

        var userFormPage = p3.checkout().continueAsGuest();
        assertThat(userFormPage.continueWithEmptyData().getNumberOfValidationErrors()).isEqualTo(EXPECTED_VALIDATION_ERRORS);
    }

    private CartPage addToCart(String category, String sub) throws Exception {
        navigationMenu = new NavigationMenu(page);
        SubcateogryListing subcateogryListing = navigationMenu.goTo(category, sub);
        List<Product> shoes = subcateogryListing.getProducts();

        Product pickedProduct = shoes.stream()
                .filter(Product::isAvailable)
                .findAny()
                .orElseThrow(() -> new Exception("No %s available".formatted(sub)));

        expectedProducts.add(pickedProduct);

        return subcateogryListing
                .addToCart(pickedProduct)
                .add();
    }

    private void validateCartItems() {
        navigationMenu = new NavigationMenu(page);
        assertThat(navigationMenu.getNumberOfCartItems()).isEqualTo(expectedProducts.size());
        assertThat((double)navigationMenu.getCartAmount())
                .isEqualTo((Double) expectedProducts.stream().mapToDouble(Product::getPrice).sum());
    }
}
