package modulos.produtos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEhPermitidoRegistrarProdutoComValorIgualAZero(){
        // Abrir o navegador //Com o webdrivermanager o driver fica salvo em C:\Users\ NOME DO USUÁRIO \.cache\selenium\chromedriver\win32
        /*System.setProperty("webdriver.chrome.driver","C:\\Caminho onde está salvo o arquivo chromedriver.exe"); Para casos que for localizar o arquivo chromedriver e não foi usado o WebDriverManager*/
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Vou maximizar a tela
        driver.manage().window().maximize();

        // Vou definir um tempo de espera padrão de 5 segundos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a página da Lojinha Web
        driver.get("http://165.227.93.41/lojinha-web/v2/");


        // Fazer login
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.cssSelector("input#usuario")).sendKeys("admin");

        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.cssSelector("input#senha")).sendKeys("admin");

        driver.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();

        // Vou para a tela de registro de produto
        //driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        //driver.findElement(By.xpath("//a[contains(text(),'Adicionar produto')]")).click();

        driver.get("http://165.227.93.41/lojinha-web/v2/produto/novo");

        // Vou preencher dados do produto e o valor será igual a zero
        driver.findElement(By.id("produtonome")).sendKeys("Celular tijolão anos 90");
        driver.findElement(By.name("produtovalor")).sendKeys("000");
        driver.findElement(By.id("produtocores")).sendKeys("preto, branco");

        // Vou submeter o formulário
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentada
        String mensagemToast = driver.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        driver.quit();
    }
}
