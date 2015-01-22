---
layout: post
title: "JBoss Enterprise Docker Containers"
description: ""
category:
tags: [docker,eap,jboss]
---
{% include JB/setup %}


Lately I have been working a lot with Docker in an attempt to both learn the technology for myself, as well as to be prepared for the upcoming release of OpenShift v3. As part of that, I have been building out Docker containers for several of the Red Hat JBoss Enterprise Middleware products that I use on a regular basis at my clients. These have already come in extremely handy for me as I spend a lot of time rebuilding and redeploying servers to my local machine. Currently there are very few official [JBoss Docker](https://github.com/jboss-dockerfiles/) containers in existence and the few that do exist are for community edition projects. Because of this, I wanted to make a post about the containers that I have put together so far so that hopefully others can benefit from them.

All of the containers that I have built so far (and any future ones that I will build) can be found on GitHub [here](https://github.com/bsaunder/docker-containers). So far I have containers for the following:

  - JBoss Enterprise Application Platform 6.0.1 to 6.3.0
  - JBoss Fuse 6.0 & 6.1
  - JBoss Fuse Service Works 6.0
  - JBoss A-MQ 6.1 & 6.1.1

 In addition to these, I am currently working on building containers for JBoss Data Grid and JBoss BRMS.
