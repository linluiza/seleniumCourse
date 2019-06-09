package br.ce.selenium.seubarriga;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestePreenchimentoCamposForm.class, TesteAlertsEPrompts.class, TesteFramesEJanelas.class, TesteCadastroForm.class
})
public class SuiteSeuBarriga {

}
