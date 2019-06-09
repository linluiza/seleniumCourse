package br.ce.selenium.seubarriga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastroForm {
	@Test
	public void deveAlertarQuandoNomeVazio() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		Assert.assertEquals("Nome eh obrigatorio", textoAlerta);
		
		alert.accept();
		driver.switchTo().defaultContent();
		Assert.assertEquals("Status: Nao cadastrado", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveAlertarQuandoSobrenomeVazio() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Aline");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		Assert.assertEquals("Sobrenome eh obrigatorio", textoAlerta);
		
		alert.accept();
		driver.switchTo().defaultContent();
		Assert.assertEquals("Status: Nao cadastrado", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveAlertarQuandoSexoVazio() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Aline");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Luiza");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		Assert.assertEquals("Sexo eh obrigatorio", textoAlerta);
		
		alert.accept();
		driver.switchTo().defaultContent();
		Assert.assertEquals("Status: Nao cadastrado", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	
	@Test
	public void deveAlertarTipoComidaIncompativel() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Aline");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Luiza");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", textoAlerta);
		
		alert.accept();
		driver.switchTo().defaultContent();
		Assert.assertEquals("Status: Nao cadastrado", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveAlertarTipoEsporteIncompativel() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Aline");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Luiza");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		Assert.assertEquals("Voce faz esporte ou nao?", textoAlerta);
		
		alert.accept();
		driver.switchTo().defaultContent();
		Assert.assertEquals("Status: Nao cadastrado", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveCadastrarComApenasCamposNomeSobrenomeESexo() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Aline");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Luiza");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Aline", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Luiza", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
		
		driver.quit();
	}
	

	@Test
	public void deveCadastrarComTodosCamposPreenchidos() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		String nome = "Aline";
		String sobrenome = "Costa";
		String escolaridade = "Superior";
		String esporte = "Corrida";
		String sugestao = "Yoga";
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobrenome);
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(sugestao);
		
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText(escolaridade);
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText(esporte);
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertEquals("Nome: "+nome, driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: "+sobrenome, driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Feminino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Carne", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: "+esporte, driver.findElement(By.id("descEsportes")).getText());
		Assert.assertEquals("Sugestoes: "+sugestao, driver.findElement(By.id("descSugestoes")).getText());
		
		driver.quit();
	}

}
