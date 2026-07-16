
ARG IMAGE=store/intersystems/iris-aa-community:2020.3.0AA.331.0
ARG IMAGE=intersystemsdc/iris-aa-community:2020.3.0AA.331.0-zpm
ARG IMAGE=intersystemsdc/iris-community:2020.3.0.200.0-zpm
ARG IMAGE=intersystemsdc/iris-community
FROM $IMAGE

USER root
WORKDIR /opt/irisapp
RUN chown ${ISC_PACKAGE_MGRUSER}:${ISC_PACKAGE_IRISGROUP} /opt/irisapp

USER ${ISC_PACKAGE_MGRUSER}

# copy files
COPY  module.xml .
COPY Installer.cls .
COPY App.PEXSetup.cls .
COPY pex-runtime.script .
COPY docker-iris-start.sh /usr/local/bin/docker-iris-start.sh
COPY iris/src src
COPY iris.script /tmp/iris.script

USER root
RUN chmod +x /usr/local/bin/docker-iris-start.sh
USER ${ISC_PACKAGE_MGRUSER}


# special extract treatment for hate-speech dataset
# RUN mkdir /data/hate-speech/ \
#	&& tar -xf /data/hate-speech.tar -C /data/

# load demo stuff
RUN iris start IRIS \
	&& iris session IRIS < /tmp/iris.script \
    && iris stop IRIS quietly

ENTRYPOINT ["/usr/local/bin/docker-iris-start.sh"]