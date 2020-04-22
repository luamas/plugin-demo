package com.luamas.idea.plugin.module

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.Disposable
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.roots.ModifiableRootModel


/**
 * @author Anna Bulenkova
 */
class DemoModuleBuilder : ModuleBuilder() {
    @Throws(ConfigurationException::class)
    override fun setupRootModel(model: ModifiableRootModel) {
    }

    override fun getModuleType(): ModuleType<*> {
        return DemoModuleType.instance
    }

    override fun getCustomOptionsStep(
        context: WizardContext,
        parentDisposable: Disposable
    ): ModuleWizardStep? {
        return DemoModuleWizardStep()
    }
}