package akkoid.shared

object SharedMessages {
  def itWorks = data.toString
//
//  import boopickle.Default._
  val data = Seq("hello", "word")
//  val buf = Pickle.intoBytes(data)
//  val helloWorld = Unpickle.apply[Seq[String]].fromBytes(buf)
}
