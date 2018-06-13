package akkoid

import akka.http.scaladsl.model.{ContentType, ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives
import akkoid.shared.ApiTest
import scalatags.Text.all.{script, _}
import upickle.Js

import scala.concurrent.ExecutionContext.Implicits.global

object Template {

  val txt = "<!DOCTYPE html>" +
    html(
      head(
        meta(charset:="utf-8"),
        meta(name:="viewport", content:="width=device-width, initial-scale=1.0"),
        meta(name:="description", content:="Layout side menu hides on mobile, just like Pure website"),
        link(
          rel:="stylesheet",
          `type`:="text/css",
          href:="/assets/lib/purecss/build/pure-min.css"
        ),
        link(
          rel:="stylesheet",
          `type`:="text/css",
          href:="/assets/css/side-menu.css"
        )
      ),
      body(
          script(tpe:="text/javascript", src:="/assets/client-fastopt.js")
      )
    )
}


class WebService() extends Directives {


  val route = {
    pathSingleSlash {
      get {
        complete {
          HttpEntity(ContentTypes.`text/html(UTF-8)`, Template.txt
          )
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
