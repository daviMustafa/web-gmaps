GMaps Web Service Project
======================

Uma aplicação feita para estudo visando aprender como funciona a API do GoogleMaps.

Configurando o projeto
=====================

O projeto por padrão é configurado para o banco de dados PostgreSQL, mas por ser uma aplicação que faz o uso de Hibernate, basta mudar as configurações no arquivo de contexto do Spring para trabalhar com qualquer banco.

Passos:

1. Importe o projeto no  [Eclipse Java EE IDE for Web Developers (Testado a partir do Helios)](http://www.eclipse.org/downloads/) ou superior. OBS: Talvez seja necessário executar "mvn eclipse:eclipse";
2.  OBS: Projeto configurado no Java 8
3.  Baixe as dependências do projeto Maven (Botão direito no Projeto -> Maven -> Update project...);
4.  Crie o banco `trixmaps_v2` com a ferramenta escolhida (no caso a usada para a aplicação em que foi utilizado o `PostgreSQL`, foi o PGAdmin);
5.  Configurações de informações do banco (user = postgres, pass = postgres, porta = 5432)  
6.  A aplicação não possui autenticação, logo não precisa adicionar nada no banco antes de iniciar a aplicação.
7.  Deploy da aplicação no `Apache Tomcat 7.x` e inicie o servidor.
8.  Acesse a aplicação através da url [http://localhost:8080/trixmaps_v2] (http://localhost:8080/trixmaps_v2) ;

Informação adicional
=====================

1. O schema do banco de dados da aplicação, `trixmaps_v2`, será criado pelo Hibernate ao iniciar a aplicação a primeira vez.

Tecnologias Usadas
==============

1. Hibernate 4.x
2. Spring 4.x
3. Bootstrap (para testes de responsividade do site)
4. AJAX
5. JSON
6. REST Services

