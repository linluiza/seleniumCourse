package br.ce.selenium.seubarriga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {
	@Test
	public void deveVerificarFrame() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoFrame = alert.getText();
		
		Assert.assertEquals("Frame OK!", textoFrame);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(textoFrame);
		
		driver.close();
	}
	
	@Test
	public void deveVerificarJanelaComId() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		driver.quit();
	}
	@Test
	public void deveVerificarJanelaSemId() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		Object[] listaJanelas = driver.getWindowHandles().toArray();
		
		driver.switchTo().window((String) listaJanelas[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		
		driver.switchTo().window((String) listaJanelas[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
		driver.quit();
	}
}
