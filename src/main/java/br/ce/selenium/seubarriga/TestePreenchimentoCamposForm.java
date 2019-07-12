package br.ce.selenium.seubarriga;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePreenchimentoCamposForm {
	private DSL dsl;
	private WebDriver driver;
	private CampoTreinamentoPage paginaCampoTreinamento;
	
	@Before
	public void setup() {
		driver = new ChromeDriver();
		String home_dir = System.getProperty("user.dir");
		
		driver.get("file:///" + home_dir
				+ "/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
		paginaCampoTreinamento = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void closeup() {
		driver.quit();
	}
	
	@Test
	public void deveVerificarTextField() {
		paginaCampoTreinamento.escreverNome("teste");
		Assert.assertEquals("teste", paginaCampoTreinamento.obterValorNome());
	}
	
	@Test
	public void deveVerificarTextArea() {
		paginaCampoTreinamento.escreverSugestao("teste area\n\nmultiplas linhas");
		Assert.assertEquals("teste area\n\nmultiplas linhas", paginaCampoTreinamento.obterValorSugestao());
	}
	
	@Test
	public void deveVerificarRadioButton() {
		paginaCampoTreinamento.selecionarSexoMasculino();
		Assert.assertTrue(paginaCampoTreinamento.isSexoMasculinoSelecionado());
	}

	@Test
	public void deveVerificarCheckBox() {
		paginaCampoTreinamento.selecionarComidaFrango();
		Assert.assertTrue(paginaCampoTreinamento.isComidaFrangoSelecionada());
	}
	
	@Test
	public void deveVerificarCombo() {
		String textoSelecionado = "Mestrado";
		
		paginaCampoTreinamento.selecionarEscolaridade(textoSelecionado);
		Assert.assertEquals(textoSelecionado, paginaCampoTreinamento.obterEscolaridadeSelecionada());
	}
	
	@Test
	public void deveVerificarComboMultiplo() {
		paginaCampoTreinamento.selecionarEsportes("Natacao","Karate","O que eh esporte?");
		
		Assert.assertEquals(3, paginaCampoTreinamento.obterEsportesSelecionados().size());
		
		paginaCampoTreinamento.deselecionarEsportes("O que eh esporte?");
		Assert.assertEquals(2, paginaCampoTreinamento.obterEsportesSelecionados().size());
	}
	
	@Test
	public void deveVerificarClickBotao() {
		paginaCampoTreinamento.clicarBotaoSimples();
		Assert.assertEquals("Obrigado!", paginaCampoTreinamento.obterValorBotaoSimples());
	}
	
	@Test
	public void deveVerificarClickLink() {
		paginaCampoTreinamento.clicarLinkVoltar();
		Assert.assertEquals("Voltou!",paginaCampoTreinamento.obterResultado());
	}
	
	@Test
	public void deveVerificarTituloPagina() {
		//interessante para titulo pq deve ser a primeira tag exibida na pagina.
		//pode nao ser interessante para outras situacoes	
		Assert.assertEquals("Campo de Treinamento", paginaCampoTreinamento.obterTitulo());
	}
	
	@Test
	public void deveVerificarComponenteSemId() {
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", paginaCampoTreinamento.obterTextoFacilAchar());
	}
	
	@Test
	public void deveVerificarCliqueBotaoTabela() {
		String coluna = "Nome";
		String valor = "Usuario A";
		
		paginaCampoTreinamento.clicarEmBotaoDeLinhaEspecificaTabela(coluna, valor, "elementosForm:tableUsuarios");
		
		Assert.assertEquals(valor, dsl.alertaObterTextoEConfirmar());
	}

}
