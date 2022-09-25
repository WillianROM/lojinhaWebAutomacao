package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeEdicaoDeProdutoPage {
    private WebDriver driver;

    public FormularioDeEdicaoDeProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String capturarMensagemApresentada(){
        return this.driver.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
