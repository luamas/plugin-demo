package com.luamas.idea.plugin.basicframework

import com.intellij.framework.FrameworkTypeEx
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModifiableRootModel
import icons.SdkIcons
import javax.swing.Icon
import javax.swing.JCheckBox
import javax.swing.JComponent


class DemoFramework protected constructor() : FrameworkTypeEx(FRAMEWORK_ID) {
    fun createProvider(): FrameworkSupportInModuleProvider {
        return object : FrameworkSupportInModuleProvider() {
            val frameworkType: FrameworkTypeEx
                get() = this@DemoFramework

            fun createConfigurable(model: FrameworkSupportModel): FrameworkSupportInModuleConfigurable {
                return object : FrameworkSupportInModuleConfigurable() {
                    fun createComponent(): JComponent? {
                        return JCheckBox("SDK Extra Option")
                    }

                    fun addSupport(
                        module: Module,
                        model: ModifiableRootModel,
                        provider: ModifiableModelsProvider
                    ) {
                        // This is the place to set up a library, generate a specific file, etc
                        // and actually add framework support to a module.
                    }
                }
            }

            fun isEnabledForModuleType(type: ModuleType<*>): Boolean {
                return true
            }
        }
    }

    val presentableName: String
        get() = "SDK Demo Framework"

    val icon: Icon
        get() = SdkIcons.Sdk_default_icon

    companion object {
        const val FRAMEWORK_ID = "com.luamas.idea.plugin.basicframework.DemoFramework"
    }
}
