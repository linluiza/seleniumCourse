package br.ce.selenium.seubarriga;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestePreenchimentoCamposForm {
	private DSL dsl;
	private WebDriver driver;
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
	}
	
	@After
	public void closeup() {
		driver.quit();
	}
	
	@Test
	public void deveVerificarTextField() {
		dsl.escreverEm("elementosForm:nome", "teste");
		Assert.assertEquals("teste", dsl.obterValor("elementosForm:nome"));
	}
	
	@Test
	public void deveVerificarTextArea() {
		dsl.escreverEm("elementosForm:sugestoes","teste area\n\nmultiplas linhas");
		Assert.assertEquals("teste area\n\nmultiplas linhas", dsl.obterValor("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveVerificarRadioButton() {
		dsl.clicarEm("elementosForm:sexo:0");
		Assert.assertTrue(dsl.estaSelecionado("elementosForm:sexo:0"));
	}

	@Test
	public void deveVerificarCheckBox() {
		dsl.clicarEm("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.estaSelecionado("elementosForm:comidaFavorita:1"));
	}
	
	@Test
	public void deveVerificarCombo() {
		String textoSelecionado = "Mestrado";
		
		dsl.selecionarOpcaoDe("elementosForm:escolaridade",textoSelecionado);
		Assert.assertEquals(textoSelecionado, dsl.obterOpcaoSelecionada("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarComboMultiplo() {
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> opcoesSelecionadas = combo.getAllSelectedOptions();		
		Assert.assertEquals(3, opcoesSelecionadas.size());
		
		combo.deselectByVisibleText("O que eh esporte?");
		opcoesSelecionadas = combo.getAllSelectedOptions();		
		Assert.assertEquals(2, opcoesSelecionadas.size());
	}
	
	@Test
	public void deveVerificarClickBotao() {
		dsl.clicarEm("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValor("buttonSimple"));
	}
	
	@Test
	public void deveVerificarClickLink() {
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveVerificarTituloPagina() {
		//interessante para titulo pq deve ser a primeira tag exibida na pagina.
		//pode nao ser interessante para outras situacoes	
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
	}
	
	@Test
	public void deveVerificarComponenteSemId() {
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

}
