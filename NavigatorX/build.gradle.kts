plugins {
    id("com.android.library")
    id("maven-publish")
}
group = "com.github.ibrahim4Hamdy"
version = "0.0.11-alpha"
android {
    namespace = "com.qena.navigation.fragments"
    compileSdk = 34
    buildToolsVersion ="35"

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles( "proguard-rules.pro") //added this line

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
publishing {
    publications {
        register<MavenPublication>("java") {
            afterEvaluate {
                from(components.findByName("java"))
                groupId = "com.github.ibrahim4Hamdy"
                artifactId = "FragmentNavigator"
                version = "0.0.11-alpha"


            }

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