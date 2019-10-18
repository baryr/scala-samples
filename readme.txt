From zero to a Scala SBT project 

1) Create a new folder for your project, 
   e.g.: mkdir myProject and cd myProject

2) Create the project folder structure:
   mkdir src\main\scala

3) Create a file in src/main/scala e.g.
   echo object Hi { def main(args: Array[String]) = println("Hi!") } > hw.scala (Windows)

4) create a file named build.sbt in your project root (e.g. in myProject/build.sbt) for example:
   name := "test-scala-app"
   

version := "1.0"
   

scalaVersion := "2.11.7"



5) Type sbt run to comple + run the project (Should print "Hi!" to the console)
   Type sbt ~compile to have the project continually compile when files change

6) Open %userprofile%\.sbt\plugins\build.sbt
  Add the following lines (the empty line in between is important)

  addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.2.0")

  addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")

7) type sbt eclipse, 
   if you want to download sources add with-source=true e.g. 
   sbt eclipse with-source=true