---
layout: post
title: "Considerations for Implementing JBoss Fuse in your Enterprise"
description: ""
category:
tags: [camel,fuse,jboss,integration,enterprise]
---
{% include JB/setup %}

In today's modern world, the need for enterprise integration has never been greater. Companies are looking for ways to reduce the costs of their application infrastructure and one of the ways they are doing that is by extending the lifespan of older legacy platforms. This introduces a number of problems when there is also a need for the implementation of new modern systems as well. Often times it is difficult if not impossible to make these new and legacy systems communicate directly. To resolve this problem, many companies are considering adopting Red Hat JBoss Fuse to integrate these systems together. If you are considering implementing JBoss Fuse in your company's enterprise there are several key pieces of information you need in order to make an informed decision. This starts with what JBoss Fuse really is, and what are the options for deploying it?

<!-- more -->

## Fuse Product Suite

JBoss Fuse is a product suite from Red Hat that helps solve enterprise integration problems like these. JBoss Fuse is a lightweight enterprise service bus that supports the integration of a wide array of platforms and protocols. It is extremely flexible in its capabilities and allows you to intelligently integrate all of your systems, whether they are locally hosted or in the cloud. The JBoss Fuse product suite consists of three offerings, each with their own benefits and drawbacks. At the core of all of these products however is the powerful and industry proven open source lightweight enterprise integration framework [Apache Camel](http://camel.apache.org/). Apache Camel is built around [Enterprise Integration Patterns](http://www.enterpriseintegrationpatterns.com/index.html). These patterns in combination with Apache Camel’s hundreds of out of the box components provide enormous power and flexibility. In addition to Apache Camel, [Apache CXF](http://cxf.apache.org/) is included to provide powerful web services capabilities as well as [Apache ActiveMQ](http://activemq.apache.org/) to provide a wide array of Java messaging capabilities.

### JBoss Fuse

[JBoss Fuse](https://www.redhat.com/en/technologies/jboss-middleware/fuse) is a lightweight platform that is built on top of [Apache Karaf](http://karaf.apache.org/). Apache Karaf is an OSGi based application container that allows for highly modular application deployments. Karaf provides many enterprise ready features such as a shell console, remote access, hot deployment, dynamic configuration and many more. The OSGi programming model is a component based system that provides a modular architecture for today’s large scale distributed systems.

### JBoss Fuse on EAP

[JBoss Fuse on EAP](https://www.redhat.com/en/technologies/jboss-middleware/fuse) is the Java Enterprise certified JBoss Enterprise Application Platform (EAP) with the JBoss Fuse components installed and running on top. With this product you retain all the power and functionality that JBoss EAP provides with the integration capabilities of Apache Camel and the messaging power of Apache ActiveMQ.

### JBoss Fuse for xPaaS

[JBoss Fuse for xPaaS](https://www.openshift.com/enterprise/middleware-services.html) is the newest member of the JBoss Fuse family. This product is built in to Red Hat OpenShift Enterprise and is designed from the ground up to provide an excellent developer experience and all of the features and functionality for building cloud based microservices. FIS provides multiple runtimes for running Apache Camel based applications such as a slimmed down Karaf container, CDI, or even directly on the JVM.

## How to Decide

Now that you have an idea of what JBoss Fuse is and what the offerings are, you probably are asking yourself “How do I decide which one is right for me?”. Like most things in software development, there is no single right answer, but there are some general guidelines you can follow when making a decision. For companies who are primarily invested in Java enterprise development using the JEE specification and already running on JBoss Enterprise Application Platform, choosing JBoss Fuse on EAP is really a no-brainer. JBoss Fuse on EAP is easily installed on all of your existing servers so no new infrastructure is needed. This also allows for a large amount of code reuse from already existing JBoss EAP based applications. Likewise, companies who are already largely invested in doing OSGi based development should strongly consider using JBoss Fuse for its OSGi based container. If your company is looking into adopting a Microservices based or highly distributed architecture, then you should consider either JBoss Fuse for non-cloud based deployments, or JBoss Fuse for xPaaS in combination with OpenShift Enterprise for cloud based deployments. In addition to being able to quickly develop a distributed and highly scalable microservices based architecture, applications built on JBoss Fuse can easily be ported to JBoss Fuse for xPaaS at a later date. So you have the flexibility to start on-premise and then move to the cloud, with minimal application changes. In addition to the above general guidelines, there is one more thing to consider before making a decision on which product is right for you, and that is training. Depending on the skills of your development team, some products could require significantly more training over the others to be successful. For instance if you are primarily a Java Enterprise development team, and you decide to go with JBoss Fuse, your developers will need to learn OSGi. Conversely, if you are an OSGi shop and you decide to go with JBoss Fuse on EAP, your developers will need to learn Java Enterprise development. JBoss Fuse on xPaaS needs even more skills in addition to just OSGi or Java Enterprise development. However regardless of which product you use, Apache Camel will play a large part in your development, and it is strongly recommend that you invest in Apache Camel training.

## Tooling Needs

When it comes to tooling and general development with JBoss Fuse products, there are not a lot of required tools. Your development tool set will largely be the same as it always has. However if you are not currently using [Apache Maven](https://maven.apache.org/) for your built tool, you will need to start. This is particularly true if your are going to be using JBoss Fuse or JBoss Fuse for xPaaS, as all of the plugins needed to build the deployables are Apache Maven plugins. While it is possible to build them without, I strongly advise against it. One other optional tool that you should consider adopting however is [JBoss Developer Studio](http://www.jboss.org/products/devstudio/overview/) (JBDS). JBoss Developer Studio is based on the Eclipse IDE and has been specifically tuned for developing with JBoss products. For JBoss Fuse in particular it provides a number of tools such as Camel editors, Fuse debuggers, and graphical data mapping.


This post was also published on the [Red Hat Developers Blog](https://developers.redhat.com/blog/2016/07/29/considerations-for-implementing-jboss-fuse-in-your-enterprise/).
