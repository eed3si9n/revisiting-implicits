package p
 
object `package` {
  implicit val packageObjectAutomobileFoo = new CanFoo[Automobile] {
    def foos(x: Automobile) = "packageObjectAutomobileFoo:" + x.toString
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
}
 
trait CanFoo[A] {
  def foos(x: A): String
}
 
case class Automobile() {}
object Automobile {
}
