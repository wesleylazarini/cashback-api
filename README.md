# API Rest Cashback Api
## _Desafio GB - Cashback Api_

### Estrutura

| Descrição | Endpoints | Metodo | Dado/Parametro |
| ------ | ------ | ------- | ------- |
| Cadastro Revendedor | http://localhost:8080/api/revendedor/ | POST | Revendedor{nome, cpf, email, senha} |
| Login Revendedor | http://localhost:8080/auth/login | POST | Login{email, senha} |
| Cadastro Compra | http://localhost:8080/api/compra | POST | Compra{codigo, valor, dataCompra, cpf} |
| Lista Compra | http://localhost:8080/api/compra?pageNumber=0&pageSize=0 | GET | ----------
| Conmsulta Valor Cashback | http://localhost:8080/api/compra/cashback{cpf} | GET | Long

## License
MIT
