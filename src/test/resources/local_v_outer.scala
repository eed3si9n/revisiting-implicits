trait CanFoo[A] {
  def foos(x: A): String
}
 
object Main {
  implicit val memberIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "memberIntFoo:" + x.toString
  }
 
  def test(): String = {
    implicit val localIntFoo = new CanFoo[Int] {
      def foos(x: Int) = "localIntFoo:" + x.toString
    }
 
    foo(1)
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
}
