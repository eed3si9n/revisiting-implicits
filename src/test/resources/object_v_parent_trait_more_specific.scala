trait Bar {
  def bar: String
}
trait Super {
  implicit def importIntToBar(x: Int) = new Bar { def bar = "importIntToBar:" + x.toString }
}
object Def extends Super {}
object ExtendedDef extends Super {
  implicit def extendedImportAnyToBar(x: Any) = new Bar { def bar = "extendedImportAnyToBar:" + x.toString }
}
 
object Main {
  def test(): String = {
    import Def.importIntToBar
    import ExtendedDef.extendedImportAnyToBar
 
    bar(1)
  }
 
  def bar[A <% Bar](x: A): String = x.bar
}
