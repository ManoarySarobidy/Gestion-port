# Compile all java file
javac -cp /home/fanilo/Documents/L2/Workspace/gestion-port/Gestion-port/WEB-INF/lib/connection.jar:/home/fanilo/Documents/L2/Workspace/gestion-port/Gestion-port/WEB-INF/lib/formulaire.jar -d /home/fanilo/Documents/L2/Workspace/gestion-port/Gestion-port/WEB-INF/classes/ /home/fanilo/Documents/L2/Workspace/gestion-port/Gestion-port/WEB-INF/classes/*.java

# # Copy project into server
rm /home/fanilo/Documents/L2/apache-tomcat-10.0.22/webapps/gestion-port.war
jar -cf /home/fanilo/Documents/L2/apache-tomcat-10.0.22/webapps/gestion-port.war .

# Restart tomcat
/home/fanilo/Documents/L2/apache-tomcat-10.0.22/bin/shutdown.sh
/home/fanilo/Documents/L2/apache-tomcat-10.0.22/bin/startup.sh
