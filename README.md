# CheetahExpress

> [!NOTE]
> este README ainda está em andamento, conforme o projeto vai se desenvolvendo vamos adicionar outras informações

## Propósito
O CheetahExpress é um sistema backend de gerenciamento logístico, voltado para controle de entregas, rastreamento de pedidos, gerenciamento de rotas, veículos e motoristas, além do registro detalhado dos pacotes.

### Objetivo principal
Centralizar o fluxo logístico, permitindo que clientes, motoristas e administradores registrem e acompanhem entregas, veículos, rotas e pacotes, garantindo eficiência e rastreabilidade.

## Tecnologias utilizadas
- Backend: Spring Boot 3, Spring Data JPA, Spring Security
- Banco de dados: PostgreSQL
- Versionamento de banco: Flyway

## Responsabilidades
| Integrante           | Responsabilidades principais                                                                                                                                                                                                   |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Gabrieli Pereira José** | • Modelagem do sistema, diagramas UML e arquitetura em camadas<br>• Desenvolvimento dos módulos de Usuário (clientes, motoristas, admins) e Autenticação JWT<br>• Validações, tratamento de exceções e integração com veículos |
| **Diogo Rodrigues**     | • Implementação dos módulos de Entrega, Rota e Package<br>• Configuração do banco PostgreSQL e Flyway<br>• Validações, tratamento de exceções e integração com veículos                             |

## Requisitos do sistema
### Requisitos funcionais
| Código   | Descrição                                                                        | Prioridade |
| -------- | -------------------------------------------------------------------------------- | ---------- |
| **RF01** | Cadastro, autenticação e autorização de usuários via JWT.                        | Alta       |
| **RF02** | Cadastro e gerenciamento de veículos associados aos motoristas.                  | Alta       |
| **RF03** | Clientes (role `CUSTOMER`) podem registrar pedidos de entrega.                   | Alta       |
| **RF04** | Atribuição de motoristas e veículos a uma entrega.                               | Alta       |
| **RF05** | Registro de pacotes dentro de uma entrega (Package).                             | Alta       |
| **RF06** | Acompanhamento do status das entregas (`PENDENTE`, `EM_TRANSPORTE`, `ENTREGUE`). | Alta       |
| **RF07** | Cadastro e consulta de rotas, vinculadas às entregas.                            | Média      |
| **RF08** | Atualização de status e histórico de entrega por motoristas.                     | Média      |
| **RF9** | Logs de operações críticas (login, criação/atualização de entregas).             | Baixa      |
| **RF10** | Documentação da API via Swagger/OpenAPI.                                         | Média      |

### Requisitos não-funcionais
| Código    | Descrição                                                                                           | Categoria          |
| --------- | --------------------------------------------------------------------------------------------------- | ------------------ |
| **RNF01** | Arquitetura em camadas: Controller, Service, Repository, Entity, DTO, Validation, ExceptionHandler. | Arquitetura        |
| **RNF02** | Desenvolvimento com Spring Boot 3+, Spring Data JPA, Spring Security e JWT.                         | Tecnologia         |
| **RNF03** | Banco de dados PostgreSQL com versionamento via Flyway.                                             | Banco de Dados     |
| **RNF04** | Tratamento centralizado de exceções (`@ControllerAdvice`).                                          | Manutenibilidade   |
| **RNF05** | Validações usando anotações (`@NotBlank`, `@Email`, `@Size`, etc.).                                 | Qualidade          |
| **RNF06** | API seguindo boas práticas REST e códigos HTTP adequados.                                           | Interoperabilidade |
| **RNF07** | Integração futura com aplicações externas via JSON.                                                 | Extensibilidade    |
| **RNF08** | Controle de versão via GitHub, com commits organizados.                                             | Controle de Versão |
| **RNF09** | Logs de autenticação, falhas e operações críticas.                                                  | Segurança          |

## Diagrama de classes
### Entidades
- Usuario – Usuário do sistema (cliente, motorista ou administrador)
- Veiculo – Veículos associados a motoristas.
- Rota – Rotas de entrega, contendo origem, destino e distância.
- Entrega – Registro de pedidos de entrega, associando cliente, motorista, veículo e rota.
- Pacote – Pacotes contidos em uma entrega (peso, dimensões, descrição).
### Relacionamentos

<img width="1002" height="588" alt="Trabaio3 drawio" src="https://github.com/user-attachments/assets/92b85771-85ec-4003-8d18-d203a55aeda6" />


### Rodando com Docker Compose

```bash
docker compose up -d
```

### Rodando a aplicação

```bash
./mvnw spring-boot:run
```

### Endpoints padrão

* API: [http://localhost:8080](http://localhost:8080)

---

## 🗂️ Estrutura de pastas

```md
CheetahExpress/
├── src/
│   ├── main/
│   │   ├── java/com/cheetahexpress/
│   │   │   ├── auth/
│   │   │   ├── user/
│   │   │   ├── vehicle/
│   │   │   ├── route/
│   │   │   ├── delivery/
│   │   │   ├── pacote/
│   │   │   └── config/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/migration/
├── pom.xml
└── README.md
```

---

## 📦 Exemplos de Requests e Responses

### 1. Login (JWT)

**POST /auth/login**

```json
{
    "nome": "Administrador",
    "senha": "123456"
}
```

**Response:**

```json
{
  "token": "jwt.token.aqui",
}
```

### 2. Criar Usuário

```json
{
  "nome": "matheus",
  "senha": "matheus"
}
```

### 3. Criar Veículo

```json
{
  "motoristaId": 8,
  "modelo": "Sprinter",
  "placa": "ABC1D18",
  "capacidadeKg": 800
}
```

### 4. Criar Rota

```json
{
  "origem": "Centro",
  "destino": "Zona Norte",
  "distanciaKm": 18.4,
  "tempoEstimadoMin": 35
}
```

### 5. Criar Entrega

```json
{
  "pacotesIds": [4, 5],
  "motoristaId": 7,
  "veiculoId": 1,
  "rotaId": 1,
  "status": "AGUARDANDO",
  "dataPrevisao": "2025-12-10"
}
```

### 6. Criar Pacote

```json
{
  "clienteId": 3,
  "descricao": "PS5",
  "pesoKg": 4,
  "valorDeclarado": 150.0
}
```

---

## 🔐 Credenciais de Desenvolvimento

**Admin**

* Nome: `Administrador`
* Senha: `admin123`

**Cliente (CUSTOMER)**

* Nome: `Cliente Padrão`
* Senha: `cliente123`

**Motorista (DRIVER)**

* Nome: `Motorista Padrão`
* Senha: `driver123`
