package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

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
        tags = "@unitario and not @ignore"// esse parâmetro é para indicar qual cenário eu quero executar sozinho
)
public class RunnerUnitTest { }
