# Blog


2020年12月15日星期二

 

目    录
目    录	I
第一章：实训概述及任务分解	1
1.1 实训概述	1
1.2 任务分解	1
第二章：构建项目	2
2.1 环境准备	2
2.2 系统功能	2
2.3 系统架构设计	2
第三章：数据库分析与设计	3
3.1 数据库分析	3
3.2 类模型图	3
3.3 SQL语句	4
第四章：功能设计与实现	5
4.1 功能模块	5
4.1.1 模块设计分析	5
4.1.2 系统功能结构图	6
4.2 框架搭建	6
4.2.1 创建项目	6
4.2.2 编写配置文件	12
4.2.3 引入相关文件资源	19
4.3 博主文章管理模块	19
4.3.1 创建持久化类	19
4.3.2 实现Mapper	20
4.3.3 实现服务类Service	34
4.3.4 实现控制层Controller	41
4.3.5 实现页面功能	41
4.3.6 页面功能展示	46
4.4 博客评论管理模块	48
4.4.1 创建持久化类	48
4.4.2 实现Mapper层接口	48
4.4.3 实现服务类Service	48
4.4.4 实现控制层Controller	49
4.4.5 实现页面功能	49
4.3.6 页面功能展示	49
心得体会	51
参考文献	52
致谢	53

 

第一章：实训概述及任务分解
1.1 实训概述
该系统将使用本学期学习的Spring+SpringMVC+Mybatis框架知识来实现一个个人博客管理系统。该系统在开发过程中整合三大框架的基础上实现了系统后台的登录、文章发布、评论、菜单管理等功能。
1.2 任务分解
1.首先是系统架构和文件组织结构的设计，然后是数据库分析和设计，系统环境的搭建。
2.系统功能模块的设计，主要模块有类目、标签、文章管理模块、页面链接公告评论管理模块、基本信息管理模块、前台博客展示模块以及前端页面设计、拦截器的设计和功能编码实现。
表1-1 项目成员介绍和任务分配
姓名	学号	任务	备注
聂志峰	20170210440114	后端主要功能设计与实现	数据的增删查改
王一凡	20170210440120	前后端交互接口实现	前端数据提取后端数据
吴启皇	20170210440104	数据库设计与数据库层实现	个人博客中数据表单的实现
江思源	20170210440111	前端页面模块设计与实现	前台页面的设计

 

第二章：构建项目
2.1 环境准备
–jdk 8;
–Tomcat 8.0：maven 3.3以上版本；Apache Maven 3.3.9
–IntelliJ IDEA 2020 x64
–Spring;
–SpringMVC;
–Mybatis;
–Bootstrap;
–jQuery;
本系统后台使用SSM三大框架实现，前台页面使用当前主流的Bootstrap和jQuery框架完成新闻展示。
2.2 系统功能
系统中主要实现了几大功能模块：后端登录以及文章博客评论回复管理和其他基础功能管理、前端留言功能、模块化前台展示等。
2.3 系统架构设计
本系统根据功能的不同，项目结构可以划分为以下几个层次：
持久层对象：由若干持久化类(实体类)组成
数据访问层：由若干Mapper接口和Mybatis映射文件组成。接口的名称统一以Mapper结尾，且Mybatis的映射文件名称要与接口的名称相同。
业务逻辑层：该层由若干Service接口和实现类组成。在本系统中，业务逻辑层的接口统一使用Service结尾，其实现类名称统一在接口名后加Impl。该层主要用于实现系统的业务逻辑。
Web表现层：该层主要包括SpringMVC中的Controller类和JSP页面。Controller类主要负责处理和拦截用户请求，并调用业务逻辑层中的相应组件的业务逻辑方法来处理用户请求，然后相应的结果返回给JSP页面。
 

第三章：数据库分析与设计
3.1 数据库分析
根据系统的功能需求，本系统的设计与实现中主要涉及用户实体、文章类别实体和文章实体，评论实体。其中文章类别实体与文章实体之间构成一对多的关联关系，用户实体和文章实体之间构成一对多的关联关系。
与对象实体相适应，本系统中涉及用户表、评论表和文章表，文章表通过comment_id (类别ID)字段与评论表构成关联关系，文章表还通过article_user_id( 用户ID)字段与用户表构成关联关系。这4张表的表结构如图所示。
3.2 类模型图

user表                 article表          
comment表         					    	article_category_refe表
3.3 SQL语句
语句和上述4个表的建表及基础数据插入的SQL语句如下。

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `article_id` int NOT NULL AUTO_INCREMENT,
  `article_user_id` int unsigned DEFAULT NULL,
  `article_title` varchar(255) DEFAULT NULL,
  `article_content` mediumtext,
  `article_view_count` int DEFAULT '0',
  `article_comment_count` int DEFAULT '0',
  `article_like_count` int DEFAULT '0',
  `article_is_comment` int unsigned DEFAULT NULL,
  `article_status` int unsigned DEFAULT '1',
  `article_order` int unsigned DEFAULT NULL,
  `article_update_time` datetime DEFAULT NULL,
  `article_create_time` datetime DEFAULT NULL,
  `article_summary` text,
  PRIMARY KEY (`article_id`)
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int unsigned NOT NULL AUTO_INCREMENT,
  `comment_pid` int unsigned DEFAULT '0',
  `comment_pname` varchar(255) DEFAULT NULL,
  `comment_article_id` int unsigned DEFAULT NULL,
  `comment_author_name` varchar(50) DEFAULT NULL,
  `comment_author_email` varchar(50) DEFAULT NULL,
  `comment_author_url` varchar(50) DEFAULT NULL,
  `comment_author_avatar` varchar(100) DEFAULT NULL,
  `comment_content` varchar(1000) DEFAULT NULL,
  `comment_agent` varchar(200) DEFAULT NULL,
  `comment_ip` varchar(50) DEFAULT NULL,
  `comment_create_time` datetime DEFAULT NULL,
  `comment_role` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT '',
  `user_pass` varchar(255) NOT NULL DEFAULT '',
  `user_nickname` varchar(255) NOT NULL DEFAULT '',
  `user_email` varchar(100) DEFAULT '',
  `user_url` varchar(100) DEFAULT '',
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_last_login_ip` varchar(255) DEFAULT NULL,
  `user_register_time` datetime DEFAULT NULL,
  `user_last_login_time` datetime DEFAULT NULL,
  `user_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

