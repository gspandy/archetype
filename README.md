# 项目原型(archetype)
因为经常需要创建一些项目， 而这些项目结构和配置都很类似，所以就动手做了一个适合自己的项目原型，这个原型我会不断优化和更新

## 项目结构

- biz 业务逻辑层
- common 公共工具
- dao 数据访问层
- model 数据模型层
- remote 调用外部接口
- service 对外接口的定义
- web 展现层

## 主要技术

- Spring
- SpringMVC
- Mybatis
- MySQL
- Redis
- Dubbo
- Log4j2
- Lombok
- Mybatis Generator
- fastjson
- Shire
- autoconfig
- pagehelper
- tk mybatis
- Freemarker
- jQuery
- Bootstrap
- Ace Admin
- html
- css
- js

## 用法

1. 克隆此项目到本地

```
git clone https://github.com/kangyonggan/archetype.git
```

2. 安装

```
mvn clean install
```

3. 使用

```
mvn archetype:generate -DarchetypeGroupId=com.kangyonggan.archetype -DarchetypeArtifactId=archetype -DarchetypeVersion=1.0.1.RELEASE -DarchetypeCatalog=local
```

执行之后会提示你输入新项目的`GroupId`、`ArtifactId`、`Version`、`Package`和最后的确认`Y`

