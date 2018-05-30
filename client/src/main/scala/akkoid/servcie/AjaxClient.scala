package akkoid.servcie

import org.scalajs.dom
import ujson.Transformable.fromString
import upickle.Js
import upickle.default._

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object AjaxClient extends autowire.Client[Js.Value, Reader, Writer] {
  override def doCall(req: Request): Future[Js.Value] = {
    dom.ext.Ajax.post(
      url = "/api/" + req.path.mkString("/"),
      data = upickle.json.write(Js.Obj(mutable.LinkedHashMap(req.args.toSeq:_*)))
    ).map(_.responseText)
      .map(upickle.json.read(_))
  }

  override def read[Result: Reader](p: Js.Value) = readJs[Result](p)
  override def write[Result: Writer](r: Result) = writeJs(r)
}
