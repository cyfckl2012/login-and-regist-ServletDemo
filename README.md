该项目为简单的注册登录功能,采用mysql数据库，DBUtils链接

DBUtils封装成了工具类DataSourceUtils.java

需要用到的jar包文件如下：
```cmd
c3p0-0.9.1.2.jar
commons-beanutils-1.8.3.jar
commons-dbutils-1.4.jar
commons-logging-1.1.1.jar
mysql-connector-java-5.1.39-bin.jar
```

数据库相关配置在\login and regist ServletDemo\srcc3p0-config.xml文件中进行修改

数据库名为：web15

表名：user

创建表的语句：
```sql
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
)
```
