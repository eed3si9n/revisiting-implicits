package p
 
object `package` {
  val name = "packageObjectAutomobileFoo"
  implicit val packageObjectAutomobileFoo = new CanFoo[Automobile] {
    def foos(x: Automobile) = "packageObjectAutomobileFoo:" + x.toString
  }
}
