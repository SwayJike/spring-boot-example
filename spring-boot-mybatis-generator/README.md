# 工程简介
Spring Boot集成MyBatis逆向工程

# 使用简介
1. 目前发现直接运行main方法不会生成mapper接口和实体类
2. 已经在pom.xml文件添加了MyBatis逆向工程的插件，可以依次运行maven命令clean和package生成逆向工程
3. 逆向工程的时间类型是Date，不是jdk8的LocalDateTime
4. mapper.xml文件含有jdbcType类型
