package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver driver;

    public FormularioDeAdicaoDeProdutoPage(WebDriver driver) {
        this.driver = driver;
    }

    public FormularioDeAdicaoDeProdutoPage informarNomeDoProduto(String produtoNome){
        this.driver.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }

    public FormularioDeAdicaoDeProdutoPage informarValorDoProduto(String produtoValor){
        this.driver.findElement(By.name("produtovalor")).sendKeys(produtoValor);
        return this;
    }

    public FormularioDeAdicaoDeProdutoPage informarCoresDoProduto(String produtoCores){
        this.driver.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro(){
        this.driver.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(this.driver);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso(){
        this.driver.findElement(By.cssSelector("button[type='submit']")).click();

        return new FormularioDeEdicaoDeProdutoPage(this.driver);
    }

}
