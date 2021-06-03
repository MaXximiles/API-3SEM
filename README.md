## 💻 Sprint 4

<p align="center"> <img src="https://user-images.githubusercontent.com/18652465/111547833-88631a00-8758-11eb-863c-ccf1e6e93f39.png" height=200 width=200> </p>

### Sprint anterior:
Na Sprint anterior tínhamos como proposta a implementação da LEP automática que facilitaria o controle do usuário na organização dos documentos. Além disso implementamos o controle de tags para traços, blocos e documentos, a fim de identificá-los.
A geração automática da LEP foi entregue incompleta, pois ainda é necessária a inserção das modificações e separações dos blocos. Ademais o front não foi implementado para a criação do controle de tags. A estrutura de diretórios foi criada seguindo o modelo de mockup do cliente.
</br><p align=center>
</br><img src="https://github.com/MaXximiles/API-3SEM/blob/main/Documenta%C3%A7%C3%A3o/User%20Story%20Cards/StoryCard6.png?raw=true" width=350 height=200>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/Documenta%C3%A7%C3%A3o/User%20Story%20Cards/StoryCard3.png?raw=true" width=350 height=200>
### Proposta:
Nesta entrega, possuímos o objetivo de criar o controle de usuários, o login de acesso ao sistema, a geração do compilado de documentos FULL e DELTA e  de finalizar as pendências da sprint passada.
</br><p align=center> 
</br><img src="https://github.com/MaXximiles/API-3SEM/blob/main/Documenta%C3%A7%C3%A3o/User%20Story%20Cards/StoryCard2.png?raw=true" width=350 height=200>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/Documenta%C3%A7%C3%A3o/User%20Story%20Cards/StoryCard4.png?raw=true" width=350 height=200>

</p></br><h1></h1>


### Detalhes da Sprint:

Resumo de modificações:
- Módulo de controle de tags:
  - Pesquisa de tags de traços, manuais e blocos;
  - Cadastro tags de traços, manuais e blocos;
  - Alteração tags de traços, manuais e blocos;
  - Exclusão tags de traços, manuais e blocos;
- Modulo de controle de usuários;
  - Pesquisa de usuários;
  - Alteração de usuários;
  - Cadastro de novos usuários;
  - Token de validação de login;
  - Controle de nível de usuários;
- Modulo de traços:
  - Pesquisa de traços;
  - Cadastro de traços;
  - Alteração de traços;
  - Exclusão de traços;
- Filtro de documentos "FULL";
- Geração de FULL do documento;
- Criação do modelo conceitual do banco de dados;
- Criação do modelo relacional do banco de dados;
</p></br><h1></h1>

### Screenshoots:

<p align=center>
<b>Tela de login</br></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120642221-00350c80-c44b-11eb-868a-0fb0c4d16419.png></br>
</p></br><h1></h1>

<p align=center>
<b>Tela inicial</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643272-535b8f00-c44c-11eb-9f02-31ea6213d256.png></br>
</p></br><h1></h1>

<p align=center>
<b>Pesquisa de usuários</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643346-6c644000-c44c-11eb-841f-6eaee3bf112b.png></br>
</p></br><h1></h1>

<p align=center>
<b>Alterar e deletar usuários</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643395-800fa680-c44c-11eb-9aa8-764c69dccf62.png></br>
</p></br><h1></h1>

<p align=center>
<b>Pesquisa de traços</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643485-9a498480-c44c-11eb-89c7-f8a3690f7e0a.png></br>
</p></br><h1></h1>

<p align=center>
<b>Cadastro de traços</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643534-ab929100-c44c-11eb-9781-4e9ef0f3fc28.png></br>
</p></br><h1></h1>

<p align=center>
<b>Alterar e deletar traços</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643577-b9481680-c44c-11eb-987c-95b8d4ad41ce.png></br>
</p></br><h1></h1>

<p align=center>
<b>Pesquisa de tags</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643633-ca912300-c44c-11eb-9add-f8efa101e2bc.png></br>
</p></br><h1></h1>

<p align=center>
<b>Cadastro de tags</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643681-d7ae1200-c44c-11eb-8d13-14f11a97652f.png></br>
</p></br><h1></h1>

<p align=center>
<b>Alterar e deletar tags</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643786-f90efe00-c44c-11eb-9709-76e6148ffb02.png></br>
</p></br><h1></h1>

<p align=center>
<b>Adicionar tags aos traços</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120643848-0a580a80-c44d-11eb-8191-b20577efabd9.png></br>
</p></br><h1></h1>

<p align=center>
<b>Adicionar traços ao manual</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644071-4e4b0f80-c44d-11eb-9252-563538fb4350.png></br>
</p></br><h1></h1>

<p align=center>
<b>Adicionar tags ao manual</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644147-63c03980-c44d-11eb-9fbd-fb6b3a9d520a.png></br>
</p></br><h1></h1>

<p align=center>
<b>Tag e traço adicionados ao manual</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644197-720e5580-c44d-11eb-958e-6023cfa05679.png></br>
</p></br><h1></h1>

<p align=center>
<b>Lista de blocos com traços</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644260-818d9e80-c44d-11eb-9a97-83331bbaba0e.png></br>
</p></br><h1></h1>

<p align=center>
<b>Botão gerar FULL habilitado, FULL gerada com sucesso</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644300-8fdbba80-c44d-11eb-8671-e3ab97de208f.png></br>
</p></br><h1></h1>

<p align=center>
<b>LEP gerada com sucesso</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644368-9ff39a00-c44d-11eb-89e9-594cd1082111.png></br>
</p></br><h1></h1>

<p align=center>
<b>Upload de arquivo feito com sucesso</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120644405-ab46c580-c44d-11eb-949c-a66f3a39348e.png></br>
</p></br><h1></h1>

<p align=center>
Atualização dos modelos Relacional e Lógico do banco de dados:</p>
<p align=center><b>Modelo Relacional</b></br></br>
<img src="https://github.com/MaXximiles/API-3SEM/blob/main/Documenta%C3%A7%C3%A3o/Modelo%20Relacional.jpeg?raw=true">

<p align=center>
</br><b>Modelo Conceitual</b></br>
</br><img src="https://github.com/MaXximiles/API-3SEM/blob/main/Documenta%C3%A7%C3%A3o/Modelo%20Logico.jpeg?raw=true">
</p></br><h1></h1>

<p align=center>
Burndown:</p>
<p align=center>
</br><b>Gráfico</b></br></br>
<img src=https://user-images.githubusercontent.com/68132461/120645381-c403ab00-c44e-11eb-8656-f1252c09078e.png></br>
</p></br><h1></h1>

