package br.com.webdrivercar.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class ConfigTest {

	// ====== CONFIGURAÇÕES DO TESTE ======
	public static final String url = "https://poetic-meringue-310602.netlify.app/";
	public static final boolean fecharNavegador = true;
	public static final boolean headless = false;
	public static final String navegador = "CHROME";

	// ====== GERADOR ======
	private static final Faker faker = new Faker(new Locale("pt-BR"));
	private static final Random random = new Random();

	// ====== DADOS DO CLIENTE ======
	public static final String nomeCliente = faker.name().fullName();
	public static final String cpf = gerarCpf();
	public static final String endereco = faker.address().streetAddress();
	public static final String cep = faker.address().zipCode().replace("-", "");
	
	// ====== LOCAÇÃO ======
	private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final String destino = faker.address().city();
	public static String dataRetirada() {
	    return LocalDate.now().format(FORMATADOR_DATA);
	}

	public static String dataDevolucao() {
	    return LocalDate.now().plusDays(2).format(FORMATADOR_DATA);
	}
	
	// ====== DADOS DO CARTÃO DE CRÉDITO ======
	public static final String numeroCartaoCredito = gerarNumeroCartaoCredito();
	public static final String vencimento = gerarDataVencimentoCartao();
	public static final String cvv = gerarCVV();

	// ====== MÉTODOS AUXILIARES ======
	private static String gerarNumeroCartaoCredito() {
		String numero;
		do {
			numero = faker.finance().creditCard().replaceAll("[^0-9]", "");
		} while (numero.length() != 16);
		return numero;
	}

	private static String gerarDataVencimentoCartao() {
		int mes = random.nextInt(12) + 1;
		int ano = LocalDate.now().getYear() % 100 + random.nextInt(5) + 1; 
		return String.format("%02d/%02d", mes, ano);
	}

	private static String gerarCVV() {
		return String.format("%03d", random.nextInt(1000));
	}

	private static String gerarCpf() {
		StringBuilder cpf = new StringBuilder();
		for (int i = 0; i < 11; i++) {
			cpf.append(random.nextInt(10));
		}
		return cpf.toString();
	}
	
	// Número aleatório
		private static final int[] VALORES = {1, 2, 3, 5, 6};
		public static int gerarNumeroAleatorio() {
			int index = random.nextInt(VALORES.length);
			return VALORES[index];
		}
}
