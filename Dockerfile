# OntologyManger base image
FROM amazoncorretto:19.0.1-al2
LABEL MAINTAINER="said.daoudagh@isti.cnr.it"
# copy files required for GROOT to run
COPY /* /
# the port numbers the container should expose
#EXPOSE 62626 
EXPOSE 8283
# Entrypoint to run the GROOT
ENTRYPOINT ["java","-cp", "Groot-jar-with-dependencies.jar", "it.cnr.isti.sedc.bieco.groot.rest.Main"]