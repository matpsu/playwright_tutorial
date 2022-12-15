package page.contactus;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import model.ContactUs;
import page.AbstractPageModel;

import java.util.Objects;

public class ContactUsModel extends AbstractPageModel {
    Locator firstName;
    Locator lastName;
    Locator email;
    Locator comments;
    Locator reset;
    Locator submit;

    public ContactUsModel(Page page) {
        super(page);
        firstName = page.getByPlaceholder("First Name");
        lastName = page.getByPlaceholder("Last Name");
        email = page.getByPlaceholder("Email Address");
        comments = page.getByPlaceholder("Comments");
        reset = page.locator("css=input[type='reset']");
        submit = page.locator("css=input[type='submit']");
    }

    public ContactUsModel fillForm(ContactUs contactUs) {
        return setFirstName(contactUs.getFirstName())
                .setLastName(contactUs.getLastName())
                .setEmail(contactUs.getEmail())
                .setComments(contactUs.getComments());
    }

    public ContactUsModel setFirstName(String firstNameValue) {
        if(Objects.isNull(firstNameValue)) {
            return this;
        }
        firstName.fill(firstNameValue);
        return this;
    }

    public ContactUsModel setLastName(String lastNameValue) {
        if(Objects.isNull(lastNameValue)) {
            return this;
        }
        lastName.fill(lastNameValue);
        return this;
    }

    public ContactUsModel setEmail(String emailValue) {
        if(Objects.isNull(emailValue)) {
            return this;
        }
        email.fill(emailValue);
        return this;
    }

    public ContactUsModel setComments(String commentsValue) {
        if(Objects.isNull(commentsValue)) {
            return this;
        }
        comments.fill(commentsValue);
        return this;
    }

    public ContactUsModel reset() {
        reset.click();
        return this;
    }

    public ContactUsFormReusltModel submit() {
        submit.click();
        page.waitForLoadState();

        ContactUsThankYouModel contactUsThankYouModel = null;
        ContactUsErrorModel contactUsErrorModel = null;

        if(page.url().endsWith("contact-form-thank-you.html")) {
            contactUsThankYouModel = new ContactUsThankYouModel(page);
        } else {
            contactUsErrorModel = new ContactUsErrorModel(page);
        }

        return ContactUsFormReusltModel.builder()
                .ok(contactUsThankYouModel)
                .fail(contactUsErrorModel)
                .build();
    }

    public ContactUs getFormData() {
        return ContactUs.builder()
                .firstName(firstName.inputValue())
                .lastName(lastName.inputValue())
                .email(email.inputValue())
                .comments(comments.inputValue())
                .build();
    }
}
