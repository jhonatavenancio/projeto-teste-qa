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
import br.com.webdrivercar.pages.CheckoutPage;
import br.com.webdrivercar.utils.Actions;

public class CheckoutTest {
	private WebDriver driver;
	private Actions actions;
	private BuscaPage busca;
	private CarroPage carro;
	private CheckoutPage checkout;
	
	@BeforeEach
	public void antes() {
		driver = Navegador.iniciarNavegador(ConfigTest.navegador, ConfigTest.headless);
		driver.navigate().to(ConfigTest.url);
		
		actions = new Actions(driver);
		busca = new BuscaPage(driver);
		carro = new CarroPage(driver);
		checkout = new CheckoutPage(driver);
		
		busca.preencherBusca(ConfigTest.destino, 
				ConfigTest.dataRetirada(), 
				ConfigTest.dataDevolucao(), "13:00");
		busca.btnPesquisar();
		actions.esperar(500);
		Assertions.assertEquals("Resultado da busca", actions.obterTextoCss(".mb-2:nth-child(1)"),"Falha: Texto diferente do esperado.");	
		
		Assertions.assertEquals("Resultado da busca", actions.obterTextoCss(".mb-2:nth-child(1)"),"Falha: Texto diferente do esperado.");	
		carro.btnAlugarCarro(ConfigTest.gerarNumeroAleatorio());
		Assertions.assertEquals("Finalizar Aluguel", actions.obterTextoCss(".text-2xl:nth-child(1)"),"Falha: Texto diferente do esperado.");
		
	}
	
	@AfterEach
	public void depois() {
		Navegador.fechar(ConfigTest.fecharNavegador);
	}
	
	@Test
	public void finalizarReservaVeiculo() {
		checkout.btnAvancar();
		checkout.preencherDadosPessoais(ConfigTest.nomeCliente, "14/10/2000", ConfigTest.endereco, ConfigTest.cpf);
		checkout.btnContinuar();
		checkout.preencherPagamento(ConfigTest.numeroCartaoCredito, ConfigTest.vencimento, ConfigTest.cvv);
		checkout.btnFinalizar();
		
		// Validar mensagem final
		Assertions.assertEquals("Obrigado por alugar com a WebDriver!", actions.obterTextoId("msg-final"),
				"Texto diferente do esperado.");
	}
	
	@Test
	public void validarCamposObrigatoriosPagamento() {
		checkout.btnAvancar();
		checkout.preencherDadosPessoais(ConfigTest.nomeCliente, "14/10/2000", ConfigTest.endereco, ConfigTest.cpf);
		checkout.btnContinuar();
		checkout.preencherPagamento("", "", "");
		checkout.btnFinalizar();
		
		// Validar avanço sem campos obrigatórios preenchidos
		Assertions.assertNotEquals("Obrigado por alugar com a WebDriver!", actions.obterTextoId("msg-final"),
				"Falha: Texto diferente do esperado.");
	}
	
	@Test
	public void validarCamposObrigatoriosDadosPessoais() {
		checkout.btnAvancar();
		checkout.preencherDadosPessoais("", "", "", "");
		checkout.btnContinuar();
		
		// Validar avanço sem campos obrigatórios preenchidos
		Assertions.assertNotEquals("Informações de Pagamento", actions.obterTextoCss(".text-xl:nth-child(1)"),
				"Falha: Texto diferente do esperado.");
	}
	
	@Test
	public void finalizarReservaVeiculoComDataDevolucao() {
		checkout.btnAvancar();
		checkout.preencherDadosPessoais(ConfigTest.nomeCliente, "14/10/2000", ConfigTest.endereco, ConfigTest.cpf);
		checkout.btnContinuar();
		
		// Validar Total x Diária
		Assertions.assertNotEquals(".justify-between > .font-semibold", ".font-bold > .text-green-600", "Falha: O valor não foi alterado após o acréscimo.");
		
		checkout.preencherPagamento(ConfigTest.numeroCartaoCredito, ConfigTest.vencimento, ConfigTest.cvv);
		checkout.btnFinalizar();
		
		// Validar mensagem final
		Assertions.assertEquals("Obrigado por alugar com a WebDriver!", actions.obterTextoId("msg-final"),
				"Texto diferente do esperado.");
	}
	
	@Test
	public void adicionarAcrescimo() {
		carro.btnAcrescimo(ConfigTest.gerarNumeroAleatorio());
		String valorAntes = actions.obterTextoCss(".justify-between > .font-semibold");
		checkout.btnAvancar();
		checkout.preencherDadosPessoais(ConfigTest.nomeCliente, "14/10/2000", ConfigTest.endereco, ConfigTest.cpf);
		checkout.btnContinuar();
		
		String valorDepois = actions.obterTextoCss(".font-bold > .text-green-600");
		// Validar valor Total com acréscimo
		Assertions.assertNotEquals(valorAntes, valorDepois, "Falha: O valor não foi alterado após o acréscimo.");
	}
}
