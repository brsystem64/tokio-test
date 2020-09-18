# Custumer Service

## Doc
Documentação do costumer service.

### Endpoints


| Resource |method | Url | Body Request |
|----|---|-----|-----|
| Lista todos Costumers | GET | `http://localhost:8080/customers?page=` | |
| Lista Costumer especifico pelo Id | GET | `http://localhost:8080/customers/{id}` | |
| Criar um Costumer | POST | `http://localhost:8080/customers` | X |
| Atualiza um Costumer | PUT | `http://localhost:8080/customers/{id}` | X |
| Deleta um Costumer | POST | `http://localhost:8080/customers/{id}` |  |


### Descrição das Endpoints com Body Request

#### Criar um Costumer
Para criação de um costumer é necessário passar um Json com o nome e email. O campo Address é opcional, mas caso utilizado, só receberá o cep e buscará as demais informações através da Api ViaCep.
```
{
	"name":"Fake fake",
	"email":"fake@gmail.com",
	"address": [
		{
			"cep":"01001000"
		}
	]
}
```
#### Atualizar um costumer
Para atualização de um costumer é necessário passar o Id do costumer a ser utilizado via parametro URL, e as demais informações no Request Body.
Para toda atualização do costumer, os ceps são novamente validados na api via Cep, para que não ocorra inconsistencia.
```
{
	"name":"Fake fake",
	"email":"fake@gmail.com",
	"address": [
		{
			"cep":"01001000"
		}
	]
}
```
Para atualizar o costumer adicionando mais um novo endereço, basta adicionar mais um objeto de cep no array address, conforme o exemplo abaixo
```
{
	"name":"Fake fake",
	"email":"fake@gmail.com",
	"address": [
		{
			"cep":"01001000"
		},
    {
      "cep":"04634030"
    }
	]
}
```


---- 
### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/leonardohenrique/tokio-test.git`
1. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/customers`



