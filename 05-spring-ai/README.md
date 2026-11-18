# API Inteligente de Orçamento Financeiro com Spring AI

Projeto desenvolvido como desafio final do módulo **05-spring-ai** da trilha Spring Boot da DIO. Trata-se de uma aplicação inteligente focada no registro e gerenciamento de transações financeiras utilizando recursos de inteligência artificial generativa e execução de ferramentas (*Tool Calling*).

---

## 🚀 O que o projeto faz
A aplicação atua como um assistente financeiro inteligente capaz de processar dados estruturados e transações, integrando modelos de linguagem e áudio (OpenAI) para extração de informações de gastos e persistência segura em banco de dados relacional.

---

## 💡 Melhoria Implementada (Desafio - Opção A)
* **Validação de Domínio e Dados nas Transações:** Implementação de regras de validação estritas na entidade de domínio (`Transaction`) e no caso de uso (`PersistTransactionUseCase`), garantindo:
  * Bloqueio de valores zerados ou negativos (`amount <= 0`).
  * Validação de descrições nulas ou em branco.
  * Obrigatoriedade de categorias válidas (`GROCERIES`, `PHARMA`, `AUTO`).
* **Testes Unitários Isolados:** Criação de uma suíte robusta de testes unitários com JUnit 5 e Mockito (`PersistTransactionUseCaseTest`), permitindo validar toda a lógica de negócio e as exceções de dados **sem dependência externa e sem gasto de créditos com chaves reais da OpenAI**.

---

## 🛠️ Tecnologias Utilizadas
* **Java 25** (OpenJDK)
* **Spring Boot 3+** & **Spring AI**
* **Docker & Docker Compose** (MySQL 9.6)
* **Gradle** (Gerenciador de dependências)
* **JUnit 5 & Mockito** (Testes unitários)
* **AssertJ**

---

## ⚙️ Como Executar a Aplicação

1. **Subir o Banco de Dados via Docker:**
   Na raiz do projeto, certifique-se de que o Docker está rodando e execute o container MySQL:
   ```bash
   docker compose up -d


## ⚙️ Como Configurar a Chave da API (Opcional para testes unitários):   

export OPENAI_API_KEY="sua-chave-aqui"

## 🛠️ Rodar os Testes (Unitários e de Integração):

./gradlew test

## 🧠 O que foi aprendido

Integração prática do ecossistema Spring Boot com os recursos do Spring AI (gerenciamento de ferramentas e modelos).

Aplicação rigorosa de conceitos de Clean Architecture / DDD separando regras de domínio, casos de uso e infraestrutura.

Estratégias de testes em ambientes isolados com WSL2, Docker e mocks para ganho de produtividade e segurança no desenvolvimento.