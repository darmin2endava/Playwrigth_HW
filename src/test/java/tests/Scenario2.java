package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Scenario2 extends BaseTest {


    @Test
    void clickDashboardLinks() {
        clickMenuLinks("Admin", "/admin/viewSystemUsers");
        clickMenuLinks("PIM", "/pim/viewEmployeeList");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leave")).first().click();

        assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList");

        clickMenuLinks("Time", "/time/viewEmployeeTimesheet");
        clickMenuLinks("Recruitment", "/recruitment/viewCandidates");
        clickMenuLinks("My Info", "/pim/viewPersonalDetails/empNumber/7");
        clickMenuLinks("Performance", "/performance/searchEvaluatePerformanceReview");
        clickMenuLinks("Dashboard", "/dashboard/index");
        clickMenuLinks("Directory", "/directory/viewDirectory");

        clickMenuLinks("Maintenance", "/maintenance/purgeEmployee");


        clickMenuLinks("Claim", "/claim/viewAssignClaim");
        clickMenuLinks("Buzz", "/buzz/viewBuzz");

    }

    public void clickMenuLinks(String linkName, String expectedPath) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(linkName)).first().click();
        Locator heading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Administrator Access"));

        try {
            heading.waitFor(new Locator.WaitForOptions().setTimeout(2000).setState(WaitForSelectorState.VISIBLE));

            if (heading.isVisible()) {

                Locator cancelBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"));

                if (cancelBtn.isVisible()) {
                    cancelBtn.click();
                }

                expectedPath = "/directory/viewDirectory";

            }
        } catch (PlaywrightException _) {

            
        }


        assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/web/index.php" + expectedPath);


    }



   

    
}


