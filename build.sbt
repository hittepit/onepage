name := "onepage"

version := "1.0"

scalaVersion := "2.9.2"

seq(webSettings :_*)

seq(ScctPlugin.instrumentSettings : _*)

libraryDependencies += "org.scalatra" % "scalatra" % "2.2.0" exclude("com.typesafe.akka", "akka-actor")

libraryDependencies += "org.scalatra" % "scalatra-scalate" % "2.2.0"

libraryDependencies += "org.scalatra" % "scalatra-fileupload" % "2.2.0"

libraryDependencies += "org.scalatra" % "scalatra-jerkson" % "2.1.1"

libraryDependencies += "org.scalatra" % "scalatra-json" % "2.2.0"

libraryDependencies += "org.json4s"   %% "json4s-jackson" % "3.0.0"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.1.5.v20120716" % "container"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.0"

libraryDependencies += "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

libraryDependencies += "org.scalatra" %% "scalatra-scalatest" % "2.2.0" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

testOptions in Test += Tests.Argument("-oD")