package com.luamas.idea.plugin.projectmodel

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages


/**
 * @author Anna Bulenkova
 */
class ShowSourceRootsActions : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val projectName = project.name
        val sourceRootsList = StringBuilder()
        val vFiles = ProjectRootManager.getInstance(project).contentSourceRoots
        for (file in vFiles) {
            sourceRootsList.append(file.url).append("\n")
        }
        Messages.showInfoMessage(
            " ${projectName}项目的源码路径\n${sourceRootsList}",
            "项目属性"
        )
    }

    override fun update(event: AnActionEvent) {
        // 项目不能为空且
        val visibility = event.project != null
        event.presentation.isEnabledAndVisible = visibility
    }
}
