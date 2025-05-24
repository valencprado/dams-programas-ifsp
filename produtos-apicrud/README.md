# API de Produtos - implementações

## Estrutura

A aplicação possui 5 camadas:
1. `Controller`: onde estão localizados os endpoints da aplicação e parte da documentação do Swagger;
1. `Service`: camada que chama a interação com o banco e realiza outras validações de negócio;
1. `Repository`: Interfaces de interação com o banco de dados;
1. `Model`: responsável por armazenar as entidades (modelo das tabelas do banco);
1. `Exception`: onde ficam as exceções customizadas para a API.

## Diagrama de classes (resumido)
![img_19.png](img README/img_19.png)


## Endpoints

### Produtos
- `GET /produtos/{id}` 
![img_1.png](img README/img_1.png)

- `GET /produtos`:
![img_2.png](img README/img_2.png)

- `GET /produtos/{id}/desconto`:
![img_3.png](img README/img_3.png)

- `GET /produtos/buscar`:
![img_4.png](img README/img_4.png)

- `POST /produtos`:
![img_5.png](img README/img_5.png)
- `PUT /produtos/{id}`:
![img_6.png](img README/img_6.png)
- `DELETE /produtos/{id}`:
![img_7.png](img README/img_7.png)

### Categorias
- `GET /categorias`:
![img_8.png](img README/img_8.png)
- `GET /categorias/{id}/produtos`:
![img_9.png](img README/img_9.png)
- `GET /categorias/{id}`:
![img_10.png](img README/img_10.png)
- `POST /categorias`:
![img_11.png](img README/img_11.png)
- `DELETE /categorias/{id}`:
![img_12.png](img README/img_12.png)

## Regras de negócio
- Não permitir descontos maiores que 50%.
- Nome não pode ser duplicado.
- Preço ≤ R$ 10.000,00.
- Não permitir atualização se o ID não existir.
- Se o nome conter “Promoção”, preço < R$ 500,00.
- A busca deve ser **contendo o texto** informado (case insensitive).
- Se nada for encontrado, retornar lista vazia (não lançar erro).
- Não permitir salvar produto sem categoria.
- A categoria deve ser escolhida por ID no momento do cadastro ou atualização do produto.

## Exemplos

### Exclusão de produto
![img_13.png](img README/img_13.png)

### Edição de produto
![img_14.png](img README/img_14.png)
![img_15.png](img README/img_15.png)
### Cálculo de desconto
![img_16.png](img README/img_16.png)

### Busca: não encontrado
![img_17.png](img README/img_17.png)

### Produtos da categoria
![img_18.png](img README/img_18.png)

*Feito por Valentina C. Prado - PT302539X*

*Disciplina de Desenvovlvimento de API's e Microsseriços - Profº Luiz Albano*
