package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Scenario1 extends BaseTest {
    @Test
    public void login() {

        page.locator("a[href='/web/index.php/pim/viewPimModule']").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();

        String firstName = "Test";
        String middleName = "Mid";
        String lastName = "User";
        String fullEmployeeName = firstName + " " + middleName + " " + lastName;

        page.locator("input[name='firstName']").fill(firstName);
        page.locator("input[name='middleName']").fill(middleName);
        page.locator("input[name='lastName']").fill(lastName);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();



        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();


        page.locator("form div.oxd-select-text").first().click();
        page.locator("div[role='option']:has-text('ESS')").click();


        page.getByPlaceholder("Type for hints...")
                .fill(firstName + " " + middleName + " ");

        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(fullEmployeeName)).first().click();


        String username = "test123488";
        String password = "password12345";

        page.locator("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input").fill(username);


        page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input").fill(password);
        //confirm password
        page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input").fill(password);
        //status
        page.locator("form div.oxd-select-text").nth(1).click();
        page.locator("div[role='option']:has-text('Enabled')").click();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).click();

        //Acceptance criteria 1

        Locator popupMessage = page.locator("div#oxd-toaster_1 div.oxd-toast-content--success");

        popupMessage.waitFor();

        assertThat(popupMessage).containsText("Success");

        //Second acceptance criteria (a flag for placement, not an important comment)
        if (!page.url().contains("/admin/viewSystemUsers")) {
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin")).click();
        }

        page.locator("input.oxd-input.oxd-input--active").first().fill("username");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();

        Locator table = page.locator("div.oxd-table");
        table.waitFor();
        assertThat(table).containsText(username);


    }


}
