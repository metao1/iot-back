FROM mysql

# Copy the database schema to the /data directory
#ADD init-db.sh data.sql /tmp/

# init_db will create the default
# database from epcis_schema.sql, then
# stop mysqld, and finally copy the /var/lib/mysql directory
# to default_mysql_db.tar.gz
#RUN chmod +x /tmp/init-db.sh
#RUN /tmp/init-db.sh
#
## run_db starts mysqld, but first it checks
## to see if the /var/lib/mysql directory is empty, if
## it is it is seeded with default_mysql_db.tar.gz before
## the mysql is fired up
#
#ENTRYPOINT "/tmp/run_db"
ENV MYSQL_DATABASE iot

COPY ./data.sql /docker-entrypoint-initdb.d/
