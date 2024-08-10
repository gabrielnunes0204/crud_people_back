# Projeto CRUD

## Descrição
Esta aplicação é uma API RESTful desenvolvida com Java e Spring Boot para gerenciar um CRUD (Create, Read, Update, Delete) de pessoas. A aplicação permite criar, ler, atualizar e excluir registros de pessoas em uma base de dados.

## Funcionalidades
- Criar: Adicionar uma nova pessoa ao banco de dados.
- Ler: Consultar todos os registros de pessoas ou buscar uma pessoa por CPF.
- Atualizar: Modificar os dados de uma pessoa existente.
- Excluir: Remover uma pessoa do banco de dados.

## Exemplo POST
- POST /people
- Exemplo:
{
  "name": "João da Silva",
  "cpf": "12345678900",
  "dateOfBirth": "1980-01-01"
}

## Exemplo PUT
- PUT /people
{
  "name": "João da Silva",
  "cpf": "12345678900",
  "dateOfBirth": "1980-01-01"
}

## Exemplo DELETE
- DELETE /people/{id}

## Exemplo GET_ALL
- GET /people

## Exemplo GET_BY_CPF
- GET /people/{cpf}

