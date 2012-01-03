package p
 
trait CanFoo[A] {
  def foos(x: A): String
}
 
case class Automobile() {}
object Automobile {
  implicit val companionAutomobileFoo = new CanFoo[Automobile] {
    def foos(x: Automobile) = "companionAutomobileFoo:" + x.toString
  }
}
 
object Main extends App {  
  def test(): String = {    
    foo(Automobile())
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
  println(test())
}