第四章：功能设计与实现
4.1 功能模块
4.1.1 模块设计分析
本系统的设计与实现分模块进行，主要涉及：
1.开发环境和框架搭建
2.用户管理模块
3.文章管理模块
4.评论管理模块
5.前台博客展示模块
4.1.2 系统功能结构图
系统中包含的几大功能模块：用户管理、文章类别管理、文章发布管理和前台展示等模块，如下图所示
 
图4-1 系统功能结构
4.2 框架搭建
系统的开发环境和框架搭建涉及各类文件的创建、引入和编写:包文件(内含各类接口和公共配置文件、页面文件以及相关的JAR包文件、资源文件等。下面对系统开发环境和框架的搭建进行解释。
4.2.1 创建项目
在Idea中创建一个名称为forestblo的Maven项目，在pom.xml内写入所需要的依赖。
●pom.xml详细内容
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.liuyanzhao</groupId>
    <artifactId>ForestBlog</artifactId>
    <packaging>war</packaging>
    <version>1.0.0-SNAPSHOT</version>
    <name>ForestBlog Maven Webapp</name>
    <url>http://maven.apache.org</url>


    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring.version>4.3.19.RELEASE</spring.version>
    </properties>


    <dependencies>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.22</version>
        </dependency>

        <!-- 添加sevlet支持 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>

        <!-- 添加jsp支持 -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.1</version>
        </dependency>
        <!-- 添加jstl支持 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.1.2</version>
        </dependency>
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>
        <!-- 添加spring支持 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--spring test支持-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--spring mvc支持-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!--spring 事务管理支持-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--spring jdbc操作支持-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--spring aop编程支持-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- 添加mybatis支持 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
        </dependency>
        <!--  jdbc驱动包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.11</version>
        </dependency>
        <!-- 添加阿里巴巴连接池Druid支持 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.16</version>
        </dependency>
        <!-- 添加log4j日志 -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.21</version>
        </dependency>
        <!-- apache共公包 -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
        <!-- 添加junit支持 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>


        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.3</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>

        <!-- jackson -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.5.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.5.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.5.0</version>
        </dependency>

        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20170516</version>
        </dependency>
        <dependency>
            <groupId>com.googlecode.rapid-framework</groupId>
            <artifactId>rapid-core</artifactId>
            <version>4.0.5</version>
        </dependency>

        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>26.0-jre</version>
        </dependency>

        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>4.2.1</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.72</version>
        </dependency>

        <!-- hutool工具包 -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.1.13</version>
        </dependency>

    </dependencies>

    <build>
        <resources>
            <!-- 编译之后包含xml -->
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <!-- 编译之后包含xml和properties -->
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>


</project>
4.2.2 编写配置文件
在项目resources目录下分别创建数据库常量配置文件、Spring 配置文件、MyBatis 配置文件、SpringMVC配置文件、log4j 配置文件以及资源配置文件。
db. properties
#MySQL
mysql.driver=com.mysql.cj.jdbc.Driver
mysql.url=jdbc:mysql://127.0.0.1:3306/forest_blog?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong
mysql.username=root
mysql.password=root
springMVC.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-4.2.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop-4.2.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd">



    <context:component-scan base-package="com.liuyanzhao.ssm.blog" />

    <!-- 一个配置节解决映射器和适配器的配置注解配置 -->
    <mvc:annotation-driven></mvc:annotation-driven>

    <!-- 配置视图解析器
        进行jsp解析，默认使用jstl标签，classpath下得有jstl的包
    -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" >
        <!--配置前缀和后缀，也可以不指定-->
        <property name="prefix" value="/WEB-INF/view/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--&lt;!&ndash;文件上传&ndash;&gt;-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--设置上传最大尺寸为50MB-->
        <property name="maxUploadSizePerFile" value="52428800"/>
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="resolveLazily" value="true"/>
    </bean>



    <!-- 静态资源映射 -->
    <mvc:resources mapping="/css/**" location="/resource/assets/css/"></mvc:resources>
    <mvc:resources mapping="/js/**" location="/resource/assets/js/"></mvc:resources>
    <mvc:resources mapping="/img/**" location="/resource/assets/img/"></mvc:resources>
    <mvc:resources mapping="/plugin/**" location="/resource/assets/plugin/"></mvc:resources>


    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="com.liuyanzhao.ssm.blog.interceptor.HomeResourceInterceptor"/>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/admin"/>
            <bean class="com.liuyanzhao.ssm.blog.interceptor.SecurityInterceptor"/>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/admin/**"/>
            <bean class="com.liuyanzhao.ssm.blog.interceptor.SecurityInterceptor"/>
        </mvc:interceptor>

    </mvc:interceptors>
</beans>
applicationContext.xml文件包含了事务传播行为以及切面的配置。在事务的传播行为中，只有查询方法的事务为只读，添加、修改和删除的操作都纳入了事务管理。
mybatis-config.xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!-- 全局配置 -->
    <settings>
        <!--允许 JDBC 支持自动生成主键-->
        <setting name="useGeneratedKeys" value="false"/>
        <!--是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典
            Java 属性名 aColumn 的类似映射。 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <!--<property name="dialect" value="mysql"/>-->
            <property name="offsetAsPageNum" value="false"/>
            <property name="rowBoundsWithCount" value="false"/>
            <property name="pageSizeZero" value="true"/>
            <property name="reasonable" value="true"/>
            <property name="supportMethodsArguments" value="false"/>
            <property name="returnPageInfo" value="none"/>
        </plugin>
    </plugins>

</configuration>
上述代码除了配置需要扫描的包、注解驱动和视图解析器外，还增加了加载属性文件和访问静态资源的配置。
除以上配置文件外，还需要在项目的/WebContentWEB-INF目录下编写web.xml文件。
web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee" xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
         http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
  <display-name>ForestBlog</display-name>

  <!--post乱码过滤器-->
  <!-- 配置springMVC编码过滤器 -->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!-- 设置过滤器中的属性值 -->
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <!-- 启动过滤器 -->
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <!-- 过滤所有请求 -->
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!--加载spring容器-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
      classpath:spring/spring-*.xml,
    </param-value>
  </context-param>

  <!--配置监听器，来加载spring容器-->
   <listener>
     <listener-class>
       org.springframework.web.context.ContextLoaderListener
     </listener-class>
   </listener>


  <!-- 以下3项参数与log4j的配置相关 -->
  <context-param>
    <param-name>log4jConfigLocation</param-name>
    <param-value>classpath:log4j.properties</param-value>
  </context-param>

  <context-param>
    <param-name>log4jRefreshInterval</param-name>
    <param-value>60000</param-value>
  </context-param>
  <listener>
    <listener-class>
      org.springframework.web.util.Log4jConfigListener
    </listener-class>
  </listener>

  <!--springmvc前端控制器 -->
  <servlet>
    <servlet-name>ForestBlog</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring/spring-mvc.xml</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>ForestBlog</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

  <!--随时获得request-->
  <listener>
    <listener-class>
      org.springframework.web.context.request.RequestContextListener
    </listener-class>
  </listener>

  <!-- 4、使用Rest风格的URI，将页面普通的post请求转为指定的delete或者put请求 -->
  <filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <filter>
    <filter-name>HttpPutFormContentFilter</filter-name>
    <filter-class>org.springframework.web.filter.HttpPutFormContentFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>HttpPutFormContentFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <filter>
    <filter-name>DruidWebStatFilter</filter-name>
    <filter-class>com.alibaba.druid.support.http.WebStatFilter</filter-class>
    <init-param>
      <param-name>exclusions</param-name>
      <param-value>*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>DruidWebStatFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>


  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  
    <!--去掉jsp页面的空白-->
      <jsp-config>
        <jsp-property-group>
          <url-pattern>*.jsp</url-pattern>
          <trim-directive-whitespaces>true</trim-directive-whitespaces>
        </jsp-property-group>
      </jsp-config>


</web-app>
在web.xml文件中配置了Spring的监听器、编码过滤器和SpringMVC的前端控制器等信息。
4.2.3 引入相关文件资源
创建项目相关目录(包)和文件，并引入相关文件资源，创建项目相关的目录(包)及文件，如相关类和接口的包、JSP文件对应的文件夹、JSP文件等，并引入项目开发需要的相关文件资源，如CSS样式文件、images图片文件、JS文件、标签文件等，为项目开发做好准备工作。在后续项目开发过程中，根据需要可以创建其他文件或引入其他资源文件。

4.3 博主文章管理模块
此模块涉及用户登录功能，文章的增删改查功能
4.3.1 创建持久化类
用户管理模块持久化类
Article.java
package com.liuyanzhao.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 王一凡
 */
@Data
public class Article implements Serializable{

    private static final long serialVersionUID = 5207865247400761539L;

    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Date articleCreateTime;

    private Date articleUpdateTime;

    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private String articleContent;

    private String articleSummary;

    private User user;

    private List<Tag> tagList;

    private List<Category> categoryList;

}
4.3.2 实现Mapper
首先创建Mapper接口，包中创建接口，并在接口中编写增删改查等方法。
ArticleMapper.java
package com.liuyanzhao.ssm.blog.mapper;

import com.liuyanzhao.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 文章Mapper
 *
 * @author 王一凡
 */
@Mapper
public interface ArticleMapper {

    /**
     * 根据ID删除
     *
     * @param articleId 文章ID
     * @return 影响函数
     */
    Integer deleteById(Integer articleId);

    /**
     * 添加文章
     *
     * @param article 文章
     * @return 文章
     */
    Integer insert(Article article);

    /**
     * 更新文章
     *
     * @param article 文章
     * @return 影响行数
     */
    Integer update(Article article);

    /**
     * 获得所有的文章
     *
     * @param criteria 查询条件
     * @return 文章列表
     */
    List<Article> findAll(HashMap<String, Object> criteria);

    /**
     * 文章归档
     * @return
     */
    List<Article> listAllNotWithContent();

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(@Param(value = "status") Integer status);

    /**
     * 获得留言总数
     *
     * @return 数量
     */
    Integer countArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 文章数量
     */
    Integer countArticleView();

    /**
     * 获得所有文章(文章归档)
     *
     * @return 文章列表
     */
    List<Article> listArticle();

    /**
     * 根据id查询用户信息
     *
     * @param status 状态
     * @param id 文章ID
     * @return 文章
     */
    Article getArticleByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * 分页操作
     *
     * @param status    状态
     * @param pageIndex 从第几页开始
     * @param pageSize  数量
     * @return 文章列表
     */
    @Deprecated
    List<Article> pageArticle(@Param(value = "status") Integer status,
                                    @Param(value = "pageIndex") Integer pageIndex,
                                    @Param(value = "pageSize") Integer pageSize);


    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(@Param(value = "id") Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(@Param(value = "id") Integer id);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    /**
     * 热评文章
     *
     * @param limit  查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);



    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(@Param(value = "articleId") Integer articleId);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();

    /**
     * 用户的文章数
     *
     * @param id 用户ID
     * @return 数量
     */
    Integer countArticleByUser(@Param(value = "id") Integer id);

    /**
     * 根据分类ID
     *
     * @param categoryId 分类ID
     * @param limit      查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                          @Param("limit") Integer limit);

    /**
     * 根据分类ID
     *
     * @param categoryIds 分类ID集合
     * @param limit       查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByLimit(Integer limit);

    /**
     * 批量删除文章
     *
     * @param ids 文章Id列表
     * @return 影响行数
     */
    Integer deleteBatch(@Param("ids") List<Integer> ids);
}
第二步创建映射文件，在包中创建Mybatis映射文件ArticleMapper.xml，并在映射文件下编写增删改查等方法的执行语句，如以下文件所示。
ArticleMapper.xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.liuyanzhao.ssm.blog.mapper.ArticleMapper">
    <resultMap id="BaseResultMap" type="com.liuyanzhao.ssm.blog.entity.Article">
        <id column="article_id" property="articleId" jdbcType="INTEGER"/>
        <result column="article_user_id" property="articleUserId" jdbcType="INTEGER"/>
        <result column="article_title" property="articleTitle" jdbcType="VARCHAR"/>
        <result column="article_content" property="articleContent" jdbcType="LONGVARCHAR"/>
        <result column="article_summary" property="articleSummary" jdbcType="VARCHAR"/>
        <result column="article_view_count" property="articleViewCount" jdbcType="INTEGER"/>
        <result column="article_comment_count" property="articleCommentCount" jdbcType="INTEGER"/>
        <result column="article_like_count" property="articleLikeCount" jdbcType="INTEGER"/>
        <result column="article_is_comment" property="articleIsComment" jdbcType="INTEGER"/>
        <result column="article_order" property="articleOrder" jdbcType="INTEGER"/>
        <result column="article_create_time" property="articleCreateTime" jdbcType="TIMESTAMP"/>
        <result column="article_update_time" property="articleUpdateTime" jdbcType="TIMESTAMP"/>
        <result column="article_status" property="articleStatus" jdbcType="INTEGER"/>
    </resultMap>

    <sql id="tb">article</sql>

    <sql id="Base_Column_List">
    article_id, article_user_id, article_title, article_content,article_summary, article_view_count, article_comment_count, article_like_count, article_create_time,
    article_update_time, article_is_comment, article_status, article_order
    </sql>


    <select id="listAllNotWithContent" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        article_id, article_user_id, article_title, article_create_time
        FROM
        <include refid="tb"/>
        WHERE article_status = 1
        ORDER BY article_id DESC
    </select>

    <select id="findAll" resultMap="BaseResultMap">
        SELECT
        article.*
        FROM
        article
        <where>
            <if test="status != null">
                article.article_status = #{status} AND
            </if>
            <if test="keywords != null">
                article.article_title LIKE concat(concat('%',#{keywords}),'%') AND
            </if>
            <if test="userId != null">
                article.article_user_id = #{userId} AND
            </if>
            <if test="categoryId != null">
                article.article_id IN (
                SELECT article_category_ref.article_id FROM article_category_ref
                WHERE article_category_ref.category_id = #{categoryId}
                ) AND
            </if>
            <if test="tagId != null">
                article.article_id IN (
                SELECT article_tag_ref.article_id FROM article_tag_ref
                WHERE article_tag_ref.tag_id = #{tagId}
                ) AND
            </if>
            1 = 1
        </where>
        ORDER BY `article`.`article_order` DESC, `article`.`article_id` DESC
    </select>

    <delete id="deleteById" parameterType="java.lang.Integer">
        delete from
        <include refid="tb"/>
        where article_id = #{articleId,jdbcType=INTEGER}
    </delete>

    <delete id="deleteBatch" parameterType="list">
        DELETE FROM
        <include refid="tb"/>
        <if test="ids != null">
            WHERE article_id IN
            <foreach collection="ids" open="(" close=")" separator="," item="id">
                #{id}
            </foreach>
        </if>
    </delete>

    <insert id="insert" parameterType="com.liuyanzhao.ssm.blog.entity.Article" useGeneratedKeys="true"
            keyProperty="articleId">
        insert into
        <include refid="tb"/>
        (article_user_id, article_title,
        article_view_count, article_comment_count,
        article_like_count, article_create_time, article_update_time,
        article_is_comment, article_status, article_order,
        article_content, article_summary)
        values (#{articleUserId,jdbcType=INTEGER}, #{articleTitle,jdbcType=VARCHAR},
        #{articleViewCount,jdbcType=INTEGER},
        #{articleCommentCount,jdbcType=INTEGER},
        #{articleLikeCount,jdbcType=INTEGER}, #{articleCreateTime,jdbcType=TIMESTAMP},
        #{articleUpdateTime,jdbcType=TIMESTAMP},
        #{articleIsComment,jdbcType=INTEGER}, #{articleStatus,jdbcType=INTEGER}, #{articleOrder,jdbcType=INTEGER},
        #{articleContent,jdbcType=LONGVARCHAR}, #{articleSummary,jdbcType=VARCHAR})
    </insert>


    <update id="update" parameterType="com.liuyanzhao.ssm.blog.entity.Article">
        update
        <include refid="tb"/>
        <set>
            <if test="articleUserId != null">article_user_id = #{articleUserId,jdbcType=INTEGER},</if>
            <if test="articleTitle != null">article_title = #{articleTitle,jdbcType=VARCHAR},</if>
            <if test="articleViewCount != null">article_view_count = #{articleViewCount,jdbcType=INTEGER},</if>
            <if test="articleCommentCount != null">article_comment_count = #{articleCommentCount,jdbcType=INTEGER},</if>
            <if test="articleLikeCount != null">article_like_count = #{articleLikeCount,jdbcType=INTEGER},</if>
            <if test="articleCreateTime != null">article_create_time = #{articleCreateTime,jdbcType=TIMESTAMP},</if>
            <if test="articleUpdateTime != null">article_update_time = #{articleUpdateTime,jdbcType=TIMESTAMP},</if>
            <if test="articleIsComment != null">article_is_comment = #{articleIsComment,jdbcType=INTEGER},</if>
            <if test="articleStatus != null">article_status = #{articleStatus,jdbcType=INTEGER},</if>
            <if test="articleOrder != null">article_order = #{articleOrder,jdbcType=INTEGER},</if>
            <if test="articleContent != null">article_content = #{articleContent,jdbcType=LONGVARCHAR},</if>
            <if test="articleSummary != null">article_summary = #{articleSummary,jdbcType=VARCHAR},</if>
        </set>
        where article_id = #{articleId,jdbcType=INTEGER}
    </update>

    <!--获取文章总数-->
    <select id="countArticle" resultType="Integer">
        SELECT COUNT(*) FROM
        <include refid="tb"/>
        WHERE article_status = 1
    </select>

    <!--获得文章留言总数-->
    <select id="countArticleComment" resultType="Integer">
        SELECT SUM(article_comment_count)
        FROM
        <include refid="tb"/>
        WHERE article_status = 1
    </select>


    <!--统计文章总访问量-->
    <select id="countArticleView" resultType="Integer">
        SELECT SUM(article_view_count) FROM
        <include refid="tb"/>
        WHERE article_status = 1
    </select>

    <!--获得所有文章-->
    <select id="listArticle" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        WHERE article_status = 1
        ORDER BY article_status ASC, article_order DESC, article_id DESC
    </select>

    <select id="getArticleByStatusAndId" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        <where>
            <if test="status != null">
                article_status = #{status} AND
            </if>
            article_id = #{id}
        </where>
    </select>

    <!--通过分页查询文章-->
    <select id="pageArticle" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        <where>
            <if test="status!=null">
                article_status=#{status}
            </if>
        </where>
        ORDER BY article_status ASC, article_order DESC, article_id DESC
        limit #{pageIndex},#{pageSize}
    </select>

    <!--获得访问量较多的文章-->
    <select id="listArticleByViewCount" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        WHERE article_status = 1
        ORDER BY article_view_count DESC,article_order DESC, article_id DESC
        limit #{limit}

    </select>


    <!--获得下一篇的文章-->
    <select id="getAfterArticle" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        <where>
            article_id > #{id} AND article_status = 1
        </where>
        ORDER BY article_id
        limit 1
    </select>
    <!--获得上一篇的文章-->
    <select id="getPreArticle" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        <where>
            article_id &lt; #{id} AND article_status = 1
        </where>
        ORDER BY article_id
        limit 1
    </select>

    <!--获得随机文章-->
    <select id="listRandomArticle" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        WHERE article_status = 1
        ORDER BY
        RAND()
        limit #{limit}
    </select>

    <!--获得评论数较多的文章列表-->
    <select id="listArticleByCommentCount" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        WHERE article_status = 1
        ORDER BY
        article_comment_count DESC,article_order DESC, article_id DESC
        limit #{limit}
    </select>

    <update id="updateCommentCount" parameterType="Integer">
        UPDATE
        <include refid="tb"/>
        SET article_comment_count =
        (
        SELECT count(*) FROM `comment`
        WHERE article.article_id=comment.comment_article_id
        )
        WHERE article_id=#{articleId}
    </update>

    <!--获得最后更新的记录-->
    <select id="getLastUpdateArticle" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        <include refid="Base_Column_List"/>
        FROM
        <include refid="tb"/>
        WHERE
        article_status = 1 AND article_update_time=
        (
        SELECT max(article_update_time) FROM article
        )
    </select>

    <select id="countArticleByUser" parameterType="Integer" resultType="Integer">
        SELECT COUNT(*)
        FROM
        <include refid="tb"/>
        WHERE article_user_id=#{id} AND article_status = 1
    </select>

    <select id="findArticleByCategoryId" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        article.article_id, article.article_user_id, article.article_title,
        article.article_view_count, article.article_comment_count,
        article.article_like_count, article.article_create_time, article.article_update_time,
        article.article_is_comment, article.article_status, article.article_order,
         article.article_summary
        FROM article, article_category_ref
        WHERE
        article.article_status = 1 AND
        article.article_id = article_category_ref.article_id AND
        article_category_ref.category_id = #{catgeoyrId}
        LIMIT #{limit}
    </select>

    <select id="findArticleByCategoryIds" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        article.article_id, article.article_user_id, article.article_title,
        article.article_view_count, article.article_comment_count,
        article.article_like_count, article.article_create_time, article.article_update_time,
        article.article_is_comment, article.article_status, article.article_order,
        article.article_summary
        FROM article, article_category_ref
        <where>
            article.article_status = 1 AND
            article.article_id = article_category_ref.article_id AND
            article_category_ref.category_id
            <if test="categoryIds != null">
                IN
                <foreach collection="categoryIds" open="(" close=")" separator="," item="id">
                    #{id}
                </foreach>
            </if>
        </where>
        LIMIT #{limit}
    </select>


    <select id="listArticleByLimit" resultType="com.liuyanzhao.ssm.blog.entity.Article">
        SELECT
        article_id, article_user_id, article_title, article_view_count, article_comment_count, article_like_count,
        article_create_time,
        article_update_time, article_is_comment, article_status, article_order
        FROM
        <include refid="tb"/>
        ORDER BY article_id DESC
        LIMIT #{value}
    </select>
</mapper>
4.3.3 实现服务类Service
创建文章和用户的Service层接口。在包中创建UserService接口和ArcticeService接口，并在该接口中编写操作用户的相关方法， 
UserService.java
package com.liuyanzhao.ssm.blog.service;

import com.liuyanzhao.ssm.blog.entity.User;

import java.util.List;

/**
 * @author 王一凡
 * @date 2017/8/24
 */

public interface UserService {
    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    List<User> listUser();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 用户
     */
    User insertUser(User user);

    /**
     * 根据用户名和邮箱查询用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户
     */
    User getUserByName(String name);

    /**
     * 根据邮箱查询用户
     *
     * @param email Email
     * @return 用户
     */
    User getUserByEmail(String email);
}
ArticleService.java
package com.liuyanzhao.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.liuyanzhao.ssm.blog.entity.Article;

import java.util.HashMap;
import java.util.List;

/**
 * 文章Service
 *
 * @author 王一凡
 * @date 2017/8/24
 */
public interface ArticleService {

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countArticle(Integer status);

    /**
     * 获取评论总数
     *
     * @return 数量
     */
    Integer countArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 数量
     */
    Integer countArticleView();

    /**
     * 统计有这个分类的文章数
     *
     * @param categoryId 分类ID
     * @return 数量
     */
    Integer countArticleByCategoryId(Integer categoryId);

    /**
     * 统计有这个表情的文章数
     *
     * @param tagId 标签ID
     * @return 数量
     */
    Integer countArticleByTagId(Integer tagId);


    /**
     * 获得所有文章不分页
     *
     * @param criteria 查询条件
     * @return 列表
     */
    List<Article> listArticle(HashMap<String, Object> criteria);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRecentArticle(Integer limit);


    /**
     * 修改文章详细信息
     *
     * @param article 文章
     */
    void updateArticleDetail(Article article);

    /**
     * 修改文章简单信息
     *
     * @param article 文章
     */
    void updateArticle(Article article);

    /**
     * 批量删除文章
     *
     * @param ids 文章ID
     */
    void deleteArticleBatch(List<Integer> ids);

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    void deleteArticle(Integer id);

    /**
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);

    /**
     * 文章详情页面显示
     *
     * @param status 状态
     * @param id     文章ID
     * @return 文章
     */
    Article getArticleByStatusAndId(Integer status, Integer id);

    /**
     * 获取访问量较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获得上一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getAfterArticle(Integer id);

    /**
     * 获得下一篇文章
     *
     * @param id 文章ID
     * @return 文章
     */
    Article getPreArticle(Integer id);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得评论数较多的文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listArticleByCommentCount(Integer limit);

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void insertArticle(Article article);


    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(Integer articleId);

    /**
     * 获得最后更新记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();

    /**
     * 获得相关文章
     *
     * @param cateId 分类ID
     * @param limit  查询数量
     * @return 列表
     */
    List<Article> listArticleByCategoryId(Integer cateId, Integer limit);

    /**
     * 获得相关文章
     *
     * @param cateIds 分类ID集合
     * @param limit   数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit);


    /**
     * 根据文章ID获得分类ID列表
     *
     * @param articleId 文章Id
     * @return 列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 获得所有的文章
     *
     * @return 列表
     */
    List<Article> listAllNotWithContent();
}
然后创建角色和用户Service层接口的实现类。,并在包中创建UserService接口的实现类UserServiceImpl和ArticleService接口的实现类ArticleServiceImpl,在类中编辑并实现接口中的方法。
4.3.4 实现控制层Controller
首先通过@Autowired注解将ArticleService对象和UserService对象注入本类中，然后创建对用户进行增、删、改、查、登录、退出等方法。
(1) ArticleController类中每个方法前通过注解@RequestMapping设置了方法的访问路径，例如@RequestMapping(value=”/insert”).
(2) insertArticleView (方法用于添加文章。添加文章时，会根据用户对文章的操作，如一级二级目录的选择，标签的选择进行相应的赋值操作，方便后期的查询功能。
4.3.5 实现页面功能
其核心代码是Admin的form表单，该表单在提交时会通过checkValue()方法检查账户或密码是否为空，如果为空，就通过alert()消息框提示用户;如果账号和密码都已填写，就将表单提交到以“/login.action”结尾的请求中。
在WEB-INF目录下创建jsp文件夹(与前面所讲的配置文件springmvc-config.xml中的前缀配置相对应)，并在jsp文件下创建公共页面，各个功能模块的页面。这几个页面主要实现后台框架页面功能，以及根据登录后保存在session中的用户信息的角色显示与其权限相适应的操作界面和菜单选项的功能。
在WEB-INF/view/admin目录下创建Arctile文件夹，并在该文件夹下创建页面index.jsp、添加文章页面insert.jsp和更新文章页面edit.jsp.

insert.jsp
<%--保留此处 start--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<%--保留此处 end--%>
    <rapid:override name="title">
        - 新建文章
    </rapid:override>

<rapid:override name="content">
    <blockquote class="layui-elem-quote">
         <span class="layui-breadcrumb" lay-separator="/">
              <a href="/admin">首页</a>
              <a href="/admin/article">文章列表</a>
              <a><cite>添加文章</cite></a>
        </span>
    </blockquote>



    <form class="layui-form"  method="post" id="myForm" action="/admin/article/insertSubmit">

        <div class="layui-form-item">
            <label class="layui-form-label">标题 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <input type="text" name="articleTitle" lay-verify="title" id="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">内容 <span style="color: #FF5722; ">*</span></label>
            <div class="layui-input-block">
                <textarea class="layui-textarea layui-hide" name="articleContent" lay-verify="content" id="content"></textarea>
            </div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分类 <span style="color: #FF5722; ">*</span> </label>
            <div class="layui-input-inline">
                <select name="articleParentCategoryId" id="articleParentCategoryId" lay-filter="articleParentCategoryId" required>
                    <option value="" selected="">一级分类</option>
                    <c:forEach items="${categoryList}" var="c">
                        <c:if test="${c.categoryPid==0}">
                            <option value="${c.categoryId}">${c.categoryName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="articleChildCategoryId" id="articleChildCategoryId">
                    <option value="" selected>二级分类</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item" pane="">
            <label class="layui-form-label">标签</label>
            <div class="layui-input-block">
                <c:forEach items="${tagList}" var="t">
                    <input type="checkbox" name="articleTagIds" lay-skin="primary" title="${t.tagName}" value="${t.tagId}">
                </c:forEach>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="articleStatus" value="1" title="发布" checked>
                <input type="radio" name="articleStatus" value="0" title="草稿" >
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="getCateIds()">重置</button>
            </div>
        </div>
        <blockquote class="layui-elem-quote layui-quote-nm">
            温馨提示：<br>
            1、文章内容的数据表字段类型为MEDIUMTEXT,每篇文章内容请不要超过500万字 <br>
            2、写文章之前，请确保标签和分类存在，否则可以先新建；请勿刷新页面，博客不会自动保存 <br>
            3、插入代码前，可以点击 <a target="_blank">代码高亮</a>,将代码转成HTML格式

        </blockquote>

    </form>


</rapid:override>

<rapid:override name="footer-script">

    <script>
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;


            //上传图片,必须放在 创建一个编辑器前面
            layedit.set({
                uploadImage: {
                     url: '/admin/upload/img' //接口url
                    ,type: 'post' //默认post
                }
            });

            //创建一个编辑器
            var editIndex = layedit.build('content',{
                    height:350,
                }
            );

            //自定义验证规则
            form.verify({
                title: function (value) {
                    if (value.length < 5) {
                        return '标题至少得5个字符啊';
                    }
                }
                , pass: [/(.+){6,12}$/, '密码必须6到12位']
                , content: function (value) {
                    layedit.sync(editIndex);
                }
            });

            layedit.build('content', {
                tool: [
                    'strong' //加粗
                    ,'italic' //斜体
                    ,'underline' //下划线
                    ,'del' //删除线
                    ,'|' //分割线
                    ,'left' //左对齐
                    ,'center' //居中对齐
                    ,'right' //右对齐
                    ,'link' //超链接
                    ,'unlink' //清除链接
                    ,'face' //表情
                    ,'image' //插入图片
                    ,'code'
                ]
            });

            layui.use('code', function(){ //加载code模块
                layui.code();
            });

            //二级联动
            form.on("select(articleParentCategoryId)",function () {
                var optionstring = "";
                var articleParentCategoryId = $("#articleParentCategoryId").val();
                <c:forEach items="${categoryList}" var="c">
                if(articleParentCategoryId==${c.categoryPid}) {
                    optionstring += "<option name='childCategory' value='${c.categoryId}'>${c.categoryName}</option>";
                }
                </c:forEach>
                $("#articleChildCategoryId").html("<option value=''selected>二级分类</option>"+optionstring);
                form.render('select'); //这个很重要
            })

     });
//        window.onbeforeunload = function() {
//            return "确认离开当前页面吗？未保存的数据将会丢失";
//        }



    </script>

</rapid:override>


<%--此句必须放在最后--%>
<%@ include file="../Public/framework.jsp"%>

4.3.6 页面功能展示
启动项目，测试登录和用户管理。将项目发布到Tomcat服务器并启动，访问地址为http://localhost:8080/admin ，进入登录页面，即可输入账号密码登录系统，如下图所示。
 
图4-2 登录页面截图
登陆时存在2种情况，第一种情况是如果账号或密码错误，则不允许登录。第二种情况是，若账号和密码正确，则允许登录系统后台。
 
管理员系统后台界面

博主登录系统后，可以进行文章的发布和修改。
 
添加文章界面
 
全部文章界面
4.4 博客评论管理模块
4.4.1 创建持久化类
与文章管理模块实现原理相同，首先需要做的就是创建持久化类，评论管理持久化类有评论类comment。
后台评论管理列表需要进行分页管理，并定义类PageBean。
4.4.2 实现Mapper层接口
在创建持久化类后需要创建Mapper的接口，在包中创建一个评论接口CommentMapper，并在接口中编写增、删、改等方法。
接着还需要创建映射文件，在包中创建两个对应的映射文件，并在映射文件中编写增、删、改等方法的具体执行语句。
4.4.3 实现服务类Service
创建评论和文章的Service层接口。在包中创建一个接口CommentService，并在该接口中编写操作用户的相关方法。
然后创建评论Service层接口的实现类，并在包中创建接口CommentService的实现类ArticleServiceImpl，并实现接口的相关方法。
4.4.4 实现控制层Controller
在包中创建用户控制器类CommentController。首先通过@Autowired注解将ArticleSerice对象和CommentService对象注入本类中，然后创建对评论进行操作的方法。
CommentController类与ArticController类一样，在一些方法中涉及业务逻辑，将CommentController类与系统中的文章管理相关页面联系起来。CommentController类与index.jsp页面在交互过程中采用了PageBean对评论进行封装，并实现了分页功能。
4.4.5 实现页面功能
先在WEB-INF/view/Admin目录下创建Commet文件夹，并在该文件夹下创建评论列表页面、回复页面和更新评论页面。
列表页面index.jsp页面文件主要用来展示评论列表，通过访问CommentContoller类的commentListView ()可以跳转到index.jsp页面并在页面中迭代显示列表信息，并在此基础上可以通过链接对评论进行修改、删除、发回复等相关操作。
4.3.6 页面功能展示
启动项目。将项目发布到Tomcat服务器并启动，访问项目首页http://localhost:8080，如下图所示。
 
图4-7 项目首页界面
游客可以进行查询文章、评论留言、打赏点赞等操作。其中，查询实现了分页效果。
 
列表和查询界面
站点地图界面
 
 

心得体会
通过此次课程设计，我更加深入的了解了ssm框架方面的知识，在项目的开发过程中遇到了不少困难，在百度和谷歌的帮助下解决了其中大部分的问题，还有一部分的问题通过组员的不断试验不断尝试，慢慢的整理思路才得以解决。我们用的是idea工具来进行开发，在学习的过程中，我们也不短熟悉了idea的强大，通过他的各种插件，比如lombok插件，简化了代码，让代码看起来更清晰。热部署功能让服务器减少重启次数，提高开发效率。
在开发阶段我们遇到不少技术问题，在课堂上书本上的知识不能解决的情况下，我们开始有些心急烦躁，发现我们不会的东西还有很多，一下子变得手足无措，但好在后来我们沉下心来，知识是学不完的，重要的是用对方法，知道如何在不太熟悉的情况下，利用有限的资源解决实际的应用问题。这次的课程设计让我重新温习了java的基础知识，也学会了在遇到问题的情况下，解决问题的办法，让我对编程有了更加深刻的印象。
在项目开发完成之后，当项目运行起来的时候，看着屏幕上自己开发的项目网站，我们无比的喜悦，这种成就感让我们觉得所有的努力都是值得的，尽管项目还有些小缺陷，小bug，但这丝毫不影响我们成长的道路。
感谢团队成员的努力和老师的悉心教导，我们会不断完善自己，不断努力，不断成长，不断收获。























参考文献

[1]陈友洪，G 公司 SAP 质量管理系统应用研究[D],甘肃，兰州大学硕士学位论文，2009,7-9.
[2]栾跃，软件开发项目管理[M],上海，上海交通大学出版社，2005,20-40. 
[3]龙中华《Spring Boot实战派》电子工业出版社2020-01
[4]黄文毅.一步一步学Spring Boot:微服务项目实战(第2版)》清华大学出版社2019-12
[5]杨洋《Spring Boot 2实战之旅》清华大学出版社 2019-07
[6]李家智《Spring Boot 2精髓：从构建小系统到架构分布式大系统》电子工业出版社2017-10
[7]杨恩雄《SpringBoot2+Thymeleaf企业应用实战》电子工业出版社2000-01
[8]Peter S. Pande et al,Robert P. Neuman,Roland R. Cavanagh,The Six Sigma Way:How GE,Motorola,and Other Top Companies are Honing Their Performance[M],McGraw-Hill,2000,1-67.　[9]David M. Levine,Statistics for Six Sigma Green Belts with Minitab and JMP[M],FT Press,2006,1-22.
[10] [日]西泽梦路.《MySQL基础教程》人民邮电出版社2020-01
 

致谢
感谢团队和老师的指点，虽然项目还有不少的缺陷，但还是对各位致以衷心的感谢。


