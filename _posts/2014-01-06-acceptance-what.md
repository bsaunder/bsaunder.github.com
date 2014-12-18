---
layout: post
title: "Acceptance What?"
description: ""
category: 
tags: [agile, scrum, acceptance_criteria]
---
{% include JB/setup %}

Recently Arjay Hinek wrote a [post](http://www.goscrumgo.com/2013/11/so-whats-big-deal-about-user-stories.html) about User Stories and in [part II](http://www.goscrumgo.com/2013/11/so-whats-big-deal-about-user-stories_19.html) of his post he talked briefly about Acceptance Criteria. Acceptance Criteria are an important aspect of user stories because the describe the boundaries of the story are used to confirm when the story is complete and working as the Customer and/or Product Owner intended.

<!-- more -->

**Why do we need Acceptance Criteria?**
There are several benefits to including Acceptance Criteria. The main benefit  is that they get the team to think through how a piece of functionality will work from the user's perspective. This helps ensure that the functionality is implemented correct the first time and helps reduce bugs and development time. Some of the other benefits are:

*   Define boundaries of a story
*   Help developers know when to stop adding more features
*   Help the team gain a shared understanding of the story or feature
*   Help the Produce Owner get the answers needed for the feature to provide value
*   Removes ambiguity from the requirements
*   Helps the developers and testers derive appropriate tests

**What are good Acceptance Criteria?** Good Acceptance Criteria are usually written in a simple language that is clear and easy to understand for everyone on the team. The most important aspect of Acceptance Criteria is that they state intent and not a solution. For example "The user can choose an account" is correct because it states the intent. However "The user can select an account from a drop-down" is not correct because it specifies a solution and includes implementation details. Acceptance criteria should also be kept at a high level and avoid low level details. One way to help ensure your criteria are correct would be to make sure the phrasing of the criteria can be applied regardless of the platform the feature/story is implemented on e.g. web, mobile, or desktop.

**Example User Story with Criteria:**

_**As an**_ internet banking customer

_**I want**_ to see a rolling balance for my everyday accounts

_**so that**_ I know the balance of my account after each transaction is applied

**Acceptance Criteria:**

*   The rolling balance is displayed correctly
*   The rolling balance is calculated correctly for each transaction
*   The balance is displayed for every transaction for the full period of time transactions are available
*   The balance is not displayed if a filter has been applied

**What about the Details?**
At this point the biggest question that remains is "Where do the details go?". The details are things like

*   The column heading is &ldquo;Balance&rdquo;
*   The rolling balance format is $99,999,999,999.9
*   We should use a drop-down rather than check-boxes

These kinds of details usually come up in the conversation with the Produce Owner and the Customer at one of the planning meetings or when development of the story starts. These items are typically kept in one of two possible places. The first location is the teams internal documentation. The purpose of team internal documentation is to serve as a reminder for team members. How much of the details need to be written down depends on the team.

The second possible location is the teams Automated Acceptance Tests. Acceptance criteria can be expressed in (almost) plain English for use by the chosen testing framework. This means that tests provide value as documentation, automated acceptance tests and as a feedback loop for developers doing BDD and any team using Automated Testing or Continuous Integration.
