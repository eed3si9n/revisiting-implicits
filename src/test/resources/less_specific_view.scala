trait Bar {
  def bar: String
}
 
object Main {
  def test(): String = {
    implicit def localAnyToBar(x: Any) = new Bar { def bar = "localAnyToBar:" + x.toString }
    implicit def localIntToBar(x: Int) = new Bar { def bar = "localIntToBar:" + x.toString }
 
    bar(1)
  }
 
  def bar[A <% Bar](x: A): String = x.bar
}
