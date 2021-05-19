## 💻 Projeto
Projeto API do 3º Semestre da turma de Banco de Dados FATEC

<p align="center"> <img src="https://user-images.githubusercontent.com/18652465/111547833-88631a00-8758-11eb-863c-ccf1e6e93f39.png"> </p>

## 🛠 Tecnologias

As seguintes tecnologias foram utilizadas no desenvolvimento do projeto:

- [ReactJs][reactjs]
- [NodeJS][nodejs]
- [SpringBoot][springboot]
- [Flyway][flyway]

[reactjs]: https://pt-br.reactjs.org
[nodejs]: https://nodejs.org/en/
[flyway]: https://flywaydb.org
[springboot]: https://spring.io/projects/spring-boot

## Integrantes:

 <a  href="https://www.linkedin.com/in/rodrigo-am%C3%A2ncio-do-prado-ten%C3%B3rio-a56641174"> <img src="https://img.shields.io/badge/Rodrigo%20Amancio%20--%20Scrum%20Master-Linkedin-blue"></a> <br>
<a href="https://www.linkedin.com/in/luisaugustosb"> <img src="https://img.shields.io/badge/Lu%C3%ADs%20Augusto%20--%20Product%20Owner-Linkedin-blue"></a> <br>
<a href="https://www.linkedin.com/in/bahij-noureddine-941b681b7/"> <img src= "https://img.shields.io/badge/Bahij%20Noureddine-Linkedin-blue"></a><br>
<a href="https://www.linkedin.com/in/mateus-senne-172905149"> <img src= "https://img.shields.io/badge/Mateus%20Senne-Linkedin-blue"></a> <br>
<a href="https://www.linkedin.com/in/maxx-barcelos-aaa106b2"> <img src= "https://img.shields.io/badge/Maximiles%20Barcelos-Linkedin-blue"></a> <br>

 ## Cliente:
 
 <p align="center"> <img src="https://images-ext-2.discordapp.net/external/qs9oB7KZGcY3KhYPpksxNKz1cebbLrPIJUdqIyv2jIg/%3Fauto%3Dformat%26fit%3Dmax%26w%3D1200/https/img.ien.com/files/base/indm/ien/image/2019/09/Embraer_logo.5d8a763612cbb.png?width=400&height=242" width=180 heicht=180></p>
 
 A EMBRAER nasceu em 1968 após o voo bem-sucedido da aeronave Bandeirante, essa que teve aceitação tão grande que exigiu a criação de uma fábrica que organizasse sua produção em série. O sonho e dedicação de Ozires Silva e sua equipe se concretizaram em 19 de agosto de 1969 com a oficialização da EMBRAER por decreto de lei. Com sede em São José dos Campos - SP, a empresa conta com mais de 18 mil funcionários (dados de 2014) e possui diversas unidades no Brasil e no exterior, inclusive joint ventures na China e em Portugal. Para mais informações acesse o [SITE EMBRAER]( https://embraer.com/br/pt) .

 ## Problema:
 Atualmente o cliente conta com diversos manuais referentes aos seus produtos, esses manuais possuem identificações primarias e secundárias e são criados a partir da junção de blocos fragmentados em arquivos pdf. Esses manuais possuem versões definidas por traços, que são compostos por determinados blocos que fazem parte do documento. O controle é feito através de planilhas alimentadas manualmente por usuários que devem conhecer a estrutura do manual.
 
 ## Solução:
 O Trace Finder tem como objetivo ser uma aplicação que permite manter, customizar e versionar partes de documentos que são armazenados em arquivos PDF, a partir disso, utilizando regras específicas, é possível gerar documentos finais que integrem as partes selecionadas.
 
  ## Documentação:
 
 - [Story Cards](https://github.com/MaXximiles/API-3SEM/tree/main/User%20Story%20Cards)
 - [Design Thinking](https://www.figma.com/file/6oV3Omfka5XEPipU1BUibe/Design-Thiking-Grupo-2-3oSEM-BD-2021?node-id=0%3A1)
 - [BSC](https://github.com/MaXximiles/API-3SEM/tree/main/BSC)
 - [Backlog](https://trello.com/b/pVgsSxrF/pi-3-sem-trace-finder)
 <!--- [Tutorial Instalação](https://github.com/MaXximiles/API-3SEM/tree/main/Documenta%C3%A7%C3%A3o) -->
 

## Sprint 3: 
### Proposta:
<p align=center>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/User%20Story%20Cards/StoryCard6.png?raw=true" width=350 height=200>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/User%20Story%20Cards/StoryCard3.png?raw=true" width=350 height=200>

</p></br><h1></h1>


### Detalhes da Sprint:

Resumo de modificações:
- Manipulação de diretórios (A criação de diretórios, organiza os manuais em seções, subseções e blocos)
  - Criação de pastas ao inserir documento (manual)
  - Criação de pastas ao inserir blocos
  - Exclusão de diretórios e subdiretórios quando manual e/ou bloco é excluído
- Manipulação de arquivos (Manipulação de arquivos possibilita o usuário a salvar os arquivos pdf dentro dos diretórios e no banco de dados)
  - Import de arquivos (será usado para importação de blocos)
  - Exclusão de arquivos importados do banco e da pasta
  - Mudança no nome do arquivo salvando no modelo do mackup 
- LEP automática (Criação da LEP automática possibilita o usuário a ter mais segurança e confiabilidade nas informações, já que será gerada pelo próprio sistema)
  - Criação das classes, Repositorys e Controller's da LEP
  - Criação da LEP automática, gravando no arquivo pdf modelolep 
  - Criação de arquivo modelo de LEP.pdf
- Implementação de tag (A implementação de tags possibilita filtrar documentos, traços e blocos pelas tags correspondentes)
  - Criação das classes tag (Documento, bloco e traço) na API
- Versionamento do Banco de Dados
- Listagem de Revisões feita através da leitura do arquivo .pdf upado para o sistema.
- Função logar e senha criptografada
</p></br><h1></h1>

### Screenshoots:
<p align=center>
Criação de LEP</br></br>
<img src=https://user-images.githubusercontent.com/68132461/117557037-73628300-b045-11eb-8cab-767ff3c6fe7c.png></br>
</p></br><h1></h1>
 
<p align=center>
Upload de arquivos</br></br>
<img src=https://user-images.githubusercontent.com/68132461/117557066-ac9af300-b045-11eb-8180-b7af0e0ca153.png></br>
</p></br><h1></h1>

<p align=center>
Estrutura de diretórios criados pelo Trace Finder</br></br>
<img src=https://user-images.githubusercontent.com/68132461/117557097-f8e63300-b045-11eb-85b7-33199b935c21.png></br>
</p></br><h1></h1>

<p align=center>
Exemplo de LEP criada automaticamente</br></br>
<img src=https://user-images.githubusercontent.com/68132461/117557085-da803780-b045-11eb-8341-371b4c37e28b.png></br>
</p></br><h1></h1>

### Proposta para próxima Sprint:
<p align=center>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/User%20Story%20Cards/StoryCard2.png?raw=true" width=350 height=200>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/User%20Story%20Cards/StoryCard4.png?raw=true" width=350 height=200>
</p></br>
Para a próxima entrega nosso grupo pretende aperfeiçoar e finalizar as funcionalidades desejadas pelo cliente, além de, dentro do possível implementar funcionalidades extras ao projeto.
 
</p></br><h1></h1>
