
# Dietoterapia - Backend

Este é o repositório do backend do projeto Dietoterapia, desenvolvido pelos
alunos da [Agência Experimental de Engenharia de Software](http://www.ages.pucrs.br/)
da [PUCRS](http://www.pucrs.br/) durante o semestre 2018/2.

## Mais informações
Para saber mais informações sobre o projeto:
* [acesse a página da AGES sobre o projeto](http://www.ages.pucrs.br/dietoterapia/)
* [acesse a wiki do projeto](http://www.tools.ages.pucrs.br/dietoterapia/wiki/wikis/home)
* [acesse o repositório da wiki do projeto](http://www.tools.ages.pucrs.br/dietoterapia/wiki)

## Configuração
Para configurar o backend, você pode seguir o
[tutorial](http://www.tools.ages.pucrs.br/dietoterapia/wiki/wikis/Configura%C3%A7%C3%A3o#backend)
disponível na wiki, com o passo a passo da configuração (usando MySQL Workbench
como interface gráfica para acesso ao MySQL e IntelliJ IDEA Ultimate como IDE).

### Requisitos
Para executar o backend, você precisará:
* instalar o [TomCat 8](https://tomcat.apache.org/download-80.cgi)
* instalar o [MySQL 5.7](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)
* criar um arquivo com as configurações para acesso ao banco de dados:
    - nome do arquivo: `config.properties`
    - localização: `<pasta-do-backend>/resources/`
    - conteúdo:
        
        ```ini
        conexao.url=jdbc:mysql://localhost/<schema>
        conexao.user=<usuario-no-mysql>
        conexao.password=<senha-no-mysql>
        ```

## Licença
Este repositório encontra-se sob a
[licença MIT](http://www.tools.ages.pucrs.br/dietoterapia/back/blob/master/LICENSE).
