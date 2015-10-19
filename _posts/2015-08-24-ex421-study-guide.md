---
layout: post
title: "Red Hat EX421 Study Guide"
description: ""
category:
tags: [camel,fuse,jboss,training,jb421,exam,ex421]
---
{% include JB/setup %}

This guide is intended to serve as a study reference for people planning to take the Red Hat Certificate of Expertise in Camel Development Exam ([EX421](http://www.redhat.com/en/services/training/ex421-red-hat-certificate-expertise-camel-development-exam)). It contains all of the information & resources that you need to properly prepare yourself for the exam.

<!-- more -->

# Audience &amp; Prerequisites
The following audiences may be interested in earning the Red Hat Certificate of Expertise in Camel Development:
- Enterprise Java developers who are familiar with service-oriented architecture (SOA) principles and are responsible for integrating enterprise applications.
- Enterprise SOA architects with some Java development experience and knowledge of enterprise integration.

Exam candidates should:
- Have taken the Camel Development with Red Hat JBoss Fuse (JB421) course or have extensive work experience in enterprise integration using Apache Camel
- Have familiarity working in a Red Hat Enterprise Linux® environment
- Review the Red Hat Certificate of Expertise in Camel Development exam (EX421) objectives


# Exam format
This exam is a performance-based evaluation of the candidate's Camel development skills and knowledge. Candidates perform tasks associated with the development and maintenance of enterprise integration patterns using Red Hat JBoss Fuse. Performance-based testing means that candidates must perform tasks similar to what they perform on the job.

This is a closed exam where candidates do not have access to the internet and candidates are not permitted to bring any papers, books or electronic aids to the exam. Candidates are, however, provided with a local copy of the official [JBoss Fuse documentation](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.0/). Candidates should familiarize themselves with this documentation when preparing for the exam.

This exam consists of 1 section lasting 4 hours.


# Scores and reporting
Official scores for exams come exclusively from Red Hat Certification Central. Red Hat does not authorize examiners or training partners to report results to candidates directly. Scores on the exam are usually reported within 3 U.S. business days.

# Recommended training
Camel Development with Red Hat JBoss Fuse ([JB421](http://www.redhat.com/en/services/training/jb421-camel-development-red-hat-jboss-fuse)) is the recommended training course for preparing for this exam.

# Additional Information
If you would like additional information about the exam and its format, you can check out the [Red Hat FAQ](http://www.innovativeexams.com/exam-faqs/red-hat-faq/) hosted by the exam company.

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
The message body contains data that will be processed by the receiver and/or manipulated by Camel processors.

- **Official Resources**
  - Training Course - JB421, Sections 1.3 & 1.4
  - [Official Documentation - Camel Development Guide, Chapter 43.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Development_Guide/index.html#Processors-MsgContent)
- **Community References**
  - [Camel Documentation - Message](http://camel.apache.org/message.html)
- **Related**
  - [Camel Documentation - Data Formats](http://camel.apache.org/data-format.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 1.3

### Work with the Message Headers
Message headers contain metadata such as sender information and security details in a java.util.Map collection.

- **Official Resources**
  - Training Course - JB421, Sections 1.3 & 1.4
  - [Official Documentation - Camel Development Guide, Chapter 43.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Development_Guide/index.html#Processors-MsgContent)
- **Community References**
  - [Camel Documentation - Message](http://camel.apache.org/message.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 1.3

### Work with the Message Attachments
Attachments contain file-based contents to be transmitted to a receiver.

- **Official Resources**
  - Training Course - JB421, Sections 1.3 & 1.4
  - [Official Documentation - Camel Development Guide, Chapter 43.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Development_Guide/index.html#Processors-MsgContent)
- **Community References**
  - [Camel Documentation - Message](http://camel.apache.org/message.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 1.3

### Work with the In/out Messages
The In/Out messages are the messages on the Camel Exchange and are manipulated by the Camel processors.

- **Official Resources**
  - Training Course - JB421, Sections 1.3 & 1.4
  - [Official Documentation - Camel Development Guide, Chapter 43.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Development_Guide/index.html#Processors-MsgContent)
- **Community References**
  - [Camel Documentation - Message](http://camel.apache.org/message.html)
  - [Camel Documentation - Using getIn or getOut](http://camel.apache.org/using-getin-or-getout-methods-on-exchange.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 1.3

## Consume and produce files

### Work with CSV files
CSV File operations in Camel are performed using Bindy.

- **Official Resources**
  - Training Course - JB421, Sections 4.7 & 4.8
  - [Official Documentation - Camel Component Guide, Chapter 43](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-File2)
- **Community References**
  - [Camel Documentation - Bindy](http://camel.apache.org/bindy.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 3.4

### Work with XML file

- **Official Resources**
  - Training Course - JB421, Sections 5.1 & 5.2
  - [Official Documentation - Camel Component Guide, Chapter 43](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-File2)
- **Community References**
  - [Camel Documentation - XSLT](http://camel.apache.org/xslt.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 3.3

## Work with databases and Camel JPA

- **Official Resources**
  - Training Course- JB421, Sections 7.8 & 7.9
  - [Official Documentation - Camel Component Guide, Chapter 76](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-JPA)
- **Community References**
  - [Camel Documentation - JPA](http://camel.apache.org/jpa.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 7.6

## Understand transactional routes

- **Official Resources**
  - Training Course- JB421, Sections 11.3 & 11.4
  - [Official Documentation - Camel Development Guide, Chapter 10.9](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgEnd-Transactional.html)
- **Community References**
  - [Camel Documentation - Transactional Client](http://camel.apache.org/transactional-client.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 9

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
The wire tap pattern enables you to route a copy of the message to a separate tap location, while the original message is forwarded to the ultimate destination.

- **Official Resources**
  - Training Course - JB421, Sections 3.5 & 3.6
  - [Official Documentation - Camel Development Guide, Chapter 11.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/WireTap.html)
- **Community References**
  - [Camel Documentation - Wire Tap](http://camel.apache.org/wire-tap.html)
- **Additional Reading**
  - Book - Enterprise Integration Patterns, Chapter 11

### Work with Splitter EIP
A splitter is a type of router that splits an incoming message into a series of outgoing messages. Each of the outgoing messages contains a piece of the original message.

- **Official Resources**
  - Training Course - JB421, Sections 3.3 & 3.4
  - [Official Documentation - Camel Development Guide, Chapter 8.4](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgRout-Splitter.html)
- **Community References**
  - [Camel Documentation - Splitter](http://camel.apache.org/splitter.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 8.3
  - Book - Enterprise Integration Patterns, Chapter 7

### Work with Aggregator EIP
The aggregator pattern enables you to combine a batch of related messages into a single message.

- **Official Resources**
  - Training Course - JB421, Sections 3.9 & 3.10
  - [Official Documentation - Camel Development Guide, Chapter 8.5](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgRout-Aggregator.html)
- **Community References**
  - [Camel Documentation - Aggregator](http://camel.apache.org/aggregator.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 8.2
  - Book - Enterprise Integration Patterns, Chapter 7

### Work with RecipientList EIP
A recipient list is a type of router that sends each incoming message to multiple different destinations. In addition, a recipient list typically requires that the list of recipients be calculated at run time.

- **Official Resources**
  - Training Course - JB421, Sections 3.7 & 3.8
  - [Official Documentation - Camel Development Guide, Chapter 8.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgRout-RecipientList.html)
- **Community References**
  - [Camel Documentation - Recipient List](http://camel.apache.org/recipient-list.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 8.4
  - Book - Enterprise Integration Patterns, Chapter 7

## Understand & use common Camel components

- **Official Resources**
  - Training Course - JB421, Sections 7.1
  - [Official Documentation - Camel Development Guide](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 7

### Work with File2 component

- **Official Resources**
  - Training Course - JB421, Sections 7.2 & 7.3
  - [Official Documentation - Camel Development Guide, Chapter 42](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-File2)
- **Community References**
  - [Camel Documentation - File2](http://camel.apache.org/file2.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 7.2

### Work with FTP component

- **Official Resources**
  - Training Course - JB421, Sections 7.2 & 7.3
  - [Official Documentation - Camel Development Guide, Chapter 47](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-FTP2)
- **Community References**
  - [Camel Documentation - FTP](http://camel.apache.org/ftp.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 7.2

### Work with JPA2 component

- **Official Resources**
  - Training Course - JB421, Sections 7.8 & 7.9
  - [Official Documentation - Camel Development Guide, Chapter 76](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-JPA)
- **Community References**
  - [Camel Documentation - JPA](http://camel.apache.org/jpa.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 7.6

### Work with JMS component

- **Official Resources**
  - Training Course - JB421, Sections 8.3 & 8.4
  - [Official Documentation - Camel Development Guide, Chapter 74](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-JMS)
- **Community References**
  - [Camel Documentation - JMS](http://camel.apache.org/jms.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 7.3

### Work with Direct component

- **Official Resources**
  - Training Course - JB421, Sections 7.4 & 7.5
  - [Official Documentation - Camel Development Guide, Chapter 32](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html-single/Apache_Camel_Component_Reference/index.html#IDU-Direct)
- **Community References**
  - [Camel Documentation - Direct](http://camel.apache.org/direct.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 7.7

## Testing Routes

### Work with Mock endpoints

- **Official Resources**
  - Training Course - JB421, Sections 9.5 & 9.6
- **Community References**
  - [Camel Documentation - Mock](http://camel.apache.org/mock.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 6.2

### Understand JUnit 4 integration

- **Official Resources**
  - Training Course - JB421, Sections 9.1 & 9.2
- **Community References**
  - [Camel Documentation - Testing](http://camel.apache.org/testing.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 6.1

### Work with CamelTest APIs

- **Official Resources**
  - Training Course- JB421, Sections 9.3 & 9.4
- **Community References**
  - [Camel Documentation - Camel Test](http://camel.apache.org/camel-test.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 6.1

## Dynamically Route Messages

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

## Exception Handling
Apache Camel provides several different mechanisms, which let you handle exceptions at different levels of granularity: you can handle exceptions within a route using doTry, doCatch, and doFinally; or you can specify what action to take for each exception type and apply this rule to all routes in a RouteBuilder using onException; or you can specify what action to take for all exception types and apply this rule to all routes in a RouteBuilder using errorHandler.

### Catch and handle exceptions

- **Official Resources**
  - Training Course - JB421, Sections 10.8 & 10.9
  - [Official Documentation - Camel Development Guide, Chapter 2.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/BasicPrinciples-ExceptionHandling.html)
- **Community References**
  - [Camel Documentation - Error Handling](http://camel.apache.org/error-handling-in-camel.html)
- **Additional Reading**
  - Book - Camel in Action v1, Chapter 5

### Work with Use the dead-letter queue

- **Official Resources**
  - Training Course - JB421, Sections 10.4 & 10.5
  - [Official Documentation - Camel Development Guide, Chapter 6.3](https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Development_Guide/MsgCh-DeadLetter.html)
- **Community References**
  - [Camel Documentation - Dead Letter Channel](http://camel.apache.org/dead-letter-channel.html)
- **Additional Reading**
  - Book - Camel in Action v1, Section 5.2

# Additional recommended reading
The following books may be useful in your exam preperation however they are not required reading and you should be able to pass the exam without reading these books.

 - [Camel in Action](http://www.amazon.com/Camel-Action-Claus-Ibsen/dp/1935182366)
 - [Apache Camel Developers Cookbook](http://www.amazon.com/Developers-Cookbook-Integration-Accessible-Recipes/dp/1782170308/)
 - [Enterprise Integration Patterns](http://www.amazon.com/Enterprise-Integration-Patterns-Designing-Deploying/dp/0321200683/)
