## Gatiling 安装
- 下载：[官网下载链接](https://gatling.io/#/download)
- 解压
> - 如果使用 IntelliJ IDEA 运行测试，无需安装 Gatling，可跳至 ```在 IntelliJ 安装 Scala``` 部分


## 首次运行
#### 运行 Gatling
- 进入 bin 目录
- Windows 环境：打开命令行运行 ```gatling.bat```
- Linux 环境：```sh gatling.sh```
- 依次输入：
    - 执行的脚本序号：如 ```0```
    - 本次测试 Id ,用作测试报告命名前缀,不能包含空格，特殊字符，中文等：如 ```my_first_gatling```
    - 本次测试描述（非必须），会显示在报告头部：如 ```my_first_gatling```

![image.png](https://upload-images.jianshu.io/upload_images/11952792-c417957334ca1def.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 当输出以下信息则本次测试运行完毕
- 最后一行路径即为本次测试的报告

![image.png](https://upload-images.jianshu.io/upload_images/11952792-407514f300f156e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 查看测试报告

![image.png](https://upload-images.jianshu.io/upload_images/11952792-2f1ea04338c7c2b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### Record UI 操作
- 它主要用于将浏览器配置代理后记录用户操作然后生成测试脚本，更多的用于用户行为模拟测试，详细请见：https://testerhome.com/topics/3633


## scala 安装
> - Gatling 是基于 Scala 开发的压测工具，如果想自定测试计划需要自己编写脚本，不过因为 Gatling 脚本很简单，常见的没几个，Gatling 封装的也很好并不需要专门去学习 Scala 语法

#### 在 IntelliJ 安装 Scala 
- 安装 Scala 插件

![image.png](https://upload-images.jianshu.io/upload_images/11952792-c84bc4036c458f24.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 新建一个 module

![image.png](https://upload-images.jianshu.io/upload_images/11952792-716af9f86a64b848.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 选择 Scala 类型

![image.png](https://upload-images.jianshu.io/upload_images/11952792-32f1ab1d1069886e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 下载 Scala SDK

![image.png](https://upload-images.jianshu.io/upload_images/11952792-b83ac1b455b2a3cd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 根据 Maven Archetype 建立项目
![image.png](https://upload-images.jianshu.io/upload_images/11952792-a4025c509b6a8f6b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
1. 【File】-> 【New】 -> 【Project】，打开新建项目窗口。项目类型选择【Maven】
2. 勾选 【Create from archetype】，点击【Add Archetype...】
3. 输入：
    - GroupId: ```io.gatling.highcharts```
    - Artifactld: ```gatling-highcharts-maven-archetype```
    - version: **```2.3.1```**
    - 点击 【ok】。
4. 选中刚才添加的 Archetype  ```io.gatling.highcharts:gatling-highcharts-maven-archetype```，点击 【Next】。
5. 填写自己项目的 GroupId， Artifactld 及 Version ，继续 【Next】。如何填写可参考 [Guide to naming conventions on groupId, artifactId and version](https://maven.apache.org/guides/mini/guide-naming-conventions.html)
6. 设置 Maven 的配置。一般情况下直接用默认值即可。继续 【Next】。
7. 设置项目名称，项目目录等。设置完毕后点击 【Finish】

> - [Gatling 的 Archetype 列表地址](http://mvnrepository.com/artifact/io.gatling.highcharts)

##### 项目目录结构
![image.png](https://upload-images.jianshu.io/upload_images/11952792-703dcae361474fa7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



#### 异常
##### 运行 Engine
```
Exception in thread "main" java.lang.NoSuchMethodError: scala.Predef$.refArrayOps([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
	at io.gatling.core.config.GatlingConfiguration$ConfigStringSeq$.toStringList$extension(GatlingConfiguration.scala:44)
	at io.gatling.core.config.GatlingConfiguration$.mapToGatlingConfig(GatlingConfiguration.scala:197)
	at io.gatling.core.config.GatlingConfiguration$.setUp(GatlingConfiguration.scala:95)
	at io.gatling.app.Gatling.start(Gatling.scala:68)
	at io.gatling.app.Gatling$.fromMap(Gatling.scala:46)
	at Engine$.delayedEndpoint$Engine$1(Engine.scala:12)
	at Engine$delayedInit$body.apply(Engine.scala:4)
	at scala.Function0.apply$mcV$sp(Function0.scala:34)
	at scala.Function0.apply$mcV$sp$(Function0.scala:34)
	at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:12)
	at scala.App.$anonfun$main$1$adapted(App.scala:76)
	at scala.collection.immutable.List.foreach(List.scala:389)
	at scala.App.main(App.scala:76)
	at scala.App.main$(App.scala:74)
	at Engine$.main(Engine.scala:4)
	at Engine.main(Engine.scala)

Process finished with exit code 1
```
- 修改 scale.version 为 2.3.1