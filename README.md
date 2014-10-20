StorageCloudSim
===============

[![Build Status](https://travis-ci.org/toebbel/StorageCloudSim.svg?branch=master)](https://travis-ci.org/toebbel/StorageCloudSim)

This is an extension to to existing toolkit [CloudSim](http://code.google.com/p/cloudsim/). Features for modeling and simulation of Storage as a Service (STaaS) Clouds were added. The currently available documentation can be found in the source code ([java doc](http://downloads.tobiassturm.de/projects/storagecloudsim/doc/index.html)) and in the bachelor thesis, that will be linked in the near future [here](http://tobiassturm.de/projects/StorageCloudSim.html).

Status
------
More work to be done ... To be continued

Requirements
------------
* Java 7 Update 21 (runs with OpenJDK 7 and OracleJDK 7 + 8)
* CloudSim (tested with [version 3.0.3](http://code.google.com/p/cloudsim/downloads/list)) (patched and included in this repo)
* [Simple 2.7.1](http://simple.sourceforge.net/download.php) (download via ivy)
* [Apache Commons CLI](http://commons.apache.org/proper/commons-cli/index.html) (download via ivy)
* and [commons-math3-3.2](http://commons.apache.org/proper/commons-math/download_math.cgi) (download via ivy)

Patched Cloudsim
----------------
Since the original Cloudsim 3.0.3 misses a method for retrieving bandwidth information from the network topology, we patched the original version and included it for convenience. The actual diff is inside the cloudsim source directory.

Build the Project
-----------------
We provided tasks in the ant build file to bootstrap ivy if oyu have not installed it. [Ivy](https://ant.apache.org/ivy/) will fetch all dependencies for you :)
```
ant init-ivy
```
After doing this, just type any of these commands to build/test/run the project
```
ant compile
ant test
ant run
```


Getting Started
---------------
Before you can run a simulation, you have to set up a scenario (or you use the default scenario that we provided in the 'example-scenario' folder). //TODO do that actually

## Creating a Cloud model
//TODO How to build
Cloud models are defined as XML files. You will pass these models into the StorageCloudSim.jar, when you start the simulation.
Use the CloudGenerator to generate a fresh XML file or modify one of the provided examples.
To see all parameters type
   java -jar CloudGenerator.jar -h

We used the following commands to generate the example cloud:
   java -jar CloudGenerator.jar --name RainyCloud -rootUrl rainy.org --location de --servers 3 --disks 3
That will generate a cloud that has 3 servers where each has 3 disks and each of them  has 1 TB capacity, which makes a total capacity of 9TB.

Remember: Whenever you should amend one of the following models, you have to re-generate the XML files so they can be parsed: CDMICloudCharacteristics, pricingPolicy, objectStorageServerModel, objectStorageDiskModel, ioLimits.

## Creating UsageSequences

