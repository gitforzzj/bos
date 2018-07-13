# bos物流系统

#### 项目介绍
（1）产品应用场景：Bos物流系统项目
（2）用户：取派员、前台柜员，用户，系统管理员
（3）系统范围：Bos物流系统
（4）基本目标：开发一个界面友好、简单易用的物流系统，快递公司使用该系统后， 能够更高效地为客户服务，
      及时通知取派员派件和上门取件，加快物流的配送速度，以及记录快递的物流信息。


#### 软件架构
本项目是基于B/S架构开发的，使用的技术工具有
1.	maven管理工具，用于管理jar包和项目构建
2.	git版本控制
3.	navicate数据库管理软件
4.	ecplise ee 编写代码的ide
  包括的开发语言有java1.8，mysql5.7，jsp，html，css，javascrtpt，jquery等，本项目前端采用easyui，easyui集成了较多了样式，
  减少了前端的部分工作，简洁大方。后端采用spring4.2.4，hibernate5.0.7，struts2.3.24框架，使用ssh框架能提高工作效率，可扩展性，可维护性等。 
#### 详细介绍
1. Bos物流系统项目我主要写了基础设置和业务受理两个模块
2. 基础设置包括取派员设置，区域设置，分区设置，定区设置，其中区域包含多个分区，而几个分区组成一个定区，
    比如，广州天河是一个区域，而华农华工华师等高校就是一个分区，所以一个区域包含多个分区，一个分区的快递量可能比较少，将华农华工组合在一起，
    就变成为一个定区。其中定区是物流分配的基本单位，可以将客户信息、取派员信息、分区信息进行关联，可以对这些基本模块进行增删查改，
    使用poi对excel表进行批量导入，批量导出数据为excel表
3. 业务受理根据就是录入寄快递用户的一些基本信息，如果是通过电话要求上门取件，可以根据电话号码查询crm系统显示出用户的基本信息，
    使用webservice的cxf框架进行跨系统的信息交互，可以根据用户的地址进行自动分单，生成工单，工单有取派员，取件状态，取件地址，
    用户联系方式等字段，取派员收到工单信息后可以上门取件，对应取派员上门取件后，将工单状态改为已取件，根据用户填写快递单信息录入一个工作单，
    记载发件人收件人商品有关的一些详细信息
4. 该系统使用shiro进行登录认证和权限控制，数据库表格有权限表，角色表和用户表，其中权限表和角色表示多对多的关系，
    角色表和用户表也是多对多的关系，目录是根据权限加载的，如果该用户没有对应权限，就没有相应的目录

#### 安装教程

1. 导入bos和crm项目到ecplise或者idea，然后通过maven加载jar包，
2. 修改bos-web项目下db.properties的数据库账号的密码
3. 新建bos32数据库，将bos-web下的bos32.sql导入数据库
4.新建crm32数据库，将crm下的crm32.sql导入数据库
6.正常的话便可运行即可
数据库已有账号  管理员 用户名admin 密码12345
               普通用户 账号123 密码12345
密码使用md5进行加密，所以数据库看不出来密码是什么



#### 项目截图
登录界面
![登录界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E7%99%BB%E5%BD%95%E7%95%8C%E9%9D%A2.png)

取派员设置界面
![取派员设置界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E5%8F%96%E6%B4%BE%E5%91%98%E8%AE%BE%E7%BD%AE.png)

取派员修改界面
![取派员修改界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E4%BF%AE%E6%94%B9%E5%8F%96%E6%B4%BE%E5%91%98.png)

取派员新增界面
![取派员新增界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E6%96%B0%E5%A2%9E%E5%8F%96%E6%B4%BE%E5%91%98.png)

分区管理界面
![分区管理界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E5%88%86%E5%8C%BA%E7%AE%A1%E7%90%86.png)

区域设置界面
![区域设置界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E5%8C%BA%E5%9F%9F%E8%AE%BE%E7%BD%AE.png)

关联分区界面
![关联分区界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E5%85%B3%E8%81%94%E5%88%86%E5%8C%BA.png)

关联客户界面
![关联客户界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E5%85%B3%E8%81%94%E5%AE%A2%E6%88%B7.png)

业务受理界面
![业务受理界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E4%B8%9A%E5%8A%A1%E5%8F%97%E7%90%86.png)

用户管理界面

![用户管理界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86.png)

权限管理界面

![权限管理界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E6%9D%83%E9%99%90%E7%AE%A1%E7%90%86.png)

角色管理界面

![角色管理界面](https://github.com/gitforzzj/bos/blob/master/bos-parent/images/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.png)
