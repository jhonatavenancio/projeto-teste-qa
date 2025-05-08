package br.com.webdrivercar.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class Actions {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public Actions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	// Clicar por ID
	public void clickId(String id) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
	}

	// Clicar por CSS Selector
	public void clickCss(String cssSelector) {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector))).click();
	}
	
	public void dropdown(String id, String hora) {
		try {
	        WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	        Select select = new Select(selectElement);
	        select.selectByVisibleText(hora);
	    } catch (NoSuchElementException | TimeoutException e) {
	        System.out.println("Valor '" + hora + "' não disponível no dropdown com ID: " + id);
	    }
	}

	// Digitar texto por ID
	public void digitarId(String id, String texto) {
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		elemento.clear();
		elemento.sendKeys(texto);
	}

	// Digitar texto por CSS Selector
	public void digitarCss(String cssSelector, String texto) {
		WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
		elemento.clear();
		elemento.sendKeys(texto);
	}

	// Obter texto por ID
	public String obterTextoId(String id) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).getText();
	}

	// Obter texto por CSS Selector
	public String obterTextoCss(String cssSelector) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))).getText();
	}
	
	// Intervalo na execução
	public void esperar(int tempoEspera) {
		try {
			Thread.sleep(tempoEspera);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	// validar existencia do elemento CSS
	public boolean elementoExistePorCss(String cssSelector) {
	    try {
	        return !driver.findElements(By.cssSelector(cssSelector)).isEmpty();
	    } catch (Exception e) {
	        return false;
	    }
	}



}
