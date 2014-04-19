![REDSOFT] (http://i.imgur.com/LHvTZ.png "iFramework") Powerfull extension for popular GWTP framework.

## Maven settings

###Latest Version
[Find the lastest version listed here](https://github.com/AlexOreshkevich/iFramework) 

###Maven Central Jars
[Download Jars from here](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22pro.redsoft.iframework%22) or follow the manual dependency download procedure.

####Define dependencies

* Properties:
```xml
<properties>
    <iframework.version>0.3</iframework.version>
</properties>
```

* Dependencies:
```xml
   <!-- GWTP MVP (client-side) extension -->
    <dependency>
      <groupId>pro.redsoft.iframework</groupId>
      <artifactId>iframework-gwtpx</artifactId>
      <version>${iframework.version}</version>
      <scope>provided</scope>
   </dependency>

   <!-- GWTP server-side extension -->
   <dependency>
      <groupId>pro.redsoft.iframework</groupId>
      <artifactId>iframework-gwtpx-servlet</artifactId>
      <version>${iframework.version}</version>
      <scope>compile</scope>
   </dependency>

   <!-- JAXB extension -->
    <dependency>
      <groupId>pro.redsoft.iframework</groupId>
      <artifactId>iframework-jaxbx</artifactId>
      <version>${iframework.version}</version>
    </dependency>
```

## Snapshots
Snapshots of the current version are regularly deployed. If you want to experiment with bleeding edge, evolving versions of the platform, feel free to collaborate in use of these snapshot dependencies. 

* Here's how you use the snapshot dependencies. Get the lastest snapshot version from [here](https://github.com/AlexOreshkevich/iFramework).
```xml
<properties>
    <iframework.version>0.4-SNAPSHOT</iframework.version>
</properties>

<repositories>
    <repository>
        <id>sonatype.snapshots</id>
        <name>Sonatype snapshot repository</name>
        <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        <layout>default</layout>
    </repository>
</repositories>
```
