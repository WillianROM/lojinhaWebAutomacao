package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver; /* 1º Principio do Page Objects - Tenha um atríbuto da classe que seja do tipo WebDriver*/

    public LoginPage(WebDriver driver){ /* 2º Prinipio do Page Objects - Tenha um construtor na classe que pegue um navegador de fora e coloque dentro do navegador*/
        this.driver = driver;
    }

    /* 3º Principio do Page Objects - Ter métodos de interação com cada elemento da tela*/
    public LoginPage informarOUsuario(String usuario){ /* o tipo LoginPage foi usado porque ao informar o usuario permanecerá na mesma página de login*/
        this.driver.findElement(By.cssSelector("label[for='usuario']")).click();
        this.driver.findElement(By.cssSelector("input#usuario")).sendKeys(usuario);

        return this; /*Design partten - Fluent Page Object Pattern - retornar a próxima página*/
    }

    public LoginPage informarASenha(String senha){
        this.driver.findElement(By.cssSelector("label[for='senha']")).click();
        this.driver.findElement(By.cssSelector("input#senha")).sendKeys(senha);

        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeLogin(){ /* o tipo ListaDeProdutosPage foi usado porque ao clicar no botão entrar irá para a página de lista de produtos*/
        this.driver.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();

        return new ListaDeProdutosPage(this.driver); /*Após clicar em entrar, a próxima página é Lista de Produtos que receberá o navegador*/
    }

}
