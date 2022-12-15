import model.Colors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.actionbuttons.RadiobuttonModel;
import page.datepicker.DatepickerModel;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DatepickerTest extends AbstractTest{
    private static final String URL = "https://webdriveruniversity.com/Datepicker/index.html";
    private DatepickerModel datepickerModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        datepickerModel = new DatepickerModel(page);
    }

    @DataProvider(name = "test1")
    public static Object[][] someDates() {
        return new Object[][] {
                {LocalDate.now().minusDays(1)},
                {LocalDate.now()},
                {LocalDate.now().plusDays(1)}};
    }

    @Test(dataProvider = "test1")
    public void check_each_and_validate(LocalDate date) {
        datepickerModel.pickDate(date);
        var pageDate = datepickerModel.getPickedDate();
        assertThat(pageDate).isEqualTo(date);
    }
}
