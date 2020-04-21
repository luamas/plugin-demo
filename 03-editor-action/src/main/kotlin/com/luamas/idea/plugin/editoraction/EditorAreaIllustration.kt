package com.luamas.idea.plugin.editoraction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages


/**
 * 如果条件支持，则使菜单可见以显示有关插入符号的信息。
 *
 * @see com.intellij.openapi.actionSystem.AnAction
 */
class EditorAreaIllustration : AnAction() {
    /**
     * 显示包含当前插入符号信息的消息。
     * @param e  与此操作相关的事件
     */
    override fun actionPerformed(e: AnActionEvent) {
        // 访问编辑器和插入符号模型。update()验证了编辑器的存在。
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel = editor.caretModel
        // 获取主插入符号可以确保我们得到可能的多个插入符号中的正确一个。
        val primaryCaret = caretModel.primaryCaret
        // 获取插入符号信息
        val logicalPos = primaryCaret.logicalPosition
        val visualPos = primaryCaret.visualPosition
        val caretOffset = primaryCaret.offset
        // 生成并显示插入符号报告。
        val report = StringBuilder(
            """
                $logicalPos
                
                """.trimIndent()
        )
        report.append(
            """
                $visualPos
                
                """.trimIndent()
        )
        report.append("位置: $caretOffset")
        Messages.showInfoMessage(report.toString(), "编辑器中的插入符号参数")
    }

    /**
     * 设置可见性并启用此操作菜单项如果：项目处于打开状态，编辑器处于活动状态，
     * @param e  与此操作相关的事件
     */
    override fun update(e: AnActionEvent) {
        // 获取所需的数据键
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        //仅在存在项目和编辑器的情况下设置可见性
        e.presentation.isEnabledAndVisible = project != null && editor != null
    }
}
