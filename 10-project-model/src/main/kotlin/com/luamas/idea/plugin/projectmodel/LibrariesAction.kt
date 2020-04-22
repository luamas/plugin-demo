package com.luamas.idea.plugin.projectmodel

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.roots.LibraryOrderEntry
import com.intellij.openapi.roots.OrderRootType
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiClass


class LibrariesAction : AnAction() {
    override fun update(event: AnActionEvent) {
        val project = event.project ?: return
        val element = event.getData(CommonDataKeys.NAVIGATABLE)
        var visibility = false
        // 这里只有光标选择到类名才会出现菜单
        if (element is PsiClass) {
            val psiFile = element.containingFile
            if (psiFile != null){
                val virtualFile = psiFile.virtualFile
                if (virtualFile != null) visibility= true
            }
        }
        event.presentation.isEnabledAndVisible = visibility
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val element =
            event.getData(CommonDataKeys.NAVIGATABLE)
        if (element is PsiClass) {
            val psiFile = element.containingFile ?: return
            val virtualFile = psiFile.virtualFile ?: return
            val fileIndex = ProjectRootManager.getInstance(project).fileIndex
            val jars = StringBuilder()
            for (orderEntry in fileIndex.getOrderEntriesForFile(virtualFile)) {
                if (orderEntry is LibraryOrderEntry) {
                    val library = orderEntry.library ?: continue
                    val files = library.getFiles(OrderRootType.CLASSES)
                    if (files.isEmpty()) continue
                    for (jar in files) {
                        jars.append(jar.name).append(", ")
                    }
                }
            }
            val fileAndLibs: String
            fileAndLibs = if (jars.isNotEmpty()) {
                virtualFile.name + ": " + jars.toString()
            } else {
                "无"
            }
            Messages.showInfoMessage(
                "库文件: $fileAndLibs",
                "库信息"
            )
        }
    }
}