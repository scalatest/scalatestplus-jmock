# jmock
ScalaTest + JMock provides integration support between ScalaTest and JMock.

**Usage**

To use it for ScalaTest 3.2.10 and JMock 2.12.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "jmock-2-12" % "3.2.10.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>jmock-2-12_2.13</artifactId>
  <version>3.2.10.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```