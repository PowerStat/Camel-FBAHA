# PowerStat's Apache Camel FBAHA

This is a Apache Camel 3 Java implementation of AVM'S Fritz!Box AHA API.

See:

* [AVM Entwicklungssupport](https://avm.de/service/schnittstellen/)

Please note that I am not related to AVM in any way and that AVM will not support this code in any way!

## Installation

Because this library is only useful for camel developers the installation depends on your build environment.

For example when using Apache Maven you could add the following dependency to your project:

    <dependency>
      <groupId>de.powerstat.camel.component</groupId>
      <artifactId>fbaha</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>

Please add the following entry to your maven `settings.xml`:

    <server>
      <id>nvd</id>
      <password>nvd api-key</password>
    </server>

The API-Key could be requested here: [National Vulnerability Database](https://nvd.nist.gov/developers/request-an-api-key)

Other build tools like gradle will work analogous.

To compile this project yourself you could use:

    mvn clean install org.pitest:pitest-maven:mutationCoverage site
    
or simply:

     mvn clean install

or for native image creation:

On windows Visual Studio 2022 is required and you have to call:

    "C:\Program Files\Microsoft Visual Studio\2022\Community\VC\Auxiliary\Build\vcvars64.bat" > nul

Compile and build image:

    mvn clean -Pnative package
    
Run the image:

    ./target/[imagename]

To find newer dependencies:

    mvn versions:display-dependency-updates
    
To find newer plugins:

    mvn versions:display-plugin-updates
    
To make a new release:

    mvn --batch-mode release:clean release:prepare release:perform
    git push -–tags
    git push origin main

To run checkstyle:

    mvn checkstyle:check
    
To run pmd:

    mvn pmd:check
    
To run spotbugs:

    mvn spotbugs:check
    
To run arch-unit:

    mvn arch-unit:arch-test
    
To run JDeprScan:

    mvn jdeprscan:jdeprscan jdeprscan:test-jdeprscan
    
To run toolchain:

    mvn toolchains:toolchain
    
If you use a sonar server:

    mvn sonar:sonar -Dsonar.token=<token>

If you use [infer][https://fbinfer.com/]:

    infer run -- mvn clean compile

To create a spdx:

    mvn spdx:createSPDX

To create a cycloneDX:

    mvn cyclonedx:makeBom
    
To upload bom to dependency-track:

    mvn dependency-track:upload-bom
    
To look for dependency-track findings: 

    mvn dependency-track:findings

## Usage

For usage in your own projects please read the Javadoc's and follow the examples in the unittests.

## Contributing

If you would like to contribute to this project please read [How to contribute](CONTRIBUTING.md).

## License

This code is licensed under the [Apache License Version 2.0](LICENSE.md).
