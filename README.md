# EventFlow

## Seu evento, do início ao ingresso.

O **EventFlow** é uma plataforma web de gestão de eventos e ingressos criada para conectar organizadores e participantes em um único ambiente.

## MVP implementado

### Participante
- Cadastro e login;
- Catálogo e pesquisa de eventos;
- Detalhes do evento;
- Visualização de lotes/tipos de ingresso;
- Seleção de quantidade;
- Aquisição com validação de disponibilidade;
- Emissão de ingresso com identificação única;
- Carteira de ingressos;
- Histórico de operações.

### Organizador
- Cadastro e login;
- Criação de eventos;
- Categoria, local, data, horário e capacidade;
- Criação de lotes de ingresso;
- Definição de preço, quantidade e disponibilidade;
- Dashboard com resumo de eventos e vendas;
- Acompanhamento das operações realizadas.

## Tecnologias

- **Frontend:** React + Vite
- **Backend:** Java 21 + Spring Boot
- **Banco de dados:** MySQL
- **Persistência:** Spring Data JPA / Hibernate
- **Segurança de senha:** BCrypt
- **Execução local:** Docker Compose

## Estrutura

```
EVENTFLOW/
├── backend/
├── frontend/
├── docs/
├── docker-compose.yml
├── README.md
├── CHANGELOG.md
└── AI_USAGE.md
```

## Executar com Docker

Na raiz do projeto:

```bash
docker compose up
```

Depois acesse:

- Frontend: http://localhost:5173
- Backend: http://localhost:8080

## Executar sem Docker

### Banco
Crie um banco MySQL chamado `eventflow`.

### Backend
```bash
cd backend
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

## Regras críticas contempladas

- Não permitir aquisição acima da disponibilidade do lote;
- Não permitir criação de lote acima da capacidade do evento;
- Atualizar a disponibilidade após a compra;
- Encerrar o lote quando a disponibilidade chegar a zero;
- Emitir um código único para cada ingresso;
- Relacionar ingresso a pedido e evento existentes;
- Armazenar senhas utilizando BCrypt.

## Projeto acadêmico

Projeto Integrador da disciplina **Programação Orientada a Objetos II**, do curso de Sistemas de Informação.
