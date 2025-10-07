# BrainUp - Sistema de Quiz# BrainUp 



## 📋 Descrição



O BrainUp é uma aplicação Spring Boot para gerenciamento de quizzes interativos com Server-Sent Events (SSE). O sistema permite que jogadores entrem em quizzes e administradores monitorem em tempo real.## 📋 Descrição## 📋 Descrição



## 🏗️ Arquitetura



O projeto segue o padrão MVC com as seguintes entidades principais:O BrainUp é uma aplicação Spring Boot para gerenciamento de quizzes interativos com Server-Sent Events (SSE). O sistema permite que jogadores entrem em quizzes e administradores monitorem em tempo real.O BrainUp é uma aplicação Spring Boot para gerenciamento de quizzes interativos. O sistema permite criar quizzes com questões de múltipla escolha, gerenciar jogadores e acompanhar pontuações.



### Entidades

- **Quiz**: Representa um quiz com título, descrição e questões

- **Question**: Representa uma questão com texto e alternativas## 🏗️ Arquitetura## 🏗️ Arquitetura

- **Alternative**: Representa uma alternativa de resposta (correta ou incorreta)  

- **Player**: Representa um jogador com nome, pontuação e status ativo



### Estrutura do ProjetoO projeto segue o padrão MVC com as seguintes entidades principais:O projeto segue o padrão MVC com as seguintes entidades principais:

```

src/main/java/com/brainup/BrainUp/

├── entity/          # Entidades

├── repository/      # Repositório em memória### Entidades### Entidades

├── service/         # Camada de serviços

├── controller/      # Controlador do Quiz Game- **Quiz**: Representa um quiz com título, descrição e questões- **Quiz**: Representa um quiz com título, descrição, questões e jogadores

├── config/          # Configurações

└── BrainUpApplication.java- **Question**: Representa uma questão com texto e alternativas- **Question**: Representa uma questão com texto e alternativas

```

- **Alternative**: Representa uma alternativa de resposta (correta ou incorreta)  - **Alternative**: Representa uma alternativa de resposta (correta ou incorreta)  

## 🚀 Como Executar

- **Player**: Representa um jogador com nome, pontuação e status ativo- **Player**: Representa um jogador com nome, pontuação e status ativo

### Pré-requisitos

- Java 21+

- Gradle

### Estrutura do Projeto### Estrutura do Projeto

### Executando a Aplicação

``````

1. **Clone o repositório**

   ```bashsrc/main/java/com/brainup/BrainUp/src/main/java/com/brainup/BrainUp/

   git clone <url-do-repositorio>

   cd BrainUp├── entity/          # Entidades├── entity/          # Entidades JPA

   ```

├── repository/      # Repositório em memória├── repository/      # Repositórios Spring Data

2. **Execute a aplicação**

   ```bash├── service/         # Camada de serviços├── service/         # Camada de serviços

   ./gradlew bootRun

   ```├── controller/      # Controlador do Quiz Game├── controller/      # Controladores REST

   

   Ou no Windows:├── config/          # Configurações├── config/          # Configurações

   ```bash

   gradlew.bat bootRun└── BrainUpApplication.java└── BrainUpApplication.java

   ```

``````

3. **Acesse a aplicação**

   - Interface de Teste: http://localhost:8080/test-sse.html

   - API: http://localhost:8080/api

## 🚀 Como Executar## 🚀 Como Executar

## 📡 API Endpoints Principais



### Iniciar Quiz (Entrar como jogador)

- **POST** `/api/start`### Pré-requisitos### Pré-requisitos

- **Body**: `{"playerName": "Nome do Jogador"}`

- **Resposta**: `{"playerId": "uuid-do-jogador"}`- Java 21+- Java 21+



### Stream para Admin (Server-Sent Events)- Gradle- Gradle

- **GET** `/api/stream/admin`

- **Content-Type**: `text/event-stream`



### Stream para Jogador (Server-Sent Events)### Executando a Aplicação### Executando a Aplicação

- **GET** `/api/stream/player/{playerId}`

- **Content-Type**: `text/event-stream`



### Sair do Quiz1. **Clone o repositório**1. **Clone o repositório**

- **DELETE** `/api/exit/{playerId}`

   ```bash   ```bash

### Status do Quiz

- **GET** `/api/quiz/status`   git clone <url-do-repositorio>   git clone <url-do-repositorio>



## 📊 Eventos SSE   cd brainup   cd BrainUp



### Para Administradores:   ```   ```

- `admin.connected` - Admin conectado

- `player.joined` - Jogador entrou no quiz

- `player.exited` - Jogador saiu do quiz

2. **Execute a aplicação**2. **Execute a aplicação**

### Para Jogadores:

- `connected` - Jogador conectado com sucesso   ```bash   ```bash



## 🧪 Como Testar   # Via Gradle   ./gradlew bootRun



1. **Abra o navegador** em: http://localhost:8080/test-sse.html   ./gradlew bootRun   ```

2. **Conecte como Admin** clicando em "Conectar como Admin"

3. **Em outra aba/janela**, digite um nome e clique em "Entrar no Quiz"      

4. **Observe** os eventos sendo recebidos em tempo real no painel do admin

5. **Teste a saída** clicando em "Sair do Quiz"   # Ou via IDE   Ou no Windows:



## 📚 Tecnologias Utilizadas   # Execute a classe BrainUpApplication.java   ```cmd



- **Spring Boot 3.5.6**   ```   gradlew.bat bootRun

- **Java 21**

- **Lombok** (para redução de boilerplate)   ```

- **Server-Sent Events (SSE)** (para comunicação em tempo real)

