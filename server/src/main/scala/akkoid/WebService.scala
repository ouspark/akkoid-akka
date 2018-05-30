package akkoid

import akka.http.scaladsl.model.{ContentType, HttpEntity}
import akka.http.scaladsl.server.Directives
import akkoid.shared.{ApiTest, SharedMessages}
import akkoid.twirl.Implicits._
import upickle.Js

import scala.concurrent.ExecutionContext.Implicits.global

class WebService() extends Directives {

  val route = {
    pathSingleSlash {
      get {
        complete {
          akkoid.html.index.render(SharedMessages.itWorks)
        }
      }
    } ~
    post {
      path("api" / Segments){ s =>
        extract(_.request.entity match {
          case HttpEntity.Strict(nb: ContentType.NonBinary, data) =>
            data.decodeString(nb.charset.value)
        }) { e =>
          complete {
            APIServer.route[ApiTest](Message)(
              autowire.Core.Request(
                s,
                upickle.json.read(e).asInstanceOf[Js.Obj].value.toMap
              )
            ).map(upickle.json.write(_))
          }
        }
      }

    } ~
    pathPrefix("assets" / Remaining) { file =>
      // optionally compresses the response with Gzip or Deflate
      // if the client accepts compressed responses
      encodeResponse {
        getFromResource("public/" + file)
      }
    }
  }
}
