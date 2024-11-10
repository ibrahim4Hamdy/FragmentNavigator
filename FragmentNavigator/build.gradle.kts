plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.qena.navigation.fragments"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
publishing {
    publications {
        create<MavenPublication>("release") {
            from(components.findByName("release"))
            groupId = "com.qena.navigator.fragment"
            artifactId = "navigator"
            version = "0.0.1-alpha"

        }
    }
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}



dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}