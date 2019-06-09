package br.ce.selenium.seubarriga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TesteAlertsEPrompts {
	@Test
	public void deveVerificarAlertSimples() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		
		Assert.assertEquals("Alert Simples", textoAlerta);
		alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarConfirmOk() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoConfirm = alert.getText();
		
		Assert.assertEquals("Confirm Simples", textoConfirm);
		alert.accept();
		
		String textoConfirmAceito = alert.getText();
		Assert.assertEquals("Confirmado", textoConfirmAceito);
		alert.accept();
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarConfirmCancel() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("confirm")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoConfirm = alert.getText();
		
		Assert.assertEquals("Confirm Simples", textoConfirm);
		alert.dismiss();
		
		String textoConfirmNegado = alert.getText();
		Assert.assertEquals("Negado", textoConfirmNegado);
		alert.dismiss();
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarPromptValue() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("prompt")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		String value = "34";
		
		
		alert.sendKeys(value);
		alert.accept();
		String textoConfirm = alert.getText();
		
		Assert.assertEquals("Era ".concat(value).concat("?"), textoConfirm);
		alert.accept();
		
		driver.quit();
	}
	
}
