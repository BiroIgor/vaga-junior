# ⛽ Sistema de Gestão de Posto de Combustível

API REST completa para gerenciamento de posto de combustível desenvolvida com **Spring Boot 3.3.0** e **Java 17**.

---

## ✅ Funcionalidades Implementadas

### **Requisitos Atendidos**
✅ **Tipos de Combustível** - CRUD completo (Criar, Listar, Alterar, Deletar)
- Nome (texto)
- Preço por litro

✅ **Bombas de Combustível** - CRUD completo relacionadas aos tipos de combustível
- Nome da bomba
- Combustível que abastece

✅ **Abastecimentos** - CRUD completo com data, volume e valor total
- Bomba utilizada no abastecimento
- Data do abastecimento
- Quantidade em valores
- Litragem

✅ **Consulta de dados** - Todos os dados cadastrados via API REST
✅ **Persistência** - Dados mantidos entre reinicializações da aplicação

### **Diferenciais Implementados**
✅ **API RESTful** completa com rotas GET, POST, PUT, DELETE  
✅ **Organização profissional** com Repository, Service e Controller  
✅ **Documentação automática** com Swagger/OpenAPI  
✅ **Spring Boot 3.3.0** com estrutura enterprise  
✅ **Exception Handling** global personalizado  
✅ **Validações de negócio** e integridade referencial  

---

## 🚀 Como Executar

### **Pré-requisitos**
- Java 17+ instalado
- Maven 3.6+ instalado
- Docker (opcional, para PostgreSQL)

### **Opção 1: H2 Database (Padrão)**
```bash
# Execute a aplicação (usa H2)
mvn spring-boot:run
```

### **Opção 2: PostgreSQL com Docker**
```bash
# 1. Subir PostgreSQL via Docker
docker-compose up -d

# 2. Executar aplicação com PostgreSQL
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

A aplicação iniciará em: `http://localhost:8080`

### **Serviços Disponíveis (PostgreSQL)**
| Serviço | URL | Credenciais |
|---------|-----|-------------|
| **PostgreSQL** | `localhost:5432` | posto_admin / posto123 |
| **pgAdmin** | `http://localhost:8081` | admin@posto.com / admin123 |

---

## 🔌 Acesso às Interfaces

| Interface | URL | Descrição |
|-----------|-----|-----------|
| **API REST** | `http://localhost:8080/api/` | Endpoints da API |
| **Swagger UI** | `http://localhost:8080/swagger-ui.html` | Documentação interativa |
| **H2 Console** | `http://localhost:8080/h2-console` | Console do banco de dados |

### **Credenciais H2 Database**
- **JDBC URL**: `jdbc:h2:file:./data/postocombustivel`
- **User**: `adminposto`
- **Password**: `adminposto`

---

## 📋 Dados Iniciais

### **Tipos de Combustível**
| ID | Nome | Preço/Litro |
|----|------|-------------|
| 1 | Gasolina Comum | R$ 5,49 |
| 2 | Gasolina Aditivada | R$ 5,79 |
| 3 | Etanol | R$ 3,89 |
| 4 | Diesel Comum | R$ 5,99 |
| 5 | Gasolina Premium | R$ 6,29 |

### **Bombas Disponíveis**
- **Bomba 1**: Gasolina Comum
- **Bomba 2**: Gasolina Aditivada  
- **Bomba 3**: Etanol
- **Bomba 4**: Diesel Comum
- **Bomba 5**: Gasolina Premium
- **Bomba 6**: Gasolina Comum
- **Bomba 7**: Etanol

---

## 🔌 Endpoints da API

### **Tipos de Combustível** (`/api/tipos-combustivel`)
```http
GET    /api/tipos-combustivel        # Listar todos
GET    /api/tipos-combustivel/{id}   # Buscar por ID
POST   /api/tipos-combustivel        # Criar novo
PUT    /api/tipos-combustivel/{id}   # Atualizar
DELETE /api/tipos-combustivel/{id}   # Deletar
```

### **Bombas de Combustível** (`/api/bombas-combustivel`)
```http
GET    /api/bombas-combustivel           # Listar todas
GET    /api/bombas-combustivel/{id}      # Buscar por ID
POST   /api/bombas-combustivel           # Criar nova
PUT    /api/bombas-combustivel/{id}      # Atualizar
DELETE /api/bombas-combustivel/{id}      # Deletar
```

