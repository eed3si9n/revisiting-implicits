trait CanFoo[A] {
  def foos(x: A): String
}
 
object CanFoo {
  implicit val canFooAutomobileFoo = new CanFoo[Automobile] {
    def foos(x: Automobile) = "canFooAutomobileFoo:" + x.toString
  }
}
 
case class Automobile()
object Automobile {
  implicit val companionAutomobileFoo = new CanFoo[Automobile] {
    def foos(x: Automobile) = "companionAutomobileFoo:" + x.toString
  }
}
 
object Main {
  def test(): String = {
    foo(Automobile())
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
}
