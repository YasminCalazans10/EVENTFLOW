# Changelog

## Módulo 1

### Parcial 1
- Estrutura inicial do repositório;
- Discovery, benchmarking e proposta de valor;
- Escopo inicial do MVP;
- Regras de negócio iniciais;
- Modelagem preliminar.

## Módulo 2

### MVP Web
- Estrutura do backend em Java com Spring Boot;
- Persistência MySQL com JPA/Hibernate;
- Cadastro e autenticação de usuário;
- Senha armazenada com BCrypt;
- Perfis Participante e Organizador;
- Cadastro e consulta de eventos;
- Cadastro de lotes de ingresso;
- Catálogo e pesquisa no frontend;
- Aquisição de ingressos;
- Validação de disponibilidade;
- Atualização da quantidade disponível;
- Emissão de ingresso com código único;
- Histórico do participante;
- Carteira de ingressos;
- Dashboard básico do organizador;
- Layout responsivo;
- Configuração de execução com Docker Compose;
- Corrigida a conexão do backend containerizado com o MySQL por hostname configurável;
- Adicionado healthcheck do MySQL antes da inicialização do backend;
- Adicionado volume persistente para os dados locais do MySQL.

### Evolução de aderência aos requisitos
- Categoria e Local integrados ao cadastro de eventos;
- Tipo de ingresso separado de lote;
- CRUD de evento ampliado com edição e exclusão condicionada;
- Soma de lotes limitada pela capacidade do evento;
- Compra transacional com bloqueio pessimista do lote para reduzir risco de overselling;
- Tratamento central de erros 400/404/409;
- Dashboard do organizador ampliado para categoria, local, tipo, lote, eventos e vendas;
- Fluxos do participante atualizados para tipo/lote, carteira e histórico;
- CORS preparado para o frontend publicado no GitHub Pages;

### Redesign comercial do frontend
- Home reformulada como marketplace de ingressos, com hero, busca, categorias, eventos em destaque e chamada para organizadores.
- Cards de eventos com maior hierarquia visual e comportamento responsivo.
- Página de evento reformulada com banner, informações, organizador, seleção de tipo/lote, quantidade, resumo e confirmação.
- Carteira de ingressos redesenhada com tickets digitais.
- Login/cadastro e dashboard do organizador receberam identidade visual própria.
- Frontend refatorado em API, componentes e páginas para facilitar manutenção e apresentação acadêmica.
