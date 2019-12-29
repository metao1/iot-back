#!/bin/bash

# Initialize MySQL database.
# ADD this file into the container via Dockerfile.
# Assuming you specify a VOLUME ["/var/lib/mysql"] or `-v /var/lib/mysql` on the `docker run` command…
# Once built, do e.g. `docker run your_image /path/to/docker-mysql-initialize.sh`
# Again, make sure MySQL is persisting data outside the container for this to have any effect.

set -e
set -x

# Start the MySQL daemon in the background.
/usr/sbin/mysqld &
mysql_pid=$!

until mysqladmin ping &>/dev/null; do
  echo -n "."; sleep 0.2
done

mysql -e "grant ALL PRIVILEGES ON iot.* TO 'iot'@'localhost';"
mysql -e "SET GLOBAL log_bin_trust_function_creators = 1;";
mysql -e "flush privileges;";

# Tell the MySQL daemon to shutdown.
mysqladmin shutdown

# Wait for the MySQL daemon to exit.
wait $mysql_pid