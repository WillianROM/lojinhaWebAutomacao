package modulos.produtos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Celular tijolão anos 90")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um  produto com valor maior que 7.000,00")
    public void testNaoEhPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){
        String mensagemApresentada = new LoginPage(driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Celular tijolão anos 90")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentav(){
       String mensagemApresentada =  new LoginPage(this.driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("balinha")
                .informarValorDoProduto("001")
                .informarCoresDoProduto("caramelo")
                .submeterFormularioDeAdicaoComSucesso()
               .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso" , mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMilReais(){
        String mensagemApresentada =  new LoginPage(this.driver)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Robô da Tesla")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("prata")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso" , mensagemApresentada);
    }

    @AfterEach
    public void afterEach(){
        this.driver.quit();
    }
}
