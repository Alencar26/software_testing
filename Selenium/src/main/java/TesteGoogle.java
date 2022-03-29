import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

    public static void main(String[] args) {

        System.setProperties("webdriver.gecko.driver", "~/www/Selenium/geckodriver/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com.br");
    }
}
