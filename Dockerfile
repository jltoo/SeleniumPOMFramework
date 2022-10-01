FROM ubuntu:latest
MAINTAINER Lovie Too
COPY . /tests
WORKDIR /tests
RUN apt-get update

RUN export DEBIAN_FRONTEND=noninteractive && \
    apt-get install -yq gnupg php wget unzip php-curl php-mbstring php-xml libxi6 libgconf-2-4 git
RUN -get install -y libnss3-dev libgdk-pixbuf2.0-dev libgtk-3-dev libxss-dev
RUN curl -sL https://deb.nodesource.com/setup_6.x | bash -
RUN apt-get install -y nodejs

RUN cd /opt
RUN wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz"
RUN tar xzf jdk-8u131-linux-x64.tar.gz
RUN export JAVA_HOME=/tests/jdk1.8.0_131
RUN export JRE_HOME=/tests/jdk1.8.0_131/jre
RUN export PATH=$PATH:/tests/jdk1.8.0_131/bin:/tests/jdk1.8.0_131/jre/bin
RUN wget --no-cookies --no-check-certificate "https://dlcdn.apache.org/maven/maven-3/3.8.3/binaries/apache-maven-3.8.3-bin.tar.gz"
RUN tar xzf apache-maven-3.8.3-bin.tar.gz
RUN export M2_HOME=/tests/apache-maven-3.8.3
RUN export MAVEN_HOME=/tests/apache-maven-3.8.3
RUN export PATH=${M2_HOME}/bin:${PATH}