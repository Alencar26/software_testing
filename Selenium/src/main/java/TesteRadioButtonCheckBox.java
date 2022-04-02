import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteRadioButtonCheckBox {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    public WebDriver instanciarNavegador(String path) {
        System.setProperty("webdriver.gecko.driver", "/home/alencar/www/Selenium/geckodriver/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(path);
        return driver;
    }

    @Test
    public void deveInteragirComRadioButton() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        boolean isSelected = driver.findElement(By.id("elementosForm:sexo:0")).isSelected();
        Assert.assertTrue(isSelected);

        driver.quit();
    }

    @Test
    public void deveInteragirComCheckBox() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        boolean isSelected = driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected();
        Assert.assertTrue(isSelected);

        driver.quit();
    }
}
