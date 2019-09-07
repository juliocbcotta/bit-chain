object Versions {
    const val kotlin = "1.3.50"
    const val dagger = "2.24"
    const val retrofit = "2.6.1"
}

object Dependencies {

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_test_junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    const val androidx_core = "androidx.core:core-ktx:1.1.0"
    const val androidx_appcompat = "androidx.appcompat:appcompat:1.1.0"
    const val androidx_preferences = "androidx.preference:preference:1.1.0"
    const val androidx_lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:2.0.0"
    const val androidx_constraint_layout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val androidx_cardview = "androidx.cardview:cardview:1.0.0"

    // AndroidX Testing
    const val androidx_test_rules = "androidx.test:rules:1.2.0"
    const val androidx_test_runner = "androidx.test:runner:1.2.0"
    const val androidx_arch_core_testing = "androidx.arch.core:core-testing:2.1.0"
    const val androidx_espresso_core = "androidx.test.espresso:espresso-core:3.2.0"
    const val androidx_ext_junit = "androidx.test.ext:junit:1.1.1"
    const val androidx_ui_automator = "androidx.test.uiautomator:uiautomator:2.2.0"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger_android_compiler = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // RxJava
    const val rx_android = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val rx_java = "io.reactivex.rxjava2:rxjava:2.2.12"

    const val mp_android_chart = "com.github.PhilJay:MPAndroidChart:v3.1.0"

    // Square
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_rx_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:4.1.1"

    const val junit = "junit:junit:4.12"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    const val mockito_inline = "org.mockito:mockito-inline:2.28.2"
    const val mock_webserver = "com.squareup.okhttp3:mockwebserver:4.1.1"
    const val rx_idler = "com.squareup.rx.idler:rx2-idler:0.9.1"

    const val gson = "com.google.code.gson:gson:2.8.5"
}