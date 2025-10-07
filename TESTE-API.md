# Testando as APIs do BrainUp

## 📋 Como Testar

A aplicação está rodando em `http://localhost:8080` e inclui dados de exemplo já carregados.

## 🎮 **NOVO! APIs de Quiz em Tempo Real com Server-Sent Events**

### 🌐 Interface de Teste Interativa
- **URL**: http://localhost:8080/test-sse.html
- Esta página permite testar todas as funcionalidades de tempo real do sistema

### 📡 Novos Endpoints SSE

#### 1. Iniciar Quiz (Entrar como jogador)
```bash
curl -X POST http://localhost:8080/api/start \
  -H "Content-Type: application/json" \
  -d '{
    "playerName": "João Silva"
  }'
```
**Resposta:**
```json
{
  "playerId": "uuid-do-jogador"
}
```

#### 2. Stream para Admin (Server-Sent Events)
```bash
curl http://localhost:8080/api/stream/admin \
  -H "Accept: text/event-stream"
```

#### 3. Stream para Jogador (Server-Sent Events)
```bash
curl http://localhost:8080/api/stream/player/{playerId} \
  -H "Accept: text/event-stream"
```

#### 4. Sair do Quiz
```bash
curl -X DELETE http://localhost:8080/api/exit/{playerId}
```

#### 5. Status do Quiz
```bash
curl http://localhost:8080/api/quiz/status
```

### 📊 Eventos SSE

#### Para Administradores:
- `admin.connected` - Admin conectado
- `player.joined` - Jogador entrou no quiz
- `player.exited` - Jogador saiu do quiz

#### Para Jogadores:
- `connected` - Jogador conectado com sucesso

### 🧪 Testando o Sistema Completo

1. **Abra o navegador** em: http://localhost:8080/test-sse.html
2. **Conecte como Admin** clicando em "Conectar como Admin"
3. **Em outra aba/janela**, digite um nome e clique em "Entrar no Quiz"
4. **Observe** os eventos sendo recebidos em tempo real no painel do admin
5. **Teste a saída** clicando em "Sair do Quiz"

### 🔍 Console H2
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:brainup`
- **Username**: `sa`
- **Password**: `password`

### 🧪 Testando as APIs

#### 1. Listar todos os quizzes
```bash
curl http://localhost:8080/api/quizzes
```

#### 2. Listar todos os jogadores
```bash
curl http://localhost:8080/api/players
```

#### 3. Listar todas as questões
```bash
curl http://localhost:8080/api/questions
```

#### 4. Buscar um quiz específico (copie o ID de um quiz da lista)
```bash
curl http://localhost:8080/api/quizzes/{UUID_DO_QUIZ}
```

#### 5. Criar um novo jogador
```bash
curl -X POST http://localhost:8080/api/players \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Ana Silva"
  }'
```

#### 6. Criar um novo quiz
```bash
curl -X POST http://localhost:8080/api/quizzes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Quiz de Programação",
    "description": "Teste seus conhecimentos em programação"
  }'
```

#### 7. Atualizar pontuação de um jogador
```bash
curl -X PUT "http://localhost:8080/api/players/{UUID_DO_PLAYER}/score?points=10" \
  -H "Content-Type: application/json"
```

#### 8. Criar uma nova questão
```bash
curl -X POST http://localhost:8080/api/questions \
  -H "Content-Type: application/json" \
  -d '{
    "text": "Qual é a linguagem de programação mais popular?"
  }'
```

### 🌐 Usando o Navegador

Você também pode acessar diretamente no navegador:
- http://localhost:8080/api/quizzes - Lista todos os quizzes
- http://localhost:8080/api/players - Lista todos os jogadores
- http://localhost:8080/api/questions - Lista todas as questões

### 📊 Dados Já Carregados

A aplicação já inclui:
- ✅ **3 jogadores**: João Silva, Maria Santos, Pedro Costa
- ✅ **1 quiz**: "Quiz de Conhecimentos Gerais" 
- ✅ **3 questões** com alternativas de múltipla escolha:
  1. Qual é a capital do Brasil?
  2. Quanto é 2 + 2?
  3. Quem escreveu 'Dom Casmurro'?

### 🛠️ Ferramentas Recomendadas

Para testar mais facilmente, você pode usar:
- **Postman** - Interface gráfica para APIs
- **curl** - Linha de comando (exemplos acima)
- **Insomnia** - Alternativa ao Postman
- **Navegador Web** - Para métodos GET simples