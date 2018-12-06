# Diniz

Neste projeto de demonstração foram utilizadas as seguintes tecnologias:

Front-end:

AngularJS
Bootstrap
Restangular

Back-end:

Java 8
Wildfly Swarm
JUnit
Maven
REST
______________________________________________________

Os exercícios podem ser simulados ao rodar a aplicação, já que os testes estão habilitados para executarem sempre que a aplicação subir.

Foi desenvolvida uma single page application para exibir os resultados dos dois exercícios (http://localhost:8080).

Para exibir os dados do exercício 2, foi criado um resource que retorna dados de um mock, mas que utiliza a classe real que cria as observações. Neste exercício, houve a refatoração da classe GeradorObservacao e foi criada uma classe que simula os dados necessários
das notas chamada NotaFiscal. Com essa refatoração, foram criados dois métodos publicos, utilizados para criar a String com a descrição
com e sem os valores, conforme solicitado no requisito. Foram utilizadas constantes para agrupar e organizar as Strings utilizadas.
Também foram removidos os comentários espalhados pela classe, para isso foram ajustados os nomes dos métodos, para informar melhor o
seu objetivo e incluído Javadoc apenas nos métodos publicos com uma breve definição. Para o resource FaturaResource não foram implementados testes, pois serve apenas para exibição dos mocks. Os testes da geração das observações no exercício 2 estão em GeradorObservacaoTest.

:)