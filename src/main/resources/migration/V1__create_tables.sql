-- USERS
CREATE TABLE users (
                       id              BIGSERIAL PRIMARY KEY,
                       nome            VARCHAR(100) NOT NULL,
                       senha           VARCHAR(255) NOT NULL,
                       role            VARCHAR(20) NOT NULL,
                       ativo           BOOLEAN DEFAULT TRUE,
                       created_at      TIMESTAMP DEFAULT NOW(),
                       updated_at      TIMESTAMP DEFAULT NOW(),
                       CONSTRAINT ck_role CHECK (role IN ('CLIENTE', 'MOTORISTA', 'ADMIN'))
);

-- VEICULOS
CREATE TABLE veiculos (
                          id              BIGSERIAL PRIMARY KEY,
                          motorista_id    BIGINT REFERENCES users(id),
                          placa           VARCHAR(20) UNIQUE NOT NULL,
                          modelo          VARCHAR(80) NOT NULL,
                          capacidade_kg   NUMERIC(10,2),
                          ano             INT,
                          ativo           BOOLEAN DEFAULT TRUE
);
-- ROTAS
CREATE TABLE rotas (
                       id                  BIGSERIAL PRIMARY KEY,
                       origem              VARCHAR(200) NOT NULL,
                       destino             VARCHAR(200) NOT NULL,
                       distancia_km        NUMERIC(10,2),
                       tempo_estimado      INTEGER,
                       created_at          TIMESTAMP DEFAULT NOW()
);

-- ENTREGAS
CREATE TABLE entregas (
                          id              BIGSERIAL PRIMARY KEY,
                          motorista_id    BIGINT NOT NULL REFERENCES users(id),
                          veiculo_id      BIGINT REFERENCES veiculos(id),
                          rota_id         BIGINT REFERENCES rotas(id),
                          status          VARCHAR(30) NOT NULl,
                          data_previsao   DATE,
                          data_entrega    DATE,
                          created_at      TIMESTAMP DEFAULT NOW(),
                          CONSTRAINT ck_status CHECK (status IN ('AGUARDANDO', 'EM_TRANSITO', 'ENTREGUE', 'CANCELADA'))
);

-- PACOTES
CREATE TABLE pacotes (
                         id                  BIGSERIAL PRIMARY KEY,
                         cliente_id          BIGINT NOT NULL REFERENCES users(id),
                         descricao           TEXT,
                         peso_kg             NUMERIC(10,2) NOT NULL,
                         valor_declarado     NUMERIC(12,2),
                         created_at          TIMESTAMP DEFAULT NOW(),
                         entrega_id          BIGINT REFERENCES entregas(id)   -- ADICIONADO
);




