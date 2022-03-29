import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

    @Test
    public void teste() {

        System.setProperty("webdriver.gecko.driver", "/home/alencar/www/Selenium/geckodriver/geckodriver");
        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());

        driver.close();
    }
}
