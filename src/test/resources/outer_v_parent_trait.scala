trait CanFoo[A] {
  def foos(x: A): String
}
trait Super {
  implicit lazy val superIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "superIntFoo:" + x.toString
  }
}
object Main extends Super {
  implicit val memberIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "memberIntFoo:" + x.toString
  }
 
  def test(): String = {
    foo(1)
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
}
