# Desafio Técnico: Sistema de Gestão Escolar e Boletins - Straloo

<p align="center">
  <img src="https://painel.straloo.com.br/assets/logo_rounded.png" width="220" alt="Straloo Logo" />
</p>

## Sumário
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Descrição do Projeto](#descrição-do-projeto)
- [Arquitetura](#arquitetura)
  - [Banco de Dados](#banco-de-dados)
  - [Fluxo da Aplicação](#fluxo-da-aplicação)
- [Executando o Projeto com Docker](#executando-o-projeto-com-docker)
    - [Pré-requisitos](#pré-requisitos)
    - [Configuração do Ambiente](#configuração-do-ambiente)
    - [Subindo os Serviços](#subindo-os-serviços)
    - [Acessando as APIs](#acessando-as-apis)
    - [Executando Testes](#executando-testes)
- [Capturas de tela](#executando-o-projeto-com-docker)
---

## Tecnologias Utilizadas

<div align="center">
  
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Quarkus](https://img.shields.io/badge/Quarkus-4695EB?style=for-the-badge&logo=quarkus&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-000?style=for-the-badge&logo=postgresql)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![Panache](https://img.shields.io/badge/Panache-0095D5?style=for-the-badge)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
<br>
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-78A641?style=for-the-badge)
![MicroProfile REST Client](https://img.shields.io/badge/MicroProfile%20REST%20Client-0095D5?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white&style=for-the-badge)
![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?logo=docker&logoColor=white&style=for-the-badge)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

</div>

---

## Descrição do Projeto

Este projeto foi desenvolvido como solução para o Desafio Técnico – Sistema de Gestão Escolar e Boletins, proposto pela Straloo.

O objetivo é automatizar a geração de boletins escolares por meio de uma arquitetura baseada em dois serviços independentes, simulando um cenário real de microsserviços.

### A solução é composta por:

1. **Serviço Core (Principal)**  
   Responsável pela gestão de alunos e turmas, além de atuar como ponto de entrada para a geração de boletins.

2. **Serviço Acadêmico (Auxiliar)**  
   Responsável pela gestão de disciplinas, avaliações e notas dos alunos.

Quando uma requisição de geração de boletim é realizada, o Serviço Core consulta o Serviço Acadêmico para obter as notas dos alunos, consolida as informações e retorna o boletim final ao consumidor da API.

O projeto foi desenvolvido utilizando **Kotlin com Quarkus**, seguindo o padrão de **Arquitetura Hexagonal**, com foco em:
- Separação clara de responsabilidades
- Código limpo e organizado
- Facilidade de manutenção e testes
- Aderência às boas práticas de desenvolvimento de software

Para facilitar a execução e validação da aplicação, os dados iniciais de alunos, turmas, disciplinas e notas são carregados automaticamente por meio de scripts de migração e seed utilizando **Flyway**, não sendo necessário cadastro manual para testes.

---

## Arquitetura

A solução foi desenvolvida seguindo uma arquitetura de microserviços, composta pelos serviços **Core (Student)** e **Acadêmico (Grades)**. A comunicação entre os serviços é realizada por meio de requisições HTTP.

Em ambos os serviços, foi aplicada a **Arquitetura Hexagonal (Ports and Adapters)**, com a organização do código dividida em três camadas principais:

### **Domain**
Camada central que contém as regras de negócio e modelos de domínio, sendo totalmente independente de frameworks e tecnologias externas.

### **Application**
Camada responsável por orquestrar os casos de uso da aplicação, contendo os serviços, DTOs e as ports que definem os contratos de entrada e saída.

### **Adapters**
Camada responsável pela interação com o mundo externo, dividida em:
- **Inbound**: controllers HTTP responsáveis por receber as requisições
- **Outbound**: implementações de acesso a banco de dados e comunicação entre serviços

Essa abordagem garante baixo acoplamento, facilita a escrita de testes unitários e permite a evolução independente de cada microserviço.

### Banco de Dados

O projeto utiliza **PostgreSQL** com uma modelagem relacional normalizada, garantindo integridade e consistência dos dados entre alunos, turmas, matrículas, disciplinas e notas.

<img width="1760" height="1360" alt="Diagrama ER do banco de dados" src="https://github.com/user-attachments/assets/97fa9c98-d544-44b4-9f26-4d8addafb1f5" />

Para suportar **soft delete**, as tabelas possuem o campo `deleted_at`, permitindo a exclusão lógica dos registros sem perda de histórico. Com isso, foram definidos índices únicos condicionais (partial unique indexes), garantindo unicidade apenas para registros ativos.

#### **Principais regras de integridade implementadas:**
- **Alunos**: unicidade de e-mail apenas para registros não excluídos logicamente (`deleted_at IS NULL`)
- **Turmas**: unicidade da combinação de ano, curso e série para turmas não excluídas logicamente
- **Matrículas**:
  - impedimento de múltiplas matrículas do mesmo aluno na mesma turma
  - garantia de apenas uma matrícula ativa por aluno
- **Disciplinas**: unicidade considerando nome, série, tipo e curso, diferenciando disciplinas base e específicas por curso
- **Notas**: unicidade de notas por matrícula, disciplina e bimestre para registros não excluídos logicamente

Essa abordagem fortalece a normalização do banco de dados, evita inconsistências mesmo em cenários de soft delete e garante regras de negócio diretamente na camada de persistência.

### Fluxo da Aplicação

O fluxo de dados da aplicação segue o padrão definido pela **Arquitetura Hexagonal**:

#### **1. Adapters (Inbound)**  
As requisições entram no sistema por meio dos controllers HTTP, localizados na camada de Adapters Inbound. Esses controllers são responsáveis apenas por receber a requisição e delegar a execução para os casos de uso da camada de Application.

#### **2. Application**  
Na camada de Application, os serviços executam a lógica de orquestração e interagem com o domínio, utilizando ports para acesso a recursos externos.

#### **3. Adapters (Outbound)**  
As implementações concretas das ports estão localizadas na camada de Adapters Outbound, sendo responsáveis pelo acesso ao banco de dados e pela comunicação entre os microserviços.

Após a execução do caso de uso, o resultado é retornado ao controller, que responde ao consumidor da API. Esse fluxo garante a separação de responsabilidades e mantém o domínio desacoplado de detalhes de infraestrutura.

---

## Executando o Projeto com Docker

### Pré-requisitos

Para executar o projeto utilizando Docker e Docker Compose, é necessário ter instalado:
- Git
- Docker
- Docker Compose

> **Nota:** Nesta abordagem, **não é necessário** ter Java, Maven ou Quarkus instalados localmente, pois todo o processo de build e execução ocorre dentro dos containers Docker.

### Configuração do Ambiente

1. **Clonando o repositório**  
   Clone o repositório para sua máquina e acesse o diretório do projeto:
   ```bash
   git clone https://github.com/Tauan-Ray/tech-academy-backend-challenge.git
   cd tech-academy-backend-challenge
   ```

2. **Configuração das variáveis de ambiente**  
   Crie um arquivo `.env` na raiz do projeto baseado no `.env.example`:
   ```bash
   cp .env.example .env
   ```
   > **Observação:** Tenha certeza de ter configurado todas as variáveis de ambiente no seu `.env` corretamente antes da execução do projeto.

3. **Configurando `application.properties`**  
   Para que os serviços sejam corretamente buildados dentro dos containers, é necessário criar o arquivo `application.properties` para ambos os serviços, com base nos arquivos de exemplo.

   **Student Service:**
   ```bash
   cp student-service/src/main/resources/application.properties.example student-service/src/main/resources/application.properties
   ```

   **Grades Service:**
   ```bash
   cp grades-service/src/main/resources/application.properties.example grades-service/src/main/resources/application.properties
   ```

   > Caso o arquivo `.env` esteja configurado corretamente, nenhuma alteração adicional é necessária nos arquivos `application.properties` para executar o projeto via Docker.

### Subindo os Serviços

Após concluir a configuração do ambiente, execute o comando abaixo na raiz do projeto para inicializar todos os serviços:

```bash
docker compose up -d --build
```

Esse comando irá:
- Buildar as imagens Docker dos dois serviços
- Subir os bancos de dados PostgreSQL de cada serviço
- Aplicar as migrations e seeds automaticamente usando o Flyway
- Inicializar as APIs Quarkus já prontas para o uso

---

**Acompanhando os logs:**

Para acompanhar os logs em tempo real de um serviço específico, utilize:

**Student Service:**
```bash
docker compose logs students-service-api -f
```

**Grades Service:**
```bash
docker compose logs grades-service-api -f
```

Ou para ver os logs de todos os serviços juntos:
```bash
docker compose logs -f
```

---

**Encerrando os serviços:**

Para encerrar os serviços e remover os containers e redes criadas:
```bash
docker compose down
```

Caso queira apenas pausar os containers sem removê-los:
```bash
docker compose stop
```

---

### Acessando as APIs

Após subir os serviços com Docker Compose, as APIs estarão disponíveis localmente nas portas configuradas no arquivo `.env`.

Por padrão:

**Student Service:**
```
http://localhost:${STUDENT_SERVICE_EXTERNAL_PORT}
```

**Grades Service:**
```
http://localhost:${GRADE_SERVICE_EXTERNAL_PORT}
```

> **Nota:** As portas externas podem variar conforme a configuração do seu `.env`. Verifique os valores de `STUDENT_SERVICE_EXTERNAL_PORT` e `GRADE_SERVICE_EXTERNAL_PORT`.


#### **Documentação da API (OpenAPI/Swagger)**

Ambos os serviços possuem documentação completa das APIs disponível através do Swagger UI, com descrição detalhada de todos os endpoints, parâmetros, modelos de requisição e resposta.

**Para acessar a documentação:**

**Student Service:**
```
http://localhost:${STUDENT_SERVICE_EXTERNAL_PORT}/docs/api
```

**Grades Service:**
```
http://localhost:${GRADE_SERVICE_EXTERNAL_PORT}/docs/api
```

> **Exemplo:** Se `STUDENT_SERVICE_EXTERNAL_PORT=8080`, acesse: `http://localhost:8080/docs/api`
> 
> **Dica:** A documentação é gerada automaticamente a partir das anotações OpenAPI nos controllers e é atualizada sempre que o serviço é reiniciado.

### Executando Testes

Os testes do projeto são focados principalmente em testes unitários, garantindo a correta execução das regras de negócio e dos casos de uso.

Para executar os testes localmente, é necessário ter instalado:
- Java
- Maven

Em cada serviço, execute o comando:

```bash
mvn test
```

> A execução dos testes é opcional e não é necessária para subir a aplicação via Docker, sendo recomendado apenas para fins de validação e análise do código.

## Capturas de tela

### 1. Listagem de turmas
   <img width="1493" height="966" alt="image" src="https://github.com/user-attachments/assets/b6dc58fa-3e6f-48a2-a923-476c120554f5" />

### 2. Listagem de alunos
   <img width="1490" height="957" alt="image" src="https://github.com/user-attachments/assets/fad6e5b9-4335-4237-ae7e-962855b6fb3f" />

### 3. Listagem de alunos por turma
   <img width="1494" height="967" alt="image" src="https://github.com/user-attachments/assets/2aea3088-31e3-4858-81e1-006df41264c0" />

### 4. Geração de Boletins
   <img width="1491" height="962" alt="image" src="https://github.com/user-attachments/assets/5a6e5acb-d575-4109-83ea-0210761b5fea" />
