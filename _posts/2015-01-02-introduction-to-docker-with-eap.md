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

>Docker is an open platform for developers and sysadmins to build, ship, and run distributed applications. Consisting of Docker Engine, a portable, lightweight runtime and packaging tool, and Docker Hub, a cloud service for sharing applications and automating workflows, Docker enables apps to be quickly assembled from components and eliminates the friction between development, QA, and production environments.

This means you can build any app in any language using any toolchain and those apps are completely portable and can run anywhere. OS X and Windows laptops, QA servers running CentOS in the cloud, and production data center VMs running Red Hat. This is a huge benefit for development teams because you can distribute the same container to everyone, allowing them to get started with development in a fraction of the time than it would take for them to manually build out a local development environment.

At the core of all this wonderful magic are Docker containers. A container comprises just the application and its dependencies. It runs as an isolated process in userspace on the host operating system, sharing the kernel with other containers. Thus, it enjoys the resource isolation and allocation benefits of VMs but is much more portable and efficient.

# Installing Docker

Docker can run on almost any platform you can think of. It even runs on Windows. If you havent installed Docker yet, you may want to before continuing. The steps for installing Docker are generally as simple as downloading the binary and running it, but some platforms require some additional steps. 

Find your platform in the list [here](https://docs.docker.com/installation/) and follow the steps described to install Docker.

# Your first container

All interactions with Docker are via the command line, so if you’ve never used the terminal, now is the time to get started. The main command you’ll use is the `docker` command. Running this command without any additional options will display the help screen, and a list of all of the options available to you. To ensure everything is working properly, go ahead and run the following command to test your Docker installation.

{% highlight bash %}
docker run hello-world
{% endhighlight %}

# Building a Docker container for EAP

Now that we have a basic understanding of what Docker is, lets jump into the meet of things and talk about our EAP container. The container itself is pretty straight forward. All you need to create it is the Dockerfile and the EAP installation zip. If you do not alreay have the ZIP archive for EAP 6.3 then you should go download it here. Now lets look at the Dockerfile for our container.

<script src="http://gist-it.appspot.com/github/bsaunder/docker-containers/blob/master/eap_6.3.0/Dockerfile"></script>

