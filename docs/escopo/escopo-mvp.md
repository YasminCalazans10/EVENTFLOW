# Escopo Inicial do MVP

O MVP do EventFlow terá como objetivo permitir o fluxo básico entre organizadores, eventos, participantes e ingressos.

## Funcionalidades do participante

- Cadastro e autenticação;
- Visualização e pesquisa de eventos;
- Consulta dos detalhes do evento;
- Visualização de data, horário e local;
- Consulta de tipos/lotes e disponibilidade;
- Seleção de quantidade;
- Reserva e/ou aquisição de ingresso;
- Visualização dos ingressos emitidos;
- Consulta do histórico.

## Funcionalidades do organizador

- Cadastro e autenticação;
- Cadastro e edição de eventos;
- Definição de categoria, local, data e horário;
- Definição de capacidade;
- Criação de tipos e lotes de ingresso;
- Definição de preço, quantidade e disponibilidade;
- Acompanhamento de reservas/vendas;
- Dashboard básico do evento.

## Fluxo prioritário do participante

Cadastro/Login → Evento → Detalhes → Tipo/Lote → Quantidade → Verificação de disponibilidade → Confirmação → Registro da operação → Emissão do ingresso → Histórico.

## Fluxo prioritário do organizador

Login → Cadastro do evento → Categoria/Local/Data → Configuração de ingressos → Publicação → Acompanhamento de disponibilidade e operações.

## Regras de negócio iniciais

- Não permitir aquisição superior à quantidade disponível;
- Respeitar a capacidade definida para o evento e/ou lote;
- Cada ingresso emitido deverá possuir identificação única;
- Todo ingresso deverá estar relacionado a uma operação válida e a um evento existente;
- A quantidade disponível deverá ser atualizada após uma operação confirmada;
- Lotes esgotados não deverão permitir novas aquisições;
- O sistema deverá controlar os estados de eventos, lotes, operações e ingressos.

## Prioridade

As funcionalidades relacionadas à criação do evento, configuração de ingressos, aquisição pelo participante, emissão do ingresso, histórico e dashboard básico serão priorizadas. Funcionalidades adicionais poderão ser desenvolvidas posteriormente conforme a evolução do projeto.