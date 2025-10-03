package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected final String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    protected final String username = "Admin";
    protected final String password = "admin123";

    @BeforeAll
    static void setUpSuite() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

    }

    @AfterAll
    public static void tearDownSuite() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    public void setUpTest() {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1440, 900));
        page = context.newPage();

        // Login before each test
        page.navigate(baseURL);
        page.locator("input[name='username']").fill(username);


        page.locator("input[name='password']").fill(password);
        page.locator("button.oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button").click();


    }


}
