package com.luamas.idea.plugin.module

import com.intellij.icons.AllIcons
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import javax.swing.Icon


/**
 * @author Anna Bulenkova
 */
class DemoModuleType :
    ModuleType<DemoModuleBuilder>(ID) {
    override fun createModuleBuilder(): DemoModuleBuilder {
        return DemoModuleBuilder()
    }

    override fun getName(): String {
        return "模块类型示例"
    }

    override fun getDescription(): String {
        return "模块类型示例"
    }

    override fun getNodeIcon(b: Boolean): Icon {
        return AllIcons.General.Information
    }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        moduleBuilder: DemoModuleBuilder,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> {
        return super.createWizardSteps(wizardContext, moduleBuilder, modulesProvider)
    }

    companion object {
        private const val ID = "DEMO_MODULE_TYPE"
        val instance: DemoModuleType
            get() = ModuleTypeManager.getInstance().findByID(ID) as DemoModuleType
    }
}
