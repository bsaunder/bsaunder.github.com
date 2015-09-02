---
layout: post
title: "Red Hat EX421 Study Guide"
description: ""
category:
tags: [camel,fuse,jboss,training,jb421,exam,ex421]
---
{% include JB/setup %}

This guide is intended to serve as a study reference for people planning to take the Red Hat Certificate of Expertise in Camel Development Exam ([EX421](http://www.redhat.com/en/services/training/ex421-red-hat-certificate-expertise-camel-development-exam)). It contains all of the information & resources that you need to properly prepare yourself for the exam.

Note that this guide is currently a WIP.

<!-- more -->

# Audience &amp; Prerequisites

The following audiences may be interested in earning the Red Hat Certificate of Expertise in Camel Development:
- Enterprise Java developers who are familiar with service-oriented architecture (SOA) principles and are responsible for integrating enterprise applications.
- Enterprise SOA architects with some Java development experience and knowledge of enterprise integration.

Exam candidates should:
- Have taken the Camel Development with Red Hat JBoss Fuse (RH421) course or have extensive work experience in enterprise integration using Apache Camel
- Have familiarity working in a Red Hat Enterprise Linux® environment
- Review the Red Hat Certificate of Expertise in Camel Development exam (EX421) objectives


# Exam format

This exam is a performance-based evaluation of the candidate's Camel development skills and knowledge. Candidates perform tasks associated with the development and maintenance of enterprise integration patterns using Red Hat JBoss Fuse. Performance-based testing means that candidates must perform tasks similar to what they perform on the job.

This is a closed exam where candidates do not have access to the internet and candidates are not permitted to bring any papers, books or electronic aids to the exam. Candidates are, however, provided with a local copy of the official [JBoss Fuse documentation](https://access.redhat.com/site/documentation/JBoss_Fuse/). Candidates should familiarize themselves with this documentation when preparing for the exam.

This exam consists of 1 section lasting 4 hours.


# Scores and reporting

Official scores for exams come exclusively from Red Hat Certification Central. Red Hat does not authorize examiners or training partners to report results to candidates directly. Scores on the exam are usually reported within 3 U.S. business days.


# Recommended training

Camel Development with Red Hat JBoss Fuse ([JB421](http://www.redhat.com/en/services/training/jb421-camel-development-red-hat-jboss-fuse)) is the recommended training course for preparing for this exam.


# Exam Objectives & Resources
This section will break down the exam by objectives and relate each objective back to the appropriate section in the JB421 training course and official documentation. Additional resources may also be referenced for some objectives when there is a need to provide more detail.

## Create & Maintain Camel Routes
Camel routes are the core construct within Camel and are used for routing messages. They are composed of Components and Endpoints and are created using one of many DSL's or XML based languages. The most common methods for creating a route are using the Java DSL or Spring XML.

- **Official Resources**
  - Training Course - JB421, Sections 1.5 & 1.6
  - [Official Documentation - Camel Development Guide, Chapter 1](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/FuseMRStartedBlocks.html)
- **Community References**
  - [Camel Documentation - Routes](http://camel.apache.org/routes.html)
  - [Camel Documentation - Java DSL](http://camel.apache.org/java-dsl.html)
  - [Camel Documentation - Spring DSL](http://camel.apache.org/spring.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 2.3 & 2.4

### Use the Java language DSL
The Java based DSL uses a fluent builder style. The Java DSL is available by extending the RouteBuilder class and implementing the configure method.

- **Official Resources**
  - Training Course - JB421, Sections 2.3 & 2.4
  - [Official Documentation - Camel Development Guide, Chapter 1](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/FuseMRStartedBlocks.html)
- **Community References**
  - [Camel Documentation - Java DSL](http://camel.apache.org/java-dsl.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 2.3

### Use the CamelContext XML
The CamelContext XML is used with the Spring XML to create and define Camel routes. You can configure a CamelContext inside any spring.xml using the CamelContextFactoryBean. This will automatically start the CamelContext along with any referenced Routes along any referenced Component and Endpoint instances.

- **Official Resources**
  - Training Course - JB421, Sections 2.5 & 2.6
  - [Official Documentation - Camel Development Guide, Chapter 1](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/FuseMRStartedBlocks.html)
- **Community References**
  - [Camel Documentation - Spring DSL](http://camel.apache.org/spring.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 2.4

## Understand Exchange messages
An exchange stores messages received and transmitted using Camel routes. It stores routing information, the properties of the route/exchange itself, and the headers and properties of messages. The exchange is composed of an ID, Exchange Pattern (MEP), Properties, and two messages (In & Out).

- **Official Resources**
  - Training Course - JB421, Sections 1.3 & 1.4
  - [Official Documentation - Camel Development Guide, Chapter 42](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgFormats.html)
- **Community References**
  - [Camel Documentation - Routes](http://camel.apache.org/routes.html)
  - [Camel Documentation - Java DSL](http://camel.apache.org/java-dsl.html)
  - [Camel Documentation - Spring DSL](http://camel.apache.org/spring.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 2.3 & 2.4

### Work with the Message Body
TODO
Related: Camel Data Formats - http://camel.apache.org/data-format.html

### Work with the Message Headers
TODO

### Work with the Message Attachments
TODO

### Work with the In/out Messages
TODO

## Consume and produce files
TODO

### Work with CSV files
TODO

### Work with XML file
TODO

## Work with databases and Camel JPA
TODO

## Understand transactional routes
TODO

## Understand & use common EIPs
EIP's are Design Patterns that are intended to be used in ESB's and other SOA systems to solve common integration problems. Camel supports most of the Enterprise Integration Patterns from the excellent book by Gregor Hohpe and Bobby Woolf.

- **Official Resources**
  - Training Course - JB421, Sections 1.7
  - [Official Documentation - Camel Development Guide, Chapter 3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/IntroToEIP.html)
- **Community References**
  - [Camel Documentation - Enterprise Integration Patterns](http://camel.apache.org/enterprise-integration-patterns.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 8

### Work with Content-based router EIP
The Content Based Router EIP pattern allows you to route messages to the correct destination based on the contents of a message exchange. Expressions are utilized to evaluate the content of the exchange and determine where to route the message.docs.redhat.

- **Official Resources**
  - Training Course - JB421, Sections 3.1 & 3.2
  - [Official Documentation - Camel Development Guide, Section 8.1](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgRout.html#MsgRout-ContentBased)
- **Community References**
  - [Camel Documentation - Content Based Router](http://camel.apache.org/content-based-router.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 2.5.1
  - Book - Enterprise Integration Patterns, Chapter 7
- **Gotchas**
  - When using the Java DSL there are limitations on how "deep" you can go into a route. If your code does not compile when using a CBR try using .endChoice() to return control back to the CBR. You can find more detailed information [here](http://camel.apache.org/why-can-i-not-use-when-or-otherwise-in-a-java-camel-route.html) Note that this is not an issue with XML Routes.

### Work with Wire Tap EIP
TODO

### Work with Splitter EIP
TODO

### Work with Aggregator EIP
TODO

### Work with RecipientList EIP
TODO

## Understand & use common Camel components
TODO

### Work with File2 component
TODO

### Work with FTP component
TODO

### Work with JPA2 component
TODO

### Work with JMS component
TODO

### Work with Direct component
TODO

## Testing Routes
TODO

### Work with Mock endpoints
TODO

### Understand JUnit 4 integration
TODO

### Work with CamelTest APIs
TODO

## Dynamically Route Messages
TODO

### Use conditional routes
Conditional routing in Camel is primarily accomplished by the combination of a Content Based Router and an Expression. In addition to the Content Based Router EIP you should be familiar with Expressions, Predicates, and the Simple Expression Language.

- **Official Resources**
  - Training Course - JB421, Sections 3.1 & 3.2
  - [Official Documentation - Camel Development Guide, Section 8.1](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgRout.html#MsgRout-ContentBased)
- **Community References**
  - [Camel Documentation - Content Based Router](http://camel.apache.org/content-based-router.html)
  - [Camel Documentation - Expressions](http://camel.apache.org/expression.html)
  - [Camel Documentation - Simple Expression Language](http://camel.apache.org/simple.html)
  - [Camel Documentation - Predicates](http://camel.apache.org/predicate.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 2.5.1
  - Book - Enterprise Integration Patterns, Chapter 7

### Use data-driven routes
TODO

## Exception Handling
TODO

### Catch and handle exceptions
TODO

### Work with Use the dead-letter queue
TODO


# Additional recommended reading
The following books may be useful in your exam preperation however they are not required reading and you should be able to pass the exam without reading these books.

 - [Camel in Action](http://www.amazon.com/Camel-Action-Claus-Ibsen/dp/1935182366)
 - [Apache Camel Developers Cookbook](http://www.amazon.com/Developers-Cookbook-Integration-Accessible-Recipes/dp/1782170308/)
 - [Enterprise Integration Patterns](http://www.amazon.com/Enterprise-Integration-Patterns-Designing-Deploying/dp/0321200683/)
