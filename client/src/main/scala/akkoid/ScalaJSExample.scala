package akkoid

import akkoid.servcie.AjaxClient
import akkoid.shared.ApiTest
import autowire._
import org.scalajs.dom

import scala.concurrent.ExecutionContext.Implicits.global

object ScalaJSExample {

  def main(args: Array[String]): Unit = {
//    dom.document.getElementById("scalajsShoutOut").textContent = Await.result(AjaxClient[ApiTest].messageTest().call(), 1 second)
    for(s <- AjaxClient[ApiTest].messageTest("first", "second").call()) {
      dom.document.getElementById("scalajsShoutOut").textContent = s.name
    }

//    AjaxClient[ApiTest].messageTestA().call().onComplete {
//      case Success(t) => dom.document.getElementById("scalajsShoutOut").textContent = t
//      case Failure(_) => dom.document.getElementById("scalajsShoutOut").textContent = "False Alarm"
//    }
  }
}
