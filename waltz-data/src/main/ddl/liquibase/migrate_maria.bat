c:/dev/tools/liquibase/liquibase ^
--driver=org.mariadb.jdbc.Driver ^
--classpath=c:/dev/tools/javalib/mariadb-java-client-1.3.2.jar ^
--changeLogFile=db.changelog-master.xml ^
--url="jdbc:mysql://localhost:3306/scratch" ^
--username=root ^
--password=password ^
migrate
