package akkoid

import upickle.default._
import upickle.Js
import akkoid.shared.{ApiTest, Messages}

object APIServer extends autowire.Server[Js.Value, Reader, Writer]{
  def read[Result: Reader](p: Js.Value) = upickle.default.readJs[Result](p)
  def write[Result: Writer](r: Result) = upickle.default.writeJs(r)

}

object Message extends ApiTest {
  override def messageTest(msg: String, name: String): Messages = Messages(name, msg)

  override def messageTestA(): String = "Test message"
}
