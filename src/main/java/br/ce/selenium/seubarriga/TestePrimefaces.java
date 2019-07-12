package br.ce.selenium.seubarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestePrimefaces {
	private DSL dsl;
	private WebDriver driver;
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
		dsl = new DSL(driver);
		
	}
	
	@After
	public void closeup() {
		driver.quit();
	}
	
	@Test
	public void selecionarCheckboxPrimefaces() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		
		String valorSelecionado = "Wii U";
		dsl.clicarEm(By.xpath("//table[contains(@id,'console')]//input[@value='"+valorSelecionado+"']/../..//span"));
		
		Assert.assertTrue(dsl.estaSelecionado("j_idt701:console:2"));
	}
	@Test
	public void selecionarValorComboPrimefaces() throws InterruptedException {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		
		String valorSelecionado = "PS4";
		
		//clicar em botao para expandir opcoes combo
		dsl.clicarEm(By.xpath("//div[contains(@id,'console')]//div[contains(@class,'ui-selectonemenu-trigger')]"));
		//esperar opcoes combo serrem carregadas e estarem disponiveis
		new WebDriverWait(driver,3000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@id,'console_items')]")));
		//clicar em opcao da combo
		dsl.clicarEm(By.xpath("//ul[contains(@id,'console_items')]//li[@data-label='"+valorSelecionado+"']"));
		
		Assert.assertEquals(dsl.obterTexto(By.xpath("//div[contains(@id,'console')]//label[contains(@id,'console_label')]")), valorSelecionado);
	}
}
