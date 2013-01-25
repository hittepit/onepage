name := "onepage"

version := "1.0"

scalaVersion := "2.9.2"

seq(webSettings :_*)

libraryDependencies += "org.scalatra" % "scalatra" % "2.1.0"

libraryDependencies += "org.scalatra" % "scalatra-json" % "2.2.0-RC3"

libraryDependencies += "org.json4s"   %% "json4s-jackson" % "3.0.0"

libraryDependencies += "org.scalatra" % "scalatra-scalate" % "2.1.0"

libraryDependencies += "org.scalatra" % "scalatra-fileupload" % "2.1.0"

libraryDependencies += "com.codahale" % "jerkson_2.9.1" % "0.5.0"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.1.5.v20120716" % "container"

//libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.0.0"

libraryDependencies += "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))