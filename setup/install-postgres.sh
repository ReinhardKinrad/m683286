#!/bin/bash
#check for root
IAM=$(whoami)
if [ ${IAM} != "root" ]; then
    echo "You must be root to use this script"
    exit 1
fi

apt update

apt -y install postgresql

apt update

iptables -A INPUT -p tcp --dport 5432 -j ACCEPT
netfilter-persistent save

psql 

\password

postgres
postgres

\q

