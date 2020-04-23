package com.luamas.idea.plugin.runconfiguration

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project


open class DemoConfigurationFactory(type: ConfigurationType) :
    ConfigurationFactory(type) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return DemoRunConfiguration(project, this, "Demo")
    }

    override fun getName(): String {
        return FACTORY_NAME
    }

    override fun getOptionsClass(): Class<out BaseState?>? {
        return DemoRunConfigurationOptions::class.java
    }

    companion object {
        private const val FACTORY_NAME = "Demo configuration factory"
    }
}
