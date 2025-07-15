#  Sistema de Gestão de Posto de Combustível

API REST completa para gerenciamento de posto de combustível desenvolvida com **Spring Boot 3.3.0** e **Java 17**.

---

##  Funcionalidades Implementadas

### **Requisitos Atendidos**
 **Tipos de Combustível** - CRUD completo (Criar, Listar, Alterar, Deletar)
- Nome (texto)  
- Preço por litro

 **Bombas de Combustível** - CRUD completo relacionadas aos tipos de combustível
- Nome da bomba
- Combustível que abastece

 **Abastecimentos** - CRUD completo com data, volume e valor total
- Bomba utilizada no abastecimento
- Data do abastecimento
- Quantidade em valores
- Litragem

 **Consulta de dados** - Todos os dados cadastrados via API REST
 **Persistência** - Dados mantidos entre reinicializações da aplicação

### **Diferenciais Implementados**
 **API RESTful** completa com rotas GET, POST, PUT, DELETE  
 **Organização profissional** com Repository, Service e Controller  
 **Documentação automática** com Swagger/OpenAPI  
 **Spring Boot 3.3.0** com estrutura enterprise  
 **Exception Handling** global personalizado  
 **Validações de negócio** e integridade referencial  

---

##  Como Executar

### **Pré-requisitos**
- Java 17+ instalado
- Maven 3.6+ instalado

### **Inicialização**
`ash
# Clone o repositório
git clone https://github.com/BiroIgor/anontest.git
cd anontest

# Execute a aplicação
mvn spring-boot:run
`

A aplicação iniciará em: http://localhost:8080

---

##  Acesso às Interfaces

| Interface | URL | Descrição |
|-----------|-----|-----------|
| **API REST** | http://localhost:8080/api/ | Endpoints da API |
| **Swagger UI** | http://localhost:8080/swagger-ui.html | Documentação interativa |
| **H2 Console** | http://localhost:8080/h2-console | Console do banco de dados |

### **Credenciais H2 Database**
- **JDBC URL**: jdbc:h2:file:./data/postocombustivel
- **User**: dminposto
- **Password**: dminposto

---

##  Status do Projeto

 **Funcionalidades Core**: Todas implementadas e testadas  
 **API REST**: Completa com documentação Swagger  
 **Persistência**: H2 com dados mantidos entre restarts  
 **Tratamento de Erros**: Global exception handling  
 **Qualidade**: Código organizado e comentado  

---

** Projeto pronto para produção!**
