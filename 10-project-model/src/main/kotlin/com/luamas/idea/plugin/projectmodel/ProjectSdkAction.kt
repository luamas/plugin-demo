package com.luamas.idea.plugin.projectmodel

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages


/**
 * @author Anna Bulenkova
 */
class ProjectSdkAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        if (project != null) {
            val projectSDKName = ProjectRootManager.getInstance(project).projectSdkName
            val newProjectSdkName = "新建Sdk名称"
            ProjectRootManager.getInstance(project).projectSdkName = newProjectSdkName
            Messages.showInfoMessage(
                "$projectSDKName 被更改为 $newProjectSdkName",
                "项目Sdk信息"
            )
        }
    }

    override fun update(event: AnActionEvent) {
        val project = event.project
        var visibility = false
        // 项目不能为空且项目的sdk路径不能为空
        if (project != null && ProjectRootManager.getInstance(project).projectSdk != null) visibility = true
        event.presentation.isEnabledAndVisible = visibility
    }
}
