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
       apt-get install iptables-persistent ;;
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