- **Gradle** (build tool)3. **Acesse a aplicação**



## 🎯 Funcionalidades Implementadas   - Interface de teste: http://localhost:8080/test-sse.html3. **Acesse a aplicação**



✅ **Start** - Jogador entra no quiz informando o nome     - API: http://localhost:8080   - API: http://localhost:8080

✅ **Game** - Jogador é encaminhado para tela de espera via SSE  

✅ **Admin** - Eventos enviados para admin quando jogador entra/sai     - Console H2: http://localhost:8080/h2-console

✅ **Repository** - Simulação de banco de dados com dados de exemplo  

✅ **Stream SSE** - Comunicação em tempo real entre cliente e servidor  ## 📡 API Endpoints Principais     - JDBC URL: `jdbc:h2:mem:brainup`

✅ **Interface de Teste** - Página HTML para testar funcionalidades  

     - Username: `sa`

## 📝 Estrutura de Dados

### Quiz Game (Server-Sent Events)     - Password: `password`

O quiz contém 5 questões sobre conhecimentos gerais de Java:

- `POST /api/start` - Jogador entrar no quiz

1. Qual palavra-chave é usada para definir uma classe em Java?

2. Qual tipo de dado armazena números inteiros?- `GET /api/stream/admin` - Stream SSE para administradores  ## 📡 API Endpoints

3. Como se declara um método estático?

4. Qual é o operador de atribuição em Java?- `GET /api/stream/player/{playerId}` - Stream SSE para jogador

5. Qual estrutura de controle repete um bloco de código enquanto uma condição é verdadeira?

- `DELETE /api/exit/{playerId}` - Jogador sair do quiz### Quizzes

## 📄 Licença

- `GET /api/quiz/status` - Status atual do quiz- `GET /api/quizzes` - Listar todos os quizzes

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
- `GET /api/quizzes/{id}` - Buscar quiz por ID

### 📊 Eventos SSE- `POST /api/quizzes` - Criar novo quiz

- `PUT /api/quizzes/{id}` - Atualizar quiz

#### Para Administradores:- `DELETE /api/quizzes/{id}` - Deletar quiz

- `admin.connected` - Admin conectado- `POST /api/quizzes/{quizId}/players` - Adicionar jogador ao quiz

- `player.joined` - Jogador entrou no quiz

- `player.exited` - Jogador saiu do quiz### Jogadores  

- `GET /api/players` - Listar todos os jogadores

#### Para Jogadores:- `GET /api/players/{id}` - Buscar jogador por ID

- `connected` - Jogador conectado com sucesso- `POST /api/players` - Criar novo jogador

- `PUT /api/players/{id}` - Atualizar jogador

## 🧪 Testando o Sistema- `DELETE /api/players/{id}` - Deletar jogador

- `PUT /api/players/{id}/score?points={pontos}` - Atualizar pontuação

1. **Abra o navegador** em: http://localhost:8080/test-sse.html- `PUT /api/players/{id}/active?active={true/false}` - Ativar/desativar jogador

2. **Conecte como Admin** clicando em "Conectar como Admin"

3. **Em outra aba/janela**, digite um nome e clique em "Entrar no Quiz"### Questões

4. **Observe** os eventos sendo recebidos em tempo real no painel do admin- `GET /api/questions` - Listar todas as questões

5. **Teste a saída** clicando em "Sair do Quiz"- `GET /api/questions/{id}` - Buscar questão por ID

- `POST /api/questions` - Criar nova questão

## 🎯 Funcionalidades Implementadas- `PUT /api/questions/{id}` - Atualizar questão

- `DELETE /api/questions/{id}` - Deletar questão

✅ **Start** - Entrada do jogador com nome- `POST /api/questions/{questionId}/alternatives` - Adicionar alternativa à questão

✅ **Game** - Tela de espera com SSE para jogador

✅ **Admin** - Eventos em tempo real para administrador## 🗂️ Exemplos de Uso

✅ **Modelagem** - Entidades conforme diagrama

✅ **Repository** - Dados de exemplo em memória### Criar um Quiz

✅ **Iniciar Quiz** - Endpoint POST /start```bash

✅ **Stream Admin** - Endpoint GET /stream/admincurl -X POST http://localhost:8080/api/quizzes \

✅ **Avisar Admin** - Evento player.joined  -H "Content-Type: application/json" \

✅ **Sair da Sala** - Endpoint DELETE /exit/{playerId}  -d '{

    "title": "Quiz de História",

## 📄 Licença    "description": "Teste seus conhecimentos sobre história mundial"

  }'

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.```

### Criar um Jogador
```bash
curl -X POST http://localhost:8080/api/players \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ana Silva"
  }'
```

### Criar uma Questão
```bash
curl -X POST http://localhost:8080/api/questions \
  -H "Content-Type: application/json" \
  -d '{
    "text": "Em que ano foi descoberto o Brasil?"
  }'
```

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database** (desenvolvimento)
- **Lombok**
- **Gradle**

## 📊 Dados Iniciais

A aplicação inclui dados de exemplo que são carregados automaticamente:
- 3 jogadores (João Silva, Maria Santos, Pedro Costa)
- 1 quiz com 3 questões de conhecimentos gerais
- Questões com alternativas de múltipla escolha

## 🔧 Configurações

### Banco de Dados
O projeto usa H2 em memória para desenvolvimento. Para produção, você pode configurar outro banco de dados no `application.properties`.

### Porta do Servidor
Por padrão, a aplicação roda na porta 8080. Você pode alterar isso no arquivo `application.properties`:
```properties
server.port=8080
```

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
