# Desafio - Crawler Service

# Objetivo

Criar um crawler que leia [este feed] (http://revistaautoesporte.globo.com/rss/ultimas/feed.xml)
e retorne um json estruturado.

## Requisitos
O campo titulo deve ser identico ao item do feed; - OK  
O campo links deve ser identico ao item do feed;  - OK  
O campo description deve ser estruturado da seguinte forma:  
tag p (com conteúdo) deve gerar um bloco do tipo 'text' com o texto como content; - OK  
tag div//img deve gerar um bloco do tipo 'image' com o endereço da imagem como content; - OK  
tag div//ul deve gerar um block do tipo 'links', com o endereço de cada link no content; - OK  

## Diferenciais
Cobertura de testes; - OK  
Projeto conteinerizado;  - X  
Organização e legibilidade do código; - OK  

## Desafio extra
Implementar como um webservice;  * Permitir que somente usuários cadastrados possam acessar o webservice; - X  
* Obs: Há um usuário padrão "cadastrado" para o consumo do serviço através de um endpoint

## Como testar o endpoint
1 - Inicializar o serviço.  
2 - Digitar no browser: http://localhost:8080/feed/download/admin/admin  
user: admin  
password:admin  

## Informações Técnicas
IDE: Spring Tool Suite (STS) 3.9.0.RELEASE  
Framework: Spring e JUnit  
Linguagen: Java  
Formatos de Arquivo: XML e JSON  
Arquitetura de Comunicação: REST  

## Amazon AWS Release
Service URL: http://backend-challenge.us-west-2.elasticbeanstalk.com/feed/download/admin/admin   
Tipo de Instância: t2.nano (Free tier)  
Container: 64bit Amazon Linux; Tomcat 8; Java 8  

## Dívida Técnica
Não consegui deixar o conteúdo de texto perfeitamente legível. Nem mesmo utilizando Regex para remoção de caracteres especiais gerados por conta das tags HTML. 

