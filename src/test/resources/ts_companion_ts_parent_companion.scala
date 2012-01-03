trait CanFoo[A] {
  def foos(x: A): String
}
 
trait Vehicle {}
object Vehicle {
  implicit val vehicleAutomobileFoo = new CanFoo[Automobile] {
    def foos(x: Automobile) = "vehicleAutomobileFoo:" + x.toString
  }
}
 
case class Automobile() extends Vehicle {}
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
