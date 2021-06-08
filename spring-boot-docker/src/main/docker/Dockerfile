# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD spring-boot-docker-1.0.0.jar /spring-boot-docker-file.jar
# 运行过程中创建一个spring-boot-docker-file.jar文件
RUN bash -c 'touch /spring-boot-docker-file.jar'
# 声明服务运行在8081端口
EXPOSE 8081
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar", "/spring-boot-docker-file.jar"]
# 指定维护者的名字
MAINTAINER JourWon

# -t 表示指定镜像仓库名称/镜像名称:镜像标签 .表示使用当前目录下的Dockerfile
#docker build -t spring-boot/spring-boot-docker-file:1.0.0 .