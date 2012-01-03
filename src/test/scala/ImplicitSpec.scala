import org.specs2.mutable._
import java.io.{File}

object ImplicitSpec extends Specification with CompilerMatcher {
  def clearTempDir {
    val tmp = new File("tmp")
    if (tmp.exists) deleteAll(tmp)
    tmp.mkdirs() // you need this for copyFileFromResource    
  }
  
  def file(name: String) = new File("src/test/resources/" + name)
  
  "implicits in local scope win over implicits in outer scope" in {
    ("Main.test" :: Nil, file("local_v_outer.scala") :: Nil) must evaluateTo("localIntFoo:1", outdir = "./tmp")
  }
  
  "implicits in local scope win over implicits in explicit import" in {
    ("Main.test" :: Nil, file("local_v_explicit.scala") :: Nil) must evaluateTo("localIntFoo:1", outdir = "./tmp")
  }
  
  "implicits from explicit imports tie with implicts from wildcard imports" in {
    (file("explicit_v_wildcard.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  "implicits from wildcard imports tie with implicits in package object" in {
    (file("wildcard_v_package.scala") :: file("package_p.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  "implicits from explicit imports tie with implicits in package object" in {
    (file("explicit_v_package.scala") :: file("package_p.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  "implicits in local scope win over implicits in package object" in {
    ("p.Main.test" :: Nil, file("local_v_package.scala") :: 
      file("package_p.scala") :: Nil) must evaluateTo("localIntFoo:1", outdir = "./tmp")
  }
  
  "specific views win over less specific views" in {
    ("Main.test" :: Nil, file("less_specific_view.scala") :: Nil) must evaluateTo("localIntToBar:1", outdir = "./tmp")
  }
  
  "implicits imported from an object win over implicits imported from the parent traits of the object" in {
    ("Main.test" :: Nil, file("parent_trait_v_object.scala") :: Nil) must evaluateTo("extendedImportIntFoo:1", outdir = "./tmp")
  }
  
  "implicits in outer scope win over implicts in an inherited trait" in {
    ("Main.test" :: Nil, file("outer_v_parent_trait.scala") :: Nil) must evaluateTo("memberIntFoo:1", outdir = "./tmp")
  }
  
  "views in local scope tie with more specificis but imported views" in {
    (file("local_v_imported_more_specific.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  "views imported from an object tie with more specific views imported from the parent traits of the object" in {
    (file("object_v_parent_trait_more_specific.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  "implicits in package object win over implicits in T's companion object" in {
    ("p.Main.test" :: Nil,
      file("package_v_ts_companion.scala") ::
      file("package_v_ts_companion_p.scala") ::
      Nil) must evaluateTo("packageObjectAutomobileFoo:Automobile()", outdir = "./tmp")
  }
  
  "implicits in T's companion object win over implicits in T's parent trait's companion object" in {
    ("Main.test" :: Nil,
      file("ts_companion_ts_parent_companion.scala") :: Nil) must evaluateTo("companionAutomobileFoo:Automobile()", outdir = "./tmp")
  }
  
  "implicits in T's package object is part of implicit scope" in {
    ("Main.test" :: Nil,
      file("ts_package.scala") ::
      file("ts_package_p.scala") :: Nil) must evaluateTo("packageObjectAutomobileFoo:Automobile()", outdir = "./tmp")
  }
  
  "implicits in T's package object tie with implicits in T's companion object" in {
    (file("ts_package_v_ts_companion.scala") ::
     file("ts_package_v_ts_companion_p.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  "implicits in T's type constructor's companion object tie with implicits in T's type parameter's companion object" in {
    (file("ts_tycons_companion_v_ts_typrams_companion.scala") :: Nil) must not be compiled(outdir = "./tmp")
  }
  
  clearTempDir
}
