package br.com.webdrivercar.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Navegador {

	private static WebDriver driver;

	public enum selecionarNavegador {
		CHROME, FIREFOX, EDGE
	}

	public static WebDriver iniciarNavegador(String navegador, boolean headless) {
		selecionarNavegador tipo;

		try {
			tipo = selecionarNavegador.valueOf(navegador.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Navegador selecionado inválido. Usando CHROME como padrão.");
			tipo = selecionarNavegador.CHROME;
		}

		switch (tipo) {
		case FIREFOX:
			driver = iniciarFirefox(headless);
			break;
		case EDGE:
			driver = iniciarEdge(headless);
			break;
		case CHROME:
		default:
			driver = iniciarChrome(headless);
			break;
		}

		return driver;
	}
	

	private static WebDriver iniciarFirefox(boolean headless) {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("layout.css.devPixelsPerPx", "1.35");

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);

		if (headless) {
			options.addArguments("--headless");
		}

		return new FirefoxDriver(options);
	}

	private static WebDriver iniciarEdge(boolean headless) {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("force-device-scale-factor=1.35");

		if (headless) {
			options.addArguments("--headless");
		}

		return new EdgeDriver(options);
	}

	private static WebDriver iniciarChrome(boolean headless) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("force-device-scale-factor=1.35");

		if (headless) {
			options.addArguments("--headless=new");
		}

		return new ChromeDriver(options);
	}


	public static void fechar(boolean fechar) {
		if (fechar && driver != null) {
			driver.quit();
			driver = null;
		}
	}
}

