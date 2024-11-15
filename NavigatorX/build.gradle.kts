plugins {
    id("com.android.library")
    id("maven-publish")
}
group = "com.github.ibrahim4Hamdy"
version = "0.0.17-alpha"
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles( "proguard-rules.pro") //added this line

        }
    }

//    sourceSets["main"].java.srcDirs("src/main/java")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))      // << --- ADD This
    }
}
//===============================

java {
    sourceCompatibility = JavaVersion.VERSION_17            // << --- ADD This
    targetCompatibility = JavaVersion.VERSION_17
}

//tasks.register<Jar>("sourcesJar") {
//    from(android.sourceSets["main"].java.srcDirs)
//    archiveClassifier.set("sources")
//}
//
//tasks.register<Jar>("javadocJar") {
//    archiveClassifier.set("javadoc")
//    from("$buildDir/javadoc")
//}
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.ibrahim4Hamdy"
            artifactId = "FragmentNavigator"
            version = "0.0.17-alpha"
            afterEvaluate {
                from(components["release"])
//                artifact("sourcesJar")
//                artifact("javadocJar")
//                tasks.register("prepareReleaseJar", Jar::class.java) {
//                    from(android.sourceSets["main"].java.srcDirs)
//                    archiveClassifier.set("classes")
//                }
            }

        }

    }

}




dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.13.0-alpha07")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}