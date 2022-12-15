package page;

import com.microsoft.playwright.Page;

import java.lang.reflect.Field;
// TODO:: maybe later
public class PageFactory {
    public static void init(AbstractPageModel abstractPageModel, Page page) {
        Class<?> clazz = abstractPageModel.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(FindBy.class)) {

            }
        }
    }


}
