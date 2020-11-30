# JavaWeb-ShopOnline
网络应用开发课程的实验。
学号：201830581503
姓名：王梓泓

# 实验

## 本次实验的部署之后的页面效果查看：
http://47.97.80.250:8080/shop/
用户名：qwe
密码：qwe
既是商家用户，又是顾客用户。
可以用来查看效果。

## 实验分析
- 本次实验是设计并实现一个电子商务网站的开发和在线部署，
基本功能要求分用户主体有两个：顾客与商家。相对应的方法有：登录，注册，注销，浏览/查询，购物篮添加，付款，电子邮件确认发货，对商品增删查改，后天销售统计报表，销售状态，客户的浏览/购买日志等。
首先完成登录以及注册界面等的相关转跳，用户实体、商品实体、5465，然后连接数据库并进行数据库数据提取与管理，电子邮箱确认发货。
将代码放置github上，以及使用tomcat作为web服务器，mysql作为数据库，部署到阿里云上。
大致实验步骤为：开发环境搭建，分析功能及主体，实现基本的登录以及页面跳转，数据库连接及查看，测试部署到阿里云，项目css以及动效的美化，商家实体操作流程完善，顾客实体操作流程完善，部署到github上。


## 实验步骤

### 1. 开发环境搭建
- 本次项目开发环境是eclipse，需要下载安装eclipse，jdk8.0,通过搭建Javaweb 项目，之后打包成war包，方式特定位置，本地安装tomcat并将项目基地址修改到上述的特定位置，通过tomcat的startup.bat启动tomcat本地服务器(war包自动解压)，本地可在浏览器地址栏中输入http://localhost:8080/shop访问。
- 项目的使用免费的MySQL数据库，安装并设置用户密码以及简单的建立数据库操作。将MySQL的连接jar包：mysql-connector-java-8.0.22.jar放在tomcat的配置文件目录中，并在项目中使用DAO结构，即可做到数据库与项目连接。

### 2. 功能及主体分析
- 通过实验要求可见，主体用户分为两种：商家和顾客。还有主体物体分为两个表：商品展示表以及用户行为日志表。
- 商家和顾客的共同行为是：登录，注册，注销，查看商品。
- 明确三个表中信息，用以接下来的数据库表的建立。
- 首先应当实现登录以及注册功能。
- 本实验通过servlet以及jsp实现，jsp主要用于展示页面以及用户交互，servlet用于处理http协议的信息，数据库以及内部逻辑。

### 3. 基本页面跳转
- 登陆页面中，可以输入用户名与密码以及选择“商家登录”或是“顾客登录”，如果输入为空，则提示不可为空，通过检索数据库中用户表信息，如果匹配成功则登录，并且依据用户身份不同跳转至对应的商品信息页，即上图的从中间页面专挑到右下角的页面；若匹配不成功，用户可以点击注册按钮进入注册页，注册页要求用户输入用户名，密码，邮箱（用以后面的邮箱确认）以及选择用户身份，输入成功，提交，则往数据库中添加用户信息，并且用户回到登录页面，可以执行登录操作。
- 页面使用jsp来写，login.jsp用于登陆页面，通过form的action 跳转到LoginServlet，使用封装好的service(), 通过doGet()接收request并进行处理用户输入的表单信息，如果匹配成功，用户名以及密码将会保存在session中，并且若用户再次访问login.jsp时，直接使用session中的用户及密码。
- 通过地址栏绕过login.jsp进入商品页，服务器会查询session中是否存在上一次登录成功的用户信息，若存在，则可以访问，若不存在，则会提示，并且回到登陆页面。
- register.jsp用于注册页面，用户上传表单信息，通过RegisterServlet保存用户信息。提示回到登录页面进行登录操作。
- 成功登录到商品展示页面后，logout_navi.jsp是一个子页面，用于include到顾客页面以及商家页面，本意是用于注销，退出登录，可以添加后续的操作。


### 4. 数据库连接使用
- 登录操作实现后，根据用户身份不同进入到不同的商品展示页面。
- 如果是顾客身份，通过LoginServlet的中的request重定向到cart.jsp，顾客的商品展示页面，页面功能以及主要的排版如下：
- 用户页面需要连接到数据库才可以调用数据，展示数据。
- 于是有了DAO结构：Dao类为连接数据库，并且封装成几个函数，提供给servlet的service()中调用。到这里整个项目的结构已经比较清晰。
- 项目的类分成三种：
    - dbclass为DAO的类（写成dbclass是因为一开始不知道这是DAO结构的实现）；
 - entity为实体类，包括商品，用户，以及用户操作记录
 - servlets类即各种servlet用于处理内部逻辑，即 C (controller)
- view页面文件的大致结构也展示如下：
- 数据库的表结构也展示如下：
- 这里采用比较朴素的办法，将数据库中信息全部选取并保存进数组中，对数据的增删查改全都在Java代码中完成，完成之后再整体放入数据库中。
- 用户可以展示所有商品信息，并且查看（每次点击当作一次浏览，记录到顾客浏览记录中），查询（通过搜索栏查询商品信息，并且页面跳转到该商品信息所对应的dom上），加入购物车（将商品编号记录，默认加入购物车的数量为1用于在购物车页面展示）。
- 商家可以查看所有商品信息，并且任意修改其中的项（当然输入格式还是要求的，这里通过form表单，用户可以改变input中的值，后端删除所有商品信息，并且将新的form表单信息写入数据库中，很显然这种做法很不科学，数据量大的时候响应慢以及数据存在危险），商家还可以查看顾客浏览以及购买记录表。鉴于商品销售情况信息与顾客浏览购买记录类似，且本次实验重点在功能实现而不是页面展示优化，于是这个项目中没有好的想法用于展示商品销售情况。




