# Banco de Dados PostgreSQL com Docker

Este projeto suporta PostgreSQL através do Docker para ambiente de produção.

## 🐳 Configuração Docker

### **Iniciar PostgreSQL**
```bash
# Subir o container PostgreSQL + pgAdmin
docker-compose up -d

# Verificar se os containers estão rodando
docker-compose ps
```

### **Parar PostgreSQL**
```bash
# Parar containers
docker-compose down

# Parar e remover volumes (CUIDADO: remove todos os dados)
docker-compose down -v
```

## 🔌 Acesso aos Serviços

| Serviço | URL | Credenciais |
|---------|-----|-------------|
| **PostgreSQL** | `localhost:5432` | posto_admin / posto123 |
| **pgAdmin** | `http://localhost:8081` | admin@posto.com / admin123 |
| **API Spring Boot** | `http://localhost:8080` | - |

## ⚙️ Configuração da Aplicação

### **Executar com PostgreSQL**
```bash
# Primeiro subir o Docker
docker-compose up -d

# Executar aplicação com profile PostgreSQL
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

### **Executar com H2 (padrão)**
```bash
# H2 não precisa de Docker
mvn spring-boot:run
```

## 🔧 Configurações do PostgreSQL

### **Variáveis de Ambiente**
```properties
# application-postgres.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/posto_combustivel
spring.datasource.username=posto_admin
spring.datasource.password=posto123
```

### **Docker Compose**
- **PostgreSQL 15 Alpine**: Banco de dados principal
- **pgAdmin 4**: Interface web para administração
- **Volume persistente**: Dados mantidos entre restarts
- **Health check**: Monitoramento automático

## 📊 Dados Iniciais

Os dados iniciais são inseridos automaticamente via `DataInitializer` em ambos os bancos:
- 5 tipos de combustível
- 7 bombas de combustível
- Estrutura completa criada automaticamente

## 🐛 Troubleshooting

### **Container não inicia**
```bash
# Verificar logs
docker-compose logs postgres

# Verificar se a porta 5432 está livre
netstat -ano | findstr :5432
```

### **Erro de conexão**
```bash
# Testar conexão direta
docker exec -it posto-combustivel-db psql -U posto_admin -d posto_combustivel
```

### **Reset completo**
```bash
# Remover tudo e recomeçar
docker-compose down -v
docker-compose up -d
```

## 🚀 Profiles Disponíveis

- **default**: H2 Database (desenvolvimento)
- **postgres**: PostgreSQL com Docker (produção)
