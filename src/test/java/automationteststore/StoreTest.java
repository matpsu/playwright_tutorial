package automationteststore;

import common.AbstractTest;
import model.automationteststore.Product;
import model.automationteststore.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.automationteststore.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreTest extends AbstractTest {
    private static final String URL = "https://automationteststore.com/";
    private NavigationMenu navigationMenu;
    List<Product> expectedProducts = new ArrayList<>();
    private static final int EXPECTED_VALIDATION_ERRORS = 7;
    private static final UserData USER_DATA = UserData.builder()
            .firstName("Dev")
            .lastName("Null")
            .email("uefi@bios.rom")
            .country("Portugal")
            .postalCode("12345")
            .state("Beja")
            .address("i2c 52/8")
            .city("RAM")
            .build();

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
        assertThat(userFormPage
                .continueWithEmptyData()
                .getNumberOfValidationErrors())
                .isEqualTo(EXPECTED_VALIDATION_ERRORS);

        CheckoutInformationPage checkoutInformationPage = userFormPage
                .fillUserData(USER_DATA)
                .continueWithFilledForm();

        UserData userData = checkoutInformationPage.getShippingData();
        assertThat(userData).isEqualTo(USER_DATA);
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
