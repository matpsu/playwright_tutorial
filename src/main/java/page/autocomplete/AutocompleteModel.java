package page.autocomplete;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.AbstractPageModel;

import java.util.Collections;
import java.util.List;


public class AutocompleteModel extends AbstractPageModel {
    Locator autocompleteInput;
    Locator autocompleteList;

    private static final String AUTOCOMPLETE_LOCATOR = "css=#myInputautocomplete-list>div";

    public AutocompleteModel(Page page) {
        super(page);
        autocompleteInput = page.getByPlaceholder("Food Item");
        autocompleteList = page.locator("css=#myInputautocomplete-list");
    }

    public AutocompleteModel fill(String name) {
        autocompleteInput.clear();
        autocompleteInput.fill(name);
        autocompleteList.waitFor();
        return this;
    }

    public AutocompleteModel pickOption(String option) {
        page.locator(AUTOCOMPLETE_LOCATOR)
                .filter(new Locator.FilterOptions().setHasText(option))
                .click();
        return this;
    }

    public List<String> getOptions() {
        if(!autocompleteList.isVisible()) {
            return Collections.emptyList();
        }
        return page.querySelectorAll(AUTOCOMPLETE_LOCATOR)
                .stream()
                .map(x -> x.textContent())
                .toList();
    }

    public String getValue() {
        return autocompleteInput.inputValue();
    }
}
