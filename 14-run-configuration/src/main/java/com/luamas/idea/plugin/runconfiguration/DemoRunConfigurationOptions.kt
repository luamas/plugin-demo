package com.luamas.idea.plugin.runconfiguration

import com.intellij.execution.configurations.RunConfigurationOptions
import com.intellij.openapi.components.StoredProperty


class DemoRunConfigurationOptions : RunConfigurationOptions() {
    private val myScriptName: StoredProperty<String?> =
        string("").provideDelegate(this, "scriptName")

    var scriptName: String?
        get() = myScriptName.getValue(this)
        set(scriptName) {
            myScriptName.setValue(this, scriptName)
        }

}
