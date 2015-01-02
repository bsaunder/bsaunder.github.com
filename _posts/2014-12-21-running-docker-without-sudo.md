---
layout: post
title: "Running Docker without Sudo"
description: ""
category: 
tags: [docker, fedora]
---
{% include JB/setup %}

Recently I have been learning Docker and have been creating some containers for our various enterprise middleware products. One of the biggest issues that I have encountered on Fedora is that after I installed Docker I get a permission denied error when I run the client with out sudo. The official documentation states that it is not neccessary to be root after Docker 1.0, but in my case, that was not true when I installed Docker 1.4 on my Fedora 20 installation. 

<!-- more -->
I repeatedly got the following error.

{% highlight console %}
Get http:///var/run/docker.sock/v1.15/containers/json: dial unix /var/run/docker.sock: permission denied
{% endhighlight %}

I found that in order for me to run Docker without sudo, I needed to add my user to the docker group. 

{% highlight bash %}
# Add the docker group if it doesn't already exist.
sudo groupadd docker
 
# Add the connected user "${USERNAME}" to the docker group.
sudo gpasswd -a ${USERNAME} docker
 
# Restart the docker daemon.
sudo service docker restart
{% endhighlight %}

## What if it still doesnt work?

There are several bugs for this issue reported on the Fedora bugzilla. If adding your user to the group doesnt solve the problem, try the obvious solution of logging out and back in. If that still does not resolve the issue, you can try these solutions.

### Update your System
Patches have been pushed to the stable repositories for Fedora 19 and 20 that resolve this issue. You can try simply updating your systemd package, but I recommend updating your entire system. In my case that resolved the problem.

### Change the Socket Group to docker
If simply being in the group does not resolve your problem, the first solution you can try is to change the group owner of the socket file to the docker group. 

{% highlight bash %}
chown root:docker /var/run/docker.sock
{% endhighlight %}

### Disable Socket Activation
The problem is ultimatley caused by the way the sockets are created and who owns them. This workaround will disable socket activation for docker, which means that systemd is no longer responsible for creating the docker socket.  The docker daemon itself will create the socket, and will create it owned by the "docker" group

{% highlight bash %}
systemctl stop docker.socket
systemctl disable docker.socket
systemctl disable docker
 
cat > /etc/systemd/system/docker.service <<EOF
[Unit]
Description=Docker Application Container Engine
Documentation=http://docs.docker.io
After=network.target
 
[Service]
Type=notify
EnvironmentFile=-/etc/sysconfig/docker
ExecStart=/usr/bin/docker -d --selinux-enabled --dns 172.17.42.1
Restart=on-failure
LimitNOFILE=1048576
LimitNPROC=1048576
 
[Install]
WantedBy=multi-user.target
EOF
 
systemctl enable docker
systemctl start docker
{% endhighlight %}


