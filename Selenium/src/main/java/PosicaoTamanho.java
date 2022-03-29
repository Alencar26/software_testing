import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PosicaoTamanho {

    @Test
    public void teste() {

        System.setProperty("webdriver.gecko.driver", "/home/alencar/www/Selenium/geckodriver/geckodriver");
        WebDriver driver = new FirefoxDriver();

        // Pegando a posição do Browser
        driver.manage().window().setPosition(new Point(100, 100));

        // Definindo tamanho da janela do browser | new Dimension(largura, altura)
        driver.manage().window().setSize(new Dimension(1200, 765));

        // Iniciar browser maximizado
        driver.manage().window().maximize();

        driver.get("https://www.google.com.br");
        Assert.assertEquals("Google", driver.getTitle());

        driver.close();
    }
}
