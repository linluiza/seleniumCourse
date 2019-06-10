package br.ce.selenium.seubarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlertsEPrompts {
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
	public void deveVerificarAlertSimples() {
		dsl.clicarEm("alert");
		Assert.assertEquals("Alert Simples", dsl.alertaObterTextoEConfirmar());
	}
	
	@Test
	public void deveVerificarConfirmOk() {
		dsl.clicarEm("confirm");
		
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEConfirmar());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEConfirmar());
	}
	
	@Test
	public void deveVerificarConfirmCancel() {
		dsl.clicarEm("confirm");
		
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENegar());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENegar());
	}
	
	@Test
	public void deveVerificarPromptValue() {
		dsl.clicarEm("prompt");
		
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		String value = "34";
		
		dsl.alertaEscreverEConfirmar(value);		
		Assert.assertEquals("Era ".concat(value).concat("?"), dsl.alertaObterTextoEConfirmar());
	}
	
}
