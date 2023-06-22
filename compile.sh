# Compile all java file
javac -cp ./WEB-INF/lib/connection.jar:$HOME/Documents/GitHub/Gestion-port/WEB-INF/lib/formulaire.jar -d $HOME/Documents/GitHub/Gestion-port/WEB-INF/classes/ $HOME/Documents/GitHub/Gestion-port/WEB-INF/classes/*.java

# # Copy project into server
rm $HOME/Desktop/tomcat/webapps/gestion-port.war
jar -cf $HOME/Desktop/tomcat/webapps/gestion-port.war .

# Restart tomcat
$HOME/Desktop/tomcat/bin/shutdown.sh
$HOME/Desktop/tomcat/bin/startup.sh