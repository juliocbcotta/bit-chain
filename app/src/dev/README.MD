dev flavor exists only because AGP does not allow to set usesCleartextTraffic="true" in 
androidTest/AndroidManifest.xml.

So this flavor should be used to run instrumented tests in Android +8.