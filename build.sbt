name := "jmock-2.8"

organization := "org.scalatestplus"

version := "3.2.6.0"

homepage := Some(url("https://github.com/scalatest/scalatestplus-jmock"))

licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

developers := List(
  Developer(
    "bvenners",
    "Bill Venners",
    "bill@artima.com",
    url("https://github.com/bvenners")
  ),
  Developer(
    "cheeseng",
    "Chua Chee Seng",
    "cheeseng@amaseng.com",
    url("https://github.com/cheeseng")
  )
)

scalaVersion := "2.13.4"

crossScalaVersions := List("2.10.7", "2.11.12", "2.12.13", "2.13.4", "3.0.0-RC1")

libraryDependencies ++= Seq(
  "org.jmock" % "jmock-legacy" % "2.8.3",
  "org.scalatest" %% "scalatest-core" % "3.2.6", 
  "org.scalatest" %% "scalatest-flatspec" % "3.2.6" % "test", 
  "org.scalatest" %% "scalatest-funsuite" % "3.2.6" % "test", 
  "org.scalatest" %% "scalatest-funspec" % "3.2.6" % "test", 
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.6" % "test"
)

Test / scalacOptions ++= (if (isDotty.value) Seq("-language:implicitConversions") else Nil)

enablePlugins(SbtOsgi)

osgiSettings

OsgiKeys.exportPackage := Seq(
  "org.scalatestplus.jmock.*"
)

OsgiKeys.importPackage := Seq(
  "org.scalatest.*",
  "org.scalactic.*", 
  "scala.*;version=\"$<range;[==,=+);$<replace;"+scalaBinaryVersion.value+";-;.>>\"",
  "*;resolution:=optional"
)

OsgiKeys.additionalHeaders:= Map(
  "Bundle-Name" -> "ScalaTestPlusJMock",
  "Bundle-Description" -> "ScalaTest+JMock is an open-source integration library between ScalaTest and JMock for Scala projects.",
  "Bundle-DocURL" -> "http://www.scalatest.org/",
  "Bundle-Vendor" -> "Artima, Inc."
)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  Some("publish-releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>https://github.com/scalatest/scalatestplus-jmock</url>
    <connection>scm:git:git@github.com:scalatest/scalatestplus-jmock.git</connection>
    <developerConnection>
      scm:git:git@github.com:scalatest/scalatestplus-jmock.git
    </developerConnection>
  </scm>
)

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Temporary disable publishing of doc in dotty, can't get it to build.
publishArtifact in (Compile, packageDoc) := !scalaBinaryVersion.value.startsWith("3.")

scalacOptions in (Compile, doc) := Seq("-doc-title", s"ScalaTest + JMock ${version.value}")