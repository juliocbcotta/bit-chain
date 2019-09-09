## Bit-Chain (The smallest block you can... chain?)

This project loads and shows a chart of the Market Price (USD) to the blockchain. Yeah, just one chart, I said it is small...

<img src="https://github.com/BugsBunnyBR/bit-chain/raw/master/screenshot.png" width="50%"/>

While the feature is simple, the project is a showcase for an Android architecture
for a multi-module project with a mixed approach for organization, with **feature**[1] and **support** modules.

[1] It does not mean it applies android plugin for dynamic feature. It means that a feature module contains an app feature.

#### Support modules
 - `:app` module contains the app Application class, a Dagger AppComponent and it bundles together some Dagger modules to make the provided dependencies available to children subcomponents.
 - `:networking` module is mostly empty but defines a Dagger module that configures the network libraries. In a more complex app it would have custom interceptors, adapters and converters.
 - `:components` module is meant to have custom views like `ChartView` that are standardized through the app.
 - `:theme` module is meant to a central place to share the `AppTheme` across modules.
 - `:android-di` module contains Dagger annotations and a `ViewModelFactory`.
 
 #### Feature module
 - `:charts` module loads the stats from blockchain API and display in a chart (MPAndroidChart).

Feature modules are meant to be 100% independent from any other feature module, but they can depend on any number of support modules.

Inside `:charts` module you will find a typical Clean Architecture in MVVM style, which means:

 - A presentation package with some View class and a ViewModel.
 - A domain package with UseCases, business models and Repository interface.
 - A data package with the Repository implementation, caches, REST API access classes/interfaces.
 - A di package that bundles together the dependencies of this feature.
 
 The data package is a bit bigger as it contains a repository implementation, a cache and a remote service, each with their own models.

     The main advantages of "feature" modules are:
     - Related code is kept together;
     - Easy to keep;
     - Enforces independence with other features;
     - Packages with a small amount of code;
         
In a large feature, it can be broken in different Gradle modules inside a parent folder for that feature. 
In that case, you can have some feature-specific support modules usually named with a prefix or suffix "common".

#### Tests
Unit tests for `:charts` are written inside the module itself in the `charts/src/test/java/br/com/bit/chain/charts` folder.

Instrumented tests are placed in `:app` as a means to easy maintenance.
This project uses Dagger as DI framework, which causes some headaches when trying 
to replace any dependence, It gets a bit easier when the dependency is at root level, 
as the BASE_URL for REST web services, for instance.
See `app/src/androidTest/java/br/com/bit/chain/BaseTest.kt` to see how I solved it.

### Flavors
This project is split into three flavors.
- `dev`
- `stage`
- `prod`

The idea is that each flavor can point to a different environment, like a different API endpoint, but I also used it to enable
running `MockWebServer` for instrumented tests in Android +8 by configuring the `app/src/dev/AndroidManifest.xml` and enabling CLEAR TEXT transport.
This avoids any possible production build to have that configuration enabled.
So it is desirable to run instrumented tests in `DevDebug` configuration.

### buildSrc
This folder contains the definitions of the app Gradle dependencies and some build configs.

### Mappers
If you look at the source code and find some `Extensions.kt` files, 
they contain extension functions that execute mapping from a lower layer model to the current layer model.

### ktlint and detekt
Both tools are applied to the project with mostly of the standard configurations.

### Libraries
- Kotlin
- CardView
- ConstraintLayout
- Dagger and Dagger Android
- Retrofit
- Okhttp and OkHttpLoggingInterceptor
- Gson
- RxJava
- RxAndroid
- Android X libraries
- MPAndroidChart
- Espresso
- RxIdler
- Mockito inline and Mockito Kotlin
- MockWebServer
- ktlint and detekt


### TODO
- Improve error handling with proper differentiation of errors.
- create a cache module with a simple abstraction over SharedPreferences.
 
