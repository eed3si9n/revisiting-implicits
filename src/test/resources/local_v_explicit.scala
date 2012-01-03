trait CanFoo[A] {
  def foos(x: A): String
}
 
object Def {
  implicit val importIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "importIntFoo:" + x.toString
  }
}
 
object Main {
  def test(): String = {
    implicit val localIntFoo = new CanFoo[Int] {
      def foos(x: Int) = "localIntFoo:" + x.toString
    }
    import Def.importIntFoo
 
    foo(1)
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
}