### **Abastecimentos** (`/api/abastecimentos`)
```http
GET    /api/abastecimentos               # Listar todos (ordenados por data)
GET    /api/abastecimentos/{id}          # Buscar por ID
POST   /api/abastecimentos               # Criar novo
PUT    /api/abastecimentos/{id}          # Atualizar
DELETE /api/abastecimentos/{id}          # Deletar
GET    /api/abastecimentos/bomba/{id}    # Listar por bomba
GET    /api/abastecimentos/estatisticas/bomba/{id}  # Estatísticas por bomba
```

---

## 💡 Exemplos de Uso

### **Criar Tipo de Combustível**
```bash
curl -X POST "http://localhost:8080/api/tipos-combustivel" \
     -H "Content-Type: application/json" \
     -d '{
       "nome": "Diesel S-10",
       "precoPorLitro": 6.15
     }'
```

### **Criar Bomba**
```bash
curl -X POST "http://localhost:8080/api/bombas-combustivel?nome=Bomba%208&tipoCombustivelId=1"
```

### **Registrar Abastecimento**
```bash
curl -X POST "http://localhost:8080/api/abastecimentos?bombaId=1&dataHora=2025-07-15T14:30:00&valorTotal=120.50&litragem=21.95"
```

### **Listar Abastecimentos**
```bash
curl -X GET "http://localhost:8080/api/abastecimentos"
```

---

## 🏗️ Tecnologias

- **Spring Boot 3.3.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco embarcado (desenvolvimento)
- **PostgreSQL** - Banco relacional (produção via Docker)
- **Docker Compose** - Orquestração de containers
- **pgAdmin** - Interface web para PostgreSQL
- **Swagger/OpenAPI 3** - Documentação automática
- **Maven** - Gerenciamento de dependências
- **Java 17** - Linguagem

### **Arquitetura**
```
src/main/java/com/biroigor/
├── controller/          # REST Controllers
├── service/             # Lógica de negócio
├── repository/          # Acesso a dados (JPA)
├── model/               # Entidades JPA
├── exception/           # Tratamento de erros
└── config/              # Configurações
```

---

## ⚙️ Configuração

### **Profiles Disponíveis**
- **default**: H2 Database (desenvolvimento)
- **postgres**: PostgreSQL com Docker (produção)

### **PostgreSQL via Docker**
```bash
# Subir containers
docker-compose up -d

# Parar containers  
docker-compose down
```

Veja `DOCKER.md` para instruções detalhadas do PostgreSQL.

### **Variáveis de Ambiente**
```properties
# application.properties
server.port=8080
spring.datasource.url=jdbc:h2:file:./data/postocombustivel
spring.datasource.username=adminposto
spring.datasource.password=adminposto
```

---

## 🐛 Logs e Debug

### **Níveis de Log**
- **DEBUG**: Queries SQL e detalhes de execução
- **INFO**: Inicialização e operações principais
- **ERROR**: Erros de sistema

### **Console H2 para Debug**
1. Acesse: `http://localhost:8080/h2-console`
2. Configure conforme credenciais acima
3. Execute queries SQL diretamente

---

## 📊 Recursos Avançados

### **Validações Implementadas**
- ✅ Nomes únicos para tipos de combustível
- ✅ Integridade referencial entre entidades
- ✅ Validação de dados de entrada
- ✅ Tratamento global de exceções

### **Funcionalidades Extras**
- ✅ Ordenação automática de abastecimentos por data
- ✅ Estatísticas por bomba de combustível
- ✅ Dados iniciais pré-carregados
- ✅ Documentação interativa com Swagger
- ✅ Persistência entre reinicializações

---

## 🔧 Troubleshooting

### **Problemas Comuns**

**Erro de porta em uso:**
```bash
# Verificar processo na porta 8080
netstat -ano | findstr :8080

# Matar processo se necessário
taskkill /PID <PID> /F
```

**Erro de conexão H2:**
- Verificar se o arquivo `./data/postocombustivel.mv.db` existe
- Confirmar credenciais: `adminposto/adminposto`
- Deletar arquivo do banco para reset completo

**Maven build error:**
```bash
# Limpar e recompilar
mvn clean install
```

---

## 📝 Status do Projeto

✅ **Funcionalidades Core**: Todas implementadas e testadas  
✅ **API REST**: Completa com documentação Swagger  
✅ **Persistência**: H2 com dados mantidos entre restarts  
✅ **Tratamento de Erros**: Global exception handling  
✅ **Qualidade**: Código organizado e comentado  

---

**🚀 Projeto pronto para produção!**

---

*Última atualização: 16/07/2025*
