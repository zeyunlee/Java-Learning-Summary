### Docker基础命令

```//```是附加注释

* 1.安装docker

```
  sudo apt-get update
  sudo apt-get install  docker
  sudo apt-get install  docker.io
  sudo apt-get install  docker-registry
```
  
* 2.安装linux 

```docker pull centos:6``` //6是版本号

* 3.查看所有的镜像，也包括tag在里面 

```docker images```

* 4.使用仓库的Dockerfile文件，创建一个镜像 

```docker build -t IMAGE```

* 5.运行镜像，并且映射主机端口12345 到容器端口 3128（默认）

```docker run -p 12345:3128 IMAGE```  //是镜像ID，下同

* 6.同上，但是此条命令是把镜像运行在后台进程中

```docker run -p 12345:3128 IMAGE```  //-d表示后台运行

* 7.查看正在运行的所有的容器的列表

```docker ps```

* 8.优雅的停止运行指定的容器

```docker stop IMAGE```

* 9.查看所有的容器列表，包括没有在运行的

```docker ps -a```

* 10.强行关闭指定的容器 

```docker kill CONTAINER_ID``` //CONTAINER_ID是容器ID

* 11.从这台机器上移除所有未运行的容器

```docker rm $(docker ps -a -q)```

* 12.显示这台机器上的所有镜像

```docker images -a```

* 13.从本机移除所有的镜像

```docker rmi $(docker images -q)```

* 14.使用Docker credentials 登录到CLI会话

```docker login```

* 15.给镜像打一个tag，为了可以上传到remote的registry

```docker tag <> username/repository:tag```

* 16.上传打完tag的镜像到remote的registry

```docker push username/repository:tag```

* 17.从registry运行指定的镜像

```docker run username/repository:tag```

如果你没有在这些命令中指定 ``` :tag ``` 部分，在你生成和运行镜像时，最新的tag ``` :latest ``` 会被默认使用。 
如果没有指定tag，Docker会使用最新的镜像版本，即latest（所以不指定会坑爹）

* 18.查看镜像的详细信息bcb31f80b037是镜像的id

```docker inspect IMAGE```

* 19.添加squid的证书

```sudo htpasswd -c squid_passwd  dreamylost```

* 20.push文件到docker的某个容器中

```sudo docker cp squid.conf  CONTAINER_ID:/etc/squid```

* 21.进入运行的容器

```sudo docker exec -it CONTAINER_ID /bin/bash``` //可能有镜像的多个实例，需要指定运行的容器ID，而不是镜像ID

* 22.查看docker的端口映射

```docker port  CONTAINER_ID```

* 23.给容器安装vi命令

1. ```apt-get update``` //同步 ```/etc/apt/sources.list``` 和 ```/etc/apt/sources.list.d```中列出的源的索引 
2. ```apt-get install vim``` //安装命令，其他类似（先登录进指定运行的容器，关闭容器则失效）

* 24.查看squid代理的链接访问日志

```/var/log/squid/access.log```

* 25.安装Apache

```apt-get install apache2=2.4.29-1ubuntu4.5```


PS:镜像可以理解是已经打包的开放包，容器是包的一次执行，与运行时相关的一般都使用容器CONTAINER_ID
