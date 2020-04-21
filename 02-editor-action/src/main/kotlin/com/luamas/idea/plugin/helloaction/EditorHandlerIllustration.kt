package com.luamas.idea.plugin.helloaction

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.editor.actionSystem.EditorActionManager


/**
 * 基于现有插入符号克隆新插入符号的菜单操作。
 *
 * @see com.intellij.openapi.actionSystem.AnAction
 */
class EditorHandlerIllustration : AnAction() {
    /**
     * 在较高的逻辑位置行号克隆新插入符号。
     * @param e  与此操作相关的事件
     */
    override fun actionPerformed(e: AnActionEvent) {
        // 从数据键获取所有必需的数据
        // 已在update()中检查编辑器和项目已存在，因此它们不为空。
        val editor = e.getRequiredData(CommonDataKeys.EDITOR)
        // 获取操作管理器以获取必要的操作处理程序。。。
        val actionManager = EditorActionManager.getInstance()
        // 获取注册到克隆插入符号的操作处理程序
        val actionHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_CLONE_CARET_ABOVE)
        // 在活动插入符号下面克隆一个插入符号
        actionHandler.execute(editor, editor.caretModel.primaryCaret, e.dataContext)
    }

    /**
     * 启用并设置此操作菜单项的可见性如果：项目处于打开状态，编辑器处于活动状态，至少存在一个插入符号
     * @param e  与此操作相关的事件
     */
    override fun update(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        // 确保至少有一个插入符号可用
        var menuAllowed = false
        if (editor != null && project != null) {
            // 确保编辑器中的插入符号列表不为空
            menuAllowed = editor.caretModel.allCarets.isNotEmpty()
        }
        e.presentation.isEnabledAndVisible = menuAllowed
    }
}
