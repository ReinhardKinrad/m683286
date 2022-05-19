#!/bin/bash
#check for root
IAM=$(whoami)
if [ ${IAM} != "root" ]; then
    echo "You must be root to use this script"
    exit 1
fi

echo "1. Установить tomcat
2. Добавить пользователя"

read n
case $n in 
    1) #prepare to install
apt-get update

echo "Задайте имя сервера(eng)"
read n
hostnamectl set-hostname $n
echo "Имя задано"

echo "Установите часовой пояс(пример: Europe/Moscow)."
read n
timedatectl set-timezone $n
echo "Часовой пояс установлен"

#automatic synx time
apt-get install chrony
systemctl enable chrony

#open port 8080
echo "Открыть порт 8080?
1 - да
2 - нет"
read n
case $n in
    1) iptables -A INPUT -p tcp --dport 8080 -j ACCEPT
       apt-get install iptables-persistent 
       netfilter-persistent save ;;
    2) echo "доступ к серверу будет затруднен." ;;
esac

#install java
apt-get install default-jdk
echo "Java has installed"

echo "Создаем пользователя tomcat..."
useradd tomcat -U -s /bin/false -d /opt/tomcat -m


echo "Приступаем к установке сервера..."
wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.63/bin/apache-tomcat-9.0.63.tar.gz
tar zxvf apache-tomcat-*.tar.gz -C /opt/tomcat --strip-components 1
echo "Сервер установлен."

echo "Настраиваем сервер TomCat..."
/opt/tomcat/bin/shutdown.sh

echo "Добавление возможности удаленного подключения.
1-разрешить подключение всем адресам
2-разрешить подключение определенному адресу
"
read n
case $n in
    1) echo "<?xml version="1.0" encoding="UTF-8"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<Context antiResourceLocking=\"false\" privileged=\"true\" >
  <CookieProcessor className=\"org.apache.tomcat.util.http.Rfc6265CookieProcessor\"
                   sameSiteCookies=\"strict\" />
  <Valve className=\"org.apache.catalina.valves.RemoteAddrValve\"
         allow=\".*\" />
  <Manager sessionAttributeValueClassNameFilter=\"java\.lang\.(?:Boolean|Integer|Long|Number|String)|org\.apache\.catalina\.filters\.CsrfPreventionFilter\$LruCache(?:\$1)?|java\.util\.(?:Linked)?HashMap\"/>
</Context>
" > /opt/tomcat/webapps/manager/META-INF/context.xml ;;
    2) echo "Введите IP-адрес удаленного пк"
    read n
    echo "<?xml version="1.0" encoding="UTF-8"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<Context antiResourceLocking=\"false\" privileged=\"true\" >
  <CookieProcessor className=\"org.apache.tomcat.util.http.Rfc6265CookieProcessor\"
                   sameSiteCookies=\"strict\" />
  <Valve className=\"org.apache.catalina.valves.RemoteAddrValve\"
         allow=\"127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1|$n\" />
  <Manager sessionAttributeValueClassNameFilter=\"java\.lang\.(?:Boolean|Integer|Long|Number|String)|org\.apache\.catalina\.filters\.CsrfPreventionFilter\$LruCache(?:\$1)?|java\.util\.(?:Linked)?HashMap\"/>
</Context>
" > /opt/tomcat/webapps/manager/META-INF/context.xml ;;
esac

#deploy to ROOT
echo "<Context path=\"\" docBase=\"marketplace\">
                    <!-- Default set of monitored resources -->
                    <WatchedResource>WEB-INF/web.xml</WatchedResource>
                </Context>" > temp6843.txt

sed -i '168r temp6843.txt' /opt/tomcat/config/server.xml
rm temp6843.txt

echo "Запускаем сервер..."
/opt/tomcat/bin/startup.sh

echo "Настройка завершена. Сервер готов к работе." ;;
    2) echo "Введите имя пользователя, по которому будет разрешено деплоить на tomcat"
read n
username=$n
echo "Введите пароль к пользователю"
read n
password=$n
     echo "<role rolename=\"manager-gui\"/>
    <role rolename=\"manager-script\"/>
    <role rolename=\"manager-jmx\"/>
    <role rolename=\"manager-status\"/>
    <user username=\"$username\" password=\"$password\" roles=\"manager-gui,manager-script,manager-jmx,manager-status\"/>" > temp6843.txt

sed -i '21r temp6843.txt' /opt/tomcat/config/tomcat-users.xml

rm temp6843.txt ;;
esac