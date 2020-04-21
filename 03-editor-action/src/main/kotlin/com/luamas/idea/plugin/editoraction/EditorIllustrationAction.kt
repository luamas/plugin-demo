package com.luamas.idea.plugin.editoraction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction

/**
 * 用固定字符串替换所选字符的菜单操作。
 *
 * @see com.intellij.openapi.actionSystem.AnAction
 */
class EditorIllustrationAction : AnAction() {
    /**
     * 用固定字符串替换插入符号选择的文本行。
     * @param e  与此操作相关的事件
     */
    override fun actionPerformed(e: AnActionEvent) {
        // 从数据键获取所有必需的数据
        // 已在update()中检查编辑器和项目已存在，因此它们不为空。
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        val document = editor.document
        // 关闭插入符号以获取选择信息
        val primaryCaret = editor.caretModel.primaryCaret
        val start = primaryCaret.selectionStart
        val end = primaryCaret.selectionEnd
        println("start position: $start, end position: $end")
        println("document text:${document.text}")
        // 用固定字符串替换所选内容。
        // 必须在写操作上下文中更改此文档。
        WriteCommandAction.runWriteCommandAction(project) {
            document.replaceString(start, end, "editor_basics")
        }
        // 取消选择掉替换的文本
        primaryCaret.removeSelection()
    }

    /**
     * 设置可见性并启用此操作菜单项，如果：项目处于打开状态、编辑器处于活动状态、选定了某些字符
     * @param e  与此操作相关的事件
     */
    override fun update(e: AnActionEvent) {
        // 获取所需的数据键
        val project = e.project
        val editor =
            e.getData(CommonDataKeys.EDITOR)
        // 仅在项目和编辑器存在并且存在选择内容的情况下设置可见性
        e.presentation.isEnabledAndVisible = project != null && editor != null && editor.selectionModel.hasSelection()
    }
}
