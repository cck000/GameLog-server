#  GameLog Server (Backend)

> API RESTful construída com Spring Boot para o gerenciamento de bibliotecas pessoais de jogos, atuando como o backend do projeto **GameLog**.

O GameLog Server fornece autenticação segura via JWT e integra-se com a **RAWG Video Games Database API** para buscar informações ricas sobre os jogos de forma automatizada.

##  Funcionalidades

- **Autenticação Segura:** Registro e Login de usuários utilizando Spring Security e JWT.
- **Integração RAWG API:** Busca de jogos com preenchimento automático de metadados (capa, ano de lançamento, plataformas e gêneros).
- **Gerenciamento de Biblioteca:** Adicionar, remover e atualizar o status dos jogos na biblioteca pessoal (ex: *Quero Jogar, Jogando, Zerado, Abandonado*).
- **Prevenção de Duplicidade:** Validação para evitar que o mesmo jogo seja adicionado duas vezes pelo mesmo usuário.

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3** (Web, Data JPA, Security)
- **PostgreSQL** (Banco de dados relacional)
- **JSON Web Token (JWT)** (Autenticação)
- **Lombok** (Redução de boilerplate)
- **Maven** (Gerenciamento de dependências)

## ⚙️ Variáveis de Ambiente

Para rodar o projeto localmente ou em produção, você precisará configurar as seguintes variáveis de ambiente no seu sistema ou no arquivo `application.properties`:

| Variável | Descrição | Exemplo |
| :--- | :--- | :--- |
| `DB_URL` | URL de conexão JDBC do PostgreSQL | `jdbc:postgresql://localhost:5432/gamelog` |
| `DB_USER` | Usuário do banco de dados | `postgres` |
| `DB_PASS` | Senha do banco de dados | `sua_senha` |
| `JWT_SECRET` | Chave secreta para geração dos tokens JWT | `sua_chave_secreta_super_segura` |
| `RAWG_KEY` | Sua chave de API da RAWG.io | `1a2b3c4d5e...` |

