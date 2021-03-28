# Documentação

## Requisitos

 -   Oracle Database ➡️ [Download](https://www.oracle.com/br/database/technologies/oracle-database-software-downloads.html)
  -   NodeJS ➡️  [Download](https://nodejs.org/dist/v14.16.0/node-v14.16.0-x64.msi)
  -   VScode ➡️ [Download](https://visualstudio.microsoft.com/pt-br/downloads/)
      - Extensão "Java Extension Pack" para o VScode


## Passo-a-passo

- Inicialmente deve ser instalado o banco de dados **"Oracle Database"** na versão mais recente, após seguir  a instalação padrão do software indicando a senha **"root"** para o usuário **"system"**, devemos executar o conteúdo do arquivo **"tracefinder.sql"**.

- Uma vez configurado o BD, devemos então mover o arquivo de configuração **"application.properties"** para o local **"\API-3SEM\Codigo\est-api\src\main\resources"** do nosso projeto.

- No proximo passo abrimos o nosso projeto no **VSCode**, navegamos até o arquivo localizado em **"\API-3SEM\Codigo\rest-api\src\main\java\com\fatec\bd\tracefinder\restapi"** e o executamos.

- Para subir o nosso web-app vamos até a pasta **"\API-3SEM\Codigo\web-service\web-app"**, abrimos o prompt de comando na respectiva pasta e executamos os comandos `npm install` e em sequência `npm start`.
