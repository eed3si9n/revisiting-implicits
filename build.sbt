organization := "com.eed3si9n"

name := "revisiting-implicits"

version := "0.1-SNAPSHOT"

libraryDependencies <++= scalaVersion { sv =>
  Seq(
    "org.specs2" %% "specs2" % "1.7.1" % "test",
    "org.scala-lang" % "scala-compiler" % sv
  )
}
