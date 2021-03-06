package com.luamas.idea.plugin.runconfiguration

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project


open class DemoRunConfiguration(
    project: Project?,
    factory: ConfigurationFactory?,
    name: String?
) :
    RunConfigurationBase<DemoRunConfigurationOptions?>(project!!, factory, name) {
    override fun getOptions(): DemoRunConfigurationOptions {
        return super.getOptions() as DemoRunConfigurationOptions
    }

    var scriptName: String?
        get() = options.scriptName
        set(scriptName) {
            options.scriptName = scriptName
        }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> {
        return DemoSettingsEditor()
    }

    override fun checkConfiguration() {}
    override fun getState(
        executor: Executor,
        executionEnvironment: ExecutionEnvironment
    ): RunProfileState? {
        return object : CommandLineState(executionEnvironment) {
            @Throws(ExecutionException::class)
            override fun startProcess(): ProcessHandler {
                val commandLine =
                    GeneralCommandLine(options.scriptName)
                val processHandler =
                    ProcessHandlerFactory.getInstance()
                        .createColoredProcessHandler(commandLine)
                ProcessTerminatedListener.attach(processHandler)
                return processHandler
            }
        }
    }
}
