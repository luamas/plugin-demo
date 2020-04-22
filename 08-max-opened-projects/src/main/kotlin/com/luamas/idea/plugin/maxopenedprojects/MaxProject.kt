package com.luamas.idea.plugin.maxopenedprojects

import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages

class MaxProject(project: Project?) : ProjectComponent {
    /**
     * 组件应该在此方法中执行初始化和与其他组件的通信。
     * 在{@link com.intellij.openapi.components.PersistentStateComponent#loadState(Object)}之后调用。
     */
    override fun initComponent() {
        // TODO: insert component initialization logic here
    }

    override fun disposeComponent() {
        // TODO: insert component disposal logic here
    }

    override fun getComponentName(): String {
        return "MaxProject"
    }

    override fun projectOpened() {
        // called when project is opened
        val commandCounter = ServiceManager.getService(MyCounter::class.java)
        if (commandCounter.increaseCounter() === -1) {
            Messages.showMessageDialog(
                "打开的项目的最大数目超过 " + java.lang.String.valueOf(commandCounter.maxCount) +
                        " 项目!", "错误", Messages.getErrorIcon()
            )
            val projectManager = ProjectManager.getInstance()
            val allProjects = projectManager.openProjects
            val project = allProjects[allProjects.size - 1]
            projectManager.closeProject(project)
        }
    }

    override fun projectClosed() {
        // 项目关闭时调用
        val commandCounter: MyCounter = service()
        commandCounter.decreaseCounter()
    }
}
