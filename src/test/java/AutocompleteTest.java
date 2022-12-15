import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.autocomplete.AutocompleteModel;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AutocompleteTest extends AbstractTest{
    private static final String URL = "https://webdriveruniversity.com/Autocomplete-TextField/autocomplete-textfield.html";
    private AutocompleteModel autocompleteModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        autocompleteModel = new AutocompleteModel(page);
    }

    @DataProvider(name = "test1")
    public static Object[][] someStrings() {
        return new Object[][] {
                {"ba"}
        };
    }

    @Test(dataProvider = "test1")
    public void fill_input_and_try_to_pick_one_option(String testData) {
        autocompleteModel.fill(testData);
        List<String> results = autocompleteModel.getOptions();

        assertThat(results).isNotEmpty();

        int elementNo = new Random().nextInt(results.size());
        autocompleteModel.pickOption(results.get(elementNo));

        assertThat(autocompleteModel.getValue()).isEqualTo(results.get(elementNo));
        assertThat(autocompleteModel.getValue()).startsWithIgnoringCase(testData);
    }
}
