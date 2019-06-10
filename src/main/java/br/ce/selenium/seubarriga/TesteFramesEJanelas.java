package br.ce.selenium.seubarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {
	
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
	public void deveVerificarFrame() {
		driver.switchTo().frame("frame1");
		dsl.clicarEm("frameButton");
		
		String textoFrame = dsl.alertaObterTextoEConfirmar();
		Assert.assertEquals("Frame OK!", textoFrame);
		
		dsl.focarEmJanelaPrincipal();
		dsl.escreverEm("elementosForm:nome",textoFrame);
	}
	
	@Test
	public void deveVerificarJanelaComId() {
		dsl.clicarEm("buttonPopUpEasy");
		
		dsl.focarEmJanela("Popup");
		dsl.escreverEm(By.tagName("textarea"),"deu certo?");
		
		driver.close();
		dsl.focarEmJanela("");
		dsl.escreverEm(By.tagName("textarea"),"e agora?");
	}
	@Test
	public void deveVerificarJanelaSemId() {
		dsl.clicarEm("buttonPopUpHard");
		Object[] listaJanelas = driver.getWindowHandles().toArray();
		
		dsl.focarEmJanela((String) listaJanelas[1]);
		dsl.escreverEm(By.tagName("textarea"),"deu certo?");
		
		dsl.focarEmJanela((String) listaJanelas[0]);
		dsl.escreverEm(By.tagName("textarea"),"e agora?");
	}
}
