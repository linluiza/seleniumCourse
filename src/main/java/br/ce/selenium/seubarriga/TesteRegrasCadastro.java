package br.ce.selenium.seubarriga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(value=Parameterized.class)
public class TesteRegrasCadastro {
	private DSL dsl;
	private WebDriver driver;
	private CampoTreinamentoPage paginaCampoTreinamento;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
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
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"", "", "", new ArrayList<String>(),new String[] {}, "Nome eh obrigatorio"},
			{"Aline", "", "", new ArrayList<String>(),new String[] {}, "Sobrenome eh obrigatorio"},
			{"Aline", "Luiza", "", new ArrayList<String>(),new String[] {}, "Sexo eh obrigatorio"},
			{"Aline", "Luiza", "Feminino", Arrays.asList("Frango","Vegetariana"),new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{"Aline", "Luiza", "Feminino", Arrays.asList("Carne"),new String[] {"Karate","O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras() {
		paginaCampoTreinamento.escreverNome(nome);
		paginaCampoTreinamento.escreverSobrenome(sobrenome);
		
		if("Feminino".equals(sexo)) {
			paginaCampoTreinamento.selecionarSexoFeminino();
		} else if("Masculino".equals(sexo)) {
			paginaCampoTreinamento.selecionarSexoMasculino();
		}
		
		if(comidas.contains("Frango")) paginaCampoTreinamento.selecionarComidaFrango();
		if(comidas.contains("Carne")) paginaCampoTreinamento.selecionarComidaCarne();
		if(comidas.contains("Vegetariana")) paginaCampoTreinamento.selecionarComidaVegetariana();
		
		paginaCampoTreinamento.selecionarEsportes(esportes);
		
		paginaCampoTreinamento.cadastrar();
		
		Assert.assertEquals(msg, dsl.alertaObterTextoEConfirmar());
	}
}
