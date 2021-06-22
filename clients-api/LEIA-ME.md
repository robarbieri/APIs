# Informações Rápidas
## Objetivo
O objetivo desse projeto é que vocês entendam e sejam capazes de CRIAR uma API simples.
 
## Se preocupem apenas com os arquivos:
    - controller/ClientController.java
    - model/Client.java
    
### O que é?
    - Annotation (Anotação): é uma abstração de algum comportamento ex: @FazAlgumaCoisa 
    - Controller: "guia" da nossa aplicação, dirá "quem" (classe e método) responde a qual "chamada" (endpoint)
    - Model: São as entidades da  aplicação  ex: (Cliente, Apolice, Fatura)
    - Getter: método para permitir a busca de uma propriedade, sem acesso DIRETO as propriedades do objeto.
    - Setter: método para definir o valor de uma propriedade, sem acesso DIRETO ao propriedades do objeto.
    
### Como testar?
    - Inicie a aplicação (botão Run)
    - Abra o Postman ou similares
    - Faça um GET no endereço http://localhost:8080/clients
    
o retorno deve ser:

***200 - OK***
```json
[
    {
        "name": "Renan",
        "phone": "98507-1229",
        "age": 26
    },
    {
        "name": "Joao",
        "phone": "98230-0239",
        "age": 38
    }
]
```

### Para fixação
    - Criar uma API que liste fornecedores fictícios (criados em tempo de execução) 