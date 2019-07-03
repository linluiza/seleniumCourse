package br.ce.selenium.seubarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastroForm {
	private WebDriver driver;
	private CampoTreinamentoPage paginaCampoTreinamento;
	@Before
	public void setup() {
		driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		paginaCampoTreinamento = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void closeup() {
		driver.quit();
	}
	
	@Test
	public void deveCadastrarComApenasCamposNomeSobrenomeESexo() {
		paginaCampoTreinamento.escreverNome("Aline");
		paginaCampoTreinamento.escreverSobrenome("Luiza");
		paginaCampoTreinamento.selecionarSexoFeminino();
		
		paginaCampoTreinamento.cadastrar();
		
		Assert.assertTrue(paginaCampoTreinamento.obterResultado().startsWith("Cadastrado!"));
		Assert.assertEquals("Aline", paginaCampoTreinamento.obterNomeCadastro());
		Assert.assertEquals("Luiza", paginaCampoTreinamento.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", paginaCampoTreinamento.obterSexoCadastro());
	}
	

	@Test
	public void deveCadastrarComTodosCamposPreenchidos() {
		String nome = "Aline";
		String sobrenome = "Costa";
		String escolaridade = "Superior";
		String esporte = "Corrida";
		String sugestao = "Yoga";
		
		paginaCampoTreinamento.escreverNome(nome);
		paginaCampoTreinamento.escreverSobrenome(sobrenome);
		paginaCampoTreinamento.escreverSugestao(sugestao);
		
		paginaCampoTreinamento.selecionarSexoFeminino();
		paginaCampoTreinamento.selecionarComidaCarne();
		
		paginaCampoTreinamento.selecionarEscolaridade(escolaridade);
		paginaCampoTreinamento.selecionarEsportes(esporte);
		
		paginaCampoTreinamento.cadastrar();
		
		Assert.assertEquals(nome, paginaCampoTreinamento.obterNomeCadastro());
		Assert.assertEquals(sobrenome, paginaCampoTreinamento.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", paginaCampoTreinamento.obterSexoCadastro());
		Assert.assertEquals("Carne", paginaCampoTreinamento.obterComidaCadastro());
		Assert.assertEquals("superior", paginaCampoTreinamento.obterEscolaridadeCadastro());
		Assert.assertEquals(esporte, paginaCampoTreinamento.obterEsporteCadastro());
		Assert.assertEquals(sugestao,paginaCampoTreinamento.obterSugestaoCadastro());
	}

}
