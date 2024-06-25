# jmock
ScalaTest + JMock provides integration support between ScalaTest and JMock.

**Usage**

To use it for ScalaTest 3.2.19 and JMock 2.13.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "jmock-2-13" % "3.2.19.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>jmock-2-13_3</artifactId>
  <version>3.2.19.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```