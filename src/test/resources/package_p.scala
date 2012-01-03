package object p { 
  val name = "packageObjectIntFoo"
  implicit val packageObjectIntFoo = new CanFoo[Int] {
    def foos(x: Int) = "packageObjectIntFoo:" + x.toString
  }
}
