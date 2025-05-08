package br.com.webdrivercar.pages;

import org.openqa.selenium.WebDriver;

import br.com.webdrivercar.utils.Actions;

public class CarroPage {

	private WebDriver driver;
	private Actions actions;
	
	public CarroPage(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(this.driver);
	}
	
	public void btnAlugarCarro(int carro) {
		switch (carro) {
		case 1:
		default:
		actions.clickId("btn-alugar-1");
		break;
		case 2:
		actions.clickId("btn-alugar-2");
		break;
		case 3:
		actions.clickId("btn-alugar-3");
		break;
		case 5:
		actions.clickId("btn-alugar-5");
		break;
		case 6:
		actions.clickId("btn-alugar-6");
		break;
		}
	}
	
	public void btnAcrescimo(int item) {
		switch (item) {
		case 1:
		default:
		actions.clickId("check-seguro");
		break;
		case 2:
		actions.clickId("check-tanque");
		break;
		case 3:
		actions.clickId("check-limpeza");
		break;
		}
	}
	
	public void btnVoltar() {
		actions.clickCss(".lucide-arrow-left");
	}
	
}
