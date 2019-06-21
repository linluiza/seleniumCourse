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
		Assert.assertEquals("Nome: Aline", paginaCampoTreinamento.obterNomeCadastro());
		Assert.assertEquals("Sobrenome: Luiza", paginaCampoTreinamento.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Feminino", paginaCampoTreinamento.obterSexoCadastro());
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
		
		Assert.assertEquals("Nome: "+nome, paginaCampoTreinamento.obterNomeCadastro());
		Assert.assertEquals("Sobrenome: "+sobrenome, paginaCampoTreinamento.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Feminino", paginaCampoTreinamento.obterSexoCadastro());
		Assert.assertEquals("Comida: Carne", paginaCampoTreinamento.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: superior", paginaCampoTreinamento.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: "+esporte, paginaCampoTreinamento.obterEsporteCadastro());
		Assert.assertEquals("Sugestoes: "+sugestao,paginaCampoTreinamento.obterSugestaoCadastro());
	}

}
