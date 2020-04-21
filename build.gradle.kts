plugins {
    val kotlinVersion = "1.3.72"
    java
    kotlin("jvm") version kotlinVersion
}
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    group = "com.luamas.idea.plugin"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
    dependencies {
        implementation(kotlin("stdlib-jdk8") as String){
            exclude(module="annotations")
        }
        testCompile("junit", "junit", "4.12")
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks {
        compileKotlin {
            kotlinOptions.jvmTarget = "1.8"
        }
        compileTestKotlin {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    tasks {
        withType<JavaCompile> {
            // 设置编码
            options.encoding = "UTF-8"
            //
            options.isFork = true
            // 增量编译
            options.isIncremental = true
        }
    }

}
