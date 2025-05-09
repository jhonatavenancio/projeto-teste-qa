## Plano e automação de testes - **WebDriver Car - Sistema de Alugel de Carros!**

Este é um projeto de automação de testes desenvolvido em **Java** utilizando o **Selenium WebDriver**, com o objetivo de validar funcionalidades críticas de um sistema de **locação de veículos**. O projeto segue o padrão **Page Object Model (POM)**, promovendo uma estrutura limpa, modular e de fácil manutenção. 

![image](https://github.com/user-attachments/assets/8afecd45-8889-400a-a5bf-74443d3e46a2)
Link do sistema testado: https://poetic-meringue-310602.netlify.app/


No repositório também tem em pdf o Plano de teste e Relatório de Falhas.

---

### 📁 Estrutura do Projeto

```
webdriver-car/
├── src/
│   ├── main/java/
│   │   ├── br.com.webdrivercar.drivers/        → Configuração de drivers (Chrome, etc.)
│   │   ├── br.com.webdrivercar.pages/          → Páginas do sistema (Page Object)
│   │   ├── br.com.webdrivercar.utils/          → Utilitários (ex: ações e dados fake)
│   ├── test/java/
│   │   ├── br.com.webdrivercar.config/         → Configurações globais dos testes
│   │   ├── br.com.webdrivercar.test/           → Casos de teste automatizados
├── pom.xml                                     → Dependências Maven
```

---

### 🔧 Tecnologias Utilizadas

* Java 11
* Selenium WebDriver
* JUnit 5
* Maven
* Faker (para geração de dados)
* Page Object Model
* Execução em Modo Headless (opcional)

---

### ⚙️ Configurações do Projeto

A classe `ConfigTest.java` permite configurar:

* Navegador (Chrome, Firefox e Edge)
* Modo headless
* Tempo de espera (timeouts)
* URL base do sistema

---

### 📌 Boas Práticas Aplicadas

* Padrão Page Object Model
* Geração de massa de dados com Faker
* Separação clara entre código de teste, configuração e páginas
* Testes reutilizáveis e modulares
* Execução paralela preparada para futuras implementações


