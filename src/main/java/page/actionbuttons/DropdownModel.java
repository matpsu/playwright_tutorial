package page.actionbuttons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import model.IdeType;
import model.LanguageType;
import model.ScriptType;
import page.AbstractPageModel;

public class DropdownModel extends AbstractPageModel {
    Locator language;
    Locator ide;
    Locator script;

    public DropdownModel(Page page) {
        super(page);
        language = page.locator("css=select[id='dropdowm-menu-1']");
        ide = page.locator("css=select[id='dropdowm-menu-2']");
        script = page.locator("css=select[id='dropdowm-menu-3']");
    }

    public DropdownModel setLanguage(LanguageType languageType) {
        language.selectOption(languageType.getType());
        return this;
    }

    public String getSelectedLanguage() {
        return language.inputValue();
    }

    public DropdownModel setIde(IdeType ideType) {
        ide.selectOption(ideType.getType());
        return this;
    }

    public String getSelectedIde() {
        return ide.inputValue();
    }

    public DropdownModel setScript(ScriptType scriptType) {
        script.selectOption(scriptType.getType());
        return this;
    }

    public String getSelectedScript() {
        return script.inputValue();
    }
}
