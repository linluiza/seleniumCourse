package br.ce.selenium.seubarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastroForm {
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
	public void deveAlertarQuandoNomeVazio() {
		dsl.clicarEm("elementosForm:cadastrar");
		
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEConfirmar());
		Assert.assertEquals("Status: Nao cadastrado", dsl.obterTexto("resultado"));
		
	}
	
	@Test
	public void deveAlertarQuandoSobrenomeVazio() {
		dsl.escreverEm("elementosForm:nome","Aline");
		dsl.clicarEm("elementosForm:cadastrar");
		
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEConfirmar());
		dsl.focarEmJanelaPrincipal();
		Assert.assertEquals("Status: Nao cadastrado", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveAlertarQuandoSexoVazio() {
		dsl.escreverEm("elementosForm:nome","Aline");
		dsl.escreverEm("elementosForm:sobrenome","Luiza");
		dsl.clicarEm("elementosForm:cadastrar");

		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEConfirmar());
		dsl.focarEmJanelaPrincipal();
		Assert.assertEquals("Status: Nao cadastrado", dsl.obterTexto("resultado"));
	}
	
	
	@Test
	public void deveAlertarTipoComidaIncompativel() {
		dsl.escreverEm("elementosForm:nome","Aline");
		dsl.escreverEm("elementosForm:sobrenome","Luiza");
		dsl.clicarEm("elementosForm:sexo:1");
		dsl.clicarEm("elementosForm:comidaFavorita:1");
		dsl.clicarEm("elementosForm:comidaFavorita:3");
		dsl.clicarEm("elementosForm:cadastrar");
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEConfirmar());
		dsl.focarEmJanelaPrincipal();
		Assert.assertEquals("Status: Nao cadastrado", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveAlertarTipoEsporteIncompativel() {
		dsl.escreverEm("elementosForm:nome","Aline");
		dsl.escreverEm("elementosForm:sobrenome","Luiza");
		dsl.clicarEm("elementosForm:sexo:1");
		
		dsl.selecionarOpcaoDe("elementosForm:esportes","Karate");
		dsl.selecionarOpcaoDe("elementosForm:esportes","O que eh esporte?");
		dsl.clicarEm("elementosForm:cadastrar");
		
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEConfirmar());
		dsl.focarEmJanelaPrincipal();
		Assert.assertEquals("Status: Nao cadastrado", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveCadastrarComApenasCamposNomeSobrenomeESexo() {
		dsl.escreverEm("elementosForm:nome","Aline");
		dsl.escreverEm("elementosForm:sobrenome","Luiza");
		dsl.clicarEm("elementosForm:sexo:1");
		dsl.clicarEm("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Aline", dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: Luiza", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.obterTexto("descSexo"));
	}
	

	@Test
	public void deveCadastrarComTodosCamposPreenchidos() {
		String nome = "Aline";
		String sobrenome = "Costa";
		String escolaridade = "Superior";
		String esporte = "Corrida";
		String sugestao = "Yoga";
		
		dsl.escreverEm("elementosForm:nome",nome);
		dsl.escreverEm("elementosForm:sobrenome",sobrenome);
		dsl.escreverEm("elementosForm:sugestoes",sugestao);
		
		dsl.clicarEm("elementosForm:sexo:1");
		dsl.clicarEm("elementosForm:comidaFavorita:0");
		
		dsl.selecionarOpcaoDe("elementosForm:escolaridade",escolaridade);
		dsl.selecionarOpcaoDe("elementosForm:esportes",esporte);
		
		dsl.clicarEm("elementosForm:cadastrar");
		
		Assert.assertEquals("Nome: "+nome, dsl.obterTexto("descNome"));
		Assert.assertEquals("Sobrenome: "+sobrenome, dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Feminino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: "+esporte, dsl.obterTexto("descEsportes"));
		Assert.assertEquals("Sugestoes: "+sugestao, dsl.obterTexto("descSugestoes"));
	}

}
