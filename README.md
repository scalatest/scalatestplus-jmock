# jmock
ScalaTest + JMock provides integration support between ScalaTest and JMock.

**Usage**

To use it for ScalaTest 3.2.7 and JMock 2.8.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "jmock-2-8" % "3.2.7.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>jmock-2-8_2.13</artifactId>
  <version>3.2.7.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```