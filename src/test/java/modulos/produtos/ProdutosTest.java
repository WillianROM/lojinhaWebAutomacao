package modulos.produtos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEhPermitidoRegistrarProdutoComValorIgualAZero(){
        // Abrir o navegador //Com o webdrivermanager o driver fica salvo em C:\Users\ NOME DO USUÁRIO \.cache\selenium\chromedriver\win32
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS); //Espera milisegundos
        driver.manage().window().maximize();

        // Navegar para a página da Lojinha Web
        driver.get("http://165.227.93.41/lojinha-web/v2/");

        // Fazer login

        // Vou para a tela de registro de produto

        // Vou preencher dados do produto e o valor será igual a zero

        // Vou submeter o formulário

        // Vou validar que a mensagem de erro foi apresentada

    }
}
