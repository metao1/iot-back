docker exec -it mysql:latest mysql -u root -piot
grant ALL PRIVILEGES ON *.* TO 'iot';
flush privileges;