package modulos.produtos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.ListaDeProdutosPage;
import paginas.LoginPage;


import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        // Abrir o navegador //Com o webdrivermanager o driver fica salvo em C:\Users\ NOME DO USUÁRIO \.cache\selenium\chromedriver\win32
        /*System.setProperty("webdriver.chrome.driver","C:\\Caminho onde está salvo o arquivo chromedriver.exe"); Para casos que for localizar o arquivo chromedriver e não foi usado o WebDriverManager*/
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

        // Vou maximizar a tela
        this.driver.manage().window().maximize();

        // Vou definir um tempo de espera padrão de 5 segundos
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a página da Lojinha Web
        this.driver.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEhPermitidoRegistrarProdutoComValorIgualAZero(){

        // Fazer login
        new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLoginr()
                .acessarFormularioAdicaoNovoProduto();


        // Vou preencher dados do produto e o valor será igual a zero
        this.driver.findElement(By.id("produtonome")).sendKeys("Celular tijolão anos 90");
        this.driver.findElement(By.name("produtovalor")).sendKeys("000");
        this.driver.findElement(By.id("produtocores")).sendKeys("preto, branco");

        // Vou submeter o formulário
        this.driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentada
        String mensagemToast = this.driver.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);
    }


    @AfterEach
    public void afterEach(){
        this.driver.quit();
    }
}
