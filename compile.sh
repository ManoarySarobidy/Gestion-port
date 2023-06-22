# Compile all java file
javac -cp /mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/lib/connection.jar:/mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/lib/formulaire.jar -d /mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/classes/ /mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/classes/*.java

# # Copy project into server
rm /home/tendry/apache-tomcat-10.1.7/webapps/gestion-port.war
jar -cf /home/tendry/apache-tomcat-10.1.7/webapps/gestion-port.war .

# Restart tomcat
/home/tendry/apache-tomcat-10.1.7/bin/shutdown.sh
/home/tendry/apache-tomcat-10.1.7/bin/startup.sh