package br.com.webdrivercar.pages;

import org.openqa.selenium.WebDriver;

import br.com.webdrivercar.utils.Actions;

public class CheckoutPage {
	private WebDriver driver;
	private Actions actions;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(this.driver);
	}
	
	public void preencherDadosPessoais(String nome, String nascimento, String endereco, String cpf){
		actions.digitarId("input-nome", nome);
		actions.digitarId("input-nascimento", nascimento);
		actions.digitarId("input-endereco", endereco);
		actions.digitarId("input-cpf", cpf);
	}
	
	public void preencherPagamento(String numeroCartao, String vencimento, String cvv){
		actions.digitarId("input-cartao", numeroCartao);
		actions.digitarId("input-vencimento", vencimento);
		actions.digitarId("input-cvv", cvv);
	}
	
	public void btnContinuar() {
		actions.clickCss(".hover\\3A bg-green-700 > span");
	}
	
	public void btnAvancar() {
		actions.clickCss(".hover\\3A bg-green-700");
	}
	
	public void btnFinalizar() {
		actions.clickId("btn-finalizar");
	}
}
