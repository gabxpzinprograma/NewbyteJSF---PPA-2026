# NewbyteJsf

NewbyteJsf é uma plataforma de ensino online desenvolvida em Java, implementada utilizando JavaServer Faces (JSF) integrado com Spring Boot. O projeto representa a migração e reimplementação completa de uma plataforma de ensino originalmente desenvolvida em PHP, transformando-a em uma aplicação web moderna e robusta.

## Descrição do Projeto

A plataforma NewbyteJsf oferece funcionalidades completas para gerenciamento de cursos online, incluindo cadastro e autenticação de alunos e professores, gerenciamento de cursos, aulas e controle de progresso dos alunos. O sistema foi projetado para suportar diferentes tipos de usuários, cada um com suas respectivas permissões e funcionalidades específicas.

## Principais Funcionalidades

- **Sistema de Autenticação**:
  | Funcionalidade                          | Descrição
  | --------------------------------| -----|
  | Cadastro e Login de Alunos  | Sistema completo de registro e autenticação para alunos
  | Cadastro e Login de Professores | Sistema dedicado para registro e autenticação de professores
  | Controle de Sessão Única  | Garante que apenas um tipo de usuário (aluno ou professor) permaneça logado simultaneamente

- **Gerenciamento de Usuários**:
  | Características                      | Detalhes
  | -------------------------------| ----|
  | Separação de Perfis       | Alunos e professores possuem tabelas distintas no banco de dados
  | Segurança de Senhas     | Todas as senhas são armazenadas utilizando BCrypt para criptografia

- **Arquitetura Técnica**:
  | Componentes Utilizados                      | Finalidade
  | ----------------------------------------| ------|
  | JavaServer Faces (JSF)                | Framework de apresentação e gerenciamento de interfaces
  | Spring Boot                          | Container de injeção de dependências e infraestrutura
  | Spring Data JPA                     | Persistência de dados e acesso ao banco de dados
  | ViewScope Customizado              | Escopo de vida dos beans adaptado para JSF

## Estrutura do Projeto
src/main/
├── java/com/example/ifbademo/
│   ├── controller/           
│   ├── model/              
│   ├── repository/        
│   ├── service/           
│   └── util/              
├── resources/
│   └── application.properties # Configurações da aplicação
└── webapp/
├── *.xhtml            
└── css/                 

## Tecnologias Utilizadas

| Tecnologia                          | Propósito
| --------------------------------| -----|
| Java                           | Linguagem de programação principal
| JavaServer Faces (JSF)      | Framework de apresentação web
| Spring Boot                   | Framework de aplicação e injeção de dependências
| Spring Data JPA             | Persistência de dados
| BCrypt                       | Criptografia de senhas
| JPA/Hibernate               | Implementação do provedor de persistência

## Instalação e Execução

1. **Pré-requisitos**:
   | Requisito                       | Versão
   | ----------------------------| ----|
   | Java Development Kit  | 11 ou superior
   | Maven                      | 3.6 ou superior
   | Banco de dados MySQL | Versão compatível com o script fornecido

2. **Configuração**:
   # Clonar o repositório
   git clone https://github.com/gabxpzinprograma/NewbyteJsf.git
   cd NewbyteJsf

   # Compilar e executar a aplicação
   mvn clean spring-boot:run
3. **Acesso à Aplicação**:
    Após a inicialização bem-sucedida, a aplicação estará disponível em:
    http://localhost:8080
    Estrutura de Banco de Dados
    O projeto utiliza um esquema de banco de dados composto pelas seguintes tabelas principais:
    
    Aluno: Armazena informações dos alunos da plataforma
    Professor: Armazena informações dos professores da plataforma
    Curso: Contém os dados dos cursos disponíveis
    Aula: Representa as aulas individuais dos cursos
    
    Funcionalidades Implementadas
    O sistema possui as seguintes funcionalidades completamente implementadas:
    
    Sistema de cadastro e login separado para alunos e professores
    Controle rigoroso de sessão garantindo apenas um tipo de usuário logado simultaneamente
    Validação de senhas com confirmação durante o cadastro
    Verificação de unicidade de e-mail para evitar cadastros duplicados
    Criptografia segura de todas as senhas utilizando BCrypt
    Navegação clara entre as diferentes áreas de aluno e professor
    
    Desenvolvimento e Estrutura do Código
    O projeto segue padrões de arquitetura em camadas, com separação clara entre:
    
    Camada de Apresentação: Gerenciada pelos beans JSF localizados no pacote controller
    Camada de Serviço: Contém a lógica de negócio e coordenação entre diferentes repositórios
    Camada de Persistência: Implementada através das interfaces de repositório Spring Data JPA
    
    Próximos Passos de Desenvolvimento
    As principais funcionalidades planejadas para implementação futura incluem:
    
    Listagem e visualização de cursos disponíveis
    Sistema de matrícula de alunos em cursos
    Controle de progresso de visualização de aulas
    Painel de gerenciamento para professores
    Sistema de upload e gerenciamento de conteúdo de aulas
    
    Contribuição
    Para contribuir com o desenvolvimento do projeto:
    
    Faça um fork do repositório
    Crie uma branch para sua funcionalidade
    Implemente as alterações e testes correspondentes
    Faça commit das alterações seguindo convenções claras
    Envie um pull request para revisão
    
    Licença
    Este projeto é desenvolvido como parte de um trabalho acadêmico para a disciplina de Linguagem de Programação II e está destinado ao uso educacional e desenvolvimento da plataforma Newbyte.
    
    Este README fornece uma visão completa e profissional do projeto, incluindo todas as informações necessárias para compreender sua estrutura, funcionalidades implementadas, tecnologias utilizadas e procedimentos para instalação e desenvolvimento. O documento está organizado de forma clara e estruturada, facilitando a compreensão tanto para desenvolvedores quanto para pessoas interessadas em estudar ou contribuir com o projeto.
       
