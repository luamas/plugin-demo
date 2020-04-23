package com.luamas.idea.plugin.projectwizard

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.StdModuleTypes
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import javax.swing.JComponent
import javax.swing.JLabel


class DemoModuleWizardStep : ModuleBuilder() {
    override fun setupRootModel(modifiableRootModel: ModifiableRootModel) {}
    override fun getModuleType(): ModuleType<*> {
        return ModuleType.EMPTY //或者是其他模块类型,比如java：StdModuleTypes.JAVA3
    }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> {
        return arrayOf(object :
            ModuleWizardStep() {
            override fun getComponent(): JComponent {
                return JLabel("把你的内容放在这里")
            }

            override fun updateDataModel() {}
        })
    }
}
