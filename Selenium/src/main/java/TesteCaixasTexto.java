import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCaixasTexto {

    public WebDriver instanciarNavegador() {
        System.setProperty("webdriver.gecko.driver", "/home/alencar/www/Selenium/geckodriver/geckodriver");
        return new FirefoxDriver();
    }

    @Test
    public void testeTextField() {

        WebDriver driver = instanciarNavegador();

        driver.manage().window().setSize(new Dimension(1200, 765));

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //escrevendo no campo de input
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Escrevendo no text field.");

        Assert.assertEquals("Escrevendo no text field.",
                    driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void interacaoComTextArea() {
        WebDriver driver = instanciarNavegador();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //escrevendo no campo
        driver.findElement(By.id("elementosForm:sugestoes"))
                .sendKeys("Testando  campo de textArea.\n\n\n\nPulei duas linhas.\n\nPulei uma linha. FIM.");

        //validando valores do campo.
        String valorTextArea = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
//        Assert.assertEquals("Testando  campo de textArea.", valorTextArea);
//
//        driver.quit();
    }
}
