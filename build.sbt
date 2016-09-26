lazy val commonSettings = Seq(
  organization := "edu.illinois.cs.cogcomp",
  version := "0.1.0",
  autoScalaLibrary := false,
  resolvers += "CogcompSoftware" at "http://cogcomp.cs.illinois.edu/m2repo/",
  crossPaths := false
)

name := "fol_ilp"


                       
resolvers += Resolver.mavenLocal

resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"

resolvers +=
"Local Maven Repository 2" at "file:///" + Path.userHome.absolutePath + "/.m2/repository"

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.12" % "test",
      "org.apache.commons" % "commons-lang3" % "3.0",
      "edu.illinois.cs.cogcomp" % "illinois-inference" % "0.6.0"      
      // "net.sf.trove4j" % "trove4j" % "3.0.3",
      // "org.apache.commons" % "commons-math3" % "3.6.1"
    )
  )


lazy val core_lang = (project in file("core-lang")).dependsOn(core).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.antlr" % "antlr4-runtime" % "4.5.3"
    )
  )

lazy val java_lang = (project in file("java_interface")).dependsOn(core).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "edu.illinois.cs.cogcomp" % "LBJava" % "1.2.13" % "test"
    )
  )

lazy val example = (project in file("example")).dependsOn(java_lang).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "edu.illinois.cs.cogcomp" % "LBJava" % "1.2.24"
    )
  )

lazy val main = project.in(file("."))
  .aggregate(core, core_lang, java_lang, example)


