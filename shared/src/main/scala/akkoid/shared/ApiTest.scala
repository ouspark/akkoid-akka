package akkoid.shared
import upickle.default.{ReadWriter => RW, macroRW}

trait ApiTest {

  def messageTest(msg: String, name: String): Messages
  def messageTestA(): String
}

case class Messages(msg: String, name: String)

object Messages {
  implicit def msgRW: RW[Messages] = macroRW
}
