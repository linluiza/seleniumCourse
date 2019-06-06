package br.ce.selenium.seubarriga;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCampoTreinamento.class, TestAlert.class, TesteFramesEJanelas.class
})
public class SuiteSeuBarriga {

}
