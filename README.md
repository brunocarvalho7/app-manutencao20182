# Trabalho da disciplina de manutenção de software 2018.2 - UFC campus Quixadá

# 1ª Entrega
##### Construa um ambiente de Integração Continua que olha para o repositório do seu projeto e a partir de um commit dispara o job que irá construir e implantar o projeto no Web server. Material binário deve estar disponível para download após a construção.

```
https://app-manutencao20182.herokuapp.com
```

* Sistema de controle de mudanças -> [Github](https://github.com)
* Sistema de integração contínua -> [CircleCI](https://circleci.com/)
* Servidor de hospedagem -> [Heroku](https://www.heroku.com)


# Documentações

* [Heroku](https://devcenter.heroku.com/)
* [CircleCI](https://circleci.com/docs/)

# Rodar SonarCloud
```
mvn install sonar:sonar   -Dsonar.projectKey=brunocarvalho7_app-manutencao20182   -Dsonar.organization=brunocarvalho7-github   -Dsonar.host.url=https://sonarcloud.io   -Dsonar.login=14bf9bff9585ef4232f52621cc016cf83d2da22d
```