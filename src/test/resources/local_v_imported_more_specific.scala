trait Bar {
  def bar: String
}
 
object Def {
  implicit def importedIntToBar(x: Int) = new Bar { def bar = "importedIntToBar:" + x.toString }
}
 
object Main {
  def test(): String = {
    import Def.importedIntToBar
    implicit def localAnyToBar(x: Any) = new Bar { def bar = "localAnyToBar:" + x.toString }
 
    bar(1)
  }
 
  def bar[A <% Bar](x: A): String = x.bar
}
