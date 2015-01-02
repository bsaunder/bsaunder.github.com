---
layout: post
title: "JGroups Binding Errors on EAP"
description: ""
category: 
tags: [jgroups, eap, jboss, clustering]
---
{% include JB/setup %}

Recently a client of mine was running into several errors in their clustered EAP environment. The errors they were getting were from JGroups and were similar to the ones below.

{% highlight console %}
WARN  [org.jgroups.protocols.TP$ProtocolAdapter] (OOB-5,shared=udp) dropping unicast message to wrong destination host2:server2/web; my local_addr is host1:server1/web
WARN [org.jgroups.protocols.UDP] (OOB-8,null) null: no physical address for 0d8a17c3-026c-1be4-3baa-32b66b40acb2, dropping message
WARN [org.jgroups.protocols.pbcast.NAKACK] (OOB-20,null) nodename/web: dropped message 3 from 0d8a17c3-026c-1be4-3baa-32b66b40acb2 (sender not in table [nodename/web]), view=[nodename/web|0] [nodename/web]
{% endhighlight %}

Initially we could not find the cause of the errors as all of the cluster configurations were correct. After some digging we initially found the problem.

<!-- more -->

All of the errors are caused by a binding issue with the IP address. In our configuration we had all of the servers binding to 0.0.0.0 on start up. This works with other JBoss services but not for JGroups. In JGroups the address plus port constitutes the identity of a JGroups node, and when a cluster node is bound to 0.0.0.0, it can't be recognized as a valid address.

The solution is to specify jgroups.bind_addr system property with the concrete network address of the host. Prior to EAP 6, it automatically picks up primary host address and set it to jgroups.bind_addr system property when ‐b 0.0.0.0 is specified. However EAP 6 doesn't do this because it often leads misconfiguration problems on a multihomed host.

You can set the property in Standalone mode by starting your server with the appropriate command line option

{% highlight bash %}
./bin/standalone.sh ...(snip)... ‐Djgroups.bind_addr=192.168.0.100
{% endhighlight %}

or in Domain mode by setting the following <jvm‐options> in host.xml.

{% highlight xml %}
<servers>
  <server group="main‐server‐group" name="server‐one">
    <jvm name="default">
      <jvm‐options>
        <option value="‐Djgroups.bind_addr=192.168.0.100" />
      </jvm‐options>
    </jvm>
  </server>
</servers>
{% endhighlight %}

Even though we had this issue with our UDP cluster, this issue can also occur in TCP based clusters.
