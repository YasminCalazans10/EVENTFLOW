# Classes Preliminares

A modelagem inicial do EventFlow transforma o problema estudado em objetos com responsabilidades distintas. Esta estrutura é preliminar e poderá evoluir durante a modelagem completa do Módulo 1.

## Usuario

Representa um participante ou organizador cadastrado.

**Atributos iniciais:**
- id
- nome
- email
- senha
- tipoUsuario

## Evento

Representa um evento criado pelo organizador.

**Atributos iniciais:**
- id
- nome
- descricao
- dataHora
- capacidade
- status

## Categoria

Classifica o evento.

**Atributos iniciais:**
- id
- nome

## Local

Representa onde o evento será realizado.

**Atributos iniciais:**
- id
- nome
- endereco
- capacidade

## LoteIngresso

Representa um lote/tipo de ingresso e controla preço e disponibilidade.

**Atributos iniciais:**
- id
- nome
- preco
- quantidadeTotal
- quantidadeDisponivel

## Pedido

Registra uma reserva ou aquisição realizada pelo participante.

**Atributos iniciais:**
- id
- data
- quantidade
- valorTotal
- status

## Ingresso

Representa um ingresso emitido após uma operação válida.

**Atributos iniciais:**
- id
- codigoUnico
- status

# Relacionamentos preliminares

- Um usuário do tipo organizador poderá organizar um ou mais eventos;
- Cada evento estará associado a uma categoria e a um local;
- Um evento poderá possuir um ou mais lotes/tipos de ingresso;
- Um participante poderá realizar pedidos;
- Um pedido estará relacionado ao participante e ao lote/evento selecionado;
- Uma operação válida poderá gerar um ou mais ingressos.

## Representação simplificada

```text
USUÁRIO (Organizador)
        |
        | organiza
        v
      EVENTO -------- CATEGORIA
        |
        +------------- LOCAL
        |
        v
   LOTE DE INGRESSO
        |
        v
      PEDIDO <------- USUÁRIO (Participante)
        |
        v
     INGRESSO
```

## Exemplo

Um organizador cadastra o **Workshop Java para Iniciantes**. O evento pertence à categoria **Tecnologia**, será realizado em um local previamente cadastrado e possui um lote de 50 ingressos. Um participante seleciona dois ingressos e realiza um pedido. Após uma operação válida, o EventFlow registra a operação e gera os ingressos com identificadores únicos.