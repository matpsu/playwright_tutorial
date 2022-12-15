package page.actionbuttons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import model.IdeType;
import model.LanguageType;
import model.ScriptType;
import page.AbstractPageModel;

import java.util.ArrayList;
import java.util.List;

public class CheckboxModel extends AbstractPageModel {
    List<Locator> checkboxes = new ArrayList<>();

    public CheckboxModel(Page page) {
        super(page);
        checkboxes.add(page.locator("css=input[value='option-1']"));
        checkboxes.add(page.locator("css=input[value='option-2']"));
        checkboxes.add(page.locator("css=input[value='option-3']"));
        checkboxes.add(page.locator("css=input[value='option-4']"));
    }

    public CheckboxModel setCheckbox(int number) {
        checkIfNotOutOfBounds(number);
        checkboxes.get(number - 1).check();
        return this;
    }

    public CheckboxModel uncheckCheckbox(int number) {
        checkIfNotOutOfBounds(number);
        checkboxes.get(number - 1).uncheck();
        return this;
    }

    public boolean isChecked(int number) {
        checkIfNotOutOfBounds(number);
        return checkboxes.get(number - 1).isChecked();
    }

    public CheckboxModel checkAllBoxes() {
        for(Locator checkbox : checkboxes) {
            checkbox.check();
        }
        return this;
    }

    private void checkIfNotOutOfBounds(int number) {
        if(number - 1 > checkboxes.size()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
