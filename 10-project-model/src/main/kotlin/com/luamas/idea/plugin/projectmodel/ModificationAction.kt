package com.luamas.idea.plugin.projectmodel

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.roots.ModuleRootModificationUtil
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiClass


class ModificationAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val element = event.getData(CommonDataKeys.NAVIGATABLE)
        if (element is PsiClass) {
            val file = element.containingFile ?: return
            val virtualFile = file.virtualFile ?: return
            val fileIndex = ProjectRootManager.getInstance(project).fileIndex
            val module = fileIndex.getModuleForFile(virtualFile) ?: return
            // 不是此模块的内容下的文件或目录则执行
            // 按道理应该会一直不执行？？？
            if (!ModuleRootManager.getInstance(module).fileIndex.isInContent(virtualFile)) {
                ModuleRootModificationUtil.addModuleLibrary(module, virtualFile.url)
            }
        }
    }

    override fun update(event: AnActionEvent) {
        val project = event.project
        val element = event.getData(CommonDataKeys.NAVIGATABLE)
        // 项目不能为空且选中元素不能为空
        event.presentation.isEnabledAndVisible = project != null && element != null
    }
}