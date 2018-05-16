import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostWithJSONOrForm extends Simulation {
  /**
    * 1、设置请求的根路径
    */
  val httpConf = http.baseURL("http://127.0.0.1:9090/springboot-simple-demo")

  /**
    * 2、设置提交内容 type
    */
  val jsonType = Map("Content-Type" -> "application/json")
//  val formType = Map("Content-Type" -> "application/x-www-form-urlencoded")

  // 生成随机数
  val randomIdFeeder =
    Iterator.continually(Map("id" ->
      (scala.util.Random.nextInt(10000000))))

  /**
    * 3、声明 Scenario，指定我们的请求动作
    */
  val scn = scenario("PostSimulation")
    .during(10)
    {
      feed(randomIdFeeder)
      .exec(
        http("/api/sys-user/save") //http 请求名称
        .post("/api/sys-user/save") //post url
        .headers(jsonType) //设置body数据格式
        //将json参数用StringBody包起,并作为参数传递给function body()
        .body(StringBody("{\"id\":\"${id}\",\"login_name\":\"jun\",\"email\":\"www.lijun07@qq.com\",\"create_date\":\"2018-05-16 14:59:00\"}")).asJSON
      )
    }

//  val scn2 = scenario("PostSimulation")
//    .exec(http("/api/sys-user/save")   //http 请求名称
//      .post("/api/sys-user/save")     //post url
//      .headers(jsonType)  //设置body数据格式
//      .formParam("login_name", "jun") //form 表单的 property
//      .formParam("email", "www.lijun07@qq.com"))

  /**
    * 4、设置线程数
    */
  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))

  /**
    * 5、运行 Engine.scala 开始压测
    */
}
