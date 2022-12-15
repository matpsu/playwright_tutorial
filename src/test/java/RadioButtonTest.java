import model.Colors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.actionbuttons.CheckboxModel;
import page.actionbuttons.RadiobuttonModel;

import static org.assertj.core.api.Assertions.assertThat;

public class RadioButtonTest extends AbstractTest{
    private static final String URL = "https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
    private RadiobuttonModel radiobuttonModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        radiobuttonModel = new RadiobuttonModel(page);
    }

    @Test
    public void check_each_and_validate() {
        for(Colors color : Colors.values()) {
            radiobuttonModel.setRadiobutton(color);
            assertThat(radiobuttonModel.isChecked(color)).isTrue();
        }
    }
}
