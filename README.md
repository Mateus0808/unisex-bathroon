# unisex-bathroon

## Problema
Um escritório contém um banheiro que pode ser utilizado tanto por homens quanto por mulheres, mas não por ambos ao mesmo tempo. 
Se um homem estiver no banheiro, outros homens podementrar, porém eventuais mulheres que desejem utilizar o banheiro devem esperar 
ele ficar vazio.Se uma mulher estiver no banheiro, outras mulheres podem entrar, porém eventuais homens quedesejem utilizar o banheiro 
devem esperar ele ficar vazio. Cada pessoa (homem ou mulher) podepassar um determinado tempo utilizando o banheiro, que possui uma 
capacidade limite de pessoasque podem utilizá-lo ao mesmo tempo.

## Como a solução foi projetada
A solução do problema foi estruturada usando semáforo, que controla o fluxo do banheiro. É feito de maneiraa aleatória quem vai entrar no banheiro, se for zero (0) é homem, senão é mulher. Além disso, a capacidade máxima permitida dentro do banheiro, são cinco pessoas.

Cada pessoa, homem ou mulher é uma thread, e existe três semáforos, um de entrada, de saída e um que fica responsável para receber as solicitação. Quando o banheiro está vazio e um dos dois entram, o semaforo do banheiro é marcado que está sendo usado, nisso, todas as mulheres podem utilizar, até o próximo homem querer entrar no banheiro, então as mulheres saem e entra os homens. Também funciona se o homem for o primeiro. Quando um dos dois saem, o banheiro fica vazio para o proximo sexo entrar.

Foi criado a classe “Bathroom”, que contém todos os atributos relativos ao funcionamento do banheiro (Filas de homens e mulheres, capacidade total, lotação atual do banheiro, o gênero que está no banheiro no dado momento, método para chamar a próxima pessoa para entrar e método para uma pessoa sair do banheiro).

Foram criados também as classes “Person” e “Sex”, a classe “Person”, que contém todos os atributos relacionados a uma pessoa, como nome da pessoa, sexo, o banheiro a ser utilizado, além disso possui os métodos “useBathroon” e “leaveBathroon”, que nada mais é, uma pessoa vai utilizar o banheiro e o outro e o método para uma pessoa sair, respectivamente.

## Lógica de sincronização utilizada
A sincronização entre as threads, ocorre quando o banheiro está sendo usado por algum gênero específico, permitindo que uma pessoa entre no banheiro, apenas se for do mesmo gênero,  evitando o problema de que pessoas de gêneros diferentes entre no banheiro ao mesmo tempo.

Dessa forma, a forma de sicronização é feita da seguinte forma, duas pessoas de gêneros diferentes não podem acessar o banheiro simultaneamente e, além disso, o banheiro tem uma forma de dizer que está disponível para uso, evitando condições de corrida.

## Corretude
A corretude da solução é garantida por meio das execuçoes do programa, mostrando que tudo está funcionado como de acordo. Além disso, as regras de negócio impostas, permite que o programa funcione da maneira certa. Desse modo, quando o banheiro está cheio não é permitido mais pessoas entrar, a única opção é que as pessoas que estão usando saiam do banheiro, permitindo a entrada de novas pessoas.

## Dificuldades encontradas	
As implementações foram trabalhosas. Por não ter muito conhecimento na linguagem Java, tive mais dificuldades. A implementação com a estrutura “Semaphore”, não foi intuitiva e fácil de fazer que resultou em dificuldades.

## Compilação do Programa
Faça o download do projeto no Github, no seguinte link https://github.com/Mateus0808/unisex-bathroon.

Feito isso, basta executar a classe principal UnisexBathroomMain e verá os resultados da compilação no terminal.
