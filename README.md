- 2024-06-03 继续优化后端界面
![image](https://github.com/Cunninger/background-backmanage/assets/113076850/5cc3e6dd-f2eb-4dd2-a345-cbc0e40449aa)

- 2024-06-03 补充AI图片存储和展示
![image](https://github.com/Cunninger/background-backmanage/assets/113076850/94a35310-d357-438b-9ec0-904e2f7c6040)


-  `application.properities`如下,自行配置
```java
spring.application.name=backmanage
spring.redis.database=10
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.password=1234
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=10MB
server.port=7788
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/backmanage?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=1234
mybatis.configuration.mapUnderscoreToCamelCase=true
logging.level.org.springframework=INFO
mybatis.mapper-locations=classpath:/mappers/*.xml
ollama.api.url=
ollama.api.token=
draw.api.url=
draw.api.token=

```

- 2024--6-03 集成Openwebui
![image](https://github.com/Cunninger/background-backmanage/assets/113076850/ad007e50-a891-43fb-af01-4ff6bbb3c634)


- 2024--6-02 群聊
![image](https://github.com/Cunninger/background-backmanage/assets/113076850/2655d302-98ee-4c53-b96d-4508e45e9d11)


- 2024-06-01 聊天室初步
![image](https://github.com/Cunninger/background-backmanage/assets/113076850/0c63205d-e390-481d-b38d-6ce308fab4a0)


- 2024.5.26 简单算法平台查询
![image](https://github.com/Cunninger/background-backmanage/assets/113076850/c1414eca-910e-4118-b5dc-93543c30a57f)
