package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/home/alencar/www/Curso-Cucumber-Java/src/test/resources/", // caminho dos arquivos feature
        glue = "steps", // caminho/ pacote dos steps codificados
        plugin = {"pretty", // é o relatórios dos cenários
                "html:target/report-html.html", // relatório em HTML detalhado
                "json:target/report.json",
                "summary"
        },
        // monochrome = false, // para não aplicar cor no console
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false, // serve para validar se o mapeamento está correto
        tags = "@funcional and not @ignore"// esse parâmetro é para indicar qual cenário eu quero executar sozinho
)
public class RunnerFunctionalTest {

    @BeforeClass //essa rotina vai rodar antes de cada CT
    public static void resetDB(){
        System.setProperty(
                "webdriver.gecko.driver",
                "/home/alencar/www/Selenium/geckodriver/geckodriver"
        );
        WebDriver driver = new FirefoxDriver();
        driver.get("https://seubarriga.wcaquino.me/logout");
        driver.findElement(By.id("email")).sendKeys("cucumber@cucumber");
        driver.findElement(By.id("senha")).sendKeys("123");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }
}
