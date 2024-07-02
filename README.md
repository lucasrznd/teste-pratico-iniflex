<h2 align="center">Teste Prático - Iniflex</h2>

<div align="center">
 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
</div>

<h2 id="description">📙 Descrição</h2>

Desenvolver um projeto Java, com os seguintes requisitos:

1. **Classe Pessoa**
    - Atributos:
        - `nome` (String)
        - `dataNascimento` (LocalDate)

2. **Classe Funcionário**
    - Herda da classe `Pessoa`
    - Atributos adicionais:
        - `salario` (BigDecimal)
        - `funcao` (String)

3. **Classe Principal**
    - Deve conter as seguintes ações:

        **3.1 Inserir Funcionários**
         - Insere todos os funcionários na mesma ordem e com as mesmas informações da tabela fornecida.
     
         **3.2 Remover Funcionário**
         - Remove o funcionário “João” da lista.
     
        **3.3 Imprimir Funcionários**
        Imprime todos os funcionários com todas as suas informações, formatadas da seguinte maneira:
        - Data no formato `dd/MM/yyyy`
        - Valores numéricos formatados com separador de milhar como ponto e decimal como vírgula.
     
        **3.4 Aumentar Salário**
        - Aplica um aumento de 10% no salário de todos os funcionários e atualiza a lista.
     
        **3.5 Agrupar Funcionários por Função**
        - Agrupa os funcionários por função em um `Map`, onde a chave é a `função` e o valor é a lista de funcionários.
     
        **3.6 Imprimir Funcionários Agrupados por Função**
        - Imprime os funcionários agrupados por função.
     
        **3.8 Imprimir Funcionários Aniversariantes**
        - Imprime os funcionários que fazem aniversário nos meses 10 e 12.
     
        **3.9 Imprimir Funcionário com Maior Idade**
        - Imprime o funcionário com a maior idade, exibindo os atributos `nome` e `idade`.
     
        **3.10 Imprimir Funcionários em Ordem Alfabética**
        - Imprime a lista de funcionários em ordem alfabética.
     
        **3.11 Imprimir Total dos Salários**
        - Imprime o total dos salários dos funcionários.
     
        **3.12 Calcular Salários Mínimos**
        - Imprime quantos salários mínimos cada funcionário recebe, considerando que o salário mínimo é R$ 1212,00.
