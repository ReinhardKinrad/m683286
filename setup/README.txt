ИНСТРУКЦИЯ:

REST-service доступен по адресу: REMOTE.SERVER.IP:8080/lots (get). Возвращает список лотов.


I. ПОДГОТОВКА К УСТАНОВКЕ

1. Добавьте конфигурацию сервера в мавен($MAVEN_HOME/conf/settings.xml)
   <server>
         <id>TomcatServer</id>
         <username>admin</username>
         <password>admin</password>
   </server>

   Для примера используется стандартный username и password

2. Измените в pom.xml настройку плагина tomcat7-maven-plugin, нужно указать ip удаленного сервера


   <plugin>
           <groupId>org.apache.tomcat.maven</groupId>
           <artifactId>tomcat7-maven-plugin</artifactId>
           <version>2.2</version>
           <configuration>
             <url>http://YOUR.IP/manager/text</url>
             <server>TomcatServer</server>
             <path>/</path>
           </configuration>
   </plugin>

3. Перенесите скрипты из папки setup на машину с ubuntu(тестировалось на ubuntu server 22)

II. УСТАНОВКА TOMCAT

1. Запустите скрипт на удаленной машине - install-tomcat.sh
2. Первый пункт инсталлятора произведет непосредственную установку сервера
3. Второй пункт инсталлятора создает пользователя, с помощью которого будет производиться деплой приложения на сервер
4. ВАЖНО: третий пункт использовать только после первого деплоя приложения

III. ПРЕДВАРИТЕЛЬНЫЙ ДЕПЛОЙ ПРИЛОЖЕНИЯ НА СЕРВЕР

1. В проекте используется плагин мавена, tomcat7, позволяющий производить деплой на удаленный сервер.
   Для того, чтобы произвести развертывание проекта, нужно выйти в cmd из под папки с проектом и ввести команду
   mvn tomcat7:deploy(для деплоя) или mvn tomcat7:redeploy(для редеплоя).
2. Запустите скрипт install-tomcat.sh на сервере и выполните 3 пункт меню

IV. УСТАНОВКА POSTGRESQL

1. Запустите скрипт на удаленной машине - install-postgresql.sh (устанавливает бд и открывает порт 5432)
2. Перейдите на стандартного пользователя бд postgres (su postgres / sudo -i -u postgres)
3. Зайдите в бд (psql) и установите пароль пользователя postgres на postgres (команда \password)
4. Используйте команду \i path для инициализации бд(скрипт инициализации - sqlinit.sql)


   После выполненных действий приложение должно быть доступно по server.ip:8080/
