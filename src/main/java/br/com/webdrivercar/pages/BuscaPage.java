package br.com.webdrivercar.pages;

import org.openqa.selenium.WebDriver;

import br.com.webdrivercar.utils.Actions;

public class BuscaPage {

	private WebDriver driver;
	private Actions actions;
	
	public BuscaPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(this.driver);
	}
	
	public void preencherBusca(String retirada, String dataRetirada, String dataDevolucao, String hora) {
		actions.digitarId("input-destino", retirada);
		actions.digitarId("input-dia", dataRetirada);
		actions.digitarId("input-dia-devolucao", dataDevolucao);
		actions.dropdown("input-hora", hora);
	}
	
	
	public void btnPesquisar() {
		actions.clickId("btn-buscar");
	}
}
