/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import org.gradle.api.logging.configuration.WarningMode
import org.gradle.testkit.runner.BuildResult
import org.gradle.util.GradleVersion
import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType
import org.jetbrains.kotlin.gradle.testbase.*
import org.junit.jupiter.api.DisplayName

@JsGradlePluginTests
class Kotlin2WasmGradlePluginIT : KGPBaseTest() {
    private val defaultJsOptions = BuildOptions.JsOptions(
        useIrBackend = true,
        jsCompilerType = KotlinJsCompilerType.IR,
    )

    final override val defaultBuildOptions =
        super.defaultBuildOptions.copy(
            jsOptions = defaultJsOptions,
            warningMode = WarningMode.Summary
        )

    private fun BuildResult.checkIrCompilationMessage() {
        //assertOutputContains(USING_JS_IR_BACKEND_MESSAGE)
    }

    @DisplayName("default output file is generated")
    @GradleTest
    fun testDefaultOutputFile(gradleVersion: GradleVersion) {
        project("kotlin2JsNoOutputFileProject", gradleVersion) {
            build("build") {
                checkIrCompilationMessage()
                assertFileExists(kotlinClassesDir(sourceSet = "test").resolve("kotlin2JsNoOutputFileProject_test.js"))
            }
        }
    }
}