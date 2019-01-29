package controllers

import javax.inject._
import play.api.libs.json._
import play.api.libs.ws._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class Notification @Inject()(cc: ControllerComponents,ws:WSClient) extends AbstractController(cc) {

  object Token {
    var tok = "default"
  }

  def webpush() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.webpush())
  }
  def getToken(token:String) : Action[AnyContent] = Action.async { implicit request =>

    val reqGet = ws.url("http://localhost:9000/webpush").withMethod("POST")
    val responseInfo = reqGet.execute()
    responseInfo.map { raw =>
      Console.print("")
      Token.tok=token
      Console.print(Token.tok)
      Ok("Token received")
    }
  }
  def sendNotification() : Action[AnyContent] = Action.async { implicit request =>
    val data: JsValue = Json.obj(
      "data" -> Json.obj(
        "notification" -> Json.obj(
          "title" -> "FCM Message",
          "body" -> "This is an FCM Message"
        )
      ),
      "to" -> Token.tok
    )
    val reqPost: WSRequest = ws.url("https://fcm.googleapis.com/fcm/send")
      .withHttpHeaders("Content-Type" -> "application/json")
      .withHttpHeaders("Authorization" -> "key=AAAA3QWSi_U:APA91bHJ443UELVeKUf0TSRolVoQ5YPKg25AI8LxQjds4fCNf_yd_AqNsLX1Lw4H1jVT_sWZqzF9BNKX720I048c4kD33m_y7uJQSpIFZppToTsVqOx_W-CdjWMe-YiMLfzs08so8UOA")
    reqPost.post(data).map { response =>
      Ok(Json.prettyPrint(data))
    }
  }
}