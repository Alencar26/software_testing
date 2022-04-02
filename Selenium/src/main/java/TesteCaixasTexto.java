import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCaixasTexto {

    final String PATH_WEB_PAGE = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

    public WebDriver instanciarNavegador(String path) {
        System.setProperty("webdriver.gecko.driver", "/home/alencar/www/Selenium/geckodriver/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get(path);
        return driver;
    }

    @Test
    public void testeTextField() {

        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        //escrevendo no campo de input
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Escrevendo no text field.");

        Assert.assertEquals("Escrevendo no text field.",
                    driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void interacaoComTextArea() {
        WebDriver driver = instanciarNavegador(PATH_WEB_PAGE);

        //escrevendo no campo
        driver.findElement(By.id("elementosForm:sugestoes"))
                .sendKeys("Testando  campo de textArea.\n\n\n\nPulei duas linhas.\n\nPulei uma linha. FIM.");

        //validando valores do campo.
        String valorTextArea = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
        
        Assert.assertEquals("Testando  campo de textArea.", valorTextArea);
        driver.quit();
    }
}
