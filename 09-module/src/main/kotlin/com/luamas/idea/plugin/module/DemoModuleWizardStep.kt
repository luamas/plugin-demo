package com.luamas.idea.plugin.module

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import javax.swing.JComponent
import javax.swing.JLabel


/**
 * @author Anna Bulenkova
 */
class DemoModuleWizardStep : ModuleWizardStep() {
    override fun getComponent(): JComponent {
        return JLabel("在这里提供一些设置")
    }

    override fun updateDataModel() {
        //todo update model according to UI
    }
}