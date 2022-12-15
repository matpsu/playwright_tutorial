import model.IdeType;
import model.LanguageType;
import model.ScriptType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.actionbuttons.DropdownModel;

import static org.assertj.core.api.Assertions.assertThat;

public class DropdownTest extends AbstractTest{
    private static final String URL = "https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
    private DropdownModel dropdownModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        dropdownModel = new DropdownModel(page);
    }

    @Test
    public void check_all_languages() {
        for(LanguageType type : LanguageType.values()) {
            dropdownModel.setLanguage(type);
            assertThat(dropdownModel.getSelectedLanguage()).isEqualTo(type.getType());
        }
    }

    @Test
    public void check_all_ide() {
        for(IdeType type : IdeType.values()) {
            dropdownModel.setIde(type);
            assertThat(dropdownModel.getSelectedIde()).isEqualTo(type.getType());
        }
    }

    @Test
    public void check_all_scripts() {
        for(ScriptType type : ScriptType.values()) {
            dropdownModel.setScript(type);
            assertThat(dropdownModel.getSelectedScript()).isEqualTo(type.getType());
        }
    }
}
