# Dispatch Engine - Sistema Avançado de Precificação e Gestão de Entregas

Este é um motor de logística e precificação dinâmico desenvolvido em **Java** com **Spring Boot**. O ecossistema foi projetado seguindo princípios de **Clean Architecture**, **Domain-Driven Design (DDD)** simplificado e **SOLID**, garantindo alta escalabilidade, desacoplamento e facilidade de manutenção.

## 🎯 O que o projeto faz na prática?

O sistema expõe uma API RESTful onde aplicações parceiras ou aplicativos móveis podem solicitar a criação de ordens de entrega. Ao receber uma requisição:
1. **Validação de Domínio:** O motor consulta a base de dados relacional para validar a existência do cliente.
2. **Precificação Dinâmica (Core Engine):** Utilizando o **Strategy Design Pattern**, o sistema elimina blocos condicionais complexos (`if/else`) para determinar o valor do frete cruzando a distância com a categoria do veículo selecionado (`MOTO`, `CARRO_PASSEIO`, `UTILITARIO`).
3. **Persistência Concorrente:** A entrega é salva com o status inicial de `CRIADA` e armazenada com segurança transacional no banco de dados **MySQL**.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java
* **Framework:** Spring Boot 3.x
* **ORM:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Design Patterns:** Strategy, DTO (Records)

## 🚀 Como Executar
Para rodar localmente, configure as credenciais do MySQL no `application.properties` e execute o comando no terminal:
`.\mvnw spring-boot:run`