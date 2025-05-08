package br.com.webdrivercar.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import br.com.webdrivercar.config.ConfigTest;
import br.com.webdrivercar.drivers.Navegador;
import br.com.webdrivercar.pages.BuscaPage;
import br.com.webdrivercar.utils.Actions;

public class BuscaTest {

	private WebDriver driver;
	private Actions actions;
	private BuscaPage busca;
	
	@BeforeEach
	public void antes() {
		driver = Navegador.iniciarNavegador(ConfigTest.navegador, ConfigTest.headless);
		driver.navigate().to(ConfigTest.url);
		
		actions = new Actions(driver);
		busca = new BuscaPage(driver);
	}
	
	@AfterEach
	public void depois() {
		Navegador.fechar(ConfigTest.fecharNavegador);
	}
	
	@Test
	public void buscarVeiculo() {
		busca.preencherBusca(ConfigTest.destino, 
				ConfigTest.dataRetirada(), 
				ConfigTest.dataDevolucao(), "13:00");
		
		busca.btnPesquisar();
		actions.esperar(500);
		// Validar redirecionamento
		Assertions.assertEquals("Resultado da busca", actions.obterTextoCss(".mb-2:nth-child(1)"),
				"Falha: Texto diferente do esperado.");
	}
	
	@Test
	public void buscarSemPreencherCampos() {
		busca.preencherBusca("", "", "", "");
		
		busca.btnPesquisar();
		actions.esperar(500);
		// Validar busca sem campos obrigatórios
		Assertions.assertFalse(actions.elementoExistePorCss(".mb-2:nth-child(1)"),
			    "Falha: O elemento deveria estar ausente, mas está presente.");

	}
	
	@Test
	public void buscarVeiculoComCamposObrigatorios() {
		busca.preencherBusca(ConfigTest.destino, 
				ConfigTest.dataRetirada(), 
				"", "13:00");
		
		busca.btnPesquisar();
		actions.esperar(500);
		// Validar busca apenas com campos obrigatórios
		Assertions.assertEquals("Resultado da busca", actions.obterTextoCss(".mb-2:nth-child(1)"),
				"Falha: Texto diferente do esperado.");
	}
	
}
