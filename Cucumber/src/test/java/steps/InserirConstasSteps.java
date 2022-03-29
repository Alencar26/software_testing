package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

public class InserirConstasSteps {

    private WebDriver driver;

    @Dado("que estou acessando a aplicação")
    public void queEstouAcessandoAAplicação() {
        System.setProperty(
                "webdriver.gecko.driver",
                "/home/alencar/www/Selenium/geckodriver/geckodriver"
        );
        driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(100,100));
        driver.manage().window().setSize(new Dimension(1200, 735));
        driver.get("https://seubarriga.wcaquino.me/logout");
    }

    @Quando("informo o usuário {string}")
    public void informoOUsuário(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @Quando("a senha {string}")
    public void aSenha(String senha) {
        driver.findElement(By.id("senha")).sendKeys(senha);
    }

    @Quando("seleciono entrar")
    public void selecionoEntrar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("visualizo a página inicial")
    public void visualizoAPáginaInicial() {
        String titulo = driver.getTitle();
        String txtBemVindo = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();

        Assert.assertEquals("Seu Barriga - Home", titulo);
        Assert.assertEquals("Bem vindo, Teste Cucumber!", txtBemVindo);
    }

    @Quando("seleciono Contas")
    public void selecionoContas() {
        driver.findElement(By.className("dropdown")).click();
    }

    @Quando("seleciono Adicionar")
    public void selecionoAdicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }

    @Quando("informo a conta {string}")
    public void informoAConta(String nomeConta) {
        driver.findElement(By.className("form-control")).sendKeys(nomeConta);
    }

    @Quando("seleciono Salvar")
    public void selecionoSalvar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Então("a conta é inserida com sucesso")
    public void aContaÉInseridaComSucesso() {
        String alert = driver.findElement(By.className("alert")).getText();
        if ("Já existe uma conta com esse nome!".equals(alert)){
            selecionoContas();
            driver.findElement(By.linkText("Listar")).click();
            String nomeConta = driver.findElement(By.tagName("td")).getText();
            Assert.assertEquals("Conta de Teste", nomeConta);
        } else {
            Assert.assertEquals("Conta adicionada com sucesso!", alert);
        }
    }

    @Então("sou notificado que o nome da conta é obrigatório")
    public void souNotificadoQueONomeDaContaÉObrigatório() {
        String txtAlert = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Informe o nome da conta", txtAlert);
    }

    @Então("sou notificado que já existe uma conta com esse nome")
    public void souNotificadoQueJáExisteUmaContaComEsseNome() {
        String txtAlert = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", txtAlert);
    }

    @Então("recebo a mensagem {string}")
    public void receboAMensagem(String mensagem) {
        String txtAlert = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(mensagem, txtAlert);
    }

    @After(order = 1, value = "@funcional")
    public void screenshot(Scenario cenario){
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After (order = 0, value = "@funcional")// after do Cucumber
    public void fecharBrowser(){
        driver.quit();
    }
}
