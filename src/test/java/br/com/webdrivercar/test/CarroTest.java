package br.com.webdrivercar.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import br.com.webdrivercar.config.ConfigTest;
import br.com.webdrivercar.drivers.Navegador;
import br.com.webdrivercar.pages.BuscaPage;
import br.com.webdrivercar.pages.CarroPage;
import br.com.webdrivercar.utils.Actions;

public class CarroTest {
	private WebDriver driver;
	private Actions actions;
	private BuscaPage busca;
	private CarroPage carro;
	
	@BeforeEach
	public void antes() {
		driver = Navegador.iniciarNavegador(ConfigTest.navegador, ConfigTest.headless);
		driver.navigate().to(ConfigTest.url);
		
		actions = new Actions(driver);
		busca = new BuscaPage(driver);
		carro = new CarroPage(driver);
		
		busca.preencherBusca(ConfigTest.destino, 
				ConfigTest.dataRetirada(), 
				ConfigTest.dataDevolucao(), "13:00");
		busca.btnPesquisar();
		actions.esperar(500);
		Assertions.assertEquals("Resultado da busca", actions.obterTextoCss(".mb-2:nth-child(1)"),"Falha: Texto diferente do esperado.");	
	}
	
	@AfterEach
	public void depois() {
		Navegador.fechar(ConfigTest.fecharNavegador);
	}
	
	@Test
	public void alugarVeiculo() {
		carro.btnAlugarCarro(ConfigTest.gerarNumeroAleatorio());
		// Validar disponibilidade de botão
		Assertions.assertEquals("Finalizar Aluguel", actions.obterTextoCss(".text-2xl:nth-child(1)"),"Falha: Texto diferente do esperado.");
	}
	
	@Test
	public void validarVeiculoIndisponivel() {	
		// Validar se botão indisponivel está visuvel
		Assertions.assertEquals("Indisponível", actions.obterTextoId("btn-alugar-indisponivel"),"Texto diferente do esperado.");
	}
	
	@Test
	public void voltarTelaBusca() {	
		carro.btnVoltar();
		// Validar se retrocedeu à página
		Assertions.assertEquals("Alugue um carro com facilidade", actions.obterTextoCss(".text-4xl"),"Texto diferente do esperado.");
	}
	
}
