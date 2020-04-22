package com.luamas.idea.plugin.projectmodel

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.Messages


/**
 * @author Anna Bulenkova
 */
class ProjectFileIndexSampleAction : AnAction() {
    override fun update(event: AnActionEvent) {
        val project = event.project
        val editor = event.getData(CommonDataKeys.EDITOR)
        // 项目不能为空且编辑器不能为空
        val visibility = project != null && editor != null
        event.presentation.isEnabledAndVisible = visibility
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        val editor = event.getData(CommonDataKeys.EDITOR)
        if (project == null || editor == null) return
        val document = editor.document
        val fileDocumentManager = FileDocumentManager.getInstance()
        val virtualFile = fileDocumentManager.getFile(document)
        val projectFileIndex = ProjectRootManager.getInstance(project).fileIndex
        if (virtualFile != null) {
            val module = projectFileIndex.getModuleForFile(virtualFile)
            val moduleName: String
            moduleName = module?.name ?: "没有为文件定义模块"
            val moduleContentRoot = projectFileIndex.getContentRootForFile(virtualFile)
            val isLibraryFile = projectFileIndex.isLibraryClassFile(virtualFile)
            val isInLibraryClasses = projectFileIndex.isInLibraryClasses(virtualFile)
            val isInLibrarySource = projectFileIndex.isInLibrarySource(virtualFile)
            Messages.showInfoMessage(
                """
                    模块: $moduleName
                    模块内容根路径: $moduleContentRoot
                    是否为库文件: $isLibraryFile
                    是否为库二进制类: ${isInLibraryClasses}, 是否为库源码: $isInLibrarySource
                    """.trimIndent(),
                "主文件信息为" + virtualFile.name
            )
        }
    }
}