### 5. 部署到阿里云

首先租用阿里云的服务器，学生优惠9.9一个月。
记录公网ip。
查看阿里云使用教程，安装Xshell以及Xftp，前者用于输入命令行，后者用于传输文件。
注意防火墙设置以及阿里云ECS云服务器的安全组配置（避免连接不了）。

新建文件目录，作为Javaweb的基地址：
useradd www
mkdir -p /data/wwwroot/default
在这个目录下添加test.jsp

上传jsk, tomcat,的压缩包。
打开Xshell，输入以下命令：添加jdk到path中：
vi /etc/profile
添加以下代码并保存：
# set java environment
export JAVA_HOME=/usr/java/jdk1.8.0_271
export CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib
export PATH=$JAVA_HOME/bin:$PATH


运行以下代码查看环境配置：
source /etc/profile
java -version
结果如下标识已经完成配置：


以下代码解压缩tomcat并且安装：
tar xzf apache-tomcat-8.5.60.tar.gz
mv apache-tomcat-8.5.60 /usr/local/tomcat/
chown -R www.www /usr/local/tomcat/


修改tomcat的配置文件server.xml，修改基目录：
cd /usr/local/tomcat/conf/
mv server.xml server.xml_bk
vi server.xml
换成以下代码并保存：
<?xml version="1.0" encoding="UTF-8"?>
<Server port="8006" shutdown="SHUTDOWN">
<Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener"/>
<Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener"/>
<Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener"/>
<Listener className="org.apache.catalina.core.AprLifecycleListener"/>
<GlobalNamingResources>
<Resource name="UserDatabase" auth="Container"
 type="org.apache.catalina.UserDatabase"
 description="User database that can be updated and saved"
 factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
 pathname="conf/tomcat-users.xml"/>
</GlobalNamingResources>
<Service name="Catalina">
<Connector port="8080"
 protocol="HTTP/1.1"
 connectionTimeout="20000"
 redirectPort="8443"
 maxThreads="1000"
 minSpareThreads="20"
 acceptCount="1000"
 maxHttpHeaderSize="65536"
 debug="0"
 disableUploadTimeout="true"
 useBodyEncodingForURI="true"
 enableLookups="false"
 URIEncoding="UTF-8"/>
<Engine name="Catalina" defaultHost="localhost">
<Realm className="org.apache.catalina.realm.LockOutRealm">
<Realm className="org.apache.catalina.realm.UserDatabaseRealm"
  resourceName="UserDatabase"/>
</Realm>
<Host name="localhost" appBase="/data/wwwroot/default" unpackWARs="true" autoDeploy="true">
<Context path="" docBase="/data/wwwroot/default" debug="0" reloadable="false" crossContext="true"/>
<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
prefix="localhost_access_log." suffix=".txt" pattern="%h %l %u %t &quot;%r&quot; %s %b" />
</Host>
</Engine>
</Service>
</Server>


接下来可以设置开机自启动服务器也可以不设置。


将war包上传放到webapp基目录下，
 
cd命令到tomcat的bin目录下，
cd /usr/local/tomcat/bin
sh startup.sh 可以开启tomcat服务
sh shutdown.sh 可以关闭tomcat服务
开启服务后即可在本地输入 http://公网IP:8080/shop访问，其中shop为项目名称。
 
-----
为了连接数据库，需要部署MySQL，
上传mysql安装包，以及mysql-connector-java-8.0.22.jar
解压MySQL安装之后，同样放入其配置文件目录中。
编写MySQL语句如show databases;等语句创建数据库以及数据表，添加初始数据。
重启以下tomcat即可使用。
以后每次修改web项目或者是其他配置文件操作，最好重启一次tomcat。


### 6. 界面美化
- 功能写的差不多了，界面美化一下。简单的通过css进行页面设计
### 7. 用户具体操作
- 商家：完成上述的操作，作业的大致要求以及完成。商家操作主要是通过读取所有商品信息，保存在form中，并且可以任意修改form中数据，点击修改按钮即可刷新数据库中商品信息。另外可以查看顾客浏览以及购买商品日志信息（也是一个数据表.）。
- 顾客：顾客主要是浏览，查询，添加购物车，购买，邮箱确认。浏览的操作就是展示所有商品信息，但是顾客不可修改信息。顾客点击商品详情信息则往顾客浏览记录表中添加一行浏览数据。查询即通过搜索商品名，在map中记录其对应的编码，页面自动翻滚到对应的商品信息块。添加至购物车则添加到session中，并且默认商品数量为1用户点击购物车时，进入购物车页面，并且可以选择数量，点击购买，则修改数据库中商品的”剩余量”数据，并且往顾客浏览购买记录中添加一行购买信息数据。并且自动发送邮箱确认，此时用户只需在注册时填写的邮箱中确认即可。


### 8. 上传至GitHub
