# 🔹 Desafio Técnico Júnior #1 – Cadastro e Consulta de Abastecimentos

## 🛠 Objetivo

Desenvolver uma aplicação simples em **Java** para cadastro e consulta de abastecimentos em um posto de combustível, com armazenamento em banco de dados e exibição dos dados via **Java Swing** ou **API REST**.

---

## 📌 Funcionalidades Implementadas

✅ Operaçoes basicas (Criar, Listar, Alterar, Deletar) de **Tipos de Combustível** 
- Nome - Texto
- Preço por litro

✅ Operaçoes basicas (Criar, Listar, Alterar, Deletar) de **Bombas de Combustível** (relacionadas a um tipo de combustível)
- Nome da bomba
- Combustivel que abastece

✅ Operaçoes basicas (Criar, Listar, Alterar, Deletar)  de **Abastecimentos** (com data, volume abastecido e valor total)
- Bomba que foi realizado o abastecimento
- Data do abastecimento
- Quantidade em valores
- Litragem
  
✅ **Consulta** de todos os dados cadastrados (via Java Swing ou API)  
✅ Persistência dos dados (ao menos em tempo de execução)  

---

## ✅ Requisitos Atendidos

- Projeto Java com estrutura organizada (usando Maven ou Gradle)
- Relacionamentos entre entidades corretamente implementados
- Interface gráfica Java Swing **ou** API HTTP para cadastro e consulta
- Código comentado e organizado

---

## 🌟 Diferenciais Implementados

- API RESTful simples com rotas `GET`, `POST`, `PUT`
- Boas práticas de organização de código (DAO, camada de serviço, etc.)
- Persistencia dos dados (em caso de restart da aplicação manter os dados)
- 
---

## 📬 Como entregar o desafio

1. **Faça um fork** deste repositório.
2. Implemente a solução no seu fork.
3. Faça commits organizados com mensagens claras.
4. Após finalizar:
   - Envie o link do **repositório forkado** com a sua solução.
   - Certifique-se de que o projeto roda sem erros e que o README está atualizado.

---
## 🔍 O que será avaliado

- Sua **comunicação**, especialmente ao surgir dúvidas ou obstáculos durante o desenvolvimento.
- **O processo de desenvolvimento** como um todo, e não apenas o resultado final.
- A clareza e organização dos **commits** realizados.
- Sua capacidade de **estruturar a solução em etapas**, mesmo que nem todos os requisitos sejam concluídos.

---

## 💡 Dicas para se sair bem

- Divida o desafio em **pequenas partes** e implemente **com calma**, focando em cada funcionalidade por vez.
- Use **commits claros e objetivos**, indicando exatamente o que foi alterado ou implementado.
- Em caso de dúvida, **comunique-se** — mostrar que você sabe buscar soluções é um ponto positivo.
- Mesmo que não finalize 100% dos requisitos, **a qualidade do seu processo será levada em conta**.

---

## 🐘 PostgreSQL Setup

### Opção 1: Usando Docker (Recomendado)

1. **Instalar Docker** (se não tiver):
   - Windows: [Docker Desktop](https://www.docker.com/products/docker-desktop)

2. **Subir o banco PostgreSQL**:
   ```bash
   docker-compose up -d
   ```

3. **Verificar se está rodando**:
   ```bash
   docker-compose ps
   ```

4. **Acessar PgAdmin** (opcional):
   - URL: http://localhost:8081
   - Email: admin@posto.com
   - Senha: admin123

### Opção 2: PostgreSQL Local

1. **Instalar PostgreSQL**:
   - Windows: [PostgreSQL Installer](https://www.postgresql.org/download/windows/)

2. **Criar banco de dados**:
   ```sql
   CREATE DATABASE posto_combustivel;
   ```

3. **Configurar usuário** (se necessário):
   ```sql
   CREATE USER postgres WITH PASSWORD 'postgres';
   GRANT ALL PRIVILEGES ON DATABASE posto_combustivel TO postgres;
   ```

## 🚀 Executando a Aplicação

```bash
# Com PostgreSQL rodando
mvn spring-boot:run

# Para testes (usa H2 em memória)
mvn test
```

## 📊 Acesso às Interfaces

- **API**: http://localhost:8080/api/
- **Swagger**: http://localhost:8080/swagger-ui.html
- **PgAdmin**: http://localhost:8081 (se usando Docker)

## 🔧 Configurações de Banco

### Desenvolvimento (PostgreSQL)
- **Host**: localhost:5432
- **Database**: posto_combustivel
- **Usuário**: postgres
- **Senha**: postgres

### Testes (H2 em memória)
- **URL**: jdbc:h2:mem:testdb
- **Usuário**: sa
- **Senha**: (vazio)
