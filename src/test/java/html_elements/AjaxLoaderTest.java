package html_elements;

import common.AbstractTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ajax.AjaxModel;

public class AjaxLoaderTest extends AbstractTest {
    private static final String URL = "https://webdriveruniversity.com/Ajax-Loader/index.html";
    private AjaxModel ajaxModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        ajaxModel = new AjaxModel(page);
    }

    @Test
    public void test_ajax_loader() {
        ajaxModel
                .clickMe()
                .closePopup();
    }
}
