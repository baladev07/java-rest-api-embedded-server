#!/bin/bash
echo "populating database.."
export PATH=$PATH:/usr/local/mysql/bin
#sudo mysqld_safe --skip-grant-tables
mysql -u root -e "create database db_test;"
#echo "ggg"
mysql -u root -e "grant all privileges on *.* to root@localhost identified by '' with grant option;";
mysql -u root db_test < test.sql
echo "database created.."