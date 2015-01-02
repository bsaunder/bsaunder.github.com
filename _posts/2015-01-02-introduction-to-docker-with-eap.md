---
layout: post
title: "Introduction to Docker with EAP"
description: ""
category:
tags: [docker,eap,jboss]
---
{% include JB/setup %}

Lately I have been experimenting with [Docker](https://www.docker.com/ "Docker") and building containers for several of our JBoss Enterprise products. Mostly it has just been an exercise to learn and familiarize myself with Docker ahead of our [OpenShift 3.0](https://blog.openshift.com/openshift-v3-platform-combines-docker-kubernetes-atomic-and-more/ "OpenShift 3.0") release, but also I am tired of recreating EAP and FSW installations every time I need to test out something new. This post will serve as a brief introduction to Docker as well as talk about some of the containers I have built so far.

<!-- more -->

# What is Docker?

Docker is an open-source platform for building and running distributed applications. The official Docker website says it best.

>Docker is an open platform for developers and sysadmins to build, ship, and run distributed applications. Consisting of Docker Engine, a portable, lightweight run-time and packaging tool, and Docker Hub, a cloud service for sharing applications and automating work-flows, Docker enables apps to be quickly assembled from components and eliminates the friction between development, QA, and production environments.

This means you can build any app in any language using any tool-chain and those apps are completely portable and can run anywhere. OS X and Windows laptops, QA servers running CentOS in the cloud, and production data center VMs running Red Hat. This is a huge benefit for development teams because you can distribute the same container to everyone, allowing them to get started with development in a fraction of the time than it would take for them to manually build out a local development environment.

At the core of all this wonderful magic are Docker containers. A container comprises just the application and its dependencies. It runs as an isolated process in user-space on the host operating system, sharing the kernel with other containers. Thus, it enjoys the resource isolation and allocation benefits of VMs but is much more portable and efficient.

# Installing Docker

Docker can run on almost any platform you can think of. It even runs on Windows. If you haven't installed Docker yet, you may want to before continuing. The steps for installing Docker are generally as simple as downloading the binary and running it, but some platforms require some additional steps.

Find your platform in the list [here](https://docs.docker.com/installation/) and follow the steps described to install Docker.

# Your first container

All interactions with Docker are via the command line, so if you’ve never used the terminal, now is the time to get started. The main command you’ll use is the `docker` command. Running this command without any additional options will display the help screen, and a list of all of the options available to you. To ensure everything is working properly, go ahead and run the following command to test your Docker installation.

{% highlight bash %}
docker run hello-world
{% endhighlight %}

This command will look for an image named `hello-world` on your local machine, and in its absence, try to download it from the Docker Hub. The Docker Hub[Docker Hub](https://hub.docker.com)  is a central repository of Docker images that can be used for free by anyone. Once it has found the image, you should see some output similar to this:

{% highlight console %}
Unable to find image 'hello-world' locally
Pulling repository hello-world
e45a5af57b00: Download complete
511136ea3c5a: Download complete
31cbccb51277: Download complete
Status: Downloaded newer image for hello-world:latest
Hello from Docker.
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (Assuming it was not already locally available.)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

For more examples and ideas, visit:
 http://docs.docker.com/userguide/
{%endhighlight %}

So a couple of things just happened. The Docker engine tried to fine the image locally but failed, so it downloaded it from the Hub. Docker then created a new container from that image which contained the executable code that produced the output. It then ran the container, and once it finished running, sent the output to your console. The same steps will happen every time you run a Docker container. You can also run an interactive Docker container by appending the `-i -t` options to the command. For instance the following command will run an interactive Fedora 21 based container:

{% highlight bash %}
sudo docker run -i -t fedora:21 bash
{% endhighlight %}

# Creating a Docker container for EAP

Now that we have a basic understanding of what Docker is, lets jump into the meet of things and talk about our EAP container. The container itself is pretty straight forward. All you need to create it is the Dockerfile and the EAP installation zip. If you do not already have the ZIP archive for EAP 6.3 then you should go download it [here](http://www.jboss.org/download-manager/file/jboss-eap-6.3.0.GA.zip) here. Now lets look at the Dockerfile for our container.

{% highlight bash %}
### Set the base image to Fedora
FROM jboss/base-jdk:7

### File Author / Maintainer
MAINTAINER "Bryan Saunders" "bsaunder@redhat.com"

### Install EAP 6.3.0
ADD installs/jboss-eap-6.3.0.zip /tmp/jboss-eap-6.3.0.zip
RUN unzip /tmp/jboss-eap-6.3.0.zip

### Set Environment
ENV JBOSS_HOME /opt/jboss/jboss-eap-6.3

### Create EAP User
RUN $JBOSS_HOME/bin/add-user.sh admin admin123! --silent

### Configure EAP
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0\""
        >> $JBOSS_HOME/bin/standalone.conf

### Open Ports
EXPOSE 8080 9990 9999

### Start EAP
ENTRYPOINT $JBOSS_HOME/bin/standalone.sh -c standalone-full-ha.xml
{% endhighlight %}

There are several steps happening in this file, and we will walk through them all one at a time.

The first command is the `FROM` command. This command tells Docker what image to use as our base image. In this case we are using the JBoss JDK 7 image which is based off of the JBoss Fedora 20 base image. In total these two images combine to create a Fedora 20 installation with a jboss user and group already configured along with a configured installation of JDK 7. By using a base image like these, we are able to skip those steps in our Dockerfile and focus purely on the EAP installation.

The second command is the `MAINTAINER` command. This command simply lists the name and email of the person who is maintaining the image.

The third command is where we start to get into the meat of the container and actually start our installation process for EAP. The `ADD` command is used to add files from our local system to our container. In this case we are adding our EAP 6.3 ZIP archive. Once we have added the file, we will use the `RUN` command to unzip into a temporary directory. The `RUN` command can be used to run any command that you would typically run from the command line.

The next step we get to is the `ENV` command. This command sets environment variables in the container, and in this case is setting the `JBOSS_HOME` variable to the location of our installation. It is important to note at this point that because we used the JBoss base images, our current working directory is `/opt/jboss/` (it was set in the base image) so when we extracted the ZIP archive, it created the folder `/opt/jboss/jboss-eap-6.3`.

Once our environment variable is set, we use the `RUN` command two more times to create our standard admin user using the add user script and set our `JAVAOPTS` variable in the standalone configuration file. Specifically we are setting the bind address to 0.0.0.0 so that EAP will bind on all IP addresses for our container. This will allow us to access it from outside the container.

The `EXPOSE` command is used to expose ports from the container. In this case we are exposing the standard ports that EAP uses. This will be mapped by Docker to an external port on the container, and then forwarded appropriately when they are accessed externally.

The final command is the `ENTRYPOINT` command that is used to define what command should be run when the container starts. In our case we are building an EAP container, so we are going to start EAP using the standalone-ha-full configuration. 

## Building the container

Now that we have the Dockerfile and our installation archive, lets build and run it. For starters, copy the contents of the Dockerfile above into a file named `Dockerfile` with no extension. Then create a folder named `installs` and move the installation archive into that folder.

Next you will build the image by running the following command from the same directory as your Docker file.

{% highlight bash %}
sudo docker build -q --rm -t bsaunder/eap_6.3-standalone .
{% endhighlight %}

Once the image has completed building, you will want to run it. From the same directory, execute the following Docker run command:

{% highlight bash %}
sudo docker run -d -P bsaunder/eap_6.3-standalone
{% endhighlight %}

Your new EAP container should now be running. In order to test it though, lets connect to the container and make sure we can get the EAP start page. First we need to find the port numbers that our container was mapped too. To do so, we will run the ps command from Docker. This command lists all of our currently running containers.

{% highlight bash %}
sudo docker ps
{% endhighlight %}

You should see a table with a list of all of the running containers, and one of them will have the image name `bsaunder/eap_6.3-standalone:latest`. To the right of that, under `PORTS` you will see a list of port mappings associated to that container that looks similar to this:

{% highlight console %}
0.0.0.0:49153->8080/tcp, 0.0.0.0:49154->9990/tcp, 0.0.0.0:49155->9999/tcp
{% endhighlight %}

You are looking for the port mapping for port `8080` which in my case is port `49153`. That is the local port that you can use to reference port `8080` inside your container. If you visit `http:\\localhost:<YOUR_PORT>` inside your browser, you should now see the EAP start page for the EAP instance running inside your container.
