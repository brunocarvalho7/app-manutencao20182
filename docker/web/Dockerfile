FROM tomcat:8-jre8
LABEL mantainer="brunocarvalho287"

COPY ROOT.war /usr/local/tomcat/webapps/ROOT.war
COPY wait-for-it.sh /usr/local/tomcat/wait-for-it.sh

ENV JDBC_DATABASE_URL=jdbc:postgresql://db:5432/catalogo-cinemas
ENV JDBC_DATABASE_USERNAME=postgres
ENV JDBC_DATABASE_PASSWORD=postgres

CMD ["./wait-for-it.sh","db:5432","--","catalina.sh", "run"]
