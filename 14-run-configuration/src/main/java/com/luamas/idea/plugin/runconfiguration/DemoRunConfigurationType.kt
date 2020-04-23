package com.luamas.idea.plugin.runconfiguration

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.icons.AllIcons
import javax.swing.Icon


class DemoRunConfigurationType : ConfigurationType {
    override fun getDisplayName(): String {
        return "示例"
    }

    override fun getConfigurationTypeDescription(): String {
        return "演示运行配置类型"
    }

    override fun getIcon(): Icon {
        return AllIcons.General.Information
    }

    override fun getId(): String {
        return "DEMO_RUN_CONFIGURATION"
    }

    override fun getConfigurationFactories(): Array<ConfigurationFactory> {
        return arrayOf(DemoConfigurationFactory(this))
    }
}
