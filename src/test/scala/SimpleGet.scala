import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SimpleGet extends Simulation {
  /**
    * 1、设置请求的根路径
    */
  val httpConf = http.baseURL("http://127.0.0.1:9090/springboot-simple-demo")

  /**
    * 2、定义参数（可选）
    */
  // ======================== 参数设置 start ===============================

  // 1. 数组定义参数
  val arrayFeeder = Array(
    Map("login_name" -> "jun", "email" -> "www.lijun07@qq.com"),
    Map("login_name" -> "ligohan", "email" -> "502216831@qq.com")
  )
    .random // 随机调用
    //.queue // 顺序调用，如果循环执行次数比参数个数多，顺序执行完一遍就会报错终止
    //.circular // 顺序循环调用

  // 2. csv 定义参数
  // val csvFeeder = csv("SimpleGet-foo.csv").random // 文件路径在 %Gatling_Home%/user-files/data/

  // 3. json 定义参数
  // val jsonFileFeeder = jsonFile("SimpleGet-foo.json").random

  // 4.jdbc
  // jdbcFeeder("databaseUrl", "username", "password", "SELECT * FROM users")

  // ======================== 参数设置 end ===============================

  /**
    * 3、声明 Scenario，指定我们的请求动作
    */
  val scn = scenario("SimpleGetSimulation") // 脚本名称
    .during(10)  // 运行 10 秒 during 默认单位秒,如果要用微秒 during(100 millisecond)
    //.repeat(100) // 循环 100 次
  {
     exec(http("/api/sys-user/list").get("/api/sys-user/list")) // exec() 里的参数就是我们的执行动作，http("本次请求的名称").get("本次http get请求的地址")
     .feed(arrayFeeder) // 加载 feed
     .exec(http("/api/sys-user/save").get("/api/sys-user/login?login_name=${login_name}&email=${email}")) // 使用参数进行 get 请求
  }

  /**
    * 4、设置线程数
    */
  // setUp(scn.inject(rampUsers(500) over(10 seconds)).protocols(httpConf)) // 用 10 秒时间，平滑启动 5000 个线程
  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf)) // atOnceUsers：用户数（并发数）

  /**
    * 5、运行 Engine.scala 开始压测
    */
}