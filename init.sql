-- Script de inicialização do banco PostgreSQL
-- Este arquivo é executado automaticamente quando o container do PostgreSQL é criado

-- Criar extensões úteis
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Configurações de encoding
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

-- Comentário sobre o banco
COMMENT ON DATABASE posto_combustivel IS 'Banco de dados do sistema de posto de combustível';

-- As tabelas serão criadas automaticamente pelo Hibernate/JPA
-- Este arquivo pode ser usado para inserções iniciais específicas se necessário
