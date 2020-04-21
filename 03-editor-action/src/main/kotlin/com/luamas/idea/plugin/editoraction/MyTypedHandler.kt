package com.luamas.idea.plugin.editoraction

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile


/**
 * 每次按下一个按键都会调用
 * 这是一个自定义的TypedHandlerDelegate，用于处理编辑器中激活的操作击键。
 * 执行方法在文档的偏移量0处插入一个固定字符串。文档更改是在写操作的上下文中进行的。
 */
internal class MyTypedHandler : TypedHandlerDelegate() {
    override fun charTyped(
        c: Char,
        project: Project,
        editor: Editor,
        file: PsiFile
    ): Result {
        // 获取文档和项目
        val document = editor.document
        // 构造runnable以替换文档中偏移量为0的字符串
        val runnable = Runnable { document.insertString(0, "editor_basics\n") }
        // 在写操作的上下文中更改文档。
        WriteCommandAction.runWriteCommandAction(project, runnable)
        return Result.STOP
    }
}
