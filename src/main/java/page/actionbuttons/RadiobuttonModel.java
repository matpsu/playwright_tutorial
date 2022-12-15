package page.actionbuttons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import model.Colors;
import page.AbstractPageModel;

public class RadiobuttonModel extends AbstractPageModel {
    private static final String RADIO_BUTTON_LOCATOR = "css=input[value='%s']";

    public RadiobuttonModel(Page page) {
        super(page);
    }

    public RadiobuttonModel setRadiobutton(Colors color) {
        Locator radioButton = page.locator(RADIO_BUTTON_LOCATOR.formatted(color.getType()));
        radioButton.check();
        return this;
    }

    public boolean isChecked(Colors color) {
        Locator radioButton = page.locator(RADIO_BUTTON_LOCATOR.formatted(color.getType()));
        return radioButton.isChecked();
    }
}
