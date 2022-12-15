import model.ContactUs;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.contactus.ContactUsFormReusltModel;
import page.contactus.ContactUsModel;
import static org.assertj.core.api.Assertions.*;

public class ContactUsTest extends AbstractTest{
    private static final String URL = "https://webdriveruniversity.com/Contact-Us/contactus.html";
    private ContactUsModel contactUsModel;

    @BeforeMethod
    public void beforeMethodCurrentClass() {
        page.navigate(URL);
        contactUsModel = new ContactUsModel(page);
    }

    @Test
    public void form_submitted_correctly_on_all_fields_filled() {
        ContactUs contactUs = ContactUs.builder()
                .firstName("Play")
                .lastName("Wright")
                .email("play@wright.com")
                .comments("comments")
                .build();

        ContactUs filledForm = contactUsModel
                .fillForm(contactUs)
                .getFormData();

        assertThat(filledForm).isEqualTo(contactUs);

        var results = contactUsModel.submit();
        assertThat(results.isOk()).isTrue();
        assertThat(results.getOk().getMessage()).isEqualTo("Thank You for your Message!");
    }

    @Test
    public void form_reset_correctly_on_all_fields_filled() {
        ContactUs contactUs = ContactUs.builder()
                .firstName("Play")
                .lastName("Wright")
                .email("play@wright.com")
                .comments("comments")
                .build();

        ContactUs filledForm = contactUsModel
                .fillForm(contactUs)
                .getFormData();

        ContactUs resetForm = contactUsModel
                .reset()
                .getFormData();

        assertThat(filledForm).isEqualTo(contactUs);
        assertThat(resetForm).isNotEqualTo(contactUs);
        assertThat(resetForm.isEmpty()).isTrue();
    }

    @Test
    public void check_validation_error_on_partially_filled_form() {
        ContactUs contactUs = ContactUs.builder()
                .firstName("Play")
                .lastName("Wright")
                .build();

       ContactUsFormReusltModel result = contactUsModel
                .fillForm(contactUs)
                .submit();

       assertThat(result.isOk()).isFalse();
       assertThat(result.getFail().getError()).isEqualTo("Error: all fields are required");
       assertThat(result.getFail().getReason()).isEqualTo("Error: Invalid email address");
    }

    @Test
    public void check_validation_error_on_invalid_email() {
        ContactUs contactUs = ContactUs.builder()
                .firstName("Play")
                .lastName("Wright")
                .email("sdfghjiuy")
                .comments("some comments")
                .build();

        ContactUsFormReusltModel result = contactUsModel
                .fillForm(contactUs)
                .submit();

        assertThat(result.isOk()).isFalse();
        assertThat(result.getFail().getError()).isEmpty();
        assertThat(result.getFail().getReason()).isEqualTo("Error: Invalid email address");
    }
}
