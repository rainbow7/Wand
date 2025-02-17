plugins {
    alias(libs.plugins.wand.kotlinMultiplatform)
    alias(libs.plugins.wand.shared)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
        }

        iosMain.dependencies {
        }

        commonMain.dependencies {

        }
    }
}

dependencies {
    debugImplementation(libs.compose.ui.tooling)
}


