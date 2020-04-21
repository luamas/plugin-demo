package com.luamas.idea.plugin.basicframework

import com.intellij.framework.FrameworkTypeEx
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModifiableRootModel
import icons.SdkIcons
import javax.swing.Icon
import javax.swing.JCheckBox
import javax.swing.JComponent


open class DemoFramework protected constructor() : FrameworkTypeEx(FRAMEWORK_ID) {
    override fun createProvider(): FrameworkSupportInModuleProvider {
        return object : FrameworkSupportInModuleProvider() {
            override fun getFrameworkType(): FrameworkTypeEx {
                return this@DemoFramework
            }

            override fun createConfigurable(model: FrameworkSupportModel): FrameworkSupportInModuleConfigurable {
                return object : FrameworkSupportInModuleConfigurable() {
                    override fun createComponent(): JComponent? {
                        return JCheckBox("SDK扩展项")
                    }

                    override fun addSupport(
                        module: Module,
                        model: ModifiableRootModel,
                        provider: ModifiableModelsProvider
                    ) {
                        // 这里可以建立一个库，生成一个特定的文件，等等，并向模块实际添加框架支持。
                    }
                }
            }

            override fun isEnabledForModuleType(type: ModuleType<*>): Boolean {
                return true
            }
        }
    }

    override fun getPresentableName(): String {
        return "SDK示例框架"
    }

    override fun getIcon(): Icon {
        return SdkIcons.Sdk_default_icon
    }

    companion object {
        const val FRAMEWORK_ID = "com.luamas.idea.plugin.basicframework.DemoFramework"
    }
}
