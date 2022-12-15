package page.datepicker;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import page.AbstractPageModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Pattern;

public class DatepickerModel extends AbstractPageModel {
    Locator datepickerInput;
    Locator callendarIcon;
    Locator datepickerSwitch;

    private static final String DAY_SELECTOR = "css=td[class='day']";
    private static final String MONTH_SELECTOR = "css=span[class='month']";
    private static final String YEAR_SELECTOR = "css=span[class='year']";

    public DatepickerModel(Page page) {
        super(page);
        datepickerInput = page.locator("css=input[class='form-control']");
        callendarIcon = page.locator("css=i[class='glyphicon glyphicon-calendar']");
        datepickerSwitch = page.locator("css=th[class='datepicker-switch']");
    }

    public DatepickerModel pickDate(LocalDate localDate) {
        LocalDate currentlySelected = getPickedDate();
        if(currentlySelected.isEqual(localDate)) {
            return this;
        }

        callendarIcon.click();

        if(currentlySelected.getMonthValue() != localDate.getMonthValue()){
            String monthSwitchLocator = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + localDate.getYear();
            datepickerSwitch
                    .filter(new Locator.FilterOptions().setHasText(monthSwitchLocator))
                    .click();
        }

        if(currentlySelected.getYear() != localDate.getYear()) {
            Pattern yearPattern = Pattern.compile("^%s$".formatted(localDate.getYear()));
            datepickerSwitch
                    .filter(new Locator.FilterOptions().setHasText(yearPattern))
                    .click();
            pickYear(localDate.getYear());
        }

        if(currentlySelected.getMonthValue() != localDate.getMonthValue()) {
            pickMonth(localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US));
        }

        pickDay(localDate.getDayOfMonth());
        return this;
    }

    public void pickYear(int year) {
        Locator yearElement = page
                .locator(YEAR_SELECTOR)
                .filter(new Locator.FilterOptions().setHasText(String.valueOf(year)));
        yearElement.click();
    }

    public void pickMonth(String month) {
        Locator monthElement = page
                .locator(MONTH_SELECTOR)
                .filter(new Locator.FilterOptions().setHasText(month));
        monthElement.click();
    }

    private void pickDay(int day) {
        Locator dayElement = page
                .locator(DAY_SELECTOR)
                .filter(new Locator.FilterOptions().setHasText(String.valueOf(day)));
        dayElement.click();
    }

    public LocalDate getPickedDate() {
        return LocalDate.parse(datepickerInput.inputValue(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }
}
