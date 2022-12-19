package html_elements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.actionbuttons.CheckboxModel;
import common.AbstractTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxTest extends AbstractTest {
    private static final String URL = "https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
    private CheckboxModel checkboxModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        checkboxModel = new CheckboxModel(page);
    }

    @Test
    public void check_all_checkboxes_and_deselect_two_of_them() {
        checkboxModel.checkAllBoxes();
        for(int i = 1; i < 4; i++) {
            assertThat(checkboxModel.isChecked(i)).isTrue();
        }

        checkboxModel
                .uncheckCheckbox(2)
                .uncheckCheckbox(4);

        for(int i = 1; i < 4; i++) {
            var assertion = assertThat(checkboxModel.isChecked(i));
            if(i % 2 == 0) {
                assertion.isFalse();
            } else {
                assertion.isTrue();
            }
        }
    }
}
