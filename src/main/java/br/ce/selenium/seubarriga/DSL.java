package br.ce.selenium.seubarriga;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreverEm(String id, String texto) {
		escreverEm(By.id(id),texto);
	}
	
	public void escreverEm(By tipoSelecao, String texto) {
		driver.findElement(tipoSelecao).sendKeys(texto);
	}
	
	public String obterValor(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	public void clicarEm(By by) {
		driver.findElement(by).click();
	}
	
	public void clicarEm(String id) {
		clicarEm(By.id(id));
	}
	
	public boolean estaSelecionado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarOpcaoDe(String id, String opcao) {
		selecionarOpcaoDe(By.id(id), opcao);
	}
	
	public void selecionarOpcaoDe(By by, String opcao) {
		Select combo = new Select(driver.findElement(by));
		combo.selectByVisibleText(opcao);
	}
	
	public String obterOpcaoSelecionada(String id) {
		Select combo = new Select(driver.findElement(By.id(id)));
		return combo.getFirstSelectedOption().getText();
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	public String obterTexto(By tipoSelecao) {
		return driver.findElement(tipoSelecao).getText();
	}
	
	public String alertaObterTextoEConfirmar() {
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		
		alert.accept();
		
		return textoAlerta;
	}
	
	public String alertaObterTextoENegar() {
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		
		alert.dismiss();
		
		return textoAlerta;
	}
	
	public String alertaObterTexto() {
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		
		return textoAlerta;
	}
	
	public void alertaEscreverEConfirmar(String texto) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(texto);
		alert.accept();
	}
	
	public void focarEmJanelaPrincipal() {
		driver.switchTo().defaultContent();
	}
	
	public void focarEmJanela(String id) {
		driver.switchTo().window(id);
	}
	
	public void selecionarOpcoesComboMultipla(String id, String... opcoes) {
		Select combo = new Select(driver.findElement(By.id(id)));
		
		for(String opcao: opcoes) {
			combo.selectByVisibleText(opcao);
		}
	}
	
	public List<String> obterOpcoesSelecionadasComboMultipla(String id) {
		Select combo = new Select(driver.findElement(By.id(id)));
		List<WebElement> elementosSelecionados = combo.getAllSelectedOptions();
		List<String> opcoesSelecionadas = new ArrayList<String>();
		
		for(WebElement opcao: elementosSelecionados) {
			opcoesSelecionadas.add(opcao.getAttribute("value"));
		}
		
		return opcoesSelecionadas;
	}
	
	public void deselecionarOpcoesComboMultipla(String id, String... opcoes) {
		Select combo = new Select(driver.findElement(By.id(id)));
		
		for(String opcao: opcoes) {
			combo.deselectByVisibleText(opcao);
		}
	}
	
	public void clicarLink(String text) {
		driver.findElement(By.linkText(text)).click();
	}
	
	public Object executarJavaScript(String comando, Object... argumentos) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		return js.executeScript(comando, argumentos);
	}
	
}
