package br.ce.selenium.seubarriga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	@Test
	public void deveVerificarTextField() {
		 WebDriver driver = new ChromeDriver();
		 String home_dir = System.getProperty("user.dir");
	        driver.get("file:///" + home_dir
	        		+ "/src/main/resources/componentes.html");
	        
	        driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");
	        Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	        
	        driver.quit();
	}
	
	@Test
	public void deveVerificarTextArea() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste area\n\nmultiplas linhas");
		Assert.assertEquals("teste area\n\nmultiplas linhas", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarRadioButton() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
	}

	@Test
	public void deveVerificarCheckBox() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarCombo() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		String textoSelecionado = "Mestrado";
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:escolaridade")));
		combo.selectByVisibleText(textoSelecionado);

		Assert.assertEquals(textoSelecionado, combo.getFirstSelectedOption().getText());
		
		driver.quit();
	}

}
