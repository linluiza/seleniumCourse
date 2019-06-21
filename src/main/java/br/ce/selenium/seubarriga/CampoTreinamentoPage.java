package br.ce.selenium.seubarriga;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void escreverNome(String nome) {
		dsl.escreverEm("elementosForm:nome", nome);
	}
	
	public String obterValorNome() {
		return dsl.obterValor("elementosForm:nome");
	}
	
	public void escreverSugestao(String texto) {
		dsl.escreverEm("elementosForm:sugestoes",texto);
	}
	
	public String obterValorSugestao() {
		return dsl.obterValor("elementosForm:sugestoes");
	}
	
	public void selecionarSexoMasculino() {
		dsl.clicarEm("elementosForm:sexo:0");
	}
	
	public boolean isSexoMasculinoSelecionado() {
		return dsl.estaSelecionado("elementosForm:sexo:0");
	}
	
	public void selecionarComidaCarne() {
		dsl.clicarEm("elementosForm:comidaFavorita:0");
	}
	public void selecionarComidaFrango() {
		dsl.clicarEm("elementosForm:comidaFavorita:1");
	}
	
	public void selecionarComidaVegetariana() {
		dsl.clicarEm("elementosForm:comidaFavorita:3");
	}
	
	public boolean isComidaFrangoSelecionada() {
		return dsl.estaSelecionado("elementosForm:comidaFavorita:1");
	}
	
	public void selecionarEscolaridade(String textoSelecionado) {
		dsl.selecionarOpcaoDe("elementosForm:escolaridade",textoSelecionado);
	}
	
	public String obterEscolaridadeSelecionada() {
		return dsl.obterOpcaoSelecionada("elementosForm:escolaridade");
	}
	
	public void selecionarEsportes(String... opcoes) {
		dsl.selecionarOpcoesComboMultipla("elementosForm:esportes",opcoes);
	}
	
	public void deselecionarEsportes(String... opcoes) {
		dsl.deselecionarOpcoesComboMultipla("elementosForm:esportes",opcoes);
	}
	
	public List<String> obterEsportesSelecionados() {
		return dsl.obterOpcoesSelecionadasComboMultipla("elementosForm:esportes");
	}
	
	public void clicarBotaoSimples() {
		dsl.clicarEm("buttonSimple");
	}
	
	public String obterValorBotaoSimples() {
		return dsl.obterValor("buttonSimple");
	}
	
	public void clicarLinkVoltar() {
		dsl.clicarLink("Voltar");
	}
	
	public String obterResultado() {
		 return dsl.obterTexto("resultado");
	}
	
	public String obterTitulo() {
		return dsl.obterTexto(By.tagName("h3"));
	}
	
	public String obterTextoFacilAchar() {
		return dsl.obterTexto(By.className("facilAchar"));
	}

	public void cadastrar() {
		dsl.clicarEm("elementosForm:cadastrar");
	}

	public void escreverSobrenome(String sobrenome) {
		dsl.escreverEm("elementosForm:sobrenome",sobrenome);
	}

	public void selecionarSexoFeminino() {
		dsl.clicarEm("elementosForm:sexo:1");
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	public String obterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}

	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}

	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	public String obterEsporteCadastro() {
		return dsl.obterTexto("descEsportes");
	}

	public String obterSugestaoCadastro() {
		return dsl.obterTexto("descSugestoes");
	}
}
