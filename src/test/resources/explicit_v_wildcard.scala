trait CanFoo[A] {
  def foos(x: A): String
}
 
object Def {
  val name = "importIntFoo"
  implicit val importIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "importIntFoo:" + x.toString
  }
}
 
object WildDef {
  val name = "wildcardImportIntFoo"
  implicit val wildcardImportIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "wildcardImportIntFoo:" + x.toString
  }
}
 
object Main {
  def test(): String = {
    import Def.{importIntFoo, name}
    import WildDef._
 
    println(name)
    foo(1)
  }
 
  def foo[A:CanFoo](x: A): String = implicitly[CanFoo[A]].foos(x)
}
