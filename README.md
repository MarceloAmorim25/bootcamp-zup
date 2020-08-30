# Postgram

    API para aplicação do tipo Instagram.
    
    ![](/readme-images/postgramlogo.png)

### executando a API

    - git clone url
    - cd bootcamp-zup
    - ./mvnw clean spring-boot:run

### sobre o projeto

    - A ideia do projeto é simples e visa atender dois eixos centrais:

        (a). Listar recursos centrais para o funcionamento da aplicação. Quais são esses 
        recursos a serem listados? Postagens, comentários e as respostas aos comentários
        (threads possíveis de serem feitas em cada comentário - aqui chamei de reply).

        (b). O segundo eixo responde à pergunta 'quem pode ter acesso à esses recursos?'. No caso aqui será implementado, em primeiro lugar, restrição de acesso às rotas, podendo ser acessadas apenas por usuários autenticados. O segundo filtro é 'essa pessoa foi autorizada pelo usuário a interagir com suas postagens?' e então foi criado aqui um método de permitir a interação apenas de usuários autorizados pelo autor do post.

### swagger

    - http://localhost:8080/swagger-ui.html
      
### O que é possível fazer com essa API?

	- Um esquema inicial de tabelas para o banco de dados

	![](/readme-images/schema.png)

	- Sobre como ficaram os endpoints =>
	
	- Panorama geral da aplicação
	
	![](/readme-images/panorama-geral-swagger.png)
	
	- User controller: é possível criar, atualizar e deletar um usuário.
	
	![](/readme-images/user-controller.png)
	
	- A autenticação está funcionando e gerando um token no formato Json Web Token(JWT) para autorizar o acesso a recursos e autenticar o usuário.
	
	![](/readme-images/autentica.png)
	
	- Esse controller permite dar follow ou unfollow e assim interagir na rede social.
	
	![](/readme-images/friend-controller.png)
	
	- O post controller permite criar posts, listar postagens pelo id do usuário (página de perfil do usuário), deletar, atualizar e listar pelo id do post (no caso de uma postagem ser selecionada para leitura única na tela).
	
	![](/readme-images/post-controller.png)
	
	- É possível comentar, atualizar o comentário e deletar.
	
	![](/readme-images/comment-controller.png)
	
	- Também é possível criar threads nos comentários com uma lista de replies.
	
	![](/readme-images/reply-controller.png)


### segurança

    - Como a aplicação dialoga com o OWASP Top Ten?

    1. Injection
    
    O seguinte regex pode contribuir para evitar ataques do tipo SQL injection, evitando a sintexe comum às queries. Apesar da ORM do Spring já implementar medidas com SQLi, aumentar o grau de segurança sempre pode evitar ataques não previstos.
    
    @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
    
    2. Broken Authentication
    
    Utilizou um token gerado no formato JWT como forma de autenticar o usuário. Esse padrão tem sido bastante utilizado e, apesar de provavelmente possuir vulnerabilidades, apresenta segurança considerável. O token possui tempo breve de expiração.
    
    3. Sensitive Data Exposure
    
    Em desenvolvimento...
    
    4. XML External Entities (XXE)
    
    Em desenvolvimento...
    
    5. Broken Access Control
    
    Em desenvolvimento...
    
    6. Security Misconfiguration
    
    Em desenvolvimento...
    
    7. Cross-Site Scripting (XSS)
    
    Nas validações das classes Model utilizou-se um regex para dificultar ataques Cross-site Scripting do tipo storage, já que que não seria possível enviar um campo de texto com tags script nem utilizando notações alternativas.
    
    @Pattern(regexp = "^[a-zA-Z0-9 .-]+$")
    
    
    8. Insecure Deserialization
    
    Em desenvolvimento...
    
    9. Using Components with Known Vulnerabilities
    
    Em desenvolvimento...
      
    10. Insufficiente Logging & Monitoring
    
    Em desenvolvimento...
    

### uma ideia de como vai ficar o visual

    - frontend em desenvolvimento :)


### referências de pesquisa e observações

OWASP Top Ten - https://owasp.org/www-project-top-ten/ -> serviu como referência para avaliar qual rumo, em relação a segurança, que o projeto estava tomando.

FuzzDB Project - https://github.com/fuzzdb-project/fuzzdb -> a idéia era fazer alguns testes automatizados com alguns ataques já bastante conhecidos e testar a resposta da aplicação a alguns tipos de SQL injection e XSS. Não consegui implementar, mas fica como projeto futuro.

Alura - https://www.alura.com.br/ -> do curso de Spring Rest Parte 2 da Alura foi baseado toda a estrutura de autenticação e autorização, com uma pequena alteração no setter da classe User, automatizando a geração da hash criada pelo BCrypt no momento de criação do usuário.

AlgaWorks - https://www.algaworks.com/ -> o projeto da semana API com Spring Rest serviu como base para algumas partes iniciais do projeto. O repo desse projeto tá pinado aqui no meu github.

https://owasp.org/www-community/OWASP_Validation_Regex_Repository -> desse repo que extraí o regex que ficou em algumas classes da pasta Models.

