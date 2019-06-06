package br.ce.selenium.seubarriga;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	@Test
	public void deveVerificarComboMultiplo() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> opcoesSelecionadas = combo.getAllSelectedOptions();		
		Assert.assertEquals(3, opcoesSelecionadas.size());
		
		combo.deselectByVisibleText("O que eh esporte?");
		opcoesSelecionadas = combo.getAllSelectedOptions();		
		Assert.assertEquals(2, opcoesSelecionadas.size());
		driver.quit();
	}
	
	@Test
	public void deveVerificarClickBotao() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarClickLink() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.linkText("Voltar")).click();
		
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarTituloPagina() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		//interessante para titulo pq deve ser a primeira tag exibida na pagina.
		//pode nao ser interessante para outras situacoes	
		String titulo = driver.findElement(By.tagName("h3")).getText();
		
		Assert.assertEquals("Campo de Treinamento", titulo);
		
		driver.quit();
	}
	
	@Test
	public void deveVerificarComponenteSemId() {
		WebDriver driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		
		driver.quit();
	}
	

	@Test
	public void deveVerificarCadastroCompleto() {
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
