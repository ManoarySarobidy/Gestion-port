# Compile all java file
# javac -cp /mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/lib/connection.jar:/mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/lib/formulaire.jar -d /mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/classes/ /mnt/d/Tendry/Project\ Programmation/Gestion-port/WEB-INF/classes/*.java
project=/Users/rakotoharisoa/Documents/mirindra/Mr_Tahina/gestion-port
tomcat='/Users/rakotoharisoa/Documents/apache-tomcat-10.0.20'

javac -cp $project/WEB-INF/lib/connection.jar:$project/WEB-INF/lib/formulaire.jar -d $project/WEB-INF/classes $project/WEB-INF/classes/*.java

# # Copy project into server
rm $tomcat/webapps/gestion-port.war
jar -cf $tomcat/webapps/gestion-port.war .

# Restart tomcat
sh $tomcat/bin/shutdown.sh
sh $tomcat/bin/startup.sh
