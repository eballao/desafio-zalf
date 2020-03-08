# Desafio Zalf
Desafio de Filtragem e apresentação de dados de Marcações - API desenvolvida com Spring Boot 4

### Direcionamentos para execução do projeto no STS:

1 - Clonar o projeto para sua Workspace;

2 - Utilizar o Maven para importar um projeto existente apontando para a pasta do projeto em sua Workspace;

3 - Criar uma base de dados com o nome zalfapirest e popular a mesma com os dados do teste;

4 - Executar o projeto importado no Spring Boot através da classe PrologApiApplication;

5 - Executar uma chamada através da URL "localhost:8080/relatorios/marcacoes?cpf=00187832013&dataInicio=2019-03-01&dataTermino=2019-03-10", por exemplo para obtenção dos dados conforme solicitado no teste;

### URLs Disponíveis

1 - URL para obtenção do relatório: localhost:8080/relatorios/marcacoes?cpf=00187832013&dataInicio=2019-03-01&dataTermino=2019-03-10

2 - URL para obtenção dos Colaboradores: localhost:8080/colaboradores/teste

3 - URL para obtenção das Marcações: localhost:8080/marcacoes/teste

4 - URL para obtenção dos Vínculos de Marcações: localhost:8080/marcacoes-vinculos/teste

5 - URL para obtenção dos Tipos de Marcações: localhost:8080/tipos-marcacao/teste

Obs: Das URLs 2 em diante o objetivo é apenas verificar o retorno dos dados.

### Tecnologias Utilzadas

Spring Boot 4;
Java;
JPA;
Hibernate;
Postgres;
Lombok;
DevTools;
Maveen;

Obs: Todos os dados necessários para conexão com o Banco de Dados encontram-se no arquivo application.properties.